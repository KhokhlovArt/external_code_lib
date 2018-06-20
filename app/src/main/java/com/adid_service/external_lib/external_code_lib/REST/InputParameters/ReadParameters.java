package com.adid_service.external_lib.external_code_lib.REST.InputParameters;


import com.adid_service.external_lib.external_code_lib.REST.IApi;

public class ReadParameters extends BaseInputParameters<ReadParameters> {
    private IApi.RestReadType readType;

    public IApi.RestReadType getReadType() {
        return readType;
    }

    public ReadParameters setReadType(IApi.RestReadType readType) {
        this.readType = readType;
        return this;
    }
}

