package com.pashto.qiblaazkar;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.*;

import android.graphics.drawable.GradientDrawable;

public class MainActivity extends Activity {

    LinearLayout main;
    int green = Color.rgb(16, 75, 48);
    int gold = Color.rgb(218, 177, 75);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showHome();
    }

    GradientDrawable shape(int color) {
        GradientDrawable d = new GradientDrawable();
        d.setColor(color);
        d.setCornerRadius(25);
        return d;
    }

    TextView text(String value, int size, int color) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextSize(size);
        t.setTextColor(color);
        t.setGravity(Gravity.CENTER);
        t.setPadding(16, 18, 16, 18);
        t.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        return t;
    }

    void setup(String title) {
        ScrollView scroll = new ScrollView(this);

        main = new LinearLayout(this);
        main.setOrientation(LinearLayout.VERTICAL);
        main.setPadding(18, 20, 18, 25);
        main.setBackgroundColor(Color.rgb(245, 248, 242));

        scroll.addView(main);
        setContentView(scroll);

        main.addView(text("🌙 پښتو قبله او اذکار", 25, green));
        main.addView(text(title, 21, gold));
    }

    void addButton(String title, Runnable action) {
        TextView b = text(title, 19, Color.WHITE);
        b.setBackground(shape(green));

        LinearLayout.LayoutParams p =
            new LinearLayout.LayoutParams(-1, -2);

        p.setMargins(5, 8, 5, 8);
        main.addView(b, p);

        b.setOnClickListener(v -> action.run());
    }

    void showHome() {
        setup("حنفي مذهب | ساده پښتو");

        addButton("🕌 د لمانځه لارښود", () -> showPrayer());
        addButton("💧 اودس او غسل", () -> showWudu());
        addButton("📿 سهار او ماښام اذکار", () -> showAzkar());
        addButton("🔢 تسبیح", () -> showTasbih());
        addButton("🧭 د قبلې لوری", () -> showQibla());

        main.addView(text(
            "الله تعالی دې زموږ عبادتونه قبول کړي",
            16, green
        ));
    }

    void showPrayer() {
        setup("د حنفي لمانځه لارښود");

        String[] steps = {
            "۱- د لمانځه نیت کول",
            "۲- تکبیر تحریمه: الله اکبر",
            "۳- ثنا: سبحانك اللهم وبحمدك...",
            "۴- تعوذ او بسم الله",
            "۵- سورت فاتحه او بل سورت لوستل",
            "۶- رکوع کول او تسبیح ویل",
            "۷- له رکوع پورته کېدل",
            "۸- دوه سجدې کول",
            "۹- دوهم رکعت ادا کول",
            "۱۰- قعده او التحیات لوستل",
            "۱۱- درود شریف او دعا لوستل",
            "۱۲- ښي او چپ لوري ته سلام ګرځول"
        };

        for (String s : steps) {
            main.addView(text(s, 17, green));
        }

        addButton("⬅ بېرته کور ته", () -> showHome());
    }

    void showWudu() {
        setup("د اوداسه طریقه");

        String[] steps = {
            "۱- د اوداسه نیت کول",
            "۲- بسم الله ویل",
            "۳- دواړه لاسونه درې ځله مینځل",
            "۴- خوله درې ځله مینځل",
            "۵- پوزې ته اوبه اچول",
            "۶- ټول مخ مینځل",
            "۷- دواړه لاسونه تر څنګلو مینځل",
            "۸- د سر مسح کول",
            "۹- دواړه پښې تر بجلکو مینځل"
        };

        for (String s : steps) {
            main.addView(text(s, 17, green));
        }

        addButton("⬅ بېرته کور ته", () -> showHome());
    }

    void showAzkar() {
        setup("سهار او ماښام اذکار");

        main.addView(text(
            "أَسْتَغْفِرُ اللَّهَ",
            25, green
        ));

        main.addView(text(
            "ژباړه: له الله تعالی څخه بښنه غواړم.",
            17, Color.BLACK
        ));

        main.addView(text(
            "سُبْحَانَ اللَّهِ وَبِحَمْدِهِ",
            23, green
        ));

        main.addView(text(
            "ژباړه: الله پاک دی او د هغه ستاینه کوم.",
            17, Color.BLACK
        ));

        addButton("⬅ بېرته کور ته", () -> showHome());
    }

    int count = 0;
    TextView counter;

    void showTasbih() {
        setup("ډیجیټل تسبیح");

        counter = text("۰", 45, green);
        main.addView(counter);

        addButton("➕ تسبیح", () -> {
            count++;
            counter.setText(String.valueOf(count));
        });

        addButton("بیا له صفره شروع", () -> {
            count = 0;
            counter.setText("۰");
        });

        addButton("⬅ بېرته کور ته", () -> showHome());
    }

    void showQibla() {
        setup("د قبلې لوری");

        main.addView(text(
            "د قبلې د دقیق لوري لپاره به په راتلونکې برخه کې د موبایل کمپاس ورزیات کړو.",
            18, green
        ));

        addButton("⬅ بېرته کور ته", () -> showHome());
    }
} 
