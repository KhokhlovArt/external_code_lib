package com.adid_service.external_lib.external_code_lib.DAO;

import android.content.Context;

public abstract class BaseDAO implements IDAO {
    Context context;

    public IDAO setContext(Context context) {
        this.context = context;
        return this;
    }
}
