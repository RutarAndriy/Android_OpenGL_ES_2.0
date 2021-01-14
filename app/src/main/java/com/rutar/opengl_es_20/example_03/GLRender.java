package com.rutar.opengl_es_20.example_03;

import android.util.Log;
import android.view.*;
import android.opengl.*;
import android.content.*;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

// ................................................................................................

public class GLRender implements GLSurfaceView.Renderer {

private Context context = null;

///////////////////////////////////////////////////////////////////////////////////////////////////

public GLRender (Context context) { this.context = context; }

///////////////////////////////////////////////////////////////////////////////////////////////////

public void onDrawFrame (GL10 gl_unused) {

Log.v("GLRender", "onDrawFrame");

// Виконуємо увесь рендеринг тут

}

///////////////////////////////////////////////////////////////////////////////////////////////////

public void onSurfaceChanged (GL10 gl_unused, int width, int height) {

Log.v("GLRender", "onSurfaceChanged");

// Встановлюємо Viewport за розміром GLSurface
GLES20.glViewport(0, 0, width, height);

}

///////////////////////////////////////////////////////////////////////////////////////////////////

public void onSurfaceCreated (GL10 gl_unused, EGLConfig config) {

Log.v("GLRender", "onSurfaceCreated");

}

///////////////////////////////////////////////////////////////////////////////////////////////////

public void onTouchEvent (final MotionEvent event) {

Log.v("GLRender", "onSurfaceCreated");

}

///////////////////////////////////////////////////////////////////////////////////////////////////

}