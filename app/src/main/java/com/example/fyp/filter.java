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

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
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
    ArrayList dishType =new ArrayList(),cuisine =new ArrayList();
    private String district="";

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

        dishType =new ArrayList();
        cuisine =new ArrayList();
        // Log status for each checkbox
        verifyCuisineForAdd("Asian", checkBoxAsian.isChecked());
        verifyCuisineForAdd("European", checkBoxEuropean.isChecked());
        verifyCuisineForAdd("German", checkBoxGerman.isChecked());
        verifyCuisineForAdd("Greek", checkBoxGreek.isChecked());
        verifyCuisineForAdd("Japanese", checkBoxJapanese.isChecked());
        verifyCuisineForAdd("Mediterranean", checkBoxMediterranean.isChecked());
        verifyCuisineForAdd("Mexican", checkBoxMexican.isChecked());
        //cuisineWhitelist= ['Asian','European','German','Greek','Japanese','Mediterranean','Mexican']
//
        verifyDishTypeForAdd("dinner", checkBoxDinner.isChecked());
        verifyDishTypeForAdd("lunch", checkBoxLunch.isChecked());
        verifyDishTypeForAdd("main course", checkBoxMainCourse.isChecked());
        verifyDishTypeForAdd("main dish", checkBoxMainDish.isChecked());
        verifyDishTypeForAdd("salad", checkBoxSalad.isChecked());
        verifyDishTypeForAdd("side dish", checkBoxSideDish.isChecked());
        //dishTypeWhitelist =['dinner', 'lunch', 'main course', 'main dish', 'salad', 'side dish']
        //District 18
        setDistrict("Central and Western", checkBoxCentralAndWestern.isChecked());
        setDistrict("Wan Chai", checkBoxWanChai.isChecked());
        setDistrict("Eastern", checkBoxEastern.isChecked());
        setDistrict("Southern", checkBoxSouthern.isChecked());
        setDistrict("Yau Tsim Mong", checkBoxYauTsimMong.isChecked());
        setDistrict("Sham Shui Po", checkBoxShamShuiPo.isChecked());
        setDistrict("Kowloon City", checkBoxKowloonCity.isChecked());
        setDistrict("Wong Tai Sin", checkBoxWongTaiSin.isChecked());
        setDistrict("Kwun Tong", checkBoxKwunTong.isChecked());
        setDistrict("Tsuen Wan", checkBoxTsuenWan.isChecked());
        setDistrict("Tuen Mun", checkBoxTuenMun.isChecked());
        setDistrict("Yuen Long", checkBoxYuenLong.isChecked());
        setDistrict("North", checkBoxNorth.isChecked());
        setDistrict("Tai Po", checkBoxTaiPo.isChecked());
        setDistrict("Sha Tin", checkBoxShaTin.isChecked());
        setDistrict("Sai Kung", checkBoxSaiKung.isChecked());
        setDistrict("Islands", checkBoxIslands.isChecked());
    }

    private void setDistrict(String district, boolean checked) {
        if (checked) {
            this.district=district;
        }
    }

    private void verifyDishTypeForAdd(String checkboxName, boolean isChecked) {
        Log.d("verifyDishTypeForAdd("+checkboxName+")",   " is " + (isChecked ? "checked" : "unchecked"));
        if (isChecked) {
        String dishTypeWhitelist [] ={"dinner", "lunch", "main course", "main dish", "salad", "side dish"};

            for (int i = 0; i < dishTypeWhitelist.length; i++) {
                if (checkboxName.equals(dishTypeWhitelist[i])) {
                    cuisine.add(checkboxName);
                    break;
                }
            }

        }

    }
    private void verifyCuisineForAdd(String checkboxName, boolean isChecked) {
        Log.d("verifyCuisineForAdd("+checkboxName+")",   " is " + (isChecked ? "checked" : "unchecked"));
        if (isChecked) {
            String cuisineWhitelist [] = {"Asian","European","German","Greek","Japanese","Mediterranean", "Mexican"};

            for (int i = 0; i < cuisineWhitelist.length; i++) {
                if (checkboxName.equals(cuisineWhitelist[i])) {
                    dishType.add(checkboxName);
                    break;
                }
            }

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.findrestaurants);

        searchView = findViewById(R.id.search_view);
        searchView.setSubmitButtonEnabled(true);
        initAllCheckboxes();
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
        checkAllCheckboxes();
        String apiURL = "http://10.0.2.2/phpcode/fypTest/api_postFoodByDatailTag.php";

        String postData="";
        //    ArrayList dishType =new ArrayList(),cuisine =new ArrayList();
        Gson gson = new Gson();

        if(dishType.size()>0) {
            postData += "dishType=";
            for (int i = 0; i < dishType.size(); i++) {
                String json = gson.toJson(dishType.get(i));
                postData+=json;
//                postData +=  URLEncoder.encode( json, "UTF-8") ;
                if(i< dishType.size()-1)
                    postData +=  ",";
            }

        }
            if(cuisine.size()>0){
                postData += "&cuisines=";
                for (int i = 0; i < cuisine.size(); i++) {
                    String json = gson.toJson(cuisine.get(i));
                    postData+=json;
//                    postData += URLEncoder.encode( json, "UTF-8");
                    if(i< cuisine.size()-1)
                        postData +=  ",";
                }

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

