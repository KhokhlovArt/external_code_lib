package com.adid_service.external_lib.external_code_lib.Logger;


import android.util.Log;

import com.adid_service.external_lib.external_code_lib.GlobalParameters;

public class Logger implements ILogger {
    static String TAG = "GAIDGetter";

    public static void log(String msg) {
        if (GlobalParameters.NEED_LOG)
        {
            Log.d(TAG,"v " + GlobalParameters.CODE_VERSION + ": " + msg);
        }
    }
}
