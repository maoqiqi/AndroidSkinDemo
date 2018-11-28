package com.codearms.maoqiqi.skin.demo.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 省市区实体类
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/10/23 10:08
 */
public class ProvinceBean {

    private String name;

    @SerializedName("city")
    private List<CityBean> cityBeanList;

    public class CityBean {

        private String name;

        @SerializedName("area")
        private List<String> districtList;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getDistrictList() {
            return districtList;
        }

        public void setDistrictList(List<String> districtList) {
            this.districtList = districtList;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CityBean> getCityBeanList() {
        return cityBeanList;
    }

    public void setCityBeanList(List<CityBean> cityBeanList) {
        this.cityBeanList = cityBeanList;
    }
}