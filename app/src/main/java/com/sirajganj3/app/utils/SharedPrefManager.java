package com.sirajganj3.app.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.sirajganj3.app.ui.login.User;


public class SharedPrefManager {
    private static final String SHARED_PREF_NAME="my_shared_pref";
    private static SharedPrefManager mInstance;
    private Context mContext;

    public SharedPrefManager(Context mContext) {
        this.mContext = mContext;
    }

    public static synchronized SharedPrefManager getInstance(Context mContext){
        if (mInstance==null){
            mInstance=new SharedPrefManager(mContext);
        }
        return mInstance;
    }

    public void saveUser(User user){
        SharedPreferences sharedPreferences=mContext.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("user_email",user.getUserEmail());
        editor.putString("user_nicename",user.getUserNicename());
        editor.putString("user_display_name",user.getUserDisplayName());
        editor.putString("token",user.getToken());
        editor.apply();
    }

    public boolean loggedIn(){
        SharedPreferences sharedPreferences=mContext.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString("user_email",null)!=null;
    }


    public User getUser(){
        SharedPreferences sharedPreferences=mContext.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        User user=new User();
        user.setToken(sharedPreferences.getString("user_email",null));
        user.setUserEmail( sharedPreferences.getString("user_email",null));
        user.setUserEmail( sharedPreferences.getString("user_nicename",null));
        user.setUserEmail( sharedPreferences.getString("user_display_name",null)
        );
       return user;
    }



    public void clear(){
        SharedPreferences sharedPreferences=mContext.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
