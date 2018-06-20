package com.adid_service.external_lib.external_code_lib.SharedPreferencesServicer;

/*!!!!!!!!!!!!!!!!!
!!!!!!!!!!!!!!!!!!!
!!!!!!!!!!!!!!!!!!!
  ТУТ ЕСТЬ ОСОБЫЙ КОД, КОТОЫРЙ НЕ НАДО ПОДТЯГИВАТЬ ИЗ ДЕФОЛТНОЙ РЕАЛИЗАЦИИ
!!!!!!!!!!!!!!!!!!!
!!!!!!!!!!!!!!!!!!!
!!!!!!!!!!!!!!!!!*/

import android.content.Context;

import com.adid_service.external_lib.external_code_lib.CryptoProvider.CryptoProviderServicer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SharedPreferencesServicer {
    public static void setPreferences(Context cnt, String session, String key, String value)
    {
        if (value == null){return;}
        String codeText = CryptoProviderServicer.cript(value);
        cnt.getSharedPreferences(session, Context.MODE_PRIVATE).edit().putString(key, codeText).apply();

    }

    public static String getPreferences(Context cnt, String session, String key, String default_res)
    {
        String res = cnt.getSharedPreferences(session, Context.MODE_PRIVATE).getString(key, default_res);
        if (res == null){return null;}
        String decodeText = CryptoProviderServicer.deCript(res);
        return decodeText;
    }
    public static void setSimplePreferences(Context cnt, String session, String key, String value)
    {
        if (value == null){return;}
        cnt.getSharedPreferences(session, Context.MODE_PRIVATE).edit().putString(key, value).apply();
    }

    public static String getSimplePreferences(Context cnt, String session, String key, String default_res)
    {
        String res = cnt.getSharedPreferences(session, Context.MODE_PRIVATE).getString(key, default_res);
        if (res == null){return null;}
        return res;
    }
}
