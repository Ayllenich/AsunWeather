package com.app.ayllenich.asunweather.util;

import android.text.TextUtils;

import com.app.ayllenich.asunweather.db.AsunWeatherDB;
import com.app.ayllenich.asunweather.model.City;
import com.app.ayllenich.asunweather.model.County;
import com.app.ayllenich.asunweather.model.Province;

/**
 * Created by jasn on 15/12/16.
 */
public class Utility {

    /**
     *  解析和处理服务器返回的省级数据
     */
    public synchronized static boolean handleProvincesResponse(AsunWeatherDB asunWeatherDB, String response) {
        if (!TextUtils.isEmpty(response)) {
            String[] allProvinces = response.split(",");
            if(allProvinces != null && allProvinces.length > 0) {
                for(String p : allProvinces) {
                    String[] array = p.split("\\|");
                    Province province = new Province();
                    province.setProvinceCode(array[0]);
                    province.setProvinceName(array[1]);
                    //将解析出来的数据存储到Province表
                    asunWeatherDB.saveProvince(province);
                }
                return true;
            }
        }
        return false;
    }

    /**
     *  解析和处理服务器返回的市级数据
     */
    public static boolean handleCitiesRespone(AsunWeatherDB asunWeatherDB, String response, int provinceId) {
        if(!TextUtils.isEmpty(response)) {
            String[] allCities = response.split(",");
            if(allCities != null && allCities.length > 0 ) {
                for(String c : allCities) {
                    String[] array = c.split("\\|");
                    City city = new City();
                    city.setCityCode(array[0]);
                    city.setCityName(array[1]);
                    city.setProvinceId(provinceId);
                    //将解析出来的数据存储到City表
                    asunWeatherDB.saveCity(city);
                }
                return true;
            }
        }
        return false;
    }

    /**
     *  解析和处理服务器返回的县级数据
     */
    public static boolean handleCountiesResponse(AsunWeatherDB asunWeatherDB, String  response, int cityId) {
        if(response != null) {
            String[] allCounties = response.split(",");
            if(allCounties != null && allCounties.length > 0) {
                for (String c : allCounties) {
                    String[] array = c.split("\\|");
                    County county = new County();
                    county.setCountCode(array[0]);
                    county.setCountName(array[1]);
                    county.setCityId(cityId);
                    //将解析出来的数据存储到County表
                    asunWeatherDB.saveCounty(county);
                }
                return true;
            }
        }
        return false;
    }
}
