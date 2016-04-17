package com.neerajsingh.myntrahd;

/**
 * Created by neeraj.singh on 17/04/16.
 */
public class Utils {
    public static int getVideo(String videoCode) {
        if(videoCode.equalsIgnoreCase("1.mp4")){
            return R.raw.a;
        }

        if(videoCode.equalsIgnoreCase("2.mp4")){
            return R.raw.b;
        }
        if(videoCode.equalsIgnoreCase("3.mp4")){
            return R.raw.c;
        }
        if(videoCode.equalsIgnoreCase("4.mp4")){
            return R.raw.d;
        }if(videoCode.equalsIgnoreCase("5.mp4")){
            return R.raw.e;
        }
        if(videoCode.equalsIgnoreCase("6.mp4")){
            return R.raw.f;
        }
        if(videoCode.equalsIgnoreCase("7.mp4")){
            return R.raw.g;
        }
        if(videoCode.equalsIgnoreCase("8.mp4")){
            return R.raw.h;
        }
        if(videoCode.equalsIgnoreCase("9.mp4")){
            return R.raw.i;
        }
        if(videoCode.equalsIgnoreCase("10.mp4")){
            return R.raw.j;
        }
        return R.raw.a;
    }

    public static int getProdImage(String videoCode) {
        if(videoCode.equalsIgnoreCase("1.png")){
            return R.drawable.a;
        }

        if(videoCode.equalsIgnoreCase("2.png")){
            return R.drawable.b;
        }
        if(videoCode.equalsIgnoreCase("3.png")){
            return R.drawable.c;
        }
        if(videoCode.equalsIgnoreCase("4.png")){
            return R.drawable.d;
        }if(videoCode.equalsIgnoreCase("5.png")){
            return R.drawable.e;
        }
        if(videoCode.equalsIgnoreCase("6.png")){
            return R.drawable.f;
        }
        if(videoCode.equalsIgnoreCase("7.png")){
            return R.drawable.g;
        }
        if(videoCode.equalsIgnoreCase("8.png")){
            return R.drawable.h;
        }
        if(videoCode.equalsIgnoreCase("9.png")){
            return R.drawable.i;
        }
        if(videoCode.equalsIgnoreCase("10.png")){
            return R.drawable.j;
        }
        return R.drawable.a;
    }

    public static boolean isNullOrEmpty(String str){
        return str==null || str.length()<=0 || str.equalsIgnoreCase("null");
    }
}
