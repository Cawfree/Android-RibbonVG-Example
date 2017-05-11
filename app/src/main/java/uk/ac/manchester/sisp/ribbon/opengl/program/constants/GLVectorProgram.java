package uk.ac.manchester.sisp.ribbon.opengl.program.constants;

import uk.ac.manchester.sisp.ribbon.opengl.IGLES20;
import uk.ac.manchester.sisp.ribbon.opengl.IScreenParameters;
import uk.ac.manchester.sisp.ribbon.opengl.buffers.GLBuffer;
import uk.ac.manchester.sisp.ribbon.opengl.matrix.IGLMatrixSource;
import uk.ac.manchester.sisp.ribbon.opengl.program.GLProgram;
import uk.ac.manchester.sisp.ribbon.opengl.program.IGLUniformProvider;
import uk.ac.manchester.sisp.ribbon.opengl.shaders.GLShader;
import uk.ac.manchester.sisp.ribbon.utils.DataUtils;

/** A polarized OpenGL-ES implementation of Loop and Blinn's efficient signed distance field Bezier fragment shader. **/
public final class GLVectorProgram extends GLProgram implements IGLUniformProvider.WorldMatrix, IGLUniformProvider.Scale, IGLUniformProvider.Color, IGLUniformProvider.Resolution, GLBuffer.IXY_UV {
	
	private static final String SOURCE_VERTEX_SHADER   =

        // Attributes and Varyings.
		"\n"+"attribute    vec2  aPosition;"+
		"\n"+"varying      vec4  vPosition;"+
		"\n"+"attribute    vec2  aBezier;"  +
		"\n"+"varying      vec2  vBezier;"  +
		"\n"+"varying      vec4  vColor;"   +

        // Uniforms.
		"\n"+"uniform   vec2 uResolution;"       +
		"\n"+"uniform  float uXScale;"           +
		"\n"+"uniform  float uYScale;"           +
		"\n"+"uniform  vec4  uColor;"            +
		"\n"+"uniform  mat4  uModelMatrix;"      +
		"\n"+"uniform  mat4  uViewMatrix;"       +
		"\n"+"uniform  mat4  uProjectionMatrix;" +

        // Main Method.
		"\n"+"void main(void) { " +
		"\n"+"\t"+"vBezier        = aBezier;" +
		"\n"+"\t"+"vPosition      = (uProjectionMatrix * uViewMatrix * uModelMatrix) * vec4(aPosition.x * uXScale, aPosition.y * uYScale, 0, 1.0);" +
		"\n"+"\t"+"gl_Position    = vPosition;" +
		"\n"+"}";

	
	private static final String SOURCE_FRAGMENT_SHADER = /** TODO: Fragment Precision. (Important for mobile devices, crashes @desktop etc). **/

	    // Varyings.
		"\n"+"varying   vec2 vBezier;"       +
		"\n"+"varying   vec4 vPosition;"     +

        // Uniforms.
		"\n"+"uniform   vec4 uColor;"        +
		"\n"+"uniform   vec2 uResolution;"   +
		"\n"+"uniform   int  uPixelPerfect;" +

		/*
		 *  Compute gradient derivatives without OpenGL extensions.
		 *  Many thanks to tower120. http://stackoverflow.com/questions/22442304/glsl-es-dfdx-dfdy-analog
		 */
		"\n"+"float myFunc(vec2 p){"+
		"\n"+"\t"+"return p.x*p.x - p.y; // that's our function. We want derivative from it."+
		"\n"+"}"+

		"\n"+"void main() { " +
        // Compute the Derivatives.
		"\n"+"\t"+"vec2 pixel_step = vec2(1.0/uResolution.x, 1.0/uResolution.y);"+
		"\n"+"\t"+"float current = myFunc(vBezier);"+
		"\n"+"\t"+"float dFdx = myFunc(vBezier + pixel_step.x) - current;"+
		"\n"+"\t"+"float dFdy = myFunc(vBezier + pixel_step.y) - current;"+
        // Compute the Shade Color.
		"\n"+"\t"+"vec4  lReturnColor = uColor;" +
		"\n"+"\t"+"vec2  px           = vec2(dFdx, 0.0);" +
		"\n"+"\t"+"vec2  py           = vec2(0.0, dFdy);" +
		"\n"+"\t"+"float lIsCurve     = float(vBezier.x != 0.0 || vBezier.y != 0.0);" +
		"\n"+"\t"+"float fx           = lIsCurve * ((2.0 * vBezier.x) * px.x - px.y);" +
		"\n"+"\t"+"float fy           = lIsCurve * ((2.0 * vBezier.y) * py.x - py.y);" +
		"\n"+"\t"+"float sd           = lIsCurve * (((((vBezier.x * vBezier.x - vBezier.y) * inversesqrt(fx * fx + fy * fy)))));" + /** TODO: Using inversesqrt... May be more efficient? **/
		// Adjust the opacity for curvature.
		"\n"+"\t"+"lReturnColor.a    *= 2.0*((1.0 - lIsCurve) + (clamp((((float((vBezier.x <= 0.0)))*((sd) + 0.5) + (float((vBezier.x > 0.0)))*((0.5) - (sd)))), 0.0, 1.0)));"+
		"\n"+"\t"+"gl_FragColor       = lReturnColor;"+
		"\n"+"}";
	
	/* Shader Attributes. */
	private int mAttributePosition;
	private int mAttributeBezier;
	
	/* Shader Uniforms. */
	private int mUniformResolution;
	private int mUniformModelMatrix;
	private int mUniformViewMatrix;
	private int mUniformProjectionMatrix;
	private int mUniformXScale;
	private int mUniformYScale;
	private int mUniformColor;

	public GLVectorProgram() {
		super(new GLShader.Vertex(GLVectorProgram.SOURCE_VERTEX_SHADER), new GLShader.Fragment(GLVectorProgram.SOURCE_FRAGMENT_SHADER));
	}
	
	@Override
	protected final void onLoaded(final IGLES20 pGLES20) {
		super.onLoaded(pGLES20);
		/* Initialize Shader Attributes. */
		this.mAttributePosition  = this.getAttributeLocation(pGLES20, "aPosition");
		this.mAttributeBezier    = this.getAttributeLocation(pGLES20, "aBezier");
		/* Initialize Shader Uniforms. */
		this.mUniformResolution       = this.getUniformLocation(pGLES20, "uResolution");
		this.mUniformModelMatrix      = this.getUniformLocation(pGLES20, "uModelMatrix");
		this.mUniformViewMatrix       = this.getUniformLocation(pGLES20, "uViewMatrix");
		this.mUniformProjectionMatrix = this.getUniformLocation(pGLES20, "uProjectionMatrix");
		this.mUniformXScale           = this.getUniformLocation(pGLES20, "uXScale");
		this.mUniformYScale           = this.getUniformLocation(pGLES20, "uYScale");
		this.mUniformColor            = this.getUniformLocation(pGLES20, "uColor");
	}

	@Override
	public final void onSupplyModelMatrix(final IGLES20 pGLES20,final IGLMatrixSource pGLMatrixSource) {
		pGLES20.glUniformMatrix4fv(this.getUniformModelMatrix(), 1, false, pGLMatrixSource.getModelMatrix(), 0);
	}

	@Override
	public final void onSupplyViewMatrix(final IGLES20 pGLES20, final IGLMatrixSource pGLMatrixSource) {
		pGLES20.glUniformMatrix4fv(this.getUniformViewMatrix(), 1, false, pGLMatrixSource.getViewMatrix(), 0);
	}

	@Override
	public final void onSupplyProjectionMatrix(final IGLES20 pGLES20, final IGLMatrixSource pGLMatrixSource) {
		pGLES20.glUniformMatrix4fv(this.getUniformProjectionMatrix(), 1, false, pGLMatrixSource.getProjectionMatrix(), 0);
	}

	@Override
	public final void onSupplyScale(final IGLES20 pGLES20, final float ... pScale) {
		pGLES20.glUniform1f(this.getUniformXScale(), pScale[0]);
		pGLES20.glUniform1f(this.getUniformYScale(), pScale[1]);
	}

	@Override
	public final void onSupplyColor(final IGLES20 pGLES20, final float[] pColorRGBA) {
		pGLES20.glUniform4fv(this.getUniformColor(), 1, pColorRGBA, 0);
	}

	@Override
	public final void onSupplyResolution(final IGLES20 pGLES20, final IScreenParameters pGLScreenParameters) {
		pGLES20.glUniform2f(this.getUniformResolution(), pGLScreenParameters.getScreenWidth(), pGLScreenParameters.getScreenHeight());
	}
	
	@Override
	protected final void onDispose() {
		super.onDispose();
		/* Destroy the dedicated shaders. */
		this.getVertexShader().dispose();
		this.getFragmentShader().dispose();
	}

	@Override
	public final int getAttributePosition() {
		return this.mAttributePosition;
	}

	@Override
	public final int getAttributeProcedural() {
		return this.mAttributeBezier;
	}
	
	private final int getUniformModelMatrix() {
		return this.mUniformModelMatrix;
	}
	
	private final int getUniformViewMatrix() {
		return this.mUniformViewMatrix;
	}
	
	private final int getUniformProjectionMatrix() {
		return this.mUniformProjectionMatrix;
	}
	
	private final int getUniformXScale() {
		return this.mUniformXScale;
	}
	
	private final int getUniformYScale() {
		return this.mUniformYScale;
	}
	
	private final int getUniformColor() {
		return this.mUniformColor;
	}
	
	private final int getUniformResolution() {
		return this.mUniformResolution;
	}
	
}