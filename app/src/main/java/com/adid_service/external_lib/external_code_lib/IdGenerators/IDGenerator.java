package com.adid_service.external_lib.external_code_lib.IdGenerators;

import android.content.Context;

import java.util.Random;

public abstract class IDGenerator implements IGenerator{
    Context context;
    protected static String getRandomString (int Size) {
        String eng = "abcdefghijklmnopqrstuvwxyz1234567890";
        String res = "";
        Random r = new Random();
        for(int i = 0; i < Size; i++)
        {
            res += eng.charAt(r.nextInt(eng.length()) );
        }
        return res;
    }

    protected static String getRandomHEXString (int Size) {
        String eng = "1234567890ABCDEF";
        String res = "";
        Random r = new Random();
        for(int i = 0; i < Size; i++)
        {
            res += eng.charAt(r.nextInt(eng.length()) );
        }
        return res;
    }

    @Override
    public IGenerator setContext(Context cnt) {
        context = cnt;
        return this;
    }
}
