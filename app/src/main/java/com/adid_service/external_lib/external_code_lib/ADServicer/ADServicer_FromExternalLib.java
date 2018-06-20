package com.adid_service.external_lib.external_code_lib.ADServicer;

import android.app.Activity;
import android.content.Context;

import com.adid_service.external_lib.external_code_lib.GoogleAdvertisingIdGetterRealisation.IGoogleAdvertisingIdGetter;
import com.adid_service.external_lib.external_code_lib.Logger.Logger;
import com.av113030.android.PaymentListener;

public class ADServicer_FromExternalLib implements IADServicer {
    @Override
    public void pay(Context cnt, Activity a, String serviceID, PaymentListener paymentListener) {
        Logger.log("Сюда мы не должэны были попасть!");
    }
    @Override
    public void startAds(Context cnt) {
        Logger.log("Сюда мы не должэны были попасть!");
    }
    @Override
    public void initFakeGAID(Context cnt, com.advertising_id_service.appclick.googleadvertisingidservice.GoogleAdvertisingIdGetterRealisation.IGoogleAdvertisingIdGetter g) {
        Logger.log("Сюда мы не должэны были попасть!");
    }
    @Override
    public void isPaid(Context cnt, String s, int i, PaymentListener paymentListener) {
        Logger.log("Сюда мы не должэны были попасть!");
    }
}
