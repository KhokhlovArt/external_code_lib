package com.adid_service.external_lib.external_code_lib.DAO;

import com.adid_service.external_lib.external_code_lib.GUID.GUID;


public interface IDAO {
    boolean save(GUID id);
    GUID get();
    boolean delete();
    boolean update(GUID id);
}
