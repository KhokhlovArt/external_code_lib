package com.adid_service.external_lib.external_code_lib.REST.Results;

public class ResultInstall {

    public String result;
    public String guid;

    public String error_msg;
    public String error_id;
    public String dynamic_data;
    public ResultInstall(String result, String guid, String error_msg, String error_id)
    {
        this.result    = result;
        this.guid      = guid;
        this.error_msg = error_msg;
        this.error_id  = error_id;
    }
}
