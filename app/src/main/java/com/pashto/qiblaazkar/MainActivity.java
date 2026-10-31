package com.pashto.qiblaazkar;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    int count = 0;
    TextView counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        layout.setPadding(24, 30, 24, 30);
        layout.setBackgroundColor(Color.rgb(243,246,243));

        TextView title = new TextView(this);
        title.setText("🕌 پښتو قبله او اذکار");
        title.setTextSize(27);
        title.setTextColor(Color.rgb(18,61,50));
        title.setGravity(Gravity.CENTER);
        layout.addView(title);

        Button qibla = new Button(this);
        qibla.setText("🧭 د قبلې لوری");
        layout.addView(qibla);

        Button morning = new Button(this);
        morning.setText("🌅 د سهار اذکار");
        layout.addView(morning);

        Button evening = new Button(this);
        evening.setText("🌇 د ماښام اذکار");
        layout.addView(evening);

        Button tasbih = new Button(this);
        tasbih.setText("📿 تسبیح");
        layout.addView(tasbih);

        counter = new TextView(this);
        counter.setText("۰");
        counter.setTextSize(40);
        counter.setGravity(Gravity.CENTER);
        layout.addView(counter);

        Button add = new Button(this);
        add.setText("تسبیح +");
        layout.addView(add);

        add.setOnClickListener(v -> {
            count++;
            counter.setText(String.valueOf(count));
        });

        setContentView(layout);
    }
} 
