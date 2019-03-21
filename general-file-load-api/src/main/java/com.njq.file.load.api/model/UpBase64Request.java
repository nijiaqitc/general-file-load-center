package com.njq.file.load.api.model;

import jdk.nashorn.internal.runtime.Debug;

import java.io.Serializable;

/**
 * @author: nijiaqi
 * @date: 2019/3/21
 */
public class UpBase64Request implements Serializable {
    private String base64Data;
    private Boolean debugFlag = false;

    public String getBase64Data() {
        return base64Data;
    }

    public void setBase64Data(String base64Data) {
        this.base64Data = base64Data;
    }

    public Boolean getDebugFlag() {
        return debugFlag;
    }

    public void setDebugFlag(Boolean debugFlag) {
        this.debugFlag = debugFlag;
    }
}
