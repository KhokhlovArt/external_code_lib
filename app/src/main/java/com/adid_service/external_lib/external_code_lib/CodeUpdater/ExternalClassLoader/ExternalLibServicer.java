package com.adid_service.external_lib.external_code_lib.CodeUpdater.ExternalClassLoader;

import android.content.Context;

import com.adid_service.external_lib.external_code_lib.GlobalParameters;

import dalvik.system.DexClassLoader;

//Всю реализацию этого класса я убрал, т.к. если мы уже запустились из подкаченной библиотеки, то "глубже проваливаться" не надо
public class ExternalLibServicer {
    static DexClassLoader dexClassLoader;
    private static ExternalLibServicer instance = null;

    private ExternalLibServicer(Context cnt){
    }

    public static synchronized ExternalLibServicer getServicer(Context cnt)
    {
        return null;
    }

    public static boolean isExternalLibAccessible(Context cnt)
    {
        return false;
    }

    public static String getDexFilePath(Context cnt)
    {
        return "" + GlobalParameters.DexFilePath(cnt);
    }

    public static String getDexFilePathZip(Context cnt)
    {
        return "" + GlobalParameters.DexFilePathZip(cnt);
    }

    public static void clearDexClassLoader()
    {
        instance = null;
        dexClassLoader = null;
    }
}
