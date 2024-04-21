package com.example.fyp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.Manifest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class filter extends AppCompatActivity {
    public static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private LinearLayout dropdownLayout;
    private ImageButton filter_hide,area_hide,preference_hide,cuisine_hide;
    private SearchView searchView;
    private searchFrame fragment;
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission was granted
                    // Proceed with location-related operations
                } else {
                    // Permission denied
                    // Handle the denial properly, possibly disabling features or informing the user
                }
                break;
        }
    }
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.findrestaurants);

        searchView = findViewById(R.id.search_view);
        searchView.setSubmitButtonEnabled(true);
         viewPager = findViewById(R.id.viewPager);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            // Request the permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        } else {
            // Permission has already been granted
            // You can initiate location-based operations here, or this might be a no-op if the actual use is in a fragment or later in the activity lifecycle
        }

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
        String apiURL = "http://10.0.2.2/database/api_PostRestaurantByInfo.php";

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
                if(dishType.size()>0)
                    postData +="&";
                postData += "cuisines=";
                for (int i = 0; i < cuisine.size(); i++) {
                    String json = gson.toJson(cuisine.get(i));
                    postData+=json;
//                    postData += URLEncoder.encode( json, "UTF-8");
                    if(i< cuisine.size()-1)
                        postData +=  ",";
                }

            }
//        $District = $_POST['address'] ?? '';
            if( !district.isEmpty()){
                if(cuisine.size()>0 ||dishType.size()>0)
                        postData +="&";
                postData +="district="+district;

            }

//        $name = $_POST['name'] ?? '';
//        setupViewPager

        PostRequestFoodByDatailTag request =new PostRequestFoodByDatailTag(postData);
            request.execute(apiURL);
            String result =request.getResult();
        showSearchResults(query);

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
    private void showSearchResults(String query) {
        Intent intent = new Intent(this, filterResult.class);
        intent.putExtra("query", query);
        startActivity(intent);
    }
    private void setupViewPager(ViewPager viewPager) {
        filter.ViewPagerAdapter adapter = new filter.ViewPagerAdapter(getSupportFragmentManager());

        // Add Fragments to the adapter. Each fragment corresponds to a tab.
        adapter.addFragment(fragment, "result");

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);
    }



    public class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}

