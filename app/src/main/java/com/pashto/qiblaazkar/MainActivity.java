package com.pashto.qiblaazkar;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
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
        d.setCornerRadius(22);
        return d;
    }

    TextView text(String value, int size, int color) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextSize(size);
        t.setTextColor(color);
        t.setGravity(Gravity.CENTER);
        t.setPadding(12, 14, 12, 14);
        t.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        t.setTextDirection(View.TEXT_DIRECTION_RTL);
        return t;
    }

    void setup(String title) {
        ScrollView scroll = new ScrollView(this);

        main = new LinearLayout(this);
        main.setOrientation(LinearLayout.VERTICAL);
        main.setPadding(16, 18, 16, 25);
        main.setBackgroundColor(Color.rgb(245, 248, 242));

        scroll.addView(main);
        setContentView(scroll);

        main.addView(text("🌙 پښتو قبله او اذکار", 25, green));
        main.addView(text(title, 21, gold));
    }

    void addButton(String title, Runnable action) {
        TextView b = text(title, 18, Color.WHITE);
        b.setBackground(shape(green));

        LinearLayout.LayoutParams p =
            new LinearLayout.LayoutParams(-1, -2);

        p.setMargins(4, 7, 4, 7);
        main.addView(b, p);
        b.setOnClickListener(v -> action.run());
    }

    void addDhikr(String title, String arabic,
                  String pronunciation, String translation,
                  int total, String source) {

        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(12, 12, 12, 12);
        card.setBackground(shape(Color.WHITE));

        LinearLayout.LayoutParams cp =
            new LinearLayout.LayoutParams(-1, -2);
        cp.setMargins(2, 8, 2, 8);

        main.addView(card, cp);

        TextView heading = text(title, 19, green);
        card.addView(heading);

        TextView ar = text(arabic, 23, Color.BLACK);
        card.addView(ar);

        TextView pron = text(
            "تلفظ: " + pronunciation, 15, green);
        card.addView(pron);

        TextView trans = text(
            "ژباړه: " + translation, 16, Color.DKGRAY);
        card.addView(trans);

        TextView src = text(
            "شمېر: " + total + " ځله\nسرچینه: " + source,
            13, Color.GRAY);
        card.addView(src);

        final int[] count = {0};

        TextView counter = text(
            "۰ / " + total, 24, green);
        card.addView(counter);

        TextView button = text(
            "ذکر وشمېره  +", 17, Color.WHITE);
        button.setBackground(shape(green));
        card.addView(button);

        button.setOnClickListener(v -> {
            if (count[0] < total) {
                count[0]++;
                counter.setText(count[0] + " / " + total);
            }
        });

        TextView reset = text("بیا له صفره شروع", 14, gold);
        card.addView(reset);

        reset.setOnClickListener(v -> {
            count[0] = 0;
            counter.setText("۰ / " + total);
        });
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
            16, green));
    }

    void showPrayer() {
        setup("د حنفي لمانځه لارښود");

        String[] steps = {
            "۱- د لمانځه نیت کول",
            "۲- تکبیر تحریمه: الله اکبر",
            "۳- ثنا لوستل",
            "۴- تعوذ او بسم الله ویل",
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
        setup("د اوداسه او غسل طریقه");

        String[] steps = {
            "د اوداسه څلور فرضونه:",
            "۱- ټول مخ مینځل",
            "۲- دواړه لاسونه تر څنګلو مینځل",
            "۳- د سر د څلورمې برخې مسح کول",
            "۴- دواړه پښې تر بجلکو مینځل",
            "",
            "د اوداسه سنتونه:",
            "۱- نیت کول او بسم الله ویل",
            "۲- لاسونه درې ځله مینځل",
            "۳- خوله درې ځله مینځل",
            "۴- پوزې ته درې ځله اوبه رسول",
            "۵- مخ درې ځله مینځل",
            "۶- لاسونه درې ځله مینځل",
            "۷- د سر مسح کول",
            "۸- پښې درې ځله مینځل",
            "",
            "د غسل درې فرضونه:",
            "۱- خوله مینځل",
            "۲- پوزې ته اوبه رسول",
            "۳- ټول بدن مینځل"
        };

        for (String s : steps) {
            main.addView(text(s, 17, green));
        }

        addButton("⬅ بېرته کور ته", () -> showHome());
    }

    void showAzkar() {
        setup("🌅 د سهار او ماښام اذکار");

        main.addView(text(
            "ټول اذکار په همدې یوه برخه کې دي. د هر ذکر شمېر جلا دی.",
            15, green));

        addDhikr(
            "۱- آیت الکرسي",
            "اللَّهُ لَا إِلَٰهَ إِلَّا هُوَ الْحَيُّ الْقَيُّومُ ۚ " +
            "لَا تَأْخُذُهُ سِنَةٌ وَلَا نَوْمٌ ۚ " +
            "لَهُ مَا فِي السَّمَاوَاتِ وَمَا فِي الْأَرْضِ ۗ " +
            "مَنْ ذَا الَّذِي يَشْفَعُ عِنْدَهُ إِلَّا بِإِذْنِهِ ۚ " +
            "يَعْلَمُ مَا بَيْنَ أَيْدِيهِمْ وَمَا خَلْفَهُمْ ۖ " +
            "وَلَا يُحِيطُونَ بِشَيْءٍ مِنْ عِلْمِهِ إِلَّا بِمَا شَاءَ ۚ " +
            "وَسِعَ كُرْسِيُّهُ السَّمَاوَاتِ وَالْأَرْضَ ۖ " +
            "وَلَا يَئُودُهُ حِفْظُهُمَا ۚ وَهُوَ الْعَلِيُّ الْعَظِيمُ.",
            "الله لا اله الا هو الحي القيوم...",
            "الله هغه ذات دی چې له هغه پرته بل د عبادت وړ معبود نشته. هغه تل ژوندی او د ټولو مخلوقاتو ساتونکی دی. نه خوب ورځي او نه پړک. د اسمانونو او ځمکې ټول څه د هغه دي. هغه ډېر لوړ او لوی ذات دی.",
            1, "قرآن کریم، البقره: ۲۵۵"
        );

        addDhikr(
            "۲- سورت اخلاص",
            "قُلْ هُوَ اللَّهُ أَحَدٌ ۝ اللَّهُ الصَّمَدُ ۝ " +
            "لَمْ يَلِدْ وَلَمْ يُولَدْ ۝ وَلَمْ يَكُنْ لَهُ كُفُوًا أَحَدٌ.",
            "قل هو الله احد، الله الصمد...",
            "ووایه، الله یو دی. الله بې‌نیازه دی. نه یې څوک زېږولی او نه دی زېږول شوی، او هېڅوک د هغه سیال نه دی.",
            3, "قرآن کریم؛ سنن ابي داود ۵۰۸۲"
        );

        addDhikr(
            "۳- سورت فلق",
            "قُلْ أَعُوذُ بِرَبِّ الْفَلَقِ ۝ مِنْ شَرِّ مَا خَلَقَ ۝ " +
            "وَمِنْ شَرِّ غَاسِقٍ إِذَا وَقَبَ ۝ " +
            "وَمِنْ شَرِّ النَّفَّاثَاتِ فِي الْعُقَدِ ۝ " +
            "وَمِنْ شَرِّ حَاسِدٍ إِذَا حَسَدَ.",
            "قل اعوذ برب الفلق...",
            "زه د سهار په پالونکي پناه غواړم، د هغه څه له شره چې پیدا کړي یې دي، د تیارې له شره او د حسد کوونکي له شره.",
            3, "قرآن کریم؛ سنن ابي داود ۵۰۸۲"
        );

        addDhikr(
            "۴- سورت ناس",
            "قُلْ أَعُوذُ بِرَبِّ النَّاسِ ۝ مَلِكِ النَّاسِ ۝ " +
            "إِلَٰهِ النَّاسِ ۝ مِنْ شَرِّ الْوَسْوَاسِ الْخَنَّاسِ ۝ " +
            "الَّذِي يُوَسْوِسُ فِي صُدُورِ النَّاسِ ۝ " +
            "مِنَ الْجِنَّةِ وَالنَّاسِ.",
            "قل اعوذ برب الناس...",
            "زه د خلکو په پالونکي، پاچا او معبود پناه غواړم، د پټ وسوسه اچوونکي له شره چې د خلکو په زړونو کې وسوسې اچوي.",
            3, "قرآن کریم؛ سنن ابي داود ۵۰۸۲"
        );

        addDhikr(
            "۵- د سهار او ماښام دعا",
            "أَصْبَحْنَا وَأَصْبَحَ الْمُلْكُ لِلَّهِ، " +
            "وَالْحَمْدُ لِلَّهِ، لَا إِلَٰهَ إِلَّا اللَّهُ " +
            "وَحْدَهُ لَا شَرِيكَ لَهُ، لَهُ الْمُلْكُ وَلَهُ الْحَمْدُ " +
            "وَهُوَ عَلَىٰ كُلِّ شَيْءٍ قَدِيرٌ.",
            "اصبحنا واصبح الملک لله...",
            "سهار مو کړ او ټوله پاچاهي د الله ده. ټولې ستاینې د الله لپاره دي. هغه یو دی، شریک نه لري او پر هر څه قادر دی.",
            1, "حصن المسلم؛ د سهار او ماښام دعا"
        );

        addDhikr(
            "۶- د الله په نوم دعا",
            "بِسْمِ اللَّهِ الَّذِي لَا يَضُرُّ مَعَ اسْمِهِ " +
            "شَيْءٌ فِي الْأَرْضِ وَلَا فِي السَّمَاءِ " +
            "وَهُوَ السَّمِيعُ الْعَلِيمُ.",
            "بسم الله الذي لا يضر مع اسمه شيء...",
            "د هغه الله په نوم چې د هغه له نوم سره په ځمکه او اسمان کې هېڅ شی زیان نه شي رسولای، او هغه اورېدونکی او پوه دی.",
            3, "سنن ابي داود ۵۰۸۸؛ جامع ترمذي ۳۳۸۸"
        );

        addDhikr(
            "۷- د الله د رضا دعا",
            "رَضِيتُ بِاللَّهِ رَبًّا، وَبِالْإِسْلَامِ دِينًا، " +
            "وَبِمُحَمَّدٍ نَبِيًّا.",
            "رضيت بالله ربا...",
            "زه په دې راضي یم چې الله زما رب، اسلام زما دین او محمد ﷺ زما پېغمبر دی.",
            3, "سنن ابي داود ۵۰۷۲؛ جامع ترمذي ۳۳۸۹"
        );

        addDhikr(
            "۸- د توحید ذکر",
            "لَا إِلَٰهَ إِلَّا اللَّهُ وَحْدَهُ لَا شَرِيكَ لَهُ، " +
            "لَهُ الْمُلْكُ وَلَهُ الْحَمْدُ، " +
            "وَهُوَ عَلَىٰ كُلِّ شَيْءٍ قَدِيرٌ.",
            "لا اله الا الله وحده لا شريک له...",
            "له الله پرته بل د عبادت وړ معبود نشته. هغه یو دی، شریک نه لري. پاچاهي او ستاینه یوازې د هغه ده او هغه پر هر څه قادر دی.",
            10, "نسائي، عمل الیوم واللیلة"
        );

        addDhikr(
            "۹- سید الاستغفار",
            "اللَّهُمَّ أَنْتَ رَبِّي لَا إِلَٰهَ إِلَّا أَنْتَ، " +
            "خَلَقْتَنِي وَأَنَا عَبْدُكَ، وَأَنَا عَلَىٰ عَهْدِكَ " +
            "وَوَعْدِكَ مَا اسْتَطَعْتُ، أَعُوذُ بِكَ مِنْ شَرِّ " +
            "مَا صَنَعْتُ، أَبُوءُ لَكَ بِنِعْمَتِكَ عَلَيَّ، " +
            "وَأَبُوءُ بِذَنْبِي، فَاغْفِرْ لِي، " +
            "فَإِنَّهُ لَا يَغْفِرُ الذُّنُوبَ إِلَّا أَنْتَ.",
            "اللهم انت ربي لا اله الا انت...",
            "اې الله! ته زما رب یې. ما تا پیدا کړی او زه ستا بنده یم. ستا نعمتونه منم او خپلې ګناهونه هم منم. نو ما وبښه، ځکه له تا پرته بل څوک ګناهونه نه شي بښلای.",
            1, "صحیح بخاري ۶۳۰۶"
        );

        addDhikr(
            "۱۰- سبحان الله وبحمده",
            "سُبْحَانَ اللَّهِ وَبِحَمْدِهِ.",
            "سبحان الله وبحمده",
            "الله له هر عیب څخه پاک دی او ټولې ستاینې د هغه لپاره دي.",
            100, "صحیح بخاري او صحیح مسلم"
        );

        addDhikr(
            "۱۱- درود شریف",
            "اللَّهُمَّ صَلِّ وَسَلِّمْ عَلَىٰ نَبِيِّنَا مُحَمَّدٍ.",
            "اللهم صل وسلم على نبينا محمد",
            "اې الله! زموږ پر پېغمبر محمد ﷺ رحمت او سلام نازل کړه.",
            10, "د درود فضیلت؛ د حدیث سرچینه باید جلا وکتل شي"
        );

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
            "حقیقي قبله کمپاس به په راتلونکې نسخه کې فعال کړو.",
            18, green));

        addButton("⬅ بېرته کور ته", () -> showHome());
    }
} 
