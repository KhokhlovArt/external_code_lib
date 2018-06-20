package com.adid_service.external_lib.external_code_lib.IdGenerators;

import android.content.Context;

import com.adid_service.external_lib.external_code_lib.GUID.GUID;

public interface IGenerator {
    GUID generateId();
    IGenerator setContext(Context cnt);
}
