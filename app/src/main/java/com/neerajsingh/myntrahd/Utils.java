package com.neerajsingh.myntrahd;

/**
 * Created by neeraj.singh on 17/04/16.
 */
public class Utils {
    public static int getVideo(String videoCode) {
        if(videoCode.equalsIgnoreCase("a")){
            return R.raw.a;
        }

        if(videoCode.equalsIgnoreCase("b")){
            return R.raw.b;
        }
        if(videoCode.equalsIgnoreCase("c")){
            return R.raw.c;
        }
        if(videoCode.equalsIgnoreCase("d")){
            return R.raw.d;
        }if(videoCode.equalsIgnoreCase("e")){
            return R.raw.e;
        }
        if(videoCode.equalsIgnoreCase("f")){
            return R.raw.f;
        }
        if(videoCode.equalsIgnoreCase("g")){
            return R.raw.g;
        }
        if(videoCode.equalsIgnoreCase("h")){
            return R.raw.h;
        }
        if(videoCode.equalsIgnoreCase("i")){
            return R.raw.i;
        }
        if(videoCode.equalsIgnoreCase("j")){
            return R.raw.j;
        }
        return R.raw.a;
    }

    public static int getProdImage(String videoCode) {
        if(videoCode.equalsIgnoreCase("1")){
            return R.drawable.a;
        }

        if(videoCode.equalsIgnoreCase("2")){
            return R.drawable.b;
        }
        if(videoCode.equalsIgnoreCase("3")){
            return R.drawable.c;
        }
        if(videoCode.equalsIgnoreCase("4")){
            return R.drawable.d;
        }if(videoCode.equalsIgnoreCase("5")){
            return R.drawable.e;
        }
        if(videoCode.equalsIgnoreCase("6")){
            return R.drawable.f;
        }
        if(videoCode.equalsIgnoreCase("7")){
            return R.drawable.g;
        }
        if(videoCode.equalsIgnoreCase("8")){
            return R.drawable.h;
        }
        if(videoCode.equalsIgnoreCase("9")){
            return R.drawable.i;
        }
        if(videoCode.equalsIgnoreCase("10")){
            return R.drawable.j;
        }
        return R.drawable.a;
    }

    public static boolean isNullOrEmpty(String str){
        return str==null || str.length()<=0 || str.equalsIgnoreCase("null");
    }
}
