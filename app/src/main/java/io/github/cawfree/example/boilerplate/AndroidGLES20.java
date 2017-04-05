package io.github.cawfree.example.boilerplate;

import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import android.opengl.GLES20;

import uk.ac.manchester.sisp.ribbon.opengl.IGLES20;

public final class AndroidGLES20 implements IGLES20 {

    @Override
    public final void glActiveTexture(final int arg0) {
        GLES20.glActiveTexture(arg0);
    }

    @Override
    public final void glBindBuffer(final int arg0, final int arg1) {
        GLES20.glBindBuffer(arg0, arg1);
    }

    @Override
    public final void glBindFramebuffer(final int arg0, final int arg1) {
        GLES20.glBindFramebuffer(arg0, arg1);
    }

    @Override
    public final void glBindRenderbuffer(final int arg0, final int arg1) {
        GLES20.glBindRenderbuffer(arg0, arg1);
    }

    @Override
    public final void glBindTexture(final int arg0, final int arg1) {
        GLES20.glBindTexture(arg0, arg1);
    }

    @Override
    public final void glBlendEquation(final int arg0) {
        GLES20.glBlendEquation(arg0);
    }

    @Override
    public final void glBlendEquationSeparate(final int arg0, final int arg1) {
        GLES20.glBlendEquationSeparate(arg0, arg1);
    }

    @Override
    public final void glBlendFunc(final int arg0, final int arg1) {
        GLES20.glBlendFunc(arg0, arg1);
    }

    @Override
    public final void glBlendFuncSeparate(final int arg0, final int arg1, final int arg2, final int arg3) {
        GLES20.glBlendFuncSeparate(arg0, arg1, arg2, arg3);
    }

    @Override
    public final void glBufferData(final int arg0, final int arg1, final Buffer arg2, final int arg3) {
        GLES20.glBufferData(arg0, arg1, arg2, arg3);
    }

    @Override
    public final void glBufferSubData(final int arg0, final int arg1, final int arg2, final Buffer arg3) {
        GLES20.glBufferSubData(arg0, arg1, arg2, arg3);
    }

    @Override
    public final int glCheckFramebufferStatus(final int arg0) {
        return GLES20.glCheckFramebufferStatus(arg0);
    }

    @Override
    public final void glClear(final int arg0) {
        GLES20.glClear(arg0);
    }

    @Override
    public final void glClearColor(final float arg0, final float arg1, final float arg2, final float arg3) {
        GLES20.glClearColor(arg0, arg1, arg2, arg3);
    }

    @Override
    public final void glClearDepthf(final float arg0) {
        GLES20.glClearDepthf(arg0);
    }

    @Override
    public final void glClearStencil(final int arg0) {
        GLES20.glClearStencil(arg0);
    }

    @Override
    public final void glColorMask(final boolean arg0, final boolean arg1, final boolean arg2, final boolean arg3) {
        GLES20.glColorMask(arg0, arg1, arg2, arg3);
    }

    @Override
    public final void glCompressedTexImage2D(final int arg0, final int arg1, final int arg2, final int arg3, final int arg4, final int arg5, final int arg6, final Buffer arg7) {
        GLES20.glCompressedTexImage2D(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
    }

    @Override
    public final void glCompressedTexSubImage2D(final int arg0, final int arg1, final int arg2, final int arg3, final int arg4, final int arg5, final int arg6, final int arg7, final Buffer arg8) {
        GLES20.glCompressedTexSubImage2D(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
    }

    @Override
    public final void glCopyTexImage2D(final int arg0, final int arg1, final int arg2, final int arg3, final int arg4, final int arg5, final int arg6, final int arg7) {
        GLES20.glCopyTexImage2D(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
    }

    @Override
    public final void glCopyTexSubImage2D(final int arg0, final int arg1, final int arg2, final int arg3, final int arg4, final int arg5, final int arg6, final int arg7) {
        GLES20.glCopyTexSubImage2D(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
    }

    @Override
    public final void glCullFace(final int arg0) {
        GLES20.glCullFace(arg0);
    }

    @Override
    public final void glDeleteBuffers(final int arg0, final IntBuffer arg1) {
        GLES20.glDeleteBuffers(arg0, arg1);
    }

    @Override
    public final void glDeleteBuffers(final int arg0, final int[] arg1, final int arg2) {
        GLES20.glDeleteBuffers(arg0, arg1, arg2);
    }

    @Override
    public final void glDeleteFramebuffers(final int arg0, final IntBuffer arg1) {
        GLES20.glDeleteFramebuffers(arg0, arg1);
    }

    @Override
    public final void glDeleteFramebuffers(final int arg0, final int[] arg1, final int arg2) {
        GLES20.glDeleteFramebuffers(arg0, arg1, arg2);
    }

    @Override
    public final void glDeleteRenderbuffers(final int arg0, final IntBuffer arg1) {
        GLES20.glDeleteRenderbuffers(arg0, arg1);
    }

    @Override
    public final void glDeleteRenderbuffers(final int arg0, final int[] arg1, final int arg2) {
        GLES20.glDeleteRenderbuffers(arg0, arg1, arg2);
    }

    @Override
    public final void glDeleteTextures(final int arg0, final IntBuffer arg1) {
        GLES20.glDeleteTextures(arg0, arg1);
    }

    @Override
    public final void glDeleteTextures(final int arg0, final int[] arg1, final int arg2) {
        GLES20.glDeleteTextures(arg0, arg1, arg2);
    }

    @Override
    public final void glDepthFunc(final int arg0) {
        GLES20.glDepthFunc(arg0);
    }

    @Override
    public final void glDepthMask(final boolean arg0) {
        GLES20.glDepthMask(arg0);
    }

    @Override
    public final void glDepthRangef(final float arg0, final float arg1) {
        GLES20.glDepthRangef(arg0, arg1);
    }

    @Override
    public final void glDisable(final int arg0) {
        GLES20.glDisable(arg0);
    }

    @Override
    public final void glDrawArrays(final int arg0, final int arg1, final int arg2) {
        GLES20.glDrawArrays(arg0, arg1, arg2);
    }

    @Override
    public final void glDrawElements(final int arg0, final int arg1, final int arg2, final int arg3) {
        GLES20.glDrawElements(arg0, arg1, arg2, arg3);
    }

    @Override
    public final void glEnable(final int arg0) {
        GLES20.glEnable(arg0);
    }

    @Override
    public final void glFinish() {
        GLES20.glFinish();
    }

    @Override
    public final void glFlush() {
        GLES20.glFlush();
    }

    @Override
    public final void glFramebufferRenderbuffer(final int arg0, final int arg1, final int arg2, final int arg3) {
        GLES20.glFramebufferRenderbuffer(arg0, arg1, arg2, arg3);
    }

    @Override
    public final void glFramebufferTexture2D(final int arg0, final int arg1, final int arg2, final int arg3, final int arg4) {
        GLES20.glFramebufferTexture2D(arg0, arg1, arg2, arg3, arg4);
    }

    @Override
    public final void glFrontFace(final int arg0) {
        GLES20.glFrontFace(arg0);
    }

    @Override
    public final void glGenBuffers(final int arg0, final IntBuffer arg1) {
        GLES20.glGenBuffers(arg0, arg1);
    }

    @Override
    public final void glGenBuffers(final int arg0, final int[] arg1, final int arg2) {
        GLES20.glGenBuffers(arg0, arg1, arg2);
    }

    @Override
    public final void glGenFramebuffers(final int arg0, final IntBuffer arg1) {
        GLES20.glGenFramebuffers(arg0, arg1);
    }

    @Override
    public final void glGenFramebuffers(final int arg0, final int[] arg1, final int arg2) {
        GLES20.glGenFramebuffers(arg0, arg1, arg2);
    }

    @Override
    public final void glGenRenderbuffers(final int arg0, final IntBuffer arg1) {
        GLES20.glGenRenderbuffers(arg0, arg1);
    }

    @Override
    public final void glGenRenderbuffers(final int arg0, final int[] arg1, final int arg2) {
        GLES20.glGenRenderbuffers(arg0, arg1, arg2);
    }

    @Override
    public final void glGenTextures(final int arg0, final IntBuffer arg1) {
        GLES20.glGenTextures(arg0, arg1);
    }

    @Override
    public final void glGenTextures(final int arg0, final int[] arg1, final int arg2) {
        GLES20.glGenTextures(arg0, arg1, arg2);
    }

    @Override
    public final void glGenerateMipmap(final int arg0) {
        GLES20.glGenerateMipmap(arg0);
    }

    @Override
    public final void glGetBufferParameteriv(final int arg0, final int arg1, final IntBuffer arg2) {
        GLES20.glGetBufferParameteriv(arg0, arg1, arg2);
    }

    @Override
    public final void glGetBufferParameteriv(final int arg0, final int arg1, final int[] arg2, final int arg3) {
        GLES20.glGetBufferParameteriv(arg0, arg1, arg2, arg3);
    }

    @Override
    public final int glGetError() {
        return GLES20.glGetError();
    }

    @Override
    public final void glGetFloatv(final int arg0, final FloatBuffer arg1) {
        GLES20.glGetFloatv(arg0, arg1);
    }

    @Override
    public final void glGetFloatv(final int arg0, final float[] arg1, final int arg2) {
        GLES20.glGetFloatv(arg0, arg1, arg2);
    }

    @Override
    public final void glGetFramebufferAttachmentParameteriv(final int arg0, final int arg1, final int arg2, final IntBuffer arg3) {
        GLES20.glGetFramebufferAttachmentParameteriv(arg0, arg1, arg2, arg3);
    }

    @Override
    public final void glGetFramebufferAttachmentParameteriv(final int arg0, final int arg1, final int arg2, final int[] arg3, final int arg4) {
        GLES20.glGetFramebufferAttachmentParameteriv(arg0, arg1, arg2, arg3, arg4);
    }

    @Override
    public final void glGetIntegerv(final int arg0, final IntBuffer arg1) {
        GLES20.glGetIntegerv(arg0, arg1);
    }

    @Override
    public final void glGetIntegerv(final int arg0, final int[] arg1, final int arg2) {
        GLES20.glGetIntegerv(arg0, arg1, arg2);
    }

    @Override
    public final void glGetRenderbufferParameteriv(final int arg0, final int arg1, final IntBuffer arg2) {
        GLES20.glGetRenderbufferParameteriv(arg0, arg1, arg2);
    }

    @Override
    public final void glGetRenderbufferParameteriv(final int arg0, final int arg1, final int[] arg2, final int arg3) {
        GLES20.glGetRenderbufferParameteriv(arg0, arg1, arg2, arg3);
    }

    @Override
    public final String glGetString(final int arg0) {
        return GLES20.glGetString(arg0);
    }

    @Override
    public final void glGetTexParameterfv(final int arg0, final int arg1, final FloatBuffer arg2) {
        GLES20.glGetTexParameterfv(arg0, arg1, arg2);
    }

    @Override
    public final void glGetTexParameterfv(final int arg0, final int arg1, final float[] arg2, final int arg3) {
        GLES20.glGetTexParameterfv(arg0, arg1, arg2, arg3);
    }

    @Override
    public final void glGetTexParameteriv(final int arg0, final int arg1, final IntBuffer arg2) {
        GLES20.glGetTexParameteriv(arg0, arg1, arg2);
    }

    @Override
    public final void glGetTexParameteriv(final int arg0, final int arg1, final int[] arg2, final int arg3) {
        GLES20.glGetTexParameteriv(arg0, arg1, arg2, arg3);
    }

    @Override
    public final void glHint(final int arg0, final int arg1) {
        GLES20.glHint(arg0, arg1);
    }

    @Override
    public final boolean glIsBuffer(final int arg0) {
        return GLES20.glIsBuffer(arg0);
    }

    @Override
    public final boolean glIsEnabled(final int arg0) {
        return GLES20.glIsEnabled(arg0);
    }

    @Override
    public final boolean glIsFramebuffer(final int arg0) {
        return GLES20.glIsFramebuffer(arg0);
    }

    @Override
    public final boolean glIsRenderbuffer(final int arg0) {
        return GLES20.glIsRenderbuffer(arg0);
    }

    @Override
    public final boolean glIsTexture(final int arg0) {
        return GLES20.glIsTexture(arg0);
    }

    @Override
    public final void glLineWidth(final float arg0) {
        GLES20.glLineWidth(arg0);
    }

    @Override
    public final void glPixelStorei(final int arg0, final int arg1) {
        GLES20.glPixelStorei(arg0, arg1);
    }

    @Override
    public final void glPolygonOffset(final float arg0, final float arg1) {
        GLES20.glPolygonOffset(arg0, arg1);
    }

    @Override
    public final void glReadPixels(final int arg0, final int arg1, final int arg2, final int arg3, final int arg4, final int arg5, final Buffer arg6) {
        GLES20.glReadPixels(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
    }

    @Override
    public final void glRenderbufferStorage(final int arg0, final int arg1, final int arg2, final int arg3) {
        GLES20.glRenderbufferStorage(arg0, arg1, arg2, arg3);
    }

    @Override
    public final void glSampleCoverage(final float arg0, final boolean arg1) {
        GLES20.glSampleCoverage(arg0, arg1);
    }

    @Override
    public final void glScissor(final int arg0, final int arg1, final int arg2, final int arg3) {
        GLES20.glScissor(arg0, arg1, arg2, arg3);
    }

    @Override
    public final void glStencilFunc(final int arg0, final int arg1, final int arg2) {
        GLES20.glStencilFunc(arg0, arg1, arg2);
    }

    @Override
    public final void glStencilMask(final int arg0) {
        GLES20.glStencilMask(arg0);
    }

    @Override
    public final void glStencilOp(final int arg0, final int arg1, final int arg2) {
        GLES20.glStencilOp(arg0, arg1, arg2);
    }

    @Override
    public final void glTexImage2D(final int arg0, final int arg1, final int arg2, final int arg3, final int arg4, final int arg5, final int arg6, final int arg7, final Buffer arg8) {
        GLES20.glTexImage2D(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
    }

    @Override
    public final void glTexParameterf(final int arg0, final int arg1, final float arg2) {
        GLES20.glTexParameterf(arg0, arg1, arg2);
    }

    @Override
    public final void glTexParameterfv(final int arg0, final int arg1, final FloatBuffer arg2) {
        GLES20.glTexParameterfv(arg0, arg1, arg2);
    }

    @Override
    public final void glTexParameterfv(final int arg0, final int arg1, final float[] arg2, final int arg3) {
        GLES20.glTexParameterfv(arg0, arg1, arg2, arg3);
    }

    @Override
    public final void glTexParameteri(final int arg0, final int arg1, final int arg2) {
        GLES20.glTexParameteri(arg0, arg1, arg2);
    }

    @Override
    public final void glTexParameteriv(final int arg0, final int arg1, final IntBuffer arg2) {
        GLES20.glTexParameteriv(arg0, arg1, arg2);
    }

    @Override
    public final void glTexParameteriv(final int arg0, final int arg1, final int[] arg2, final int arg3) {
        GLES20.glTexParameteriv(arg0, arg1, arg2, arg3);
    }

    @Override
    public final void glTexSubImage2D(final int arg0, final int arg1, final int arg2, final int arg3, final int arg4, final int arg5, final int arg6, final int arg7, final Buffer arg8) {
        GLES20.glTexSubImage2D(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
    }

    @Override
    public final void glViewport(final int arg0, final int arg1, final int arg2, final int arg3) {
        GLES20.glViewport(arg0, arg1, arg2, arg3);
    }

    @Override
    public final void glAttachShader(final int arg0, final int arg1) {
        GLES20.glAttachShader(arg0, arg1);
    }

    @Override
    public final void glBindAttribLocation(final int arg0, final int arg1, final String arg2) {
        GLES20.glBindAttribLocation(arg0, arg1, arg2);
    }

    @Override
    public final void glBlendColor(final float arg0, final float arg1, final float arg2, final float arg3) {
        GLES20.glBlendColor(arg0, arg1, arg2, arg3);
    }

    @Override
    public final void glCompileShader(final int arg0) {
        GLES20.glCompileShader(arg0);
    }

    @Override
    public final int glCreateProgram() {
        return GLES20.glCreateProgram();
    }

    @Override
    public final int glCreateShader(final int arg0) {
        return GLES20.glCreateShader(arg0);
    }

    @Override
    public final void glDeleteProgram(final int arg0) {
        GLES20.glDeleteProgram(arg0);
    }

    @Override
    public final void glDeleteShader(final int arg0) {
        GLES20.glDeleteShader(arg0);
    }

    @Override
    public final void glDetachShader(final int arg0, final int arg1) {
        GLES20.glDetachShader(arg0, arg1);
    }

    @Override
    public final void glDisableVertexAttribArray(final int arg0) {
        GLES20.glDisableVertexAttribArray(arg0);
    }

    @Override
    public final void glEnableVertexAttribArray(final int arg0) {
        GLES20.glEnableVertexAttribArray(arg0);
    }

    @Override
    public final void glGetActiveAttrib(final int arg0, final int arg1, final int arg2, final int[] arg3, final int arg4, final int[] arg5, final int arg6, final int[] arg7, final int arg8, final byte[] arg9, final int arg10) {
        GLES20.glGetActiveUniform(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10);
    }

    @Override
    public final void glGetActiveUniform(final int arg0, final int arg1, final int arg2, final int[] arg3, final int arg4, final int[] arg5, final int arg6, final int[] arg7, final int arg8, final byte[] arg9, final int arg10) {
        GLES20.glGetActiveUniform(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10);
    }

    @Override
    public final void glGetAttachedShaders(final int arg0, final int arg1, final IntBuffer arg2, final IntBuffer arg3) {
        GLES20.glGetAttachedShaders(arg0, arg1, arg2, arg3);
    }

    @Override
    public final void glGetAttachedShaders(final int arg0, final int arg1, final int[] arg2, final int arg3, final int[] arg4, final int arg5) {
        GLES20.glGetAttachedShaders(arg0, arg1, arg2, arg3, arg4, arg5);
    }

    @Override
    public final int glGetAttribLocation(final int arg0, final String arg1) {
        return GLES20.glGetAttribLocation(arg0, arg1);
    }

    @Override
    public final void glGetProgramiv(final int arg0, final int arg1, final IntBuffer arg2) {
        GLES20.glGetProgramiv(arg0, arg1, arg2);
    }

    @Override
    public final void glGetProgramiv(final int arg0, final int arg1, final int[] arg2, final int arg3) {
        GLES20.glGetProgramiv(arg0, arg1, arg2, arg3);
    }

    @Override
    public final void glGetShaderSource(final int arg0, final int arg1, final int[] arg2, final int arg3, final byte[] arg4, final int arg5) {
        GLES20.glGetShaderSource(arg0, arg1, arg2, arg3, arg4, arg5);
    }

    @Override
    public void glGetShaderiv(final int arg0, final int arg1, final IntBuffer arg2) {
        GLES20.glGetShaderiv(arg0, arg1, arg2);
    }

    @Override
    public final void glGetShaderiv(final int arg0, final int arg1, final int[] arg2, final int arg3) {
        GLES20.glGetShaderiv(arg0, arg1, arg2, arg3);
    }

    @Override
    public final int glGetUniformLocation(final int arg0, final String arg1) {
        return GLES20.glGetUniformLocation(arg0, arg1);
    }

    @Override
    public final void glGetUniformfv(final int arg0, final int arg1, final FloatBuffer arg2) {
        GLES20.glGetUniformfv(arg0, arg1, arg2);
    }

    @Override
    public final void glGetUniformfv(final int arg0, final int arg1, final float[] arg2, final int arg3) {
        GLES20.glGetUniformfv(arg0, arg1, arg2, arg3);
    }

    @Override
    public final void glGetUniformiv(final int arg0, final int arg1, final IntBuffer arg2) {
        GLES20.glGetUniformiv(arg0, arg1, arg2);
    }

    @Override
    public final void glGetUniformiv(final int arg0, final int arg1, final int[] arg2, final int arg3) {
        GLES20.glGetUniformiv(arg0, arg1, arg2, arg3);
    }

    @Override
    public final void glGetVertexAttribfv(final int arg0, final int arg1, final FloatBuffer arg2) {
        GLES20.glGetVertexAttribfv(arg0, arg1, arg2);
    }

    @Override
    public final void glGetVertexAttribfv(final int arg0, final int arg1, final float[] arg2, final int arg3) {
        GLES20.glGetVertexAttribfv(arg0, arg1, arg2, arg3);
    }

    @Override
    public final void glGetVertexAttribiv(final int arg0, final int arg1, final IntBuffer arg2) {
        GLES20.glGetVertexAttribiv(arg0, arg1, arg2);
    }

    @Override
    public final void glGetVertexAttribiv(final int arg0, final int arg1, final int[] arg2, final int arg3) {
        GLES20.glGetVertexAttribiv(arg0, arg1, arg2, arg3);
    }

    @Override
    public final boolean glIsProgram(final int arg0) {
        return GLES20.glIsProgram(arg0);
    }

    @Override
    public final boolean glIsShader(final int arg0) {
        return GLES20.glIsShader(arg0);
    }

    @Override
    public final void glLinkProgram(final int arg0) {
        GLES20.glLinkProgram(arg0);
    }

    @Override
    public final void glShaderSource(final int arg0, final String arg2) {
        GLES20.glShaderSource(arg0, arg2);
    }

    @Override
    public final void glStencilFuncSeparate(final int arg0, final int arg1, final int arg2, final int arg3) {
        GLES20.glStencilFuncSeparate(arg0, arg1, arg2, arg3);
    }

    @Override
    public final void glStencilMaskSeparate(final int arg0, final int arg1) {
        GLES20.glStencilMaskSeparate(arg0, arg1);
    }

    @Override
    public final void glStencilOpSeparate(final int arg0, final int arg1, final int arg2, final int arg3) {
        GLES20.glStencilOpSeparate(arg0, arg1, arg2, arg3);
    }

    @Override
    public final void glUniform1f(final int arg0, final float arg1) {
        GLES20.glUniform1f(arg0, arg1);
    }

    @Override
    public final void glUniform1fv(final int arg0, final int arg1, final FloatBuffer arg2) {
        GLES20.glUniform1fv(arg0, arg1, arg2);
    }

    @Override
    public final void glUniform1fv(final int arg0, final int arg1, final float[] arg2, final int arg3) {
        GLES20.glUniform1fv(arg0, arg1, arg2, arg3);
    }

    @Override
    public final void glUniform1i(final int arg0, final int arg1) {
        GLES20.glUniform1i(arg0, arg1);
    }

    @Override
    public final void glUniform1iv(final int arg0, final int arg1, final IntBuffer arg2) {
        GLES20.glUniform1iv(arg0, arg1, arg2);
    }

    @Override
    public final void glUniform1iv(final int arg0, final int arg1, final int[] arg2, final int arg3) {
        GLES20.glUniform1iv(arg0, arg1, arg2, arg3);
    }

    @Override
    public final void glUniform2i(final int arg0, final int arg1, final int arg2) {
        GLES20.glUniform2i(arg0, arg1, arg2);
    }

    @Override
    public final void glUniform2f(final int arg0, final float arg1, final float arg2) {
        GLES20.glUniform2f(arg0, arg1, arg2);
    }

    @Override
    public final void glUniform2fv(final int arg0, final int arg1, final FloatBuffer arg2) {
        GLES20.glUniform2fv(arg0, arg1, arg2);
    }

    @Override
    public final void glUniform2fv(final int arg0, final int arg1, final float[] arg2, final int arg3) {
        GLES20.glUniform2fv(arg0, arg1, arg2, arg3);
    }

    @Override
    public final void glUniform3fv(final int arg0, final int arg1, final FloatBuffer arg2) {
        GLES20.glUniform3fv(arg0, arg1, arg2);
    }

    @Override
    public final void glUniform3fv(final int arg0, final int arg1, final float[] arg2, final int arg3) {
        GLES20.glUniform3fv(arg0, arg1, arg2, arg3);
    }

    @Override
    public final void glUniform3iv(final int arg0, final int arg1, final IntBuffer arg2) {
        GLES20.glUniform3iv(arg0, arg1, arg2);
    }

    @Override
    public final void glUniform3iv(final int arg0, final int arg1, final int[] arg2, final int arg3) {
        GLES20.glUniform3iv(arg0, arg1, arg2, arg3);
    }

    @Override
    public final void glUniform3i(final int arg0, final int arg1, final int arg2, final int arg3) {
        GLES20.glUniform3i(arg0, arg1, arg2, arg3);
    }

    @Override
    public final void glUniform4f(final int arg0, final float arg1, final float arg2, final float arg3, final float arg4) {
        GLES20.glUniform4f(arg0, arg1, arg2, arg3, arg4);
    }

    @Override
    public final void glUniform4fv(final int arg0, final int arg1, final FloatBuffer arg2) {
        GLES20.glUniform4fv(arg0, arg1, arg2);
    }

    @Override
    public final void glUniform4fv(final int arg0, final int arg1, final float[] arg2, final int arg3) {
        GLES20.glUniform4fv(arg0, arg1, arg2, arg3);
    }

    @Override
    public final void glUniform4i(final int arg0, final int arg1, final int arg2, final int arg3, final int arg4) {
        GLES20.glUniform4i(arg0, arg1, arg2, arg3, arg4);
    }

    @Override
    public final void glUniform4iv(final int arg0, final int arg1, final IntBuffer arg2) {
        GLES20.glUniform4iv(arg0, arg1, arg2);
    }

    @Override
    public final void glUniform4iv(final int arg0, final int arg1, final int[] arg2, final int arg3) {
        GLES20.glUniform4iv(arg0, arg1, arg2, arg3);
    }

    @Override
    public final void glUniformMatrix2fv(final int arg0, final int arg1, final boolean arg2, final FloatBuffer arg3) {
        GLES20.glUniformMatrix2fv(arg0, arg1, arg2, arg3);
    }

    @Override
    public final void glUniformMatrix2fv(final int arg0, final int arg1, final boolean arg2, final float[] arg3, final int arg4) {
        GLES20.glUniformMatrix2fv(arg0, arg1, arg2, arg3, arg4);
    }

    @Override
    public final void glUniformMatrix3fv(final int arg0, final int arg1, final boolean arg2, final FloatBuffer arg3) {
        GLES20.glUniformMatrix3fv(arg0, arg1, arg2, arg3);
    }

    @Override
    public final void glUniformMatrix3fv(final int arg0, final int arg1, final boolean arg2, final float[] arg3, final int arg4) {
        GLES20.glUniformMatrix3fv(arg0, arg1, arg2, arg3, arg4);
    }

    @Override
    public final void glUniformMatrix4fv(final int arg0, final int arg1, final boolean arg2, final FloatBuffer arg3) {
        GLES20.glUniformMatrix4fv(arg0, arg1, arg2, arg3);
    }

    @Override
    public final void glUniformMatrix4fv(final int arg0, final int arg1, final boolean arg2, final float[] arg3, final int arg4) {
        GLES20.glUniformMatrix4fv(arg0, arg1, arg2, arg3, arg4);
    }

    @Override
    public final void glUseProgram(final int arg0) {
        GLES20.glUseProgram(arg0);
    }

    @Override
    public final void glValidateProgram(final int arg0) {
        GLES20.glValidateProgram(arg0);
    }

    @Override
    public final void glVertexAttrib1f(final int arg0, final float arg1) {
        GLES20.glVertexAttrib1f(arg0, arg1);
    }

    @Override
    public final void glVertexAttrib1fv(final int arg0, final FloatBuffer arg1) {
        GLES20.glVertexAttrib1fv(arg0, arg1);
    }

    @Override
    public final void glVertexAttrib1fv(final int arg0, final float[] arg1, final int arg2) {
        GLES20.glVertexAttrib1fv(arg0, arg1, arg2);
    }

    @Override
    public final void glVertexAttrib2f(final int arg0, final float arg1, final float arg2) {
        GLES20.glVertexAttrib2f(arg0, arg1, arg2);
    }

    @Override
    public final void glVertexAttrib2fv(final int arg0, final FloatBuffer arg1) {
        GLES20.glVertexAttrib2fv(arg0, arg1);
    }

    @Override
    public final void glVertexAttrib2fv(final int arg0, final float[] arg1, final int arg2) {
        GLES20.glVertexAttrib2fv(arg0, arg1, arg2);
    }

    @Override
    public final void glVertexAttrib3f(final int arg0, final float arg1, final float arg2, final float arg3) {
        GLES20.glVertexAttrib3f(arg0, arg1, arg2, arg3);
    }

    @Override
    public final void glVertexAttrib3fv(final int arg0, final FloatBuffer arg1) {
        GLES20.glVertexAttrib3fv(arg0, arg1);
    }

    @Override
    public final void glVertexAttrib3fv(final int arg0, final float[] arg1, final int arg2) {
        GLES20.glVertexAttrib3fv(arg0, arg1, arg2);
    }

    @Override
    public final void glVertexAttrib4f(final int arg0, final float arg1, final float arg2, final float arg3, final float arg4) {
        GLES20.glVertexAttrib4f(arg0, arg1, arg2, arg3, arg4);
    }

    @Override
    public final void glVertexAttrib4fv(final int arg0, final FloatBuffer arg1) {
        GLES20.glVertexAttrib4fv(arg0, arg1);
    }

    @Override
    public final void glVertexAttrib4fv(final int arg0, final float[] arg1, final int arg2) {
        GLES20.glVertexAttrib4fv(arg0, arg1, arg2);
    }

    @Override
    public final void glVertexAttribPointer(final int arg0, final int arg1, final int arg2, final boolean arg3, final int arg4, final int arg5) {
        GLES20.glVertexAttribPointer(arg0, arg1, arg2, arg3, arg4, arg5);
    }

    @Override
    public final void glReleaseShaderCompiler() {
        GLES20.glReleaseShaderCompiler();
    }

    @Override
    public final void glShaderBinary(final int arg0, final IntBuffer arg1, final int arg2, final Buffer arg3, final int arg4) {
        GLES20.glShaderBinary(arg0, arg1, arg2, arg3, arg4);
    }

    @Override
    public final void glShaderBinary(final int arg0, final int[] arg1, final int arg2, final int arg3, final Buffer arg4, final int arg5) {
        GLES20.glShaderBinary(arg0, arg1, arg2, arg3, arg4, arg5);
    }

    @Override
    public final void glGetShaderPrecisionFormat(final int arg0, final int arg1, final IntBuffer arg2, final IntBuffer arg3) {
        GLES20.glGetShaderPrecisionFormat(arg0, arg1, arg2, arg3);
    }

    @Override
    public final void glGetShaderPrecisionFormat(final int arg0, final int arg1, final int[] arg2, final int arg3, final int[] arg4, final int arg5) {
        GLES20.glGetShaderPrecisionFormat(arg0, arg1, arg2, arg3, arg4, arg5);
    }

}