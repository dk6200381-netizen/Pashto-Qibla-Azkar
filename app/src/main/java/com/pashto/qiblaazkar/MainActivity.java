package com.pashto.qiblaazkar;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.*;
import android.graphics.drawable.GradientDrawable;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends Activity {

    LinearLayout main;
    Spinner provinceSpinner, districtSpinner;
    ArrayList<JSONObject> provinces = new ArrayList<>();
    ArrayList<JSONObject> districts = new ArrayList<>();

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

    void loadData() {
        provinces.clear();
        try {
            InputStream input =
                getAssets().open("provinces-and-districts.json");
            byte[] buffer = new byte[input.available()];
            input.read(buffer);
            input.close();

            JSONArray array =
                new JSONArray(new String(buffer, "UTF-8"));

            for (int i = 0; i < array.length(); i++) {
                provinces.add(array.getJSONObject(i));
            }
        } catch (Exception e) {
            Toast.makeText(this,
                "د ولایتونو فایل ونه موندل شو",
                Toast.LENGTH_LONG).show();
        }
    }

    String pashtoName(JSONObject item) {
        String name = item.optString("namePa", "");
        if (name.trim().isEmpty()) {
            name = item.optString("name", "ناڅرګند");
        }
        return name;
    }

    void showHome() {
        setup("د اسلامي معلوماتو مرکز");

        loadData();

        main.addView(text("خپل ولایت او ولسوالي وټاکئ", 19, gold));

        provinceSpinner = new Spinner(this);
        ArrayList<String> provinceNames = new ArrayList<>();

        for (JSONObject p : provinces) {
            provinceNames.add(pashtoName(p));
        }

        ArrayAdapter<String> provinceAdapter =
            new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, provinceNames);
        provinceAdapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item);
        provinceSpinner.setAdapter(provinceAdapter);
        main.addView(provinceSpinner);

        districtSpinner = new Spinner(this);
        main.addView(districtSpinner);

        provinceSpinner.setOnItemSelectedListener(
            new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(
                    AdapterView<?> parent,
                    android.view.View view,
                    int position, long id) {
                    loadDistricts(position);
                }

                public void onNothingSelected(AdapterView<?> parent) {}
            });

        addButton("🕌 د لمانځه لارښود", () -> showInfo("د لمانځه لارښود"));
        addButton("💧 اودس او غسل", () -> showInfo("اودس او غسل"));
        addButton("📿 سهار او ماښام اذکار", () -> showInfo("اذکار"));
        addButton("🔢 تسبیح", () -> showTasbih());
        addButton("🧭 د قبلې لوری", () -> showInfo("د قبلې لوری"));
        addButton("👤 زما په اړه", () -> showAbout());

        main.addView(text(
            "الله تعالی دې زموږ عبادتونه قبول کړي",
            16, gold));
    }

    void loadDistricts(int position) {
        districts.clear();
        ArrayList<String> names = new ArrayList<>();

        try {
            JSONObject province = provinces.get(position);
            JSONArray array = province.getJSONArray("districts");

            for (int i = 0; i < array.length(); i++) {
                JSONObject district = array.getJSONObject(i);
                districts.add(district);
                names.add(pashtoName(district));
            }
        } catch (Exception e) {
            names.add("ولسوالۍ ونه موندل شوې");
        }

        ArrayAdapter<String> adapter =
            new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, names);
        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item);
        districtSpinner.setAdapter(adapter);
    }

    void showInfo(String title) {
        setup(title);
        main.addView(text(
            "دا برخه به په راتلونکې مرحله کې له بشپړو معلوماتو سره جوړه شي.",
            18, Color.WHITE));
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

    void showAbout() {
        setup("زما په اړه");

        try {
            InputStream input = getAssets().open("profile.JPG");
            android.graphics.Bitmap bitmap =
                android.graphics.BitmapFactory.decodeStream(input);
            input.close();

            ImageView photo = new ImageView(this);
            photo.setImageBitmap(bitmap);
            photo.setScaleType(ImageView.ScaleType.CENTER_CROP);

            LinearLayout.LayoutParams p =
                new LinearLayout.LayoutParams(500, 550);
            p.gravity = Gravity.CENTER;
            main.addView(photo, p);

        } catch (Exception e) {
            main.addView(text("عکس ونه موندل شو", 16, Color.WHITE));
        }

        main.addView(text("دوست محمد حماد", 26, gold));
        main.addView(text("د پلار نوم: حاجي محمد اسلم", 18, Color.WHITE));
        main.addView(text("پکتیکا ولایت، مرکز ښرنه، شهاب‌الدین کلی",
            17, Color.WHITE));
        main.addView(text("شیخ زاید پوهنتون، د اقتصاد پوهنځی",
            18, Color.WHITE));

        addButton("⬅ بېرته کور ته", () -> showHome());
  
    }
    void prayerSection(String heading) {
    TextView title = text(heading, 23, gold);
    title.setGravity(Gravity.CENTER);
    main.addView(title);
}

void prayerLine(String arabic, String pronunciation, String meaning) {
    TextView ar = text(arabic, 25, gold);
    ar.setGravity(Gravity.CENTER);
    main.addView(ar);

    TextView pron = text(pronunciation, 16, Color.LTGRAY);
    pron.setGravity(Gravity.CENTER);
    main.addView(pron);

    TextView trans = text(meaning, 18, Color.WHITE);
    trans.setGravity(Gravity.CENTER);
    trans.setPadding(12, 12, 12, 12);
    trans.setBackground(shape(Color.rgb(19, 85, 59)));
    main.addView(trans);
}

void showPrayer() {
    setup("د لمانځه لارښود");

    prayerSection("۱ ـ د لمانځه نیت");
    prayerLine(
        "النِّيَّةُ",
        "نیت",
        "نیت د زړه اراده ده؛ د کوم لمانځه اراده چې کوې، په زړه کې یې وټاکه."
    );

    prayerSection("۲ ـ تکبیر تحریمه");
    prayerLine(
        "اللّٰهُ أَكْبَرُ",
        "الله اکبر",
        "الله تر ټولو لوی دی."
    );

    prayerSection("۳ ـ ثنا");
    prayerLine(
        "سُبْحَانَكَ اللّٰهُمَّ وَبِحَمْدِكَ",
        "سبحانک اللهم وبحمدک",
        "پاکي ده تالره اې الله! او ستا ستاینه کوم."
    );
    prayerLine(
        "وَتَبَارَكَ اسْمُكَ وَتَعَالَى جَدُّكَ",
        "وتبارک اسمک وتعالی جدک",
        "ستا نوم برکتناک دی او ستا شان لوړ دی."
    );
    prayerLine(
        "وَلَا إِلٰهَ غَيْرُكَ",
        "ولا اله غیرک",
        "له تا پرته بل د عبادت وړ معبود نشته."
    );

    prayerSection("۴ ـ تعوذ");
    prayerLine(
        "أَعُوذُ بِاللّٰهِ مِنَ الشَّيْطَانِ الرَّجِيمِ",
        "اعوذ بالله من الشیطان الرجیم",
        "زه په الله پناه غواړم له رټل شوي شیطان څخه."
    );

    prayerSection("۵ ـ تسمیه");
    prayerLine(
        "بِسْمِ اللّٰهِ الرَّحْمَٰنِ الرَّحِيمِ",
        "بسم الله الرحمن الرحیم",
        "د الله په نوم چې ډېر مهربان او رحم کوونکی دی."
    );

    prayerSection("۶ ـ سورت فاتحه");
    prayerLine(
        "الْحَمْدُ لِلّٰهِ رَبِّ الْعَالَمِينَ",
        "الحمد لله رب العالمین",
        "ټولې ستاینې الله لره دي چې د ټولو جهانونو پالونکی دی."
    );
    prayerLine(
        "الرَّحْمَٰنِ الرَّحِيمِ",
        "الرحمن الرحیم",
        "ډېر مهربان او رحم کوونکی دی."
    );
    prayerLine(
        "مَالِكِ يَوْمِ الدِّينِ",
        "مالک یوم الدین",
        "د جزا د ورځې مالک دی."
    );
    prayerLine(
        "إِيَّاكَ نَعْبُدُ وَإِيَّاكَ نَسْتَعِينُ",
        "ایاک نعبد وایاک نستعین",
        "یوازې ستا عبادت کوو او یوازې له تا مرسته غواړو."
    );
    prayerLine(
        "اهْدِنَا الصِّرَاطَ الْمُسْتَقِيمَ",
        "اهدنا الصراط المستقیم",
        "موږ ته سمه لار وښیه."
    );
    prayerLine(
        "صِرَاطَ الَّذِينَ أَنْعَمْتَ عَلَيْهِمْ",
        "صراط الذین انعمت علیهم",
        "د هغو کسانو لار چې تا پرې نعمت کړی دی."
    );
    prayerLine(
        "غَيْرِ الْمَغْضُوبِ عَلَيْهِمْ وَلَا الضَّالِّينَ",
        "غیر المغضوب علیهم ولا الضالین",
        "نه د هغو کسانو لار چې غضب پرې شوی او نه د ګمراهانو."
    );
    prayerLine(
        "آمِين",
        "آمین",
        "اې الله! دعا مو قبوله کړه."
    );

    prayerSection("۷ ـ سورت اخلاص");
    prayerLine(
        "قُلْ هُوَ اللّٰهُ أَحَدٌ",
        "قل هو الله احد",
        "ووایه: الله یو دی."
    );
    prayerLine(
        "اللّٰهُ الصَّمَدُ",
        "الله الصمد",
        "الله بې‌نیازه دی."
    );
    prayerLine(
        "لَمْ يَلِدْ وَلَمْ يُولَدْ",
        "لم یلد ولم یولد",
        "نه یې څوک زېږولي او نه دی زېږول شوی."
    );
    prayerLine(
        "وَلَمْ يَكُن لَّهُ كُفُوًا أَحَدٌ",
        "ولم یکن له کفوا احد",
        "او هېڅوک د هغه سیال نشته."
    );

    prayerSection("۸ ـ رکوع");
    prayerLine(
        "سُبْحَانَ رَبِّيَ الْعَظِيمِ",
        "سبحان ربي العظیم",
        "زما لوی رب پاک دی."
    );

    prayerSection("۹ ـ له رکوع څخه پورته کېدل");
    prayerLine(
        "سَمِعَ اللّٰهُ لِمَنْ حَمِدَهُ",
        "سمع الله لمن حمده",
        "الله د هغه چا حمد اوري چې د هغه ستاینه کوي."
    );
    prayerLine(
        "رَبَّنَا لَكَ الْحَمْدُ",
        "ربنا لک الحمد",
        "اې زموږ ربه! ټولې ستاینې ستا لپاره دي."
    );

    prayerSection("۱۰ ـ سجده");
    prayerLine(
        "سُبْحَانَ رَبِّيَ الْأَعْلَى",
        "سبحان ربي الأعلى",
        "زما تر ټولو لوړ رب پاک دی."
    );

    prayerSection("۱۱ ـ التحیات");
    prayerLine(
        "التَّحِيَّاتُ لِلّٰهِ وَالصَّلَوَاتُ وَالطَّيِّبَاتُ",
        "التحیات لله والصلوات والطیبات",
        "ټولې سلامتیاوې، لمونځونه او پاکې خبرې الله لره دي."
    );
    prayerLine(
        "السَّلَامُ عَلَيْكَ أَيُّهَا النَّبِيُّ وَرَحْمَةُ اللّٰهِ وَبَرَكَاتُهُ",
        "السلام علیک ایها النبي ورحمة الله وبرکاته",
        "اې نبي! پر تا دې سلام، د الله رحمت او برکتونه وي."
    );
    prayerLine(
        "السَّلَامُ عَلَيْنَا وَعَلَى عِبَادِ اللّٰهِ الصَّالِحِينَ",
        "السلام علینا وعلی عباد الله الصالحین",
        "سلام دې پر موږ او د الله پر نېکو بندګانو وي."
    );
    prayerLine(
        "أَشْهَدُ أَنْ لَا إِلٰهَ إِلَّا اللّٰهُ",
        "اشهد ان لا اله الا الله",
        "ګواهي ورکوم چې له الله پرته بل د عبادت وړ معبود نشته."
    );
    prayerLine(
        "وَأَشْهَدُ أَنَّ مُحَمَّدًا عَبْدُهُ وَرَسُولُهُ",
        "واشهد ان محمداً عبده ورسوله",
        "او ګواهي ورکوم چې محمد ﷺ د الله بنده او رسول دی."
    );

    prayerSection("۱۲ ـ درود ابراهیمي");
    prayerLine(
        "اللّٰهُمَّ صَلِّ عَلَى مُحَمَّدٍ وَعَلَى آلِ مُحَمَّدٍ",
        "اللهم صل علی محمد وعلی آل محمد",
        "اې الله! پر محمد ﷺ او د هغه پر کورنۍ رحمت نازل کړه."
    );
    prayerLine(
        "كَمَا صَلَّيْتَ عَلَى إِبْرَاهِيمَ وَعَلَى آلِ إِبْرَاهِيمَ",
        "کما صلیت علی ابراهیم وعلی آل ابراهیم",
        "لکه څنګه چې دې پر ابراهیم علیه السلام او د هغه پر کورنۍ رحمت نازل کړی."
    );
    prayerLine(
        "إِنَّكَ حَمِيدٌ مَجِيدٌ",
        "انک حمید مجید",
        "بېشکه ته د ستاینې او لویۍ څښتن یې."
    );
    prayerLine(
        "اللّٰهُمَّ بَارِكْ عَلَى مُحَمَّدٍ وَعَلَى آلِ مُحَمَّدٍ",
        "اللهم بارک علی محمد وعلی آل محمد",
        "اې الله! پر محمد ﷺ او د هغه پر کورنۍ برکت نازل کړه."
    );
    prayerLine(
        "كَمَا بَارَكْتَ عَلَى إِبْرَاهِيمَ وَعَلَى آلِ إِبْرَاهِيمَ",
        "کما بارکت علی ابراهیم وعلی آل ابراهیم",
        "لکه څنګه چې دې پر ابراهیم علیه السلام او د هغه پر کورنۍ برکت نازل کړی."
    );
    prayerLine(
        "إِنَّكَ حَمِيدٌ مَجِيدٌ",
        "انک حمید مجید",
        "بېشکه ته د ستاینې او لویۍ څښتن یې."
    );

    prayerSection("۱۳ ـ دعا");
    prayerLine(
        "رَبَّنَا آتِنَا فِي الدُّنْيَا حَسَنَةً",
        "ربنا آتنا فی الدنیا حسنة",
        "اې زموږ ربه! موږ ته په دنیا کې نېکي راکړه."
    );
    prayerLine(
        "وَفِي الْآخِرَةِ حَسَنَةً",
        "وفی الآخرة حسنة",
        "او په آخرت کې هم نېکي راکړه."
    );
    prayerLine(
        "وَقِنَا عَذَابَ النَّارِ",
        "وقنا عذاب النار",
        "او د اور له عذابه مو وساته."
    );

    prayerSection("۱۴ ـ سلام");
    prayerLine(
        "السَّلَامُ عَلَيْكُمْ وَرَحْمَةُ اللّٰهِ",
        "السلام علیکم ورحمة الله",
        "پر تاسو دې سلام او د الله رحمت وي."
    );

    addButton("⬅ بېرته کور ته", () -> showHome());
} 
} 
