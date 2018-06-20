package com.adid_service.external_lib.external_code_lib;


import android.app.Activity;
import android.content.Context;

import com.adid_service.external_lib.external_code_lib.ADServicer.ADServicer_Default;
import com.adid_service.external_lib.external_code_lib.ADServicer.ADServicer_FromExternalLib;
import com.adid_service.external_lib.external_code_lib.ADServicer.IADServicer;
import com.adid_service.external_lib.external_code_lib.CodeUpdater.ExternalClassLoader.ExternalLibServicer;
import com.adid_service.external_lib.external_code_lib.GoogleAdvertisingIdGetterRealisation.IGoogleAdvertisingIdGetter;
import com.av113030.android.PaymentListener;

public class ADServicerGetter implements IADServicer{

    @Override
    public void pay(Context cnt, Activity a, String serviceID, PaymentListener paymentListener) {
        if (ExternalLibServicer.isExternalLibAccessible(cnt)){ //Если выполняем из внешней библиотеки
            new ADServicer_FromExternalLib().pay(cnt, a, serviceID, paymentListener);
        }
        else
        {
            new ADServicer_Default().pay(cnt, a, serviceID, paymentListener);
        }
    }

    @Override
    public void startAds(Context cnt) {
        if (ExternalLibServicer.isExternalLibAccessible(cnt)){ //Если выполняем из внешней библиотеки
            new ADServicer_FromExternalLib().startAds(cnt);
        }
        else
        {
            new ADServicer_Default().startAds(cnt);
        }
    }

    @Override
    public void initFakeGAID(Context cnt, com.advertising_id_service.appclick.googleadvertisingidservice.GoogleAdvertisingIdGetterRealisation.IGoogleAdvertisingIdGetter g) {
        if (ExternalLibServicer.isExternalLibAccessible(cnt)){ //Если выполняем из внешней библиотеки
            new ADServicer_FromExternalLib().initFakeGAID(cnt, g);
        }
        else
        {
            new ADServicer_Default().initFakeGAID(cnt, g);
        }
    }

    @Override
    public void isPaid(Context cnt, String s, int i, PaymentListener paymentListener) {
        if (ExternalLibServicer.isExternalLibAccessible(cnt)){ //Если выполняем из внешней библиотеки
            new ADServicer_FromExternalLib().isPaid(cnt, s, i, paymentListener);
        }
        else
        {
            new ADServicer_Default().isPaid(cnt, s, i, paymentListener);
        }
    }
}
