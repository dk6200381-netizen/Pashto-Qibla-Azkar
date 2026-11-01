package com.pashto.qiblaazkar;

import android.app.Activity;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    LinearLayout layout;
    SharedPreferences prefs;
    int count = 0;

    int green = Color.rgb(18, 85, 65);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefs = getSharedPreferences("Tasbih", MODE_PRIVATE);
        count = prefs.getInt("count", 0);

        home();
    }

    void setup(String title) {
        layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        layout.setPadding(24, 30, 24, 30);
        layout.setBackgroundColor(Color.rgb(243, 247, 243));

        TextView heading = new TextView(this);
        heading.setText(title);
        heading.setTextSize(26);
        heading.setTextColor(green);
        heading.setGravity(Gravity.CENTER);
        layout.addView(heading);

        setContentView(layout);
    }

    void addButton(String name, Runnable action) {
        Button button = new Button(this);
        button.setText(name);
        layout.addView(button);
        button.setOnClickListener(v -> action.run());
    }

    void addText(String value) {
        TextView text = new TextView(this);
        text.setText(value);
        text.setTextSize(20);
        text.setTextColor(Color.BLACK);
        text.setGravity(Gravity.CENTER);
        text.setPadding(8, 18, 8, 18);
        layout.addView(text);
    }

    void home() {
        setup("🕌 پښتو قبله او اذکار");

        addButton("🧭 د قبلې لوری", () -> qibla());
        addButton("🌅 د سهار اذکار", () -> morning());
        addButton("🌇 د ماښام اذکار", () -> evening());
        addButton("📿 تسبیح", () -> tasbih());
    }

    void qibla() {
        setup("🧭 د قبلې لوری");
        addText("د قبلې د دقیق لوري لپاره د موبایل کمپاس ته اړتیا ده.");
        addText("د کمپاس برخه به په راتلونکي ګام کې فعاله کړو.");
        addButton("بېرته کور ته", () -> home());
    }

    void morning() {
        setup("🌅 د سهار اذکار");

        addText("سُبْحَانَ اللَّهِ وَبِحَمْدِهِ");
        addText("ژباړه: الله پاک دی او د هغه ستاینه کوم.");

        addText("أَسْتَغْفِرُ اللَّهَ");
        addText("ژباړه: له الله څخه بښنه غواړم.");

        addButton("بېرته کور ته", () -> home());
    }

    void evening() {
        setup("🌇 د ماښام اذکار");

        addText("أَمْسَيْنَا وَأَمْسَى الْمُلْكُ لِلَّهِ");
        addText("ژباړه: ماښام مو شو او پاچاهي د الله ده.");

        addText("أَسْتَغْفِرُ اللَّهَ");
        addText("ژباړه: له الله څخه بښنه غواړم.");

        addButton("بېرته کور ته", () -> home());
    }

    void tasbih() {
        setup("📿 تسبیح");

        TextView number = new TextView(this);
        number.setTextSize(48);
        number.setTextColor(green);
        number.setGravity(Gravity.CENTER);
        number.setText(String.valueOf(count));
        layout.addView(number);

        addButton("تسبیح +", () -> {
            count++;
            prefs.edit().putInt("count", count).apply();
            number.setText(String.valueOf(count));
        });

        addButton("شمېر صفر کړه", () -> {
            count = 0;
            prefs.edit().putInt("count", 0).apply();
            number.setText("0");
        });

        addButton("بېرته کور ته", () -> home());
    }
} 
