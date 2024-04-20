package com.example.fyp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
public class filter extends AppCompatActivity {
    private LinearLayout dropdownLayout;
    private ImageButton filter_hide,area_hide,preference_hide,cuisine_hide;
    private SearchView searchView;
    CheckBox checkBoxAsian,checkBoxEuropean,checkBoxGerman,checkBoxGreek,checkBoxJapanese
            ,checkBoxMediterranean,checkBoxMexican,checkBoxDinner,checkBoxLunch,checkBoxMainCourse,
            checkBoxMainDish,checkBoxSalad,checkBoxCentralAndWestern,checkBoxWanChai,checkBoxEastern,
            checkBoxSouthern,checkBoxYauTsimMong,checkBoxShamShuiPo,checkBoxKowloonCity,
            checkBoxWongTaiSin,checkBoxKwunTong,checkBoxTsuenWan,checkBoxTuenMun,checkBoxYuenLong,
            checkBoxNorth,checkBoxTaiPo,checkBoxShaTin,checkBoxSaiKung,checkBoxIslands,checkBoxSideDish;
    public void initAllCheckboxes(){
        // Define and initialize each checkbox
         checkBoxAsian = findViewById(R.id.asian);
         checkBoxEuropean = findViewById(R.id.european);
         checkBoxGerman = findViewById(R.id.german);
         checkBoxGreek = findViewById(R.id.Greek);
         checkBoxJapanese = findViewById(R.id.Japanese);
         checkBoxMediterranean = findViewById(R.id.Mediterranean);
         checkBoxMexican = findViewById(R.id.Mexican);
         checkBoxDinner = findViewById(R.id.Dinner);
         checkBoxLunch = findViewById(R.id.Lunch);
         checkBoxMainCourse = findViewById(R.id.Main_course);
         checkBoxMainDish = findViewById(R.id.Main_dish);
         checkBoxSalad = findViewById(R.id.Salad);
         checkBoxSideDish = findViewById(R.id.Side_dish);
         checkBoxCentralAndWestern = findViewById(R.id.central_and_western);
         checkBoxWanChai = findViewById(R.id.wan_chai);
         checkBoxEastern = findViewById(R.id.eastern);
         checkBoxSouthern = findViewById(R.id.southern);
         checkBoxYauTsimMong = findViewById(R.id.yau_tsim_mong);
         checkBoxShamShuiPo = findViewById(R.id.sham_shui_po);
         checkBoxKowloonCity = findViewById(R.id.kowloon_city);
         checkBoxWongTaiSin = findViewById(R.id.wong_tai_sin);
         checkBoxKwunTong = findViewById(R.id.kwun_tong);
         checkBoxTsuenWan = findViewById(R.id.tsuen_wan);
         checkBoxTuenMun = findViewById(R.id.tuen_mun);
         checkBoxYuenLong = findViewById(R.id.yuen_long);
         checkBoxNorth = findViewById(R.id.north);
         checkBoxTaiPo = findViewById(R.id.tai_po);
         checkBoxShaTin = findViewById(R.id.sha_tin);
         checkBoxSaiKung = findViewById(R.id.sai_kung);
         checkBoxIslands = findViewById(R.id.islands);
    }
    private void checkAllCheckboxes() {


        // Log status for each checkbox
        logCheckboxStatus("Asian", checkBoxAsian.isChecked());
        logCheckboxStatus("European", checkBoxEuropean.isChecked());
        logCheckboxStatus("German", checkBoxGerman.isChecked());
        logCheckboxStatus("Greek", checkBoxGreek.isChecked());
        logCheckboxStatus("Japanese", checkBoxJapanese.isChecked());
        logCheckboxStatus("Mediterranean", checkBoxMediterranean.isChecked());
        logCheckboxStatus("Mexican", checkBoxMexican.isChecked());
        logCheckboxStatus("Dinner", checkBoxDinner.isChecked());
        logCheckboxStatus("Lunch", checkBoxLunch.isChecked());
        logCheckboxStatus("Main Course", checkBoxMainCourse.isChecked());
        logCheckboxStatus("Main Dish", checkBoxMainDish.isChecked());
        logCheckboxStatus("Salad", checkBoxSalad.isChecked());
        logCheckboxStatus("Side Dish", checkBoxSideDish.isChecked());
        logCheckboxStatus("Central and Western", checkBoxCentralAndWestern.isChecked());
        logCheckboxStatus("Wan Chai", checkBoxWanChai.isChecked());
        logCheckboxStatus("Eastern", checkBoxEastern.isChecked());
        logCheckboxStatus("Southern", checkBoxSouthern.isChecked());
        logCheckboxStatus("Yau Tsim Mong", checkBoxYauTsimMong.isChecked());
        logCheckboxStatus("Sham Shui Po", checkBoxShamShuiPo.isChecked());
        logCheckboxStatus("Kowloon City", checkBoxKowloonCity.isChecked());
        logCheckboxStatus("Wong Tai Sin", checkBoxWongTaiSin.isChecked());
        logCheckboxStatus("Kwun Tong", checkBoxKwunTong.isChecked());
        logCheckboxStatus("Tsuen Wan", checkBoxTsuenWan.isChecked());
        logCheckboxStatus("Tuen Mun", checkBoxTuenMun.isChecked());
        logCheckboxStatus("Yuen Long", checkBoxYuenLong.isChecked());
        logCheckboxStatus("North", checkBoxNorth.isChecked());
        logCheckboxStatus("Tai Po", checkBoxTaiPo.isChecked());
        logCheckboxStatus("Sha Tin", checkBoxShaTin.isChecked());
        logCheckboxStatus("Sai Kung", checkBoxSaiKung.isChecked());
        logCheckboxStatus("Islands", checkBoxIslands.isChecked());
    }

    private void logCheckboxStatus(String checkboxName, boolean isChecked) {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.findrestaurants);

        searchView = findViewById(R.id.search_view);
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                try {
                    Search(query);
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
                return false;
            }
            @Override
            public boolean onQueryTextChange (String newText) {
                return true;
            }});
        ImageButton btn_arrow_back = findViewById(R.id.arrow_back);
        btn_arrow_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(filter.this, MainActivity.class);
                startActivity(intent);
            }
        });

        filter_hide = findViewById(R.id.filter_hide);
        filter_hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dropdownLayout = findViewById(R.id.filter);
                hideFilter(filter_hide, dropdownLayout);
            }
        });

        area_hide = findViewById(R.id.area_hide);
        area_hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dropdownLayout = findViewById(R.id.area);
                hideFilter(area_hide, dropdownLayout);
            }
        });

        preference_hide = findViewById(R.id.preference_hide);
        preference_hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dropdownLayout = findViewById(R.id.preference);
                hideFilter(preference_hide, dropdownLayout);
            }
        });

        cuisine_hide = findViewById(R.id.cuisine_hide);
        cuisine_hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dropdownLayout = findViewById(R.id.cuisine);
                hideFilter(cuisine_hide, dropdownLayout);
            }
        });
    }


    @SuppressLint("SuspiciousIndentation")
    public void Search(String query) throws UnsupportedEncodingException {

        String apiURL = "http://10.0.2.2/phpcode/fypTest/api_getFoodByDatailTag.php";

        ArrayList<CheckBox> Cusines = null;
        String postData="";
        for (int i = 0; i < Cusines.size(); i++) {
            CheckBox Cusine =Cusines.get(i);
            if( Cusine.isChecked())
            postData += "cusine=" + URLEncoder.encode((String) Cusine.getText(), "UTF-8");
        }
        new PostRequestFoodByDatailTag(postData).execute(apiURL);
        Log.d("Search",query);
    }
    public void hideFilter(ImageButton btn, LinearLayout dropdownLayout) {
        if (dropdownLayout.getVisibility() == View.VISIBLE) {
            dropdownLayout.setVisibility(View.GONE);
            btn.setImageResource(R.drawable.baseline_arrow_drop_down_24);
        } else {
            dropdownLayout.setVisibility(View.VISIBLE);
            btn.setImageResource(R.drawable.baseline_arrow_drop_up_24);
        }
    }


}

