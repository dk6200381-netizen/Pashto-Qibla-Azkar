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
    int green = Color.rgb(5, 45, 32);
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
        t.setPadding(15, 17, 15, 17);
        t.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        t.setTextDirection(android.view.View.TEXT_DIRECTION_RTL);
        return t;
    }

    void setup(String title) {
        ScrollView scroll = new ScrollView(this);
        main = new LinearLayout(this);
        main.setOrientation(LinearLayout.VERTICAL);
        main.setPadding(18, 20, 18, 25);
        main.setBackgroundColor(green);
        scroll.addView(main);
        setContentView(scroll);

        main.addView(text("🌙 پښتو قبله او اذکار", 27, gold));
        main.addView(text(title, 21, Color.WHITE));
    }

    void addButton(String title, Runnable action) {
        TextView b = text(title, 19, Color.WHITE);
        b.setBackground(shape(Color.rgb(19, 85, 59)));
        LinearLayout.LayoutParams p =
            new LinearLayout.LayoutParams(-1, -2);
        p.setMargins(4, 8, 4, 8);
        main.addView(b, p);
        b.setOnClickListener(v -> action.run());
    }

    void showHome() {
        setup("د اسلامي معلوماتو مرکز");

        addButton("🕌 د لمانځه لارښود", () -> showPrayer());
        addButton("💧 اودس او غسل", () -> showWudu());
        addButton("📿 سهار او ماښام اذکار", () -> showAzkar());
        addButton("🔢 تسبیح", () -> showTasbih());
        addButton("🧭 د قبلې لوری", () -> showQibla());
        addButton("👤 زما په اړه", () -> showAbout());

        main.addView(text(
            "الله تعالی دې زموږ عبادتونه قبول کړي",
            16, gold));
    }

    void showAbout() {
        setup("زما په اړه");

        int id = getResources().getIdentifier(
            "profile", "drawable", getPackageName());

        if (id != 0) {
            ImageView photo = new ImageView(this);
            photo.setImageResource(id);
            photo.setScaleType(ImageView.ScaleType.CENTER_CROP);

            LinearLayout.LayoutParams p =
                new LinearLayout.LayoutParams(500, 550);
            p.gravity = Gravity.CENTER;
            p.setMargins(5, 15, 5, 15);
            main.addView(photo, p);
        }

        main.addView(text("دوست محمد حماد", 26, gold));
        main.addView(text(
            "د پلار نوم: حاجي محمد اسلم", 18, Color.WHITE));
        main.addView(text(
            "زېږېدنه: پکتیکا ولایت، مرکز ښرنه، شهاب الدین کلی",
            17, Color.WHITE));
        main.addView(text(
            "زده کړې: شیخ زاید پوهنتون، د اقتصاد پوهنځی",
            18, Color.WHITE));
        main.addView(text(
            "فېسبوک: دوست محمد حماد", 18, gold));

        addButton("⬅ بېرته کور ته", () -> showHome());
    }

    void showPrayer() {
        setup("د حنفي لمانځه لارښود");

        String[] items = {
            "۱- نیت کول",
            "۲- الله اکبر ویل",
            "۳- ثنا لوستل",
            "۴- تعوذ او بسم الله",
            "۵- فاتحه او بل سورت لوستل",
            "۶- رکوع او تسبیح",
            "۷- له رکوع پورته کېدل",
            "۸- دوه سجدې",
            "۹- دوهم رکعت",
            "۱۰- التحیات",
            "۱۱- درود شریف او دعا",
            "۱۲- سلام ګرځول"
        };

        for (String s : items)
            main.addView(text(s, 17, Color.WHITE));

        addButton("⬅ بېرته کور ته", () -> showHome());
    }

    void showWudu() {
        setup("اودس او غسل");

        String[] items = {
            "د اوداسه څلور فرضونه:",
            "۱- د ټول مخ مینځل",
            "۲- لاسونه تر څنګلو مینځل",
            "۳- د سر د څلورمې برخې مسح",
            "۴- پښې تر بجلکو مینځل",
            "",
            "د غسل درې فرضونه:",
            "۱- خوله مینځل",
            "۲- پوزې ته اوبه رسول",
            "۳- ټول بدن مینځل"
        };

        for (String s : items)
            main.addView(text(s, 17, Color.WHITE));

        addButton("⬅ بېرته کور ته", () -> showHome());
    }

    void showAzkar() {
        setup("سهار او ماښام اذکار");

        String[] items = {
            "آیت الکرسي - ۱ ځل",
            "سورت اخلاص - ۳ ځله",
            "سورت فلق - ۳ ځله",
            "سورت ناس - ۳ ځله",
            "بسم الله الذي لا يضر - ۳ ځله",
            "رضيت بالله ربا - ۳ ځله",
            "لا اله الا الله وحده - ۱۰ ځله",
            "سید الاستغفار - ۱ ځل",
            "سبحان الله وبحمده - ۱۰۰ ځله",
            "درود شریف - ۱۰ ځله"
        };

        for (String s : items)
            main.addView(text(s, 18, Color.WHITE));

        addButton("⬅ بېرته کور ته", () -> showHome());
    }

    int count = 0;
    TextView counter;

    void showTasbih() {
        setup("ډیجیټل تسبیح");

        counter = text("۰", 45, gold);
        main.addView(counter);

        addButton("➕ تسبیح", () -> {
            count++;
            counter.setText(String.valueOf(count));
        });

        addButton("بیا صفر کول", () -> {
            count = 0;
            counter.setText("۰");
        });

        addButton("⬅ بېرته کور ته", () -> showHome());
    }

    void showQibla() {
        setup("د قبلې لوری");
        main.addView(text(
            "دقیق کمپاس به په راتلونکې نسخه کې ورزیات شي.",
            18, Color.WHITE));
        addButton("⬅ بېرته کور ته", () -> showHome());
    }
} 
