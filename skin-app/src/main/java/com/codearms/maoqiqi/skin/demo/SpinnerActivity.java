package com.codearms.maoqiqi.skin.demo;

import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.codearms.maoqiqi.skin.demo.bean.ProvinceBean;
import com.codearms.maoqiqi.skin.demo.utils.AssetsUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * 演示Spinner换肤属性
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/11/3 15:32
 */
public class SpinnerActivity extends BaseActivity {

    private Spinner spinnerProvince1;
    private Spinner spinnerCity1;
    private Spinner spinnerDistrict1;
    private AppCompatSpinner spinnerProvince2;
    private AppCompatSpinner spinnerCity2;
    private AppCompatSpinner spinnerDistrict2;

    private List<ProvinceBean> provinceBeanList;
    private int provincePosition = 0;
    private int cityPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Spinner");
        setSupportActionBar(toolbar);

        String json = AssetsUtils.getJson(this, "address.json");
        provinceBeanList = new Gson().fromJson(json, new TypeToken<List<ProvinceBean>>() {
        }.getType());

        spinnerProvince1 = findViewById(R.id.spinner_province_1);
        spinnerCity1 = findViewById(R.id.spinner_city_1);
        spinnerDistrict1 = findViewById(R.id.spinner_district_1);
        spinnerProvince2 = findViewById(R.id.spinner_province_2);
        spinnerCity2 = findViewById(R.id.spinner_city_2);
        spinnerDistrict2 = findViewById(R.id.spinner_district_2);

        setProvince();
        spinnerProvince1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                provincePosition = position;
                cityPosition = 0;
                List<ProvinceBean.CityBean> cityBeanList = provinceBeanList.get(provincePosition).getCityBeanList();
                List<String> districtList = cityBeanList.get(cityPosition).getDistrictList();
                setCity(cityBeanList);
                setDistrict(districtList);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerCity1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cityPosition = position;
                List<String> districtList = provinceBeanList.get(provincePosition).getCityBeanList().get(cityPosition).getDistrictList();
                setDistrict(districtList);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        String[] provinces = new String[provinceBeanList.size()];
        for (int i = 0; i < provinceBeanList.size(); i++) {
            provinces[i] = provinceBeanList.get(i).getName();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item_text, R.id.tv, provinces);
        spinnerProvince2.setAdapter(adapter);
        spinnerCity2.setAdapter(adapter);
        spinnerDistrict2.setAdapter(adapter);
    }

    private void setProvince() {
        String[] provinces = new String[provinceBeanList.size()];
        for (int i = 0; i < provinceBeanList.size(); i++) {
            provinces[i] = provinceBeanList.get(i).getName();
        }
        ArrayAdapter<String> provinceAdapter = new ArrayAdapter<>(this, R.layout.item_text, R.id.tv, provinces);
        spinnerProvince1.setAdapter(provinceAdapter);
    }

    private void setCity(List<ProvinceBean.CityBean> cityBeanList) {
        String[] cities = new String[cityBeanList.size()];
        for (int i = 0; i < cityBeanList.size(); i++) {
            cities[i] = cityBeanList.get(i).getName();
        }
        ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(this, R.layout.item_text, R.id.tv, cities);
        spinnerCity1.setAdapter(cityAdapter);
    }

    private void setDistrict(List<String> districtList) {
        String[] districts = new String[districtList.size()];
        for (int i = 0; i < districtList.size(); i++) {
            districts[i] = districtList.get(i);
        }
        ArrayAdapter<String> districtAdapter = new ArrayAdapter<>(this, R.layout.item_text, R.id.tv, districts);
        spinnerDistrict1.setAdapter(districtAdapter);
    }
}