package com.example.junior.thetailorv20;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;

public class AnotherClass {
    //design_home_activity designHomeActivity = new design_home_activity();
    public static Context context;// = designHomeActivity;

    public AnotherClass(){

    }

    public AnotherClass(Context context1){

        this.context = context1;

    }

    public void setContext(Context context1){
        this.context = context1;
    }

    public Context getContext(){
        return context;
    }

   /* static ContentResolver tryGetContentResolver(Context c){

        ContentResolver contentResolver = c.getContentResolver();
        return contentResolver;
    }*/
}
