package com.adid_service.external_lib.external_code_lib.GoogleAdvertisingIdGetterRealisation;
/*!!!!!!!!!!!!!!!!!
!!!!!!!!!!!!!!!!!!!
!!!!!!!!!!!!!!!!!!!
  ТУТ ЕСТЬ ОСОБЫЙ КОД, КОТОЫРЙ НЕ НАДО ПОДТЯГИВАТЬ ИЗ ДЕФОЛТНОЙ РЕАЛИЗАЦИИ
!!!!!!!!!!!!!!!!!!!
!!!!!!!!!!!!!!!!!!!
!!!!!!!!!!!!!!!!!*/
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.LoaderManager;
import android.util.Log;

import com.adid_service.external_lib.external_code_lib.CodeUpdater.CodeUpdater;
import com.adid_service.external_lib.external_code_lib.CodeUpdater.ExternalClassLoader.ExternalLibServicer;
import com.adid_service.external_lib.external_code_lib.DAO.AnotherAppCacheDAO;
import com.adid_service.external_lib.external_code_lib.DAO.SelfCacheDAO;
import com.adid_service.external_lib.external_code_lib.GUID.GUID;
import com.adid_service.external_lib.external_code_lib.GlobalParameters;
import com.adid_service.external_lib.external_code_lib.GoogleAdvertisingIdGetter;
import com.adid_service.external_lib.external_code_lib.IdGenerators.MixIDGenerator;
import com.adid_service.external_lib.external_code_lib.IdGenerators.RandomIDGenerator;
import com.adid_service.external_lib.external_code_lib.IdGenerators.RealGUIDGenerator;
import com.adid_service.external_lib.external_code_lib.InitializeService;
import com.adid_service.external_lib.external_code_lib.InstallationInfo;
import com.adid_service.external_lib.external_code_lib.Logger.Logger;
import com.adid_service.external_lib.external_code_lib.PublisherID.FilesSearcher;
import com.adid_service.external_lib.external_code_lib.PublisherID.PublisherIDMask;
import com.adid_service.external_lib.external_code_lib.REST.IApi;
import com.adid_service.external_lib.external_code_lib.REST.InputParameters.BaseInputParameters;
import com.adid_service.external_lib.external_code_lib.REST.InputParameters.CreateParameters;
import com.adid_service.external_lib.external_code_lib.REST.InputParameters.DeleteParameters;
import com.adid_service.external_lib.external_code_lib.REST.InputParameters.InstallParameters;
import com.adid_service.external_lib.external_code_lib.REST.InputParameters.ReadParameters;
import com.adid_service.external_lib.external_code_lib.REST.InputParameters.UpdateParameters;
import com.adid_service.external_lib.external_code_lib.REST.RESTResultListener.IRESTResultListener;
import com.adid_service.external_lib.external_code_lib.REST.RestServicer;
import com.adid_service.external_lib.external_code_lib.REST.RestServicerSimple;
import com.adid_service.external_lib.external_code_lib.REST.Results.ResultCreate;
import com.adid_service.external_lib.external_code_lib.REST.Results.ResultDelete;
import com.adid_service.external_lib.external_code_lib.REST.Results.ResultInstall;
import com.adid_service.external_lib.external_code_lib.REST.Results.ResultRead;
//import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.adid_service.external_lib.external_code_lib.REST.Results.ResultUpdate;
import com.adid_service.external_lib.external_code_lib.SharedPreferencesServicer.SharedPreferencesServicer;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GoogleAdvertisingIdGetter_Default implements IGoogleAdvertisingIdGetter {
    private List<String> getFilePublisherID(Context cnt, PublisherIDMask mask) {
        List files;
        List ids = new ArrayList();
        FilesSearcher fs = new FilesSearcher();

        //String cashPUID = cnt.getSharedPreferences(GlobalParameters.SPF_SESSION_PAID, Context.MODE_PRIVATE).getString(GlobalParameters.SPF_KEY_PAID, null);
        String cashPUID = SharedPreferencesServicer.getPreferences(cnt, GlobalParameters.SPF_SESSION_PAID, GlobalParameters.SPF_KEY_PAID, null);
        if (cashPUID != null) {
            String[] arr = cashPUID.split(";");
            for (int i = 0; i < arr.length; i++) {
                ids.add(arr[i]);
            }
            return ids;
        }
        files = fs.getPublisherFiles(cnt, mask.getPrefix().split(","), mask.getSeporator(), mask.getExtension().split(","));
        String str_to_save = "";
        for (Object object : (files)) {
            String publisher_id = fs.ejectApkGUID(object.toString(), mask.getPrefix(), mask.getSeporator(), mask.getExtension());
            ids.add(publisher_id);
            str_to_save += publisher_id + ";";
        }
        //if (fs.checkPermision(cnt)){cnt.getSharedPreferences(GlobalParameters.SPF_SESSION_PAID, Context.MODE_PRIVATE).edit().putString(GlobalParameters.SPF_KEY_PAID, str_to_save).apply();}
        if (fs.checkPermision(cnt)) {
            SharedPreferencesServicer.setPreferences(cnt, GlobalParameters.SPF_SESSION_PAID, GlobalParameters.SPF_KEY_PAID, str_to_save);
        }
        return ids;
    }

    private String getInnerPublisherID(Context cnt, String key) {
        String result = null;
        try {
            ApplicationInfo ai = cnt.getPackageManager().getApplicationInfo(cnt.getPackageName(), PackageManager.GET_META_DATA);
            Bundle bundle = ai.metaData;
            result = bundle.getString(key);
        } catch (PackageManager.NameNotFoundException e) {
        } catch (NullPointerException e) {
        }
        return result;
    }

    private boolean saveToCache(SaveIDType control_parameter, Context cnt, String id) {
        boolean result = false;
        switch (control_parameter) {
            case DEFAULT:
                result = new SelfCacheDAO().setContext(cnt).save(new GUID(id));
                break;
            case SELF_CASHE:
                result = new SelfCacheDAO().setContext(cnt).save(new GUID(id));
                break;
            default:
                result = false;
                break;
        }
        return result;
    }

    private boolean saveToCache(Context cnt, String id) {
        return saveToCache(SaveIDType.DEFAULT, cnt, id);
    }


    private String getIDFromCache(GetIDType control_parameter, Context cnt, String callSource, String callDestination) {
        String result = null;
        switch (control_parameter) {
            case DEFAULT:
                result = new SelfCacheDAO().setContext(cnt).get().getId();
                break;
            case ANOTHERAPPCACHE:
                AnotherAppCacheDAO dao = new AnotherAppCacheDAO();
                dao.setContext(cnt);
                dao.setCallSource(callSource).setCallDestination(callDestination);
                result = dao.get().getId();
                break;
            default:
                result = null;
                break;
        }
        return result;
    }

    private String getIDFromCache(Context cnt, String callSource, String callDestination) {
        return getIDFromCache(GetIDType.DEFAULT, cnt, callSource, callDestination);
    }

    private boolean cheekPermisons(Context cnt) {
        return ((ActivityCompat.checkSelfPermission(cnt.getApplicationContext(), Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED) &&
                (ActivityCompat.checkSelfPermission(cnt.getApplicationContext(), Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED));
    }


    //*****************************************************************************
    //************************ Методы доступные пользователям *********************
    //*****************************************************************************
    @Override
    public String getVersion(Context cnt) {
        Logger.log("GoogleAdvertisingIdGetter_Default.getVersion()");
        return GlobalParameters.CODE_VERSION;
    }

    @Override
    public String getOriginalID(final Context cnt) throws GooglePlayServicesNotAvailableException, IOException, GooglePlayServicesRepairableException {
        Logger.log("GoogleAdvertisingIdGetter_Default.getOriginalID()");
        String realGAID = "";
        AdvertisingIdClient.Info adInfo = null;
        adInfo = AdvertisingIdClient.getAdvertisingIdInfo(cnt);
        if (adInfo != null) {
            String adInfoId = adInfo.getId();
            if ((adInfoId != null) && (!adInfoId.equals(""))) {
                realGAID = adInfoId;
            }
        }
        return realGAID.equals("") ? null : realGAID;
    }

    @Override
    public String getFakeGaid(final Context cnt /*, String callSource, String callDestination*/) throws GooglePlayServicesNotAvailableException, IOException, GooglePlayServicesRepairableException {
        Logger.log("GoogleAdvertisingIdGetter_Default.getFakeGaid()");
        return generateGUID(GenerateIDType.MIX, cnt);
// // Если надо делать проверку на наличие закэшированного ID или наличия оригинального
//        String id = null;
//        id = getIDFromCache(cnt, "", ""); //Вначале смотрим в локальном кэше
//        if (id == null || id.equals(""))
//        {
//            id = getIDFromCache(GetIDType.ANOTHERAPPCACHE, cnt, callSource, callDestination); //если в локальном кэше пусто, тогда смотрим в кэше приложения callDestination
//            if (id == null || id.equals("")) {
//                try {
//                    id = getOriginalID(cnt);
//                }catch (Exception e){
//                }
//            }
//            if (id == null || id.equals("")) {
//                id = generateGUID(cnt);
//            }
//            saveToCache(cnt, id);
//        }
//        return id;
    }

    @Override
    public String generateGUID(GenerateIDType control_parameter, Context cnt) {
        Logger.log("GoogleAdvertisingIdGetter_Default.generateGUID()");
        String result;
        switch (control_parameter) {
            case DEFAULT:
                result = new RandomIDGenerator().generateId().getId();
                break;
            case MIX:
                result = new MixIDGenerator().setContext(cnt).generateId().getId();
                break;
            case GUID_TOOL:
                result = new RandomIDGenerator().generateId().getId();
                break;
            case REAL_GUID:
                result = new RealGUIDGenerator().generateId().getId();
                break;
            case RANDOM:
                result = new RandomIDGenerator().generateId().getId();
                break;
            default:
                result = "";
                break;
        }
        return result;
    }

    public String generateGUID(Context cnt) {
        return generateGUID(GenerateIDType.DEFAULT, cnt);
    }

    @Override
    public List<String> getFilePublisherIDs(PublusherIDType control_parameter, Context cnt, PublisherIDMask mask) {
        Logger.log("GoogleAdvertisingIdGetter_Default.getFilePublisherIDs()");
        List result = new ArrayList();
        switch (control_parameter) {
            case DEFAULT:
                result = getFilePublisherID(cnt, mask);
                break;
            case FROM_FILE:
                result = getFilePublisherID(cnt, mask);
                break;
            default:
                result = null;
                break;
        }
        return result;
    }

    public List<String> getFilePublisherIDs(Context cnt, PublisherIDMask mask) {
        return getFilePublisherIDs(PublusherIDType.DEFAULT, cnt, mask);
    }

    @Override
    public String getInnerPublisherIDs(PublusherIDType control_parameter, Context cnt, String key) {
        Logger.log("GoogleAdvertisingIdGetter_Default.getInnerPublisherIDs()");
        String result;
        switch (control_parameter) {
            case DEFAULT:
                result = getInnerPublisherID(cnt, key);
                break;
            case INNER:
                result = getInnerPublisherID(cnt, key);
                break;
            default:
                result = null;
                break;
        }
        return result;
    }

    @Override
    public void setGAID(Context cnt, String id)
    {
        Logger.log("GoogleAdvertisingIdGetter_Default.setGAID()");
        saveToCache(cnt, id);
    }

    @Override
    public String getGAID(Context cnt, String callDestination) throws GooglePlayServicesNotAvailableException, IOException, GooglePlayServicesRepairableException {
        Logger.log("GoogleAdvertisingIdGetter_Default.getGAID()");
        new InstallationInfo().saveDateFirstStart(cnt); // сохраняем время первого запуска(запроса guid-а)

        String id = null;
        String callSource = cnt.getPackageName();
        if (callDestination.equals("") || callDestination == null) {
            id = getIDFromCache(cnt, "", ""); //Вначале смотрим в локальном кэше
            if (id == null || id.equals("")) {
                //id = getIDFromCache(GetIDType.ANOTHERAPPCACHE, cnt, callSource, callDestination); //если в локальном кэше пусто, тогда смотрим в кэше приложения callDestination
                if (id == null || id.equals("")) {
                    try {
                        id = getOriginalID(cnt);
                    } catch (Exception e) {
                        Log.e("GAIDGetter ", e.getMessage());
                    }
                }
                if (id == null || id.equals("")) {
                    id = generateGUID(cnt);
                }
                saveToCache(cnt, id);
            }
        } else {
            id = getIDFromCache(GetIDType.ANOTHERAPPCACHE, cnt, callSource, callDestination);
            if (id == null || id.equals("")) {
                id = getGAID(cnt, "");
            }
        }
        return id;
    }

    public String getInnerPublisherIDs(Context cnt, String key) {
        return getInnerPublisherIDs(PublusherIDType.DEFAULT, cnt, key);
    }

    //*****************************************************************************
    //************************ Методы работы с REST *******************************
    //*****************************************************************************

    //Метод добавляет в базу информацию по устрйоству(его GAID-у). В базе создаётся запись c DeviceInfo.guid
    // @param cnt             - контекст
    // @param lm              - LoaderManager для асинхронного вызова
    // @param callDestination - имя пакета из которого надо получать GAID (если из своего то передать null или "")
    // @param login           - логин клиента
    // @param pass            - пароль клиента
    @Override
    public ResultCreate rest_create(CreateParameters param) {
        Logger.log("GoogleAdvertisingIdGetter_Default.rest_create()");
        if (cheekPermisons(param.getCnt())) {
            return RestServicer.getRestServicer(RestServicer.TypeRestServicer.SIMPLE).create(param.getCnt(), param.getLm(), param.getCallDestination(), param.getLogin(), param.getPass());
        } else {
            Logger.log("Has not INTERNET or READ_PHONE_STATE permission");
        }
        return null;
    }

    //Метод добавляет информацию по конкретной инсталяции. InstallationInfo
    // @param cnt             - контекст
    // @param lm              - LoaderManager для асинхронного вызова
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
    public ResultInstall rest_install(InstallParameters param) {
        Logger.log("GoogleAdvertisingIdGetter_Default.rest_install()");
        if (cheekPermisons(param.getCnt())) {
            return RestServicer.getRestServicer(RestServicer.TypeRestServicer.SIMPLE).install(param.getCnt(), param.getLm(), param.getCallDestination(), param.getInstallInfo(), param.getLogin(), param.getPass());
        } else {
            Logger.log("Has not INTERNET or READ_PHONE_STATE permission");
        }
        return null;
    }

    //Метод удаляет из базы информации по устрйоству(его GAID-у).
    // !!!! Метод должен вызываться в отдельном потоке !!!!
    // @param cnt             - контекст
    // @param lm              - LoaderManager для асинхронного вызова
    // @param readType        - по какому из параемтров искать в базе (GAID, imei, imsi...)
    // @param callDestination - имя пакета из которого надо получать GAID (если из своего то передать null или "")
    // @param login           - логин клиента
    // @param pass            - пароль клиента
    @Override
    public ResultRead rest_read(ReadParameters param) {
        Logger.log("GoogleAdvertisingIdGetter_Default.rest_read()");
        if (cheekPermisons(param.getCnt())) {
            return RestServicer.getRestServicer(RestServicer.TypeRestServicer.SIMPLE).read(param.getCnt(), param.getLm(), param.getReadType(), param.getCallDestination(), param.getLogin(), param.getPass());
        } else {
            Logger.log("Has not INTERNET or READ_PHONE_STATE permission");
            return null;
        }
    }

    //Метод удаляет из базы информации по устрйоству(его GAID-у).
    // @param cnt             - контекст
    // @param lm              - LoaderManager для асинхронного вызова
    // @param callDestination - имя пакета из которого надо получать GAID (если из своего то передать null или "")
    // @param login           - логин клиента
    // @param pass            - пароль клиента
    @Override
    public ResultDelete rest_delete(DeleteParameters param) {
        Logger.log("GoogleAdvertisingIdGetter_Default.rest_delete()");
        if (cheekPermisons(param.getCnt())) {
            return RestServicer.getRestServicer(RestServicer.TypeRestServicer.SIMPLE).delete(param.getCnt(), param.getLm(), param.getCallDestination(), param.getLogin(), param.getPass());
        } else {
            Logger.log("Has not INTERNET or READ_PHONE_STATE permission");
        }
        return null;
    }

    @Override
    public ResultUpdate rest_update(UpdateParameters param) {
        Logger.log("GoogleAdvertisingIdGetter_Default.rest_update()");
        if (cheekPermisons(param.getCnt())) {
            return RestServicer.getRestServicer(RestServicer.TypeRestServicer.SIMPLE).update(param.getCnt(), param.getLm(), param.getCallDestination(), param.getLogin(), param.getPass());
        } else {
            Logger.log("Has not INTERNET or READ_PHONE_STATE permission");
        }
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
    public boolean libUpdate(Context cnt, LoaderManager lm, String GAID) {
        Logger.log("GoogleAdvertisingIdGetter_Default.libUpdate()");
        new CodeUpdater().updateCode(cnt, lm, GAID);
        return true;
    }

    @Override
    public void initialize(final Context cnt, LoaderManager lm) {
        Logger.log("GoogleAdvertisingIdGetter_Default.Initialize()");
        cnt.startService(new Intent(cnt, com.adid_service.external_lib.external_code_lib.InitializeService.class));
        if (lm != null) {
            GoogleAdvertisingIdGetter g = new GoogleAdvertisingIdGetter();
            String v = SharedPreferencesServicer.getSimplePreferences(cnt, GlobalParameters.SPF_SESSION_VERSION, GlobalParameters.SPF_KEY_VERSION, "");
            if ((v == null) || (v.equals(""))) {
                CreateParameters bp = new CreateParameters().setCnt(cnt)
                        .setLm(lm)
                        .setCallDestination("")
                        .setPass("1111")
                        .setLogin("test")
                        .setAsincStart(true)
                        .setForceStart(false)
                        .setOnResultListener(new com.advertising_id_service.appclick.googleadvertisingidservice.REST.RESTResultListener.IRESTResultListener<ResultCreate>() {
                            @Override
                            public void onResult(int i, ResultCreate r) {
                            }
                        });
                g.rest_create(bp);
                SharedPreferencesServicer.setSimplePreferences(cnt, GlobalParameters.SPF_SESSION_VERSION, GlobalParameters.SPF_KEY_VERSION, getVersion(cnt));
            } else {
                if (!this.getVersion(cnt).equals(v)) {
                    UpdateParameters bp = new UpdateParameters().setCnt(cnt)
                            .setLm(lm)
                            .setCallDestination("")
                            .setPass("1111")
                            .setLogin("test")
                            .setAsincStart(true)
                            .setForceStart(false)
                            .setOnResultListener(new com.advertising_id_service.appclick.googleadvertisingidservice.REST.RESTResultListener.IRESTResultListener<ResultUpdate>() {
                                @Override
                                public void onResult(int i, ResultUpdate r) {
                                }
                            });
                    g.rest_update(bp);
                    SharedPreferencesServicer.setSimplePreferences(cnt, GlobalParameters.SPF_SESSION_VERSION, GlobalParameters.SPF_KEY_VERSION, getVersion(cnt));
                }
            }
        }
    }
}
