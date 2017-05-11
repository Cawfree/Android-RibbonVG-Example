package io.github.cawfree.example;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.opengl.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.opengles.GL10;

import io.github.cawfree.example.boilerplate.AndroidGLES20;
import uk.ac.manchester.sisp.ribbon.font.global.FontGlobal;
import uk.ac.manchester.sisp.ribbon.font.truetype.TrueTypeDecoder;
import uk.ac.manchester.sisp.ribbon.font.truetype.TrueTypeFont;
import uk.ac.manchester.sisp.ribbon.io.ArrayStore;
import uk.ac.manchester.sisp.ribbon.io.EEntryMode;
import uk.ac.manchester.sisp.ribbon.opengl.GLContext;
import uk.ac.manchester.sisp.ribbon.opengl.IGL;
import uk.ac.manchester.sisp.ribbon.opengl.IGLES20;
import uk.ac.manchester.sisp.ribbon.opengl.IGLRunnable;
import uk.ac.manchester.sisp.ribbon.opengl.IScreenParameters;
import uk.ac.manchester.sisp.ribbon.opengl.buffers.GLBuffer;
import uk.ac.manchester.sisp.ribbon.opengl.matrix.GLMatrix;
import uk.ac.manchester.sisp.ribbon.opengl.program.constants.GLVectorProgram;
import uk.ac.manchester.sisp.ribbon.opengl.text.GLTextRenderer;
import uk.ac.manchester.sisp.ribbon.opengl.vector.VectorPath;
import uk.ac.manchester.sisp.ribbon.opengl.vector.VectorPathContext;
import uk.ac.manchester.sisp.ribbon.opengl.vector.global.EFillRule;
import uk.ac.manchester.sisp.ribbon.opengl.vector.global.ELineCap;
import uk.ac.manchester.sisp.ribbon.opengl.vector.global.ELineJoin;
import uk.ac.manchester.sisp.ribbon.utils.DataUtils;
import uk.ac.manchester.sisp.ribbon.utils.GLUtils;

/**
 *  MainActivity.java
 *  Created by Alexander Thomas (@Cawfree), 2017.
 *  Demonstrates a very basic example of Ribbon's Signed-Distance Vector Graphics.
 **/
public class MainActivity extends AppCompatActivity {

    /* Static Declarations. */
    private static final int SIZE_BUFFER_READ = 2048;

    /** Defines whether OpenGL ES 2.0 is supported. */
    private static final boolean isGLES20Supported(final Activity pActivity) {
        return ((ActivityManager)pActivity.getSystemService(Context.ACTIVITY_SERVICE)).getDeviceConfigurationInfo().reqGlEsVersion >= 0x20000;
    }

    /** Saves an Android resource at ResourceId to a Location in Internal Memory. (Thanks to Grimmace @ http://stackoverflow.com/users/146628/grimmace) */
    private static final void toInternalMemory(final Context pContext, final int pResourceId, final File pLocation) throws IOException {
        // Allocate the File Streams.
        final InputStream      lInputStream      = pContext.getResources().openRawResource(pResourceId);
        final FileOutputStream lFileOutputStream = new FileOutputStream(pLocation);
        // Declare a buffer to read our data into.
        final byte[]           lBuffer           = new byte[MainActivity.SIZE_BUFFER_READ];
        // Use a variable to count the number of bytes read.
              int              lBytesRead;
        // While we're successfully reading bytes from the InputStream...
        while((lBytesRead=lInputStream.read(lBuffer)) > 0) {
            // Write the Bytes to the File.
            lFileOutputStream.write(lBuffer, 0, lBytesRead);
        }
        // Close the Dependencies.
        lFileOutputStream.close();
        lInputStream.close();
    }

    /** Thanks to codezjx (http://stackoverflow.com/users/3919425/codezjx). */
    private static class MultisampleConfigChooser implements GLSurfaceView.EGLConfigChooser {
        static private final String kTag = "GDC11";
        @Override
        public EGLConfig chooseConfig(EGL10 egl, EGLDisplay display) {
            mValue = new int[1];

            // Try to find a normal multisample configuration first.
            int[] configSpec = {
                    EGL10.EGL_RED_SIZE, 5,
                    EGL10.EGL_GREEN_SIZE, 6,
                    EGL10.EGL_BLUE_SIZE, 5,
                    EGL10.EGL_DEPTH_SIZE, 16,
                    // Requires that setEGLContextClientVersion(2) is called on the view.
                    EGL10.EGL_RENDERABLE_TYPE, 4 /* EGL_OPENGL_ES2_BIT */,
                    EGL10.EGL_SAMPLE_BUFFERS, 1 /* true */,
                    EGL10.EGL_SAMPLES, 4,
                    EGL10.EGL_NONE
            };

            if (!egl.eglChooseConfig(display, configSpec, null, 0,
                    mValue)) {
                throw new IllegalArgumentException("eglChooseConfig failed");
            }
            int numConfigs = mValue[0];

            if (numConfigs <= 0) {
                // No normal multisampling config was found. Try to create a
                // converage multisampling configuration, for the nVidia Tegra2.
                // See the EGL_NV_coverage_sample documentation.

                final int EGL_COVERAGE_BUFFERS_NV = 0x30E0;
                final int EGL_COVERAGE_SAMPLES_NV = 0x30E1;

                configSpec = new int[]{
                        EGL10.EGL_RED_SIZE, 5,
                        EGL10.EGL_GREEN_SIZE, 6,
                        EGL10.EGL_BLUE_SIZE, 5,
                        EGL10.EGL_DEPTH_SIZE, 16,
                        EGL10.EGL_RENDERABLE_TYPE, 4 /* EGL_OPENGL_ES2_BIT */,
                        EGL_COVERAGE_BUFFERS_NV, 1 /* true */,
                        EGL_COVERAGE_SAMPLES_NV, 2,  // always 5 in practice on tegra 2
                        EGL10.EGL_NONE
                };

                if (!egl.eglChooseConfig(display, configSpec, null, 0,
                        mValue)) {
                    throw new IllegalArgumentException("2nd eglChooseConfig failed");
                }
                numConfigs = mValue[0];

                if (numConfigs <= 0) {
                    // Give up, try without multisampling.
                    configSpec = new int[]{
                            EGL10.EGL_RED_SIZE, 5,
                            EGL10.EGL_GREEN_SIZE, 6,
                            EGL10.EGL_BLUE_SIZE, 5,
                            EGL10.EGL_DEPTH_SIZE, 16,
                            EGL10.EGL_RENDERABLE_TYPE, 4 /* EGL_OPENGL_ES2_BIT */,
                            EGL10.EGL_NONE
                    };

                    if (!egl.eglChooseConfig(display, configSpec, null, 0,
                            mValue)) {
                        throw new IllegalArgumentException("3rd eglChooseConfig failed");
                    }
                    numConfigs = mValue[0];

                    if (numConfigs <= 0) {
                        throw new IllegalArgumentException("No configs match configSpec");
                    }
                } else {
                    mUsesCoverageAa = true;
                }
            }

            // Get all matching configurations.
            EGLConfig[] configs = new EGLConfig[numConfigs];
            if (!egl.eglChooseConfig(display, configSpec, configs, numConfigs,
                    mValue)) {
                throw new IllegalArgumentException("data eglChooseConfig failed");
            }

            // CAUTION! eglChooseConfigs returns configs with higher bit depth
            // first: Even though we asked for rgb565 configurations, rgb888
            // configurations are considered to be "better" and returned first.
            // You need to explicitly filter the data returned by eglChooseConfig!
            int index = -1;
            for (int i = 0; i < configs.length; ++i) {
                if (findConfigAttrib(egl, display, configs[i], EGL10.EGL_RED_SIZE, 0) == 5) {
                    index = i;
                    break;
                }
            }
            if (index == -1) {
                Log.w(kTag, "Did not find sane config, using first");
            }
            EGLConfig config = configs.length > 0 ? configs[index] : null;
            if (config == null) {
                throw new IllegalArgumentException("No config chosen");
            }
            return config;
        }

        private int findConfigAttrib(EGL10 egl, EGLDisplay display,
                                     EGLConfig config, int attribute, int defaultValue) {
            if (egl.eglGetConfigAttrib(display, config, attribute, mValue)) {
                return mValue[0];
            }
            return defaultValue;
        }

        public boolean usesCoverageAa() {
            return mUsesCoverageAa;
        }

        private int[] mValue;
        private boolean mUsesCoverageAa;
    }

    /* Member Variables. */
    private GLSurfaceView     mGLSurfaceView;
    private GLVectorProgram   mGLVectorProgram;
    private ArrayStore.Float  mFloatStore;
    private VectorPathContext mVectorPathContext;
    private TrueTypeFont      mTrueTypeFont;

    @Override
    protected final void onCreate(Bundle pSavedInstanceState) {
        // Implement the parent definition.
        super.onCreate(pSavedInstanceState);
        // Initialize Member Variables.
        this.mGLVectorProgram     = new GLVectorProgram();
        this.mVectorPathContext   = new VectorPathContext();
        this.mFloatStore          = new ArrayStore.Float();
        this.mVectorPathContext   = new VectorPathContext();
        // Declare where we wish to save the Font File.
        final File            lFontFile        = new File(this.getFilesDir() + File.separator + "hack_bold.ttf");
         // Allocate a TrueTypeDecoder.
        final TrueTypeDecoder lTrueTypeDecoder = new TrueTypeDecoder();
        // Read the Font.
        try {
            // Read from Android Resources into the FontFile. /** TODO: I know this sucks. I have yet to refactor the FontDecoder's interface to support the definition of an InputStream.  */
            MainActivity.toInternalMemory(this.getApplicationContext(), R.raw.hack_bold, lFontFile);
            // Fetch the TrueTypeFont.
            this.mTrueTypeFont = lTrueTypeDecoder.onCreateFromFile(lFontFile);
        }
        catch (final IOException pIOException) {
            // Print the Stack Trace.
            pIOException.printStackTrace();
        }
        // Allocate the GLTextRenderer. (You may specify any unicode characters supported by the Font.)
        /** Check whether OpenGL ES 2.0 is supported by this device. */
        if(MainActivity.isGLES20Supported(this)) {
            // Allocate the GLSurfaceView.
            this.mGLSurfaceView = new GLSurfaceView(this);
            // Set the EGLConfig to use MSAA.
            this.getSurfaceView().setEGLConfigChooser(new MultisampleConfigChooser());
            // Allocate the GLContext; this is where graphically-delegated resources are managed and persisted.
            final GLContext lGLContext = new GLContext() {
                /* Member Variables. */
                private int            mWidth;
                private int            mHeight;
                private GLBuffer.XY_UV mFillBuffer;
                private GLBuffer.XY_UV mStrokeBuffer;
                private GLTextRenderer mGLTextRenderer;
                /** Constructor implementation; ensure graphical dependencies are initialized. */
                { this.invokeLater(new IGLRunnable() { @Override public final void run(final IGLES20 pGLES20, final GLContext pGLContext) { pGLContext.onHandleDelegates(EEntryMode.SUPPLY, pGLES20, getGLVectorProgram().getVertexShader(), getGLVectorProgram().getFragmentShader(), getGLVectorProgram()); } }); }
                /** Handle a resize event. */
                @Override public final void onResized(final IGLES20 pGLES20, final int pX, final int pY, final int pWidth, final int pHeight) {
                    // Handle as usual.
                    super.onResized(pGLES20, pX, pY, pWidth, pHeight);
                    // Initialize dimensions.
                    this.mWidth          = pWidth;
                    this.mHeight         = pHeight;
                    this.mGLTextRenderer = new GLTextRenderer("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!,", getTrueTypeFont(), this, getFloatStore(), getVectorPathContext());
                    // Have we allocated the Fill and Stroke?
                    if(this.getFillBuffer() != null && this.getStrokeBuffer() != null) {
                        // Delete the Buffers.
                        this.onHandleDelegates(EEntryMode.WITHDRAW, pGLES20, this.getFillBuffer());
                        this.onHandleDelegates(EEntryMode.WITHDRAW, pGLES20, this.getStrokeBuffer());
                    }
                    // Create the representation of a circle in the middle of the screen, and push it into the FloatStore.
                    getVectorPathContext().onCircle(getFloatStore(), this.getScreenWidth() / 2, this.getScreenHeight() / 2, this.getScreenWidth() / 2);
                    // Fetch the VectorPath.
                    final VectorPath     lVectorPath = getVectorPathContext().onCreatePath(getFloatStore());
                    // Triangulate the Circle representation into a polygon representation that is understood by the GLVectorProgram.
                    getVectorPathContext().onTriangulateFill(lVectorPath, getFloatStore(), EFillRule.NON_ZERO);
                    // Wrap the triangulated vertices into a GLBuffer. We're using XY_UV; XY controls vertex position, UV controls the signed distance component.
                    this.mFillBuffer   = new GLBuffer.XY_UV(DataUtils.delegateNative(getFloatStore().onProduceArray()), IGLES20.GL_ARRAY_BUFFER, IGLES20.GL_DYNAMIC_DRAW);
                    // Triangulate the Circle representation into a polygon representation that is understood by the GLVectorProgram.
                    getVectorPathContext().onTriangulateStroke(lVectorPath, getFloatStore(), 100.0f, ELineCap.BUTT, ELineJoin.ROUND);
                    // Wrap the triangulated vertices into a GLBuffer. We're using XY_UV; XY controls vertex position, UV controls the signed distance component.
                    this.mStrokeBuffer = new GLBuffer.XY_UV(DataUtils.delegateNative(getFloatStore().onProduceArray()), IGLES20.GL_ARRAY_BUFFER, IGLES20.GL_DYNAMIC_DRAW);
                    // Provide the GLBuffer with some graphical context.
                    this.onHandleDelegates(EEntryMode.SUPPLY, pGLES20, this.getFillBuffer());
                    this.onHandleDelegates(EEntryMode.SUPPLY, pGLES20, this.getStrokeBuffer());
                }
                /** Define what to draw. */
                @Override protected final void onRenderFrame(final IGLES20 pGLES20, final float pCurrentTimeSeconds) {
                    // Initialize the Model Matrix.
                    Matrix.setIdentityM(this.getModelMatrix(), 0);
                    // Initialize the View Matrix.
                    Matrix.setIdentityM(this.getViewMatrix(), 0);
                    // Assert that we're going to be rendering in 2D; use an Orthographic Perspective. (3D uses a Frustrum.)
                    Matrix.orthoM(this.getProjectionMatrix(), 0, 0, this.getScreenWidth(), this.getScreenHeight(), 0, Float.MIN_VALUE, Float.MAX_VALUE);
					/* Bind to the GLVectorProgram. */
                    getGLVectorProgram().bind(pGLES20);
                    // Compute the Scale.
                    final float lScale =  (float)(Math.sin(2.0 * Math.PI * 0.1f * pCurrentTimeSeconds) + 2.0);
                    // Adjust the Scale.
                    Matrix.scaleM(this.getModelMatrix(), 0, lScale, lScale, 0);
					/* Supply the Resolution. */
                    getGLVectorProgram().onSupplyResolution(pGLES20, this);
                    // Render at a normal scale. (Note: this is *not* the same as scaling using matrices. This is a useful post-processing step for supporting differences in co-ordinate system polarity, i.e. TrueType fonts use an inverted axis, i.e. 1.0, -1.0.)
                    getGLVectorProgram().onSupplyScale(pGLES20, 1.0f, 1.0f);
					/* Update the GLVectorProgram's World Matrices. */
                    GLUtils.onUpdateWorldMatrices(pGLES20, getGLVectorProgram(), this);
                    // Draw the Circle Fill and Stroke. /** TODO: Note that these are persisted between frames! The shapes are changing, but we're not actually performing any vector computation!*/
                    this.draw(pGLES20,   this.getFillBuffer(), new float[] { 0.0f, 0.0f, 1.0f, 1.0f });
                    this.draw(pGLES20, this.getStrokeBuffer(), new float[] { 1.0f, 0.0f, 0.0f, 1.0f });
                    // Reset the ModelMatrix.
                    GLMatrix.setIdentityM(this.getModelMatrix());

                    // Declare the String to Draw.
                    final String lText = "Hello, world!";
                    /* Supply the colour (R, G, B, A). I want to draw in red! */
                    getGLVectorProgram().onSupplyColor(pGLES20, new float[] { 0.0f, 1.0f, 0.0f, 0.5f });
                    // Calculate the DPI Font Scale.
                    final float lFontScale = getTrueTypeFont().getFontScale(this.getDotsPerInch(), 12.0f);
                    // Calculate the Bounding Box of the Text.
                    final float lWidth     = FontGlobal.onCalculateLineWidth (getTrueTypeFont(), lFontScale, lText);
                    final float lHeight    = FontGlobal.onCalculateLineHeight(getTrueTypeFont(), lFontScale, lText);
                    // Offset the Text.
                    GLMatrix.translateM(this.getModelMatrix(), (this.getScreenWidth() - lWidth) / 2, (this.getScreenHeight() - lHeight) / 2, 0);
                    // Render some text.
                    this.draw(pGLES20, "Hello, world!", lFontScale, new float[]{ 0.0f, 1.0f, 0.0f, 1.0f });
                    this.getGLTextRenderer().onRenderText(pGLES20, this, getGLVectorProgram(), lText, lFontScale);
                    // Kill the GLBuffer, since we're rendering it then destroying it on a single frame. (Note, that since the vertices are persisted on a GPU, we only have to create the circle vertices every time the screen dimension changes.)
                    /* Bind to the GLVectorProgram. */
                    getGLVectorProgram().unbind(pGLES20);
                }
                /** A very simple draw method. */
                private final void draw(final IGLES20 pGLES20, final GLBuffer.XY_UV pGLBuffer, final float[] pColor) {
                    // Bind to the buffer! This tells OpenGL how to understand how to use the Buffer, as well as makes it accessible to the GLVectorProgram.
                    pGLBuffer.bind(pGLES20, getGLVectorProgram());
					/* Supply the colour (R, G, B, A). I want to draw in red! */
                    getGLVectorProgram().onSupplyColor(pGLES20, pColor);
					/* Draw the circle's vertices as a whole. Ensure we treat the buffer's vertices as floating point numbers, not individual bytes. */
                    pGLES20.glDrawArrays(IGL.GL_TRIANGLES, 0, (pGLBuffer.getByteBuffer().capacity() / DataUtils.BYTES_PER_FLOAT));
                    // Unbind from the GLBuffer. It's no longer available to shaders.
                    pGLBuffer.unbind(pGLES20, getGLVectorProgram());
                }
                /** A simplistic single-line text rendering method. */
                private final void draw(final IGLES20 pGLES20, final String pText, final float pFontScale, final float[] pColor) {
                    // Supply the Font Scale, ensure we invert the Y Axis. (TTF uses a different co-ordinate system to Ribbon.)
                    getGLVectorProgram().onSupplyScale(pGLES20, pFontScale, -pFontScale);
					/* Update the GLVectorProgram's World Matrices. */
                    GLUtils.onUpdateWorldMatrices(pGLES20, getGLVectorProgram(), this);
                }
                /** Generic Overrides. */
                @Override public final float getDotsPerInch()  { return getResources().getDisplayMetrics().densityDpi; }
                @Override public final int   getScreenWidth()  { return this.mWidth;                                   }
                @Override public final int   getScreenHeight() { return this.mHeight;                                  }
                /** Unused implementations. */
                @Override public final void onScreenParametersChanged(final IScreenParameters pScreenParameters) { }
                /* Getters. */
                private final GLBuffer.XY_UV getFillBuffer()     { return this.mFillBuffer;     }
                private final GLBuffer.XY_UV getStrokeBuffer()   { return this.mStrokeBuffer;   }
                private final GLTextRenderer getGLTextRenderer() { return this.mGLTextRenderer; }
            };
            // Allocate the Android-Specific OpenGL ES 2.0 API, through the abstract boilerplate API.
            final IGLES20 lGLES20 = new AndroidGLES20();
            // Assert we're using OpenGL ES 2.0.
            this.getSurfaceView().setEGLContextClientVersion(2);
            // We don't have to preserve the context; the GLContext manages the persistence of resource delegation for us. :-)
            //this.getSurfaceView().setPreserveEGLContextOnPause(true);
            this.getSurfaceView().setRenderer(new GLSurfaceView.Renderer() {
                /** Generic routing methods. */
                @Override public final void onSurfaceCreated(final GL10 pGLUnused, final EGLConfig pEGLConfig)          { lGLContext.onInitialize(lGLES20);                     }
                @Override public final void onSurfaceChanged(final GL10 pGLUnused, final int pWidth, final int pHeight) { lGLContext.onResized(lGLES20, 0, 0, pWidth, pHeight); }
                @Override public final void      onDrawFrame(final GL10 pGLUnused)                                      { lGLContext.onDisplay(lGLES20);                        }
            });
            // Display at the result.
            this.setContentView(this.getSurfaceView());
        }
        else {
            // Assert that the application isn't supported.
            Toast.makeText(this.getBaseContext(), "Sorry, your device is not supported.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onResume() {
        // Resume the application.
        super.onResume();
        // Resume the surface.
        this.getSurfaceView().onResume();
    }

    @Override
    protected void onPause() {
        // Pause the surface.
        this.getSurfaceView().onPause();
        // Continue pausing as normal.
        super.onPause();
    }

    /* Getters. */
    private final GLSurfaceView getSurfaceView() {
        return this.mGLSurfaceView;
    }


    private final GLVectorProgram getGLVectorProgram() {
        return this.mGLVectorProgram;
    }

    private final ArrayStore.Float getFloatStore() {
        return this.mFloatStore;
    }

    private final VectorPathContext getVectorPathContext() {
        return this.mVectorPathContext;
    }

    private final TrueTypeFont getTrueTypeFont() {
        return this.mTrueTypeFont;
    }

}
