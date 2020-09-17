package com.foodella.driver.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.inputmethod.InputMethodManager;

import com.foodella.driver.features.login.model.CountryCode;

import java.util.ArrayList;

public class AppUtil {

    public static void hideKeyboard(Activity activity) {
        try {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
            }
        } catch (Exception e) {
        }
    }

    public static ArrayList<CountryCode> getListOfCountryCode() {
        final ArrayList<CountryCode> list = new ArrayList<>();
        list.add(new CountryCode("", "965"));
        list.add(new CountryCode("", "994"));
        list.add(new CountryCode("", "965"));
        list.add(new CountryCode("", "965"));
        return list;
    }

    public static int convertDpToPixel(Context context, float dp) {
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        float px = dp * (dm.densityDpi / 160f);
        return (int) px;
    }

    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static boolean isEmailValid(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


}
