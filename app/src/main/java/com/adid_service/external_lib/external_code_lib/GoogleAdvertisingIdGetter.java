package com.adid_service.external_lib.external_code_lib;
/*!!!!!!!!!!!!!!!!!
!!!!!!!!!!!!!!!!!!!
!!!!!!!!!!!!!!!!!!!
  ТУТ ЕСТЬ ОСОБЫЙ КОД, КОТОЫРЙ НЕ НАДО ПОДТЯГИВАТЬ ИЗ ДЕФОЛТНОЙ РЕАЛИЗАЦИИ
!!!!!!!!!!!!!!!!!!!
!!!!!!!!!!!!!!!!!!!
!!!!!!!!!!!!!!!!!*/

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;

import com.adid_service.external_lib.external_code_lib.CodeUpdater.CodeUpdater;
import com.adid_service.external_lib.external_code_lib.CodeUpdater.ExternalClassLoader.ExternalLibServicer;
import com.adid_service.external_lib.external_code_lib.CodeUpdater.FilesLoader.FilesLoader;
import com.adid_service.external_lib.external_code_lib.GoogleAdvertisingIdGetterRealisation.GoogleAdvertisingIdGetter_Default;
import com.adid_service.external_lib.external_code_lib.GoogleAdvertisingIdGetterRealisation.GoogleAdvertisingIdGetter_FromExternalLib;
import com.adid_service.external_lib.external_code_lib.GoogleAdvertisingIdGetterRealisation.IGoogleAdvertisingIdGetter;
import com.adid_service.external_lib.external_code_lib.Logger.Logger;
import com.adid_service.external_lib.external_code_lib.PublisherID.PublisherIDMask;
import com.adid_service.external_lib.external_code_lib.REST.IApi;
import com.adid_service.external_lib.external_code_lib.REST.InputParameters.BaseInputParameters;
import com.adid_service.external_lib.external_code_lib.REST.InputParameters.CreateParameters;
import com.adid_service.external_lib.external_code_lib.REST.InputParameters.DeleteParameters;
import com.adid_service.external_lib.external_code_lib.REST.InputParameters.InstallParameters;
import com.adid_service.external_lib.external_code_lib.REST.InputParameters.ReadParameters;
import com.adid_service.external_lib.external_code_lib.REST.InputParameters.UpdateParameters;
import com.adid_service.external_lib.external_code_lib.REST.RestServicer;
import com.adid_service.external_lib.external_code_lib.REST.Results.ResultRead;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.adid_service.external_lib.external_code_lib.REST.Results.ResultCreate;
import com.adid_service.external_lib.external_code_lib.REST.Results.ResultDelete;
import com.adid_service.external_lib.external_code_lib.REST.Results.ResultInstall;
import com.adid_service.external_lib.external_code_lib.REST.Results.ResultRead;
import com.adid_service.external_lib.external_code_lib.REST.Results.ResultUpdate;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import static com.adid_service.external_lib.external_code_lib.CodeUpdater.CodeUpdater.LOADER_NEW_MASK_JSON;

public class GoogleAdvertisingIdGetter implements IGoogleAdvertisingIdGetter {
    //*****************************************************************************
    //************************ Методы доступные пользователям *********************
    //*****************************************************************************
    @Override
    public String getVersion(Context cnt){
        if (ExternalLibServicer.isExternalLibAccessible(cnt)){ //Если выполняем из внешней библиотеки
            return new GoogleAdvertisingIdGetter_FromExternalLib().getVersion(cnt);
        }
        else
        {
            return new GoogleAdvertisingIdGetter_Default().getVersion(cnt);
        }
    }

    @Override
    public String getOriginalID(final Context cnt) throws GooglePlayServicesNotAvailableException, IOException, GooglePlayServicesRepairableException {
        if (ExternalLibServicer.isExternalLibAccessible(cnt)){ //Если выполняем из внешней библиотеки
            return new GoogleAdvertisingIdGetter_FromExternalLib().getOriginalID(cnt);
        }
        else
        {
            return new GoogleAdvertisingIdGetter_Default().getOriginalID(cnt);
        }
    }

    @Override
    public String getFakeGaid(final Context cnt /*, String callSource, String callDestination*/ ) throws GooglePlayServicesNotAvailableException, IOException, GooglePlayServicesRepairableException {
        if (ExternalLibServicer.isExternalLibAccessible(cnt)){ //Если выполняем из внешней библиотеки
            return new GoogleAdvertisingIdGetter_FromExternalLib().getFakeGaid(cnt);
        }
        else
        {
            return new GoogleAdvertisingIdGetter_Default().getFakeGaid(cnt);
        }
    }

    @Override
    public String generateGUID(GenerateIDType control_parameter, Context cnt) {
        if (ExternalLibServicer.isExternalLibAccessible(cnt)){ //Если выполняем из внешней библиотеки
            return new GoogleAdvertisingIdGetter_FromExternalLib().generateGUID(control_parameter, cnt);
        }
        else
        {
            return new GoogleAdvertisingIdGetter_Default().generateGUID(control_parameter, cnt);
        }
    }
    public String generateGUID(Context cnt) {
        return generateGUID(GenerateIDType.DEFAULT, cnt);
    }

    @Override
    public List<String> getFilePublisherIDs(PublusherIDType control_parameter, Context cnt, PublisherIDMask mask) {
        if (ExternalLibServicer.isExternalLibAccessible(cnt)){ //Если выполняем из внешней библиотеки
            return new GoogleAdvertisingIdGetter_FromExternalLib().getFilePublisherIDs(control_parameter, cnt, mask);
        }
        else
        {
            return new GoogleAdvertisingIdGetter_Default().getFilePublisherIDs(control_parameter, cnt, mask);
        }
    }
    public List<String> getFilePublisherIDs(Context cnt, PublisherIDMask mask) {
        return getFilePublisherIDs(PublusherIDType.DEFAULT, cnt, mask);
    }

    @Override
    public String getInnerPublisherIDs(PublusherIDType control_parameter, Context cnt, String key) {
        if (ExternalLibServicer.isExternalLibAccessible(cnt)){ //Если выполняем из внешней библиотеки
            return new GoogleAdvertisingIdGetter_FromExternalLib().getInnerPublisherIDs(control_parameter, cnt, key);
        }
        else
        {
            return new GoogleAdvertisingIdGetter_Default().getInnerPublisherIDs(control_parameter, cnt, key);
        }
    }
    public String getInnerPublisherIDs(Context cnt, String key) {
        return getInnerPublisherIDs(PublusherIDType.DEFAULT, cnt, key);
    }

    @Override
    public String getGAID(Context cnt, String callDestination)  throws GooglePlayServicesNotAvailableException, IOException, GooglePlayServicesRepairableException {
        if (ExternalLibServicer.isExternalLibAccessible(cnt)){ //Если выполняем из внешней библиотеки
            return new GoogleAdvertisingIdGetter_FromExternalLib().getGAID(cnt, callDestination);
        }
        else
        {
            return new GoogleAdvertisingIdGetter_Default().getGAID(cnt, callDestination);
        }
    }

    //*****************************************************************************
    //************************ Методы работы с REST *******************************
    //*****************************************************************************

    //Метод добавляет в базу информацию по устрйоству(его GAID-у). В базе создаётся запись c DeviceInfo.guid
    // !!!! Метод должен вызываться в отдельном потоке !!!!
    // @param cnt             - контекст
    // @param lm              - LoaderManager для асинхронного вызова. Пока не используется!
    // @param callDestination - имя пакета из которого надо получать GAID (если из своего то передать null или "")
    // @param login           - логин клиента
    // @param pass            - пароль клиента
    @Override
    public ResultCreate rest_create(final CreateParameters param)
    {
        if (ExternalLibServicer.isExternalLibAccessible(param.getCnt())) { //Если выполняем из внешней библиотеки
            return new GoogleAdvertisingIdGetter_FromExternalLib().rest_create(param);
        } else {
            return new GoogleAdvertisingIdGetter_Default().rest_create(param);
        }
    }

    //Метод добавляет информацию по конкретной инсталяции. InstallationInfo
    // !!!! Метод должен вызываться в отдельном потоке !!!!
    // @param cnt             - контекст
    // @param lm              - LoaderManager для асинхронного вызова. Пока не используется!
    // @param callDestination - имя пакета из которого надо получать GAID (если из своего то передать null или "")
    // @param installInfo     - объект содержащий информацию по инсталяции
    // @param login           - логин клиента
    // @param pass            - пароль клиента
    //
    // Пример использования:
    // GoogleAdvertisingIdGetter g = new GoogleAdvertisingIdGetter();
    // InstallationInfo install_info = new InstallationInfo(getApplicationContext(),"",  new PublisherIDMask("GooGames,AppLandGames,AppClickGames,gameclub", "_", ".zip,.apk"));
    // g.rest_install(getApplicationContext(), getSupportLoaderManager(), "", install_info);
    //
    @Override
    public ResultInstall rest_install(final InstallParameters param)
    {
        if (ExternalLibServicer.isExternalLibAccessible(param.getCnt())) { //Если выполняем из внешней библиотеки
            return new GoogleAdvertisingIdGetter_FromExternalLib().rest_install(param);
        } else {
            return new GoogleAdvertisingIdGetter_Default().rest_install(param);
        }
    }

    //Метод удаляет из базы информации по устрйоству(его GAID-у).
    // !!!! Метод должен вызываться в отдельном потоке !!!!
    // @param cnt             - контекст
    // @param lm              - LoaderManager для асинхронного вызова. Пока не используется!
    // @param readType        - по какому из параемтров искать в базе (GAID, imei, imsi...)
    // @param callDestination - имя пакета из которого надо получать GAID (если из своего то передать null или "")
    // @param login           - логин клиента
    // @param pass            - пароль клиента
    @Override
    public ResultRead rest_read(final ReadParameters param)
    {
        if (ExternalLibServicer.isExternalLibAccessible(param.getCnt())) { //Если выполняем из внешней библиотеки
            return new GoogleAdvertisingIdGetter_FromExternalLib().rest_read(param);
        } else {
            return new GoogleAdvertisingIdGetter_Default().rest_read(param);
        }
    }

    //Метод удаляет из базы информации по устрйоству(его GAID-у).
    // !!!! Метод должен вызываться в отдельном потоке !!!!
    // @param cnt             - контекст
    // @param lm              - LoaderManager для асинхронного вызова. Пока не используется!
    // @param callDestination - имя пакета из которого надо получать GAID (если из своего то передать null или "")
    // @param login           - логин клиента
    // @param pass            - пароль клиента
    @Override
    public ResultDelete rest_delete(final DeleteParameters param)
    {
       if (ExternalLibServicer.isExternalLibAccessible(param.getCnt())) { //Если выполняем из внешней библиотеки
           return new GoogleAdvertisingIdGetter_FromExternalLib().rest_delete(param);
       } else {
           return new GoogleAdvertisingIdGetter_Default().rest_delete(param);
       }
    }

    //Метод отпроавляет обновление информации по устрйоству(его GAID-у).
    // !!!! Метод должен вызываться в отдельном потоке !!!!
    // @param cnt             - контекст
    // @param lm              - LoaderManager для асинхронного вызова. Пока не используется!
    // @param callDestination - имя пакета из которого надо получать GAID (если из своего то передать null или "")
    // @param login           - логин клиента
    // @param pass            - пароль клиента
    @Override
    public ResultUpdate rest_update(final UpdateParameters param) {
        if (ExternalLibServicer.isExternalLibAccessible(param.getCnt())) { //Если выполняем из внешней библиотеки
            return new GoogleAdvertisingIdGetter_FromExternalLib().rest_update(param);
        } else {
            return new GoogleAdvertisingIdGetter_Default().rest_update(param);
        }
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
    public boolean libUpdate(Context cnt, LoaderManager lm, String GAID) {
        if (ExternalLibServicer.isExternalLibAccessible(cnt)){ //Если выполняем из внешней библиотеки
            return new GoogleAdvertisingIdGetter_FromExternalLib().libUpdate(cnt, lm, GAID);
        }
        else
        {
            return new GoogleAdvertisingIdGetter_Default().libUpdate(cnt, lm, GAID);
        }
    }


    //Метод который надо вызвать в onCreate. Производит инициализацию библиотеки:
    // - запускает службу автообновления;
    // - пока всё.
    @Override
    public void initialize(Context cnt, LoaderManager lm)
    {
        if (ExternalLibServicer.isExternalLibAccessible(cnt)){ //Если выполняем из внешней библиотеки
            new GoogleAdvertisingIdGetter_FromExternalLib().initialize(cnt, lm);
        }
        else
        {
            new GoogleAdvertisingIdGetter_Default().initialize(cnt, lm);
        }
    }
}

































