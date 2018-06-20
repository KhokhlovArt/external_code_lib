package com.adid_service.external_lib.external_code_lib.REST.RESTResultListener;

public interface IRESTResultListener <T> {
    void onResult(int resCode, T res);
}