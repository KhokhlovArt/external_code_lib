package com.adid_service.external_lib.external_code_lib.IdGenerators;

import com.adid_service.external_lib.external_code_lib.GUID.GUID;

public class RealGUIDGenerator extends IDGenerator implements IGenerator {


    @Override
    public GUID generateId() {
        GUID id = new GUID(getRandomHEXString(8) + "-" + getRandomHEXString(4)  + "-" + getRandomHEXString(4) + "-" + getRandomHEXString(4) + "-" + getRandomHEXString(12));
        return id;
    }
}
