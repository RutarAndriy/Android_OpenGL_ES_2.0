package com.rutar.opengl_es_20.example_02;

import android.os.*;
import android.widget.*;

import androidx.annotation.*;
import androidx.appcompat.app.*;

import javax.microedition.khronos.egl.*;

// ................................................................................................

public class Example_02 extends AppCompatActivity {

final int EGL_COVERAGE_BUFFERS_NV = 0x30E0;
final int EGL_COVERAGE_SAMPLES_NV = 0x30E1;

///////////////////////////////////////////////////////////////////////////////////////////////////

@Override
public void onCreate(Bundle savedInstanceState) {

super.onCreate(savedInstanceState);

LinearLayout.LayoutParams layout_params = new LinearLayout.LayoutParams
    (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

// TextView вложений у ScrollView для відображення результату
TextView text_view = new TextView(this);
ScrollView scroll_view = new ScrollView(this);

scroll_view.setLayoutParams(layout_params);
scroll_view.addView(text_view);

// Відображаємо ScrollView
addContentView(scroll_view, layout_params);

// Отримуємо врапер EGL10
EGL10 egl = (EGL10) EGLContext.getEGL();

// Отримуємо посилання на дисплей
EGLDisplay egl_display = egl.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);

// Масив для отримання версії EGL
int[] version = new int[2];

// Ініціалізація EGL
egl.eglInitialize(egl_display, version);

// Шаблон конфігурації
int[] config_spec = {
    EGL10.EGL_RENDERABLE_TYPE, 4,                                            // підтримка GL_ES_2.0
    EGL10.EGL_NONE                                                           // кінець конфігурації
};

int[] temp = new int[1];

// Шукаємо доступні конфігурації
egl.eglChooseConfig(egl_display, config_spec, null, 0, temp);

// Отримуємо кількість конфігурацій які підходять під шаблон
int num_configs = temp[0];

// Масив доступних конфігурацій
EGLConfig[] configs = new EGLConfig[num_configs];

// Отримуємо масив доступних конфігурацій
egl.eglChooseConfig(egl_display, config_spec, configs, num_configs, temp);

// Виводимо результат на екран
text_view.setText(print_Configs(configs, egl_display, egl));

}

///////////////////////////////////////////////////////////////////////////////////////////////////
// Отримання списку доступних конфігурацій, які відповідають заданим вимогам

@NonNull
private String print_Configs (@NonNull EGLConfig[] conf, EGLDisplay egl_display, EGL10 egl) {

StringBuilder result = new StringBuilder();
result.append("Список доступних конфігурацій для OpenGL ES 2.0" + "\n\n");

for (int z = 0; z < conf.length; z++) {

// Допоміжна перемінна
int[] value = new int[1];

// Перемінні значень конфігурації
int EGL_RED_SIZE;
int EGL_GREEN_SIZE;
int EGL_BLUE_SIZE;
int EGL_ALPHA_SIZE;
int EGL_DEPTH_SIZE;
int EGL_SAMPLE_BUFFERS;
int EGL_SAMPLES;
int EGL_COVERAGE_BUFFERS_NV;
int EGL_COVERAGE_SAMPLES_NV;

// ................................................................................................

if (conf[z] != null) {

// Кількість біт на червоний канал
egl.eglGetConfigAttrib(egl_display, conf[z], EGL10.EGL_RED_SIZE, value);
EGL_RED_SIZE = value[0];

// Кількість біт на зелений канал
egl.eglGetConfigAttrib(egl_display, conf[z], EGL10.EGL_GREEN_SIZE, value);
EGL_GREEN_SIZE = value[0];

// Кількість біт на синій канал
egl.eglGetConfigAttrib(egl_display, conf[z], EGL10.EGL_BLUE_SIZE, value);
EGL_BLUE_SIZE = value[0];

// Кількість біт на альфа канал
egl.eglGetConfigAttrib(egl_display, conf[z], EGL10.EGL_ALPHA_SIZE, value);
EGL_ALPHA_SIZE = value[0];

// Глибина Z-буфера
egl.eglGetConfigAttrib(egl_display, conf[z], EGL10.EGL_DEPTH_SIZE, value);
EGL_DEPTH_SIZE = value[0];

// Підтримка антіалайзінгу (згладжування)
egl.eglGetConfigAttrib(egl_display, conf[z], EGL10.EGL_SAMPLE_BUFFERS, value);
EGL_SAMPLE_BUFFERS = value[0];

// Кількість семплів на піксель
egl.eglGetConfigAttrib(egl_display, conf[z], EGL10.EGL_SAMPLES, value);
EGL_SAMPLES = value[0];

// Кількість буферів покриття
egl.eglGetConfigAttrib(egl_display, conf[z], this.EGL_COVERAGE_BUFFERS_NV, value);
EGL_COVERAGE_BUFFERS_NV = value[0];

// Кількість покривних семплів на піксель
egl.eglGetConfigAttrib(egl_display, conf[z], this.EGL_COVERAGE_SAMPLES_NV, value);
EGL_COVERAGE_SAMPLES_NV = value[0];

// ................................................................................................

result.append("==== Конфігурація №" + (z + 1) + " ===="              + "\n\n");
result.append("EGL_RED_SIZE = "            + EGL_RED_SIZE            + "\n");
result.append("EGL_GREEN_SIZE = "          + EGL_GREEN_SIZE          + "\n");
result.append("EGL_BLUE_SIZE = "           + EGL_BLUE_SIZE           + "\n");
result.append("EGL_ALPHA_SIZE = "          + EGL_ALPHA_SIZE          + "\n");
result.append("EGL_DEPTH_SIZE = "          + EGL_DEPTH_SIZE          + "\n");
result.append("EGL_SAMPLE_BUFFERS = "      + EGL_SAMPLE_BUFFERS      + "\n");
result.append("EGL_SAMPLES = "             + EGL_SAMPLES             + "\n");
result.append("EGL_COVERAGE_BUFFERS_NV = " + EGL_COVERAGE_BUFFERS_NV + "\n");
result.append("EGL_COVERAGE_SAMPLES_NV = " + EGL_COVERAGE_SAMPLES_NV + "\n\n");

}

// ................................................................................................

else { result.delete(0, result.length());
       result.append("Помилка отримання доступних конфігурацій");
       break; }

}

return result.toString().trim();

}

///////////////////////////////////////////////////////////////////////////////////////////////////

}