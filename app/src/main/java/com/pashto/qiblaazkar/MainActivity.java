package com.pashto.qiblaazkar;

import android.app.Activity;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {

    LinearLayout layout;
    SharedPreferences prefs;
    int count;
    int green = Color.rgb(12, 65, 48);
    int gold = Color.rgb(218, 174, 75);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = getSharedPreferences("Tasbih", MODE_PRIVATE);
        count = prefs.getInt("count", 0);
        home();
    }

    void setup(String title) {
        ScrollView scroll = new ScrollView(this);
        layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        layout.setPadding(22, 30, 22, 30);
        layout.setBackgroundColor(Color.rgb(12, 65, 48));

        TextView heading = new TextView(this);
        heading.setText(title);
        heading.setTextSize(27);
        heading.setTextColor(gold);
        heading.setGravity(Gravity.CENTER);
        heading.setPadding(5, 20, 5, 25);
        layout.addView(heading);

        scroll.addView(layout);
        setContentView(scroll);
    }

    void addButton(String name, final Runnable action) {
        Button b = new Button(this);
        b.setText(name);
        b.setTextSize(18);
        b.setTextColor(green);
        b.setBackgroundTintList(
            android.content.res.ColorStateList.valueOf(gold)
        );

        LinearLayout.LayoutParams p =
            new LinearLayout.LayoutParams(
                -1, -2
            );
        p.setMargins(0, 9, 0, 9);
        layout.addView(b, p);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action.run();
            }
        });
    }

    void addText(String value) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextSize(20);
        t.setTextColor(Color.WHITE);
        t.setGravity(Gravity.CENTER);
        t.setPadding(12, 15, 12, 15);
        layout.addView(t);
    }

    void home() {
        setup("🕌 پښتو قبله او اذکار");
        addText("ستاسو د عبادت ملګری");

        addButton("🧭 د قبلې لوری", new Runnable() {
            public void run() { qibla(); }
        });

        addButton("🌅 د سهار اذکار", new Runnable() {
            public void run() { morning(); }
        });

        addButton("🌙 د ماښام اذکار", new Runnable() {
            public void run() { evening(); }
        });

        addButton("📿 تسبیح", new Runnable() {
            public void run() { tasbih(); }
        });
    }

    void qibla() {
        setup("🧭 د قبلې لوری");
        addText("د قبلې د دقیق لوري لپاره کمپاس ته اړتیا ده.");
        addText("د کمپاس فعاله برخه به په راتلونکي پړاو کې ورزیاته کړو.");
        addButton("بېرته کور ته", new Runnable() {
            public void run() { home(); }
        });
    }

    void morning() {
        setup("🌅 د سهار اذکار");

        addText("سُبْحَانَ اللَّهِ وَبِحَمْدِهِ");
        addText("ژباړه: الله پاک دی او د هغه ستاینه کوم.");

        addText("أَسْتَغْفِرُ اللَّهَ");
        addText("ژباړه: له الله څخه بښنه غواړم.");

        addText("لَا إِلَهَ إِلَّا اللَّهُ");
        addText("ژباړه: له الله پرته بل معبود نشته.");

        addButton("بېرته کور ته", new Runnable() {
            public void run() { home(); }
        });
    }

    void evening() {
        setup("🌙 د ماښام اذکار");

        addText("أَمْسَيْنَا وَأَمْسَى الْمُلْكُ لِلَّهِ");
        addText("ژباړه: ماښام مو شو او پاچاهي د الله ده.");

        addText("أَسْتَغْفِرُ اللَّهَ");
        addText("ژباړه: له الله څخه بښنه غواړم.");

        addText("سُبْحَانَ اللَّهِ");
        addText("ژباړه: الله پاک دی.");

        addButton("بېرته کور ته", new Runnable() {
            public void run() { home(); }
        });
    }

    void tasbih() {
        setup("📿 تسبیح");

        final TextView number = new TextView(this);
        number.setText(String.valueOf(count));
        number.setTextSize(55);
        number.setTextColor(gold);
        number.setGravity(Gravity.CENTER);
        number.setPadding(10, 30, 10, 30);
        layout.addView(number);

        addButton("تسبیح +", new Runnable() {
            public void run() {
                count++;
                prefs.edit().putInt("count", count).apply();
                number.setText(String.valueOf(count));
            }
        });

        addButton("شمېر صفر کړه", new Runnable() {
            public void run() {
                count = 0;
                prefs.edit().putInt("count", 0).apply();
                number.setText("0");
            }
        });

        addButton("بېرته کور ته", new Runnable() {
            public void run() { home(); }
        });
    }
} 
