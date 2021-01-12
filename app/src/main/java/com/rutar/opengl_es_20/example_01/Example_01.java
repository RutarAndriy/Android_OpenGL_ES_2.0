package com.rutar.opengl_es_20.example_01;

import android.os.*;
import android.view.*;
import android.opengl.*;
import androidx.appcompat.app.*;

// ................................................................................................

public class Example_01 extends AppCompatActivity {

private nRender renderer = null;
private GLSurfaceView glSurfaceView = null;

///////////////////////////////////////////////////////////////////////////////////////////////////

@Override
protected void onCreate (Bundle bundle) {

super.onCreate(bundle);

// Встановлюємо повноекранний режим
getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                     WindowManager.LayoutParams.FLAG_FULLSCREEN);

try {

    // Намагаємося ініціалізувати OpenGL
    glSurfaceView = new GLSurfaceView(this);

    // Задаємо версію OpenGL ES, рівну 2
    glSurfaceView.setEGLContextClientVersion(2);

    // Встановлюємо нашу реалізацію GLSurfaceView.Renderer для обробки подій
    renderer = new nRender();
    glSurfaceView.setRenderer(renderer);

    // Режим зміни кадрів
    // RENDERMODE_CONTINUOUSLY - автоматична зміна кадрів
    // RENDERMODE_WHEN_DIRTY - за вимогою ( glSurfaceView.requestRender(); )
    glSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);

    // Відображаємо результат
    setContentView(glSurfaceView);
}

// Показуємо повідомлення "Шкода, але ваш пристрій занадто ..."
catch (RuntimeException ignored) {}

}

///////////////////////////////////////////////////////////////////////////////////////////////////

}