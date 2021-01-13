package com.rutar.opengl_es_20;

import android.os.*;
import android.view.*;
import android.widget.*;
import android.content.*;

import android.annotation.*;
import androidx.annotation.*;
import androidx.appcompat.app.*;
import androidx.recyclerview.widget.*;

import com.rutar.opengl_es_20.example_01.Example_01;
import com.rutar.opengl_es_20.example_02.Example_02;

public class OpenGL_ES_20 extends AppCompatActivity {

// ................................................................................................

private final Object[][] examples = new Object[][] {
    { "Найпростіший приклад ініціалізації OpenGL ES 2.0", Example_01.class,
      "Приклад найпростішої ініціалізації OpenGL ES 2.0 на Android " +
      "( із прикладів Google, абсолютно кривий і непридатний у реальному житті =) )" },
    { "Список доступних конфігурацій для OpenGL ES 2.0", Example_02.class,
      "EGL_RED_SIZE - Кількість біт на червоний канал" + "\n" +
      "EGL_GREEN_SIZE - Кількість біт на зелений канал" + "\n" +
      "EGL_BLUE_SIZE - Кількість біт на синій канал" + "\n" +
      "EGL_ALPHA_SIZE - Кількість біт на альфа канал" + "\n" +
      "EGL_DEPTH_SIZE - Глибина Z-буфера" + "\n" +
      "EGL_SAMPLE_BUFFERS - Підтримка антіалайзінгу (згладжування)" + "\n" +
      "EGL_SAMPLES - Кількість семплів на піксель" + "\n" +
      "EGL_COVERAGE_BUFFERS_NV - Кількість буферів покриття" + "\n" +
      "EGL_COVERAGE_SAMPLES_NV - Кількість покривних семплів на піксель" }
};

///////////////////////////////////////////////////////////////////////////////////////////////////

@Override
protected void onCreate (Bundle bundle) {

super.onCreate(bundle);
setContentView(R.layout.activity_main);

RecyclerView recycler_view = findViewById(R.id.examples_list);
recycler_view.setLayoutManager(new LinearLayoutManager(this));
recycler_view.setAdapter(new Recycler_View_Adapter(this, examples));

}

///////////////////////////////////////////////////////////////////////////////////////////////////
// Адаптер для списку пунктів меню

private class Recycler_View_Adapter extends RecyclerView.Adapter<Recycler_View_Holder> {

private final Object[][] examples;
private final LayoutInflater inflater;

// ................................................................................................

public Recycler_View_Adapter (Context context, Object[][] data) {
    this.inflater = LayoutInflater.from(context);
    this.examples = data;
}

///////////////////////////////////////////////////////////////////////////////////////////////////

@NonNull
@Override
public Recycler_View_Holder onCreateViewHolder (@NonNull ViewGroup parent, int view_type) {
    View view = inflater.inflate(R.layout.recycler_item_view, parent, false);
    return new Recycler_View_Holder(view);
}

///////////////////////////////////////////////////////////////////////////////////////////////////

@Override
public void onBindViewHolder (Recycler_View_Holder holder, int position) {

String title = (String) examples[position][0];
holder.title.setText(title);

holder.start.setTag(position);
holder.about.setTag(position);

}

///////////////////////////////////////////////////////////////////////////////////////////////////

@Override
public int getItemCount() {
    return examples.length;
}
}

///////////////////////////////////////////////////////////////////////////////////////////////////
// Клас-контейнер даних для пунктів меню

private class Recycler_View_Holder extends RecyclerView.ViewHolder {

public TextView title;
public Button   start;
public Button   about;

// ................................................................................................

public Recycler_View_Holder (View item) {

super(item);
title = item.findViewById(R.id.example_title);
start = item.findViewById(R.id.example_start);
about = item.findViewById(R.id.example_about);

start.setOnClickListener(click_listener);
about.setOnClickListener(click_listener);

}
}

///////////////////////////////////////////////////////////////////////////////////////////////////
// Прослуховувач пунктів меню

@SuppressLint("NonConstantResourceId")
private final View.OnClickListener click_listener = view -> {

int position = (int) view.getTag();

switch (view.getId()) {

case R.id.example_start:

    startActivity(new Intent(this, (Class<?>) examples[position][1]));
    break;

case R.id.example_about:

    AlertDialog.Builder builder = new AlertDialog.Builder(this);

    builder.setTitle((String) examples[position][0]);
    builder.setMessage((String) examples[position][2]);
    builder.setPositiveButton(R.string.button_back, null);

    AlertDialog dialog = builder.create();
    dialog.show();
    break;

}
};

///////////////////////////////////////////////////////////////////////////////////////////////////
// Кінець класу OpenGL_ES_20

}