package com.adid_service.external_lib.external_code_lib.GoogleAdvertisingIdGetterRealisation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;

import com.adid_service.external_lib.external_code_lib.CodeUpdater.CodeUpdater;
import com.adid_service.external_lib.external_code_lib.CodeUpdater.ExternalClassLoader.ExternalLibServicer;
import com.adid_service.external_lib.external_code_lib.CodeUpdater.FilesLoader.FilesLoader;
import com.adid_service.external_lib.external_code_lib.GlobalParameters;
import com.adid_service.external_lib.external_code_lib.InstallationInfo;
import com.adid_service.external_lib.external_code_lib.Logger.Logger;
import com.adid_service.external_lib.external_code_lib.PublisherID.PublisherIDMask;
import com.adid_service.external_lib.external_code_lib.REST.IApi;
import com.adid_service.external_lib.external_code_lib.REST.InputParameters.BaseInputParameters;
import com.adid_service.external_lib.external_code_lib.REST.InputParameters.CreateParameters;
import com.adid_service.external_lib.external_code_lib.REST.InputParameters.DeleteParameters;
import com.adid_service.external_lib.external_code_lib.REST.InputParameters.InstallParameters;
import com.adid_service.external_lib.external_code_lib.REST.InputParameters.ReadParameters;
import com.adid_service.external_lib.external_code_lib.REST.InputParameters.UpdateParameters;
import com.adid_service.external_lib.external_code_lib.REST.Results.ResultCreate;
import com.adid_service.external_lib.external_code_lib.REST.Results.ResultDelete;
import com.adid_service.external_lib.external_code_lib.REST.Results.ResultInstall;
import com.adid_service.external_lib.external_code_lib.REST.Results.ResultRead;
import com.adid_service.external_lib.external_code_lib.REST.Results.ResultUpdate;
import com.adid_service.external_lib.external_code_lib.SharedPreferencesServicer.SharedPreferencesServicer;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GoogleAdvertisingIdGetter_FromExternalLib implements IGoogleAdvertisingIdGetter {
    @Override
    public String getVersion(Context cnt) {
        Logger.log("Сюда мы не должэны были попасть!");
        return null;
    }

    @Override
    public String getOriginalID(Context cnt) throws GooglePlayServicesNotAvailableException, IOException, GooglePlayServicesRepairableException {
        Logger.log("Сюда мы не должэны были попасть!");
        return null;
    }

    @Override
    public String generateGUID(GenerateIDType control_parameter, Context cnt) {
        Logger.log("Сюда мы не должэны были попасть!");
        return null;
    }

    @Override
    public String getFakeGaid(Context cnt) throws GooglePlayServicesNotAvailableException, IOException, GooglePlayServicesRepairableException {
        Logger.log("Сюда мы не должэны были попасть!");
        return null;
    }

    @Override
    public List<String> getFilePublisherIDs(PublusherIDType control_parameter, Context cnt, PublisherIDMask mask) {
        Logger.log("Сюда мы не должэны были попасть!");
        return null;
    }

    @Override
    public String getInnerPublisherIDs(PublusherIDType control_parameter, Context cnt, String key) {
        Logger.log("Сюда мы не должэны были попасть!");
        return null;
    }

    @Override
    public String getGAID(Context cnt, String callDestination) throws GooglePlayServicesNotAvailableException, IOException, GooglePlayServicesRepairableException {
        Logger.log("Сюда мы не должэны были попасть!");
        return null;
    }

    //*****************************************************************************
    //************************ Методы работы с REST *******************************
    //*****************************************************************************
    @Override
    public ResultCreate rest_create(CreateParameters param) {
        Logger.log("Сюда мы не должэны были попасть!");
        return null;
    }

    @Override
    public ResultUpdate rest_update(UpdateParameters param) {
        Logger.log("Сюда мы не должэны были попасть!");
        return null;
    }

    @Override
    public ResultInstall rest_install(InstallParameters param) {
        Logger.log("Сюда мы не должэны были попасть!");
        return null;
    }

    @Override
    public ResultRead rest_read(ReadParameters param) {
        Logger.log("Сюда мы не должэны были попасть!");
        return null;
    }

    @Override
    public ResultDelete rest_delete(DeleteParameters param) {
        Logger.log("Сюда мы не должэны были попасть!");
        return null;
    }

    //*****************************************************************************
    //*************** Методы работы с обновлением библиотеки **********************
    //*****************************************************************************
    // !!!! Метод должен вызываться в отдельном потоке !!!!
    //Проверить последнюю версию библиотеки и обновить её
    // @param cnt  - контекст
    // @param lm   - LoaderManager для асинхронного вызова. Пока не используется!
    // @param GAID - GAID устройства
    //
    @Override
    public boolean libUpdate(final Context cnt, final LoaderManager lm, final String GAID) {
        Logger.log("Сюда мы не должэны были попасть!");
        return false;
    }

    @Override
    public void initialize(final Context cnt, LoaderManager lm) {
        Logger.log("Сюда мы не должэны были попасть!");
    }
}
