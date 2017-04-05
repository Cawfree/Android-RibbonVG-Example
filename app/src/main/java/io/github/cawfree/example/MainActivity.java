package io.github.cawfree.example;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import io.github.cawfree.example.boilerplate.AndroidGLES20;
import uk.ac.manchester.sisp.ribbon.io.ArrayStore;
import uk.ac.manchester.sisp.ribbon.io.EEntryMode;
import uk.ac.manchester.sisp.ribbon.opengl.GLContext;
import uk.ac.manchester.sisp.ribbon.opengl.IGL;
import uk.ac.manchester.sisp.ribbon.opengl.IGLES20;
import uk.ac.manchester.sisp.ribbon.opengl.IGLRunnable;
import uk.ac.manchester.sisp.ribbon.opengl.IScreenParameters;
import uk.ac.manchester.sisp.ribbon.opengl.buffers.GLBuffer;
import uk.ac.manchester.sisp.ribbon.opengl.program.constants.GLVectorProgram;
import uk.ac.manchester.sisp.ribbon.opengl.vector.VectorPathContext;
import uk.ac.manchester.sisp.ribbon.opengl.vector.global.EFillRule;
import uk.ac.manchester.sisp.ribbon.utils.DataUtils;
import uk.ac.manchester.sisp.ribbon.utils.GLUtils;

/**
 *  MainActivity.java
 *  Created by Alexander Thomas (@Cawfree), 2017.
 *  Demonstrates a very basic example of Ribbon's Signed-Distance Vector Graphics.
 **/
public class MainActivity extends AppCompatActivity {

    private static final boolean isGLES20Supported(final Activity pActivity) {
        return ((ActivityManager)pActivity.getSystemService(Context.ACTIVITY_SERVICE)).getDeviceConfigurationInfo().reqGlEsVersion >= 0x20000;
    }

    /* Member Variables. */
    private GLSurfaceView     mGLSurfaceView;
    private GLVectorProgram   mGLVectorProgram;
    private ArrayStore.Float  mFloatStore;
    private VectorPathContext mVectorPathContext;

    @Override
    protected final void onCreate(Bundle pSavedInstanceState) {
        // Implement the parent definition.
        super.onCreate(pSavedInstanceState);
        // Initialize Member Variables.
        this.mGLVectorProgram     = new GLVectorProgram();
        this.mVectorPathContext   = new VectorPathContext();
        this.mFloatStore          = new ArrayStore.Float();
        this.mVectorPathContext   = new VectorPathContext();
        /** Check whether OpenGL ES 2.0 is supported by this device. */
        if(MainActivity.isGLES20Supported(this)) {
            // Allocate the GLSurfaceView.
            this.mGLSurfaceView = new GLSurfaceView(this);
            // Allocate the GLContext; this is where graphically-delegated resources are managed and persisted.
            final GLContext lGLContext = new GLContext() {
                /* Member Variables. */
                private int mWidth;
                private int mHeight;
                /** Constructor implementation; ensure graphical dependencies are initialized. */
                { this.invokeLater(new IGLRunnable() { @Override public final void run(final IGLES20 pGLES20, final GLContext pGLContext) { pGLContext.onHandleDelegates(EEntryMode.SUPPLY, pGLES20, getGLVectorProgram().getVertexShader(), getGLVectorProgram().getFragmentShader(), getGLVectorProgram()); } }); }
                /** Handle a resize event. */
                @Override public final void onResized(final IGLES20 pGLES20, final int pX, final int pY, final int pWidth, final int pHeight) {
                    // Handle as usual.
                    super.onResized(pGLES20, pX, pY, pWidth, pHeight);
                    // Initialize dimensions.
                    this.mWidth  = pWidth;
                    this.mHeight = pHeight;
                }
                /** Define what to draw. */
                @Override protected final void onRenderFrame(final IGLES20 pGLES20, final float pCurrentTimeSeconds) {
                    // Initialize the Model Matrix.
                    Matrix.setIdentityM(this.getModelMatrix(), 0);
                    // Initialize the View Matrix.
                    Matrix.setIdentityM(this.getViewMatrix(), 0);
                    // Assert that we're going to be rendering in 2D; use an Orthographic Perspective. (3D uses a Frustrum.)
                    Matrix.orthoM(this.getProjectionMatrix(), 0, 0, this.getScreenWidth(), this.getScreenHeight(), 0, Float.MIN_VALUE, Float.MAX_VALUE);
					/* Reset the GLVectorProgram's scale. */
                    GLUtils.onReinitializeScale(pGLES20, getGLVectorProgram());
					/* Bind to the GLVectorProgram. */
                    getGLVectorProgram().bind(pGLES20);
					/* Supply the Resolution. */
                    getGLVectorProgram().onSupplyResolution(pGLES20, this);
                    // Render at a normal scale. (Note: this is *not* the same as scaling using matrices. This is a useful post-processing step for supporting differences in co-ordinate system polarity, i.e. TrueType fonts use an inverted axis, i.e. 1.0, -1.0.)
                    getGLVectorProgram().onSupplyScale(pGLES20, 1.0f, 1.0f);
					/* Update the GLVectorProgram's World Matrices. */
                    GLUtils.onUpdateWorldMatrices(pGLES20, getGLVectorProgram(), this);
                    // Create the representation of a circle in the middle of the screen, and push it into the FloatStore.
                    getVectorPathContext().onCircle(getFloatStore(), this.getScreenWidth() / 2, this.getScreenHeight() / 2, this.getScreenWidth() / 2);
                    // Triangulate the Circle representation into a polygon representation that is understood by the GLVectorProgram.
                    getVectorPathContext().onTriangulateFill(getVectorPathContext().onCreatePath(getFloatStore()), getFloatStore(), EFillRule.NON_ZERO);
                    // Wrap the triangulated vertices into a GLBuffer. We're using XY_UV; XY controls vertex position, UV controls the signed distance component.
                    final GLBuffer.XY_UV lGLBuffer = new GLBuffer.XY_UV(DataUtils.delegateNative(getFloatStore().onProduceArray()), IGLES20.GL_ARRAY_BUFFER, IGLES20.GL_DYNAMIC_DRAW);
                    // Provide the GLBuffer with some graphical context.
                    this.onHandleDelegates(EEntryMode.SUPPLY, pGLES20, lGLBuffer);
                    // Bind to the buffer! This tells OpenGL how to understand how to use the Buffer, as well as makes it accessible to the GLVectorProgram.
                    lGLBuffer.bind(pGLES20, getGLVectorProgram());
					/* Supply the colour (R, G, B, A). I want to draw in red! */
                    getGLVectorProgram().onSupplyColor(pGLES20, new float[]{1, 0, 0, 1});
					/* Draw the circle's vertices as a whole. Ensure we treat the buffer's vertices as floating point numbers, not individual bytes. */
                    pGLES20.glDrawArrays(IGL.GL_TRIANGLES, 0, (lGLBuffer.getByteBuffer().capacity() / DataUtils.BYTES_PER_FLOAT));
                    // Unbind from the GLBuffer. It's no longer available to shaders.
                    lGLBuffer.unbind(pGLES20, getGLVectorProgram());
                    // Kill the GLBuffer, since we're rendering it then destroying it on a single frame. (Note, that since the vertices are persisted on a GPU, we only have to create the circle vertices every time the screen dimension changes.)
                    // We're also permitted to scale, rotate, translate and skew the rendering of that buffer arbitratily; we'll still get an anti-aliased vector image, without retessellating the shape! This is the power of signed distance rendering.
                    this.onHandleDelegates(EEntryMode.WITHDRAW, pGLES20, lGLBuffer);
					/* Bind to the GLVectorProgram. */
                    getGLVectorProgram().unbind(pGLES20);
                }
                /** Generic Overrides. */
                @Override public final float getDotsPerInch()  { return getResources().getDisplayMetrics().densityDpi; }
                @Override public final int   getScreenWidth()  { return this.mWidth;                                   }
                @Override public final int   getScreenHeight() { return this.mHeight;                                  }
                /** Unused implementations. */
                @Override public final void onScreenParametersChanged(final IScreenParameters pScreenParameters) { }
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

}
