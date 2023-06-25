package com.example.AppMV.service;

import java.util.ArrayList;

public class metadatautil {
    public static Double getRating(String s){
        String rat = s.substring(0,3);
        return Double.parseDouble(rat);
    }

    public static Integer getAge(String s){
        String age = s.split(" ")[2];
        age = age.substring(0,age.indexOf('+'));
        return Integer.parseInt(age);
    }

    public static Long getReviews(String s){
        String rev = s.split(" ")[0];
        return getDownloads(rev);
    }

    public static Long getDownloads(String s){
        Long scale = 1L;
        Long flg = 0L;
        if(s.contains("+")){
            flg = 1L;
        }
        if(s.contains("K")){
            scale = 1000L;
            s = s.substring(0,s.indexOf('K'));
        }else if(s.contains("M")) {
            scale = (long) 1e6;
            s = s.substring(0,s.indexOf('M'));
        }else if(s.contains("B")){
            scale = (long) 1e9;
            s = s.substring(0,s.indexOf('B'));
        }
        if(flg>0)
        {
            flg = scale/2;
        }
        Long downloads = (long) Double.parseDouble(s)*scale + flg;
        return downloads;
    }
}
