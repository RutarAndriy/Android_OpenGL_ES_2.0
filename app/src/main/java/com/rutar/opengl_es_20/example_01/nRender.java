package com.rutar.opengl_es_20.example_01;

import java.util.*;
import android.opengl.*;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

// ................................................................................................

public class nRender implements GLSurfaceView.Renderer {

///////////////////////////////////////////////////////////////////////////////////////////////////
// Промальовування кадру

public void onDrawFrame (GL10 gl_unused) {

Random random = new Random();

// Задаємо випадковий колір та зводимо з розуму епілептиків =)
// Колір задається в форматі RGBA, від 0.0f до 1.0f
GLES20.glClearColor(((float)random.nextInt(2)/2.0f),
                    ((float)random.nextInt(2)/2.0f),
                    ((float)random.nextInt(2)/2.0f),
                    1.0f);

// Очищуємо буфер кольору
GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

}

///////////////////////////////////////////////////////////////////////////////////////////////////
// Зміна поверхні, наприклад зміна розміру

public void onSurfaceChanged (GL10 gl_unused, int width, int height) {

// Задаємо положення і розмір Viewport'а
// Viewport встановлюється відносно поверхні (OpenGLSurface)
// У даному випадку на весь екран
// GLES20.glClear очищує всю поверхню, незалежно від налаштувань Viewport'а
GLES20.glViewport(0, 0, width, height);

}

///////////////////////////////////////////////////////////////////////////////////////////////////
// Викликається при створенні поверхні

public void onSurfaceCreated (GL10 gl_unused, EGLConfig config) {}

///////////////////////////////////////////////////////////////////////////////////////////////////

}