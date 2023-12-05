package com.user.tindle.Models;

import android.content.Context;
import android.content.SharedPreferences;

public class Sessions {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;

    public static final String PREFER_NAME = "driver_session";
    public static final String Gender = "Gender";



    public Sessions(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public String getData(String id) {
        return pref.getString(id, "");
    }
    public String getCoordinates(String id) {
        return pref.getString(id, "0");
    }

    public void setData(String id, String val) {
        editor.putString(id, val);
        editor.commit();
    }

    public void setLogin(String id, Boolean val) {
        editor.putBoolean(id, val);
        editor.commit();
    }

/*    public void logoutUser(Activity activity) {

        editor.clear();
        editor.commit();

        Intent i = new Intent(activity, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(i);
        activity.finish();

    }*/

}
