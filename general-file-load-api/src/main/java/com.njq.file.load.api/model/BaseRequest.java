package com.njq.file.load.api.model;

import java.io.Serializable;

/**
 * @author: nijiaqi
 * @date: 2019/3/26
 */
public class BaseRequest implements Serializable {
    private Boolean debugFlag = false;
    private String cookieStr;

    public Boolean getDebugFlag() {
        return debugFlag;
    }

    public void setDebugFlag(Boolean debugFlag) {
        this.debugFlag = debugFlag;
    }

    public String getCookieStr() {
        return cookieStr;
    }

    public void setCookieStr(String cookieStr) {
        this.cookieStr = cookieStr;
    }
}
