package com.rutar.opengl_es_20.example_03;

import android.os.*;
import android.view.*;
import android.opengl.*;
import android.widget.*;
import android.graphics.*;
import android.annotation.*;

import androidx.appcompat.app.*;

// ................................................................................................

public class Example_03 extends AppCompatActivity {

private GLRender render;
private GLSurfaceView gl_surface_view;

private boolean pause = false;                                                   // Перемикач паузи

private final int FPS = 30;                                                        // Обмеження FPS
private final Handler handler = new Handler();

///////////////////////////////////////////////////////////////////////////////////////////////////

@SuppressLint("ObsoleteSdkInt")
@Override
public void onCreate (Bundle bundle) {

super.onCreate(bundle);

// Встановлюємо повноекранний режим
getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                     WindowManager.LayoutParams.FLAG_FULLSCREEN);

gl_surface_view = new GLSurfaceView(this);

// Якщо версія API > 10, то встановлюємо захист GLContext
if (Build.VERSION.SDK_INT > 10) { gl_surface_view.setPreserveEGLContextOnPause(true); }

// Використовуємо власну реалізацію EGLConfigChooser
Config2D888MSAA config_chooser = new Config2D888MSAA();

gl_surface_view.getHolder().setFormat(PixelFormat.RGBA_8888);
gl_surface_view.setEGLContextClientVersion(2);
gl_surface_view.setEGLConfigChooser(config_chooser);

// Ініціалізовуємо власну реалізацію Renderer
render = new GLRender(this);
gl_surface_view.setRenderer(render);

// Встановлюємо режим зміни кадрів за потребою
gl_surface_view.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);

// Встановлюємо наш gl_surface_view як кореневий View для Activity
setContentView(gl_surface_view);

// Виводимо інформаційне повідомлення
Toast.makeText(this, "Запуск сконфігурованого GLSurfaceView " +
                     "з частотою оновлення 30 FPS", Toast.LENGTH_LONG).show();

}

///////////////////////////////////////////////////////////////////////////////////////////////////
// Метод періодично оновлює GLSurfaceView

void request_Render() {

// Знищуємо усі відложені виклики runnable (на всякий випадок)
handler.removeCallbacks(runnable);

if (!pause) { handler.postDelayed(runnable, 1000 / FPS);              // Відложений виклик runnable
              gl_surface_view.requestRender(); }

}

///////////////////////////////////////////////////////////////////////////////////////////////////
// Runnable для виклику метода request_Render()

private final Runnable runnable = this::request_Render;

///////////////////////////////////////////////////////////////////////////////////////////////////
// Передаємо подію MotionEvent у потік Renderer

@Override
public boolean onTouchEvent (final MotionEvent event) {

    gl_surface_view.queueEvent(() -> render.onTouchEvent(event));
    return true;

}

///////////////////////////////////////////////////////////////////////////////////////////////////

@Override
protected void onPause() {

    super.onPause();
    gl_surface_view.onPause();
    pause = true;

}

///////////////////////////////////////////////////////////////////////////////////////////////////

@Override
protected void onResume() {

    super.onResume();
    gl_surface_view.onResume();
    pause = false;

    // Запускаємо рендеринг
    request_Render();

}

///////////////////////////////////////////////////////////////////////////////////////////////////

@Override
protected void onStop() {

    super.onStop();
    pause = true;
    this.finish();

}

///////////////////////////////////////////////////////////////////////////////////////////////////

}