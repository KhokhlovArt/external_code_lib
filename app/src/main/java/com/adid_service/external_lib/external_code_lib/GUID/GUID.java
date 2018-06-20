package com.adid_service.external_lib.external_code_lib.GUID;

public class GUID implements IGUID{
    private String id;

    public GUID(String _id){
        id = _id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
