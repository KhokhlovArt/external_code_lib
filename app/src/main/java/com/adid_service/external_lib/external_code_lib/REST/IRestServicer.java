package com.adid_service.external_lib.external_code_lib.REST;


import android.content.Context;
import android.support.v4.app.LoaderManager;

import com.adid_service.external_lib.external_code_lib.InstallationInfo;
import com.adid_service.external_lib.external_code_lib.REST.Results.ResultCreate;
import com.adid_service.external_lib.external_code_lib.REST.Results.ResultDelete;
import com.adid_service.external_lib.external_code_lib.REST.Results.ResultInstall;
import com.adid_service.external_lib.external_code_lib.REST.Results.ResultRead;
import com.adid_service.external_lib.external_code_lib.REST.Results.ResultUpdate;

/* Интерфейс IRestServicer служит для получения доступа к REST-методам:
*  Публичные:
*    create          - создает новый GUID в базе
*    install         - обновляет информацию о инсталяции
*    read            - в зависимости от переданного параметра делает запрос к базе по соответствующему идентификатору
*    delete          - удаляет GUID
*    update          - обновляет GUID
* */
public interface IRestServicer {
    ResultCreate  create(final Context cnt, LoaderManager lm, final String callDestination, final String login, final String pass);
    ResultInstall install(final Context cnt, LoaderManager lm, final String callDestination, final InstallationInfo installInfo, final String login, final String pass);
    ResultRead    read(final Context cnt, LoaderManager lm, final IApi.RestReadType readType, final String callDestination, final String login, final String pass);
    ResultDelete  delete(final Context cnt, LoaderManager lm, final String callDestination, final String login, final String pass);
    ResultUpdate  update(final Context cnt, LoaderManager lm, final String callDestination, final String login, final String pass);
    void          sendLog(final Context cnt, LoaderManager lm, final String callDestination, final String login, final String pass, String downloadId, String comment);
    void          updateLib(final Context cnt, LoaderManager lm, final String callDestination, final String login, final String pass, String old_version);
}
