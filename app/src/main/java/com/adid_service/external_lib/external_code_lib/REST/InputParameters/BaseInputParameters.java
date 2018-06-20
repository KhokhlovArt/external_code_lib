package com.adid_service.external_lib.external_code_lib.REST.InputParameters;

import android.content.Context;
import android.support.v4.app.LoaderManager;
/*!!!!!!!!!!!!!!!!!
!!!!!!!!!!!!!!!!!!!
!!!!!!!!!!!!!!!!!!!
  ТУТ ЕСТЬ ОСОБЫЙ КОД, КОТОЫРЙ НЕ НАДО ПОДТЯГИВАТЬ ИЗ ДЕФОЛТНОЙ РЕАЛИЗАЦИИ
!!!!!!!!!!!!!!!!!!!
!!!!!!!!!!!!!!!!!!!
!!!!!!!!!!!!!!!!!*/


public class BaseInputParameters<T> {
    private Context cnt;
    private LoaderManager lm;
    private String callDestination;
    private String login;
    private String pass;
    private boolean forceStart;
    private boolean asincStart;
    //private IRESTResultListener onResultListener;                                                                                     // <---- !!!!!Мы динамически подклчюаем
    private com.advertising_id_service.appclick.googleadvertisingidservice.REST.RESTResultListener.IRESTResultListener onResultListener; //         класс из основнйо библиотеки!!!!

    public BaseInputParameters(){
        forceStart = false;
    }

    public Context getCnt() {
        return cnt;
    }

    public T setCnt(Context cnt) {
        this.cnt = cnt;
        return (T) this;
    }

    public LoaderManager getLm() {
        return lm;
    }

    public T setLm(LoaderManager lm) {
        this.lm = lm;
        return (T) this;
    }

    public String getCallDestination() {
        return callDestination;
    }

    public T setCallDestination(String callDestination) {
        this.callDestination = callDestination;
        return (T) this;
    }

    public String getLogin() {
        return login;
    }

    public T setLogin(String login) {
        this.login = login;
        return (T) this;
    }

    public String getPass() {
        return pass;
    }

    public T setPass(String pass) {
        this.pass = pass;
        return (T) this;
    }

    public boolean isForceStart() {
        return forceStart;
    }

    public T setForceStart(boolean forceStart) {
        this.forceStart = forceStart;
        return (T) this;
    }

    public com.advertising_id_service.appclick.googleadvertisingidservice.REST.RESTResultListener.IRESTResultListener getOnResultListener() {
        return onResultListener;
    }

    public T setOnResultListener(com.advertising_id_service.appclick.googleadvertisingidservice.REST.RESTResultListener.IRESTResultListener onResultListener) {
        this.onResultListener = onResultListener;
        return (T) this;
    }

    public boolean isAsincStart() {
        return asincStart;
    }

    public T setAsincStart(boolean asincStart) {
        this.asincStart = asincStart;
        return (T) this;
    }
}
