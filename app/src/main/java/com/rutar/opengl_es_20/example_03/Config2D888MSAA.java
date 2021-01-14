package com.rutar.opengl_es_20.example_03;

import android.util.*;
import android.opengl.GLSurfaceView;

import androidx.annotation.*;
import javax.microedition.khronos.egl.*;

// ................................................................................................

public class Config2D888MSAA implements GLSurfaceView.EGLConfigChooser {

///////////////////////////////////////////////////////////////////////////////////////////////////

public EGLConfig chooseConfig (@NonNull EGL10 egl, EGLDisplay display) {

int[] value = new int[1];

// Шаблон специфікації для RBG_888_MSAA
int[] config_spec = {
    EGL10.EGL_RED_SIZE, 8,
    EGL10.EGL_GREEN_SIZE, 8,
    EGL10.EGL_BLUE_SIZE, 8,
    EGL10.EGL_RENDERABLE_TYPE, 4,
    EGL10.EGL_SAMPLE_BUFFERS, 1,
    EGL10.EGL_SAMPLES, 2,
    EGL10.EGL_NONE
};

// Перевіряємо доступність конфігурацій, які задовільняють шаблон специфікації
if (!egl.eglChooseConfig(display, config_spec, null, 0, value)) {
    Log.w("Config_2D_888_MSAA", "RGB_888_MSAA eglChooseConfig failed");
}

// Отримуємо кількість конфігурацій, які відповідають заданим параметрам
int num_configs = value[0];

// Якщо підходящих конфігурацій RGB_888_MSAA немає,
// то намагаємося отримати RGB_888 без згладжування
if (num_configs <= 0) {

    // Шаблон специфікації для RBG_888
    config_spec = new int[] {
        EGL10.EGL_RED_SIZE, 8,
        EGL10.EGL_GREEN_SIZE, 8,
        EGL10.EGL_BLUE_SIZE, 8,
        EGL10.EGL_RENDERABLE_TYPE, 4,
        EGL10.EGL_NONE
    };

    // Перевіряємо доступність конфігурацій, які задовільняють шаблон специфікації
    if (!egl.eglChooseConfig(display, config_spec, null, 0, value)) {
        Log.w("Config_2D_888_MSAA", "RGB_888 eglChooseConfig failed");
    }

    // Отримуємо кількість конфігурацій, які відповідають заданим параметрам
    num_configs = value[0];

    // Якщо підходящих конфігурацій RGB_888 немає,
    // то намагаємося отримати хоча б RGB_565 без згладжування
    if (num_configs <= 0) {

        // Шаблон специфікації для RBG_565
        config_spec = new int[] {
            EGL10.EGL_RED_SIZE, 5,
            EGL10.EGL_GREEN_SIZE, 6,
            EGL10.EGL_BLUE_SIZE, 5,
            EGL10.EGL_RENDERABLE_TYPE, 4,
            EGL10.EGL_NONE
        };

        // Перевіряємо доступність конфігурацій, які задовільняють шаблон специфікації
        if (!egl.eglChooseConfig(display, config_spec, null, 0, value)) {
            Log.w("Config_2D_888_MSAA", "RGB_565 eglChooseConfig failed");
        }

        // Отримуємо кількість конфігурацій, які відповідають заданим параметрам
        num_configs = value[0];

        // Пристрій не підтримує жодної конфігурації
        if (num_configs <= 0) {
            Log.e("Config_2D_888_MSAA", "No configs match configSpec RGB565");
        }
    }
}

// ................................................................................................

// Масив конфігурацій
EGLConfig[] configs = new EGLConfig[num_configs];

// Отримуємо масив конфігурацій
egl.eglChooseConfig(display, config_spec, configs, num_configs, value);

// Повертаємо конфігурацію
return configs[0];

}

///////////////////////////////////////////////////////////////////////////////////////////////////

}