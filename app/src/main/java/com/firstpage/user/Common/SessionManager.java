package com.firstpage.user.Common;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.firstpage.user.Ui.JobdetailstatusActivity;
import com.firstpage.user.Ui.LoginscreenActivity;
import com.firstpage.user.Ui.WorkboardActivity;
import com.firstpage.user.Ui.Workboardsupervisor_Activity;

import java.util.HashMap;


public class SessionManager {
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "AndroidHivePref";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // User name (make variable public to access from outside)
//    public static final String KEY_NAME = "name";

    // Email address (make variable public to access from outside)
    public static final String KEY_EMAIL = "email";
    private static final String IS_ADMIN = "role";

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(String email, boolean role) {
        // Storing activity_login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing email in pref
        editor.putString(KEY_EMAIL, email);
        editor.putBoolean(IS_ADMIN, role);

        // commit changes
        editor.commit();
    }

    public void checkLogin() {
        // Check activity_login status
        if (isLoggedIn()) {

            if (Isadmin()) {
                Intent i = new Intent(_context, WorkboardActivity.class);
                _context.startActivity(i);
            } else {
                Intent i = new Intent(_context, Workboardsupervisor_Activity.class);
                _context.startActivity(i);
            }
        }

    }

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        // user name

        // user email id
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));

        // return user
        return user;
    }

    public void logoutUser() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, LoginscreenActivity.class);

        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        _context.startActivity(i);

    }

    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }

    public boolean Isadmin() {
        return pref.getBoolean(IS_ADMIN, false);
    }
}






















