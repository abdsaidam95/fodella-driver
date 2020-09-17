package com.foodella.driver.utils;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.os.Build;
import android.os.LocaleList;

import com.orhanobut.hawk.Hawk;

import java.util.Locale;

public class AppLanguageContextWrapper extends ContextWrapper {

    public AppLanguageContextWrapper(Context base) {
        super(base);
    }

    public static Context wrap(Context newBase) {

        Locale newLocale = new Locale(getAppLanguage());

        Configuration configuration = newBase.getResources().getConfiguration();
        configuration.setLayoutDirection(newLocale);
        configuration.setLocale(newLocale);

        if (isAfter24()) {
            LocaleList localeList = new LocaleList(newLocale);
            LocaleList.setDefault(localeList);
            configuration.setLocales(localeList);

        } else {
            Locale.setDefault(newLocale);
        }
        Context context = newBase.createConfigurationContext(configuration);

        return new ContextWrapper(context);
    }

    public static boolean isAfter24() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N;
    }

    public static String getAppLanguage() {
        return Hawk.get("language", "en");
    }

    public static void setAppLanguage(String language) {
        Hawk.put("language", language);
    }

}
