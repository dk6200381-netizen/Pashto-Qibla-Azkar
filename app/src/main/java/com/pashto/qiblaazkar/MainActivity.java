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
} 
