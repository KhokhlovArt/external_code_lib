package com.adid_service.external_lib.external_code_lib.REST.InputParameters;


import com.adid_service.external_lib.external_code_lib.InstallationInfo;

public class InstallParameters extends  BaseInputParameters<InstallParameters> {
    private InstallationInfo installInfo;

    public InstallationInfo getInstallInfo() {
        return installInfo;
    }

    public InstallParameters setInstallInfo(InstallationInfo installInfo) {
        this.installInfo = installInfo;
        return this;
    }
}

