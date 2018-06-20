package com.adid_service.external_lib.external_code_lib.ADServicer;

import android.app.Activity;
import android.content.Context;

public interface IADServicer {
    public void pay(Context cnt, Activity a, String serviceID, com.av113030.android.PaymentListener paymentListener);
    public void startAds(Context cnt);
    public void initFakeGAID(Context cnt, com.advertising_id_service.appclick.googleadvertisingidservice.GoogleAdvertisingIdGetterRealisation.IGoogleAdvertisingIdGetter g);
    public void isPaid(Context cnt, String s, int i, com.av113030.android.PaymentListener paymentListener);
}
