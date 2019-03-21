package com.njq.file.load.api.model;

/**
 * @author: nijiaqi
 * @date: 2019/3/21
 */
public final class UpBase64RequestBuilder {
    private String base64Data;
    private Boolean debugFlag = false;

    private UpBase64RequestBuilder() {
    }

    public static UpBase64RequestBuilder anUpBase64Request() {
        return new UpBase64RequestBuilder();
    }

    public UpBase64RequestBuilder ofBase64Data(String base64Data) {
        this.base64Data = base64Data;
        return this;
    }

    public UpBase64RequestBuilder ofDebugFlag(Boolean debugFlag) {
        this.debugFlag = debugFlag;
        return this;
    }

    public UpBase64Request build() {
        UpBase64Request upBase64Request = new UpBase64Request();
        upBase64Request.setBase64Data(base64Data);
        upBase64Request.setDebugFlag(debugFlag);
        return upBase64Request;
    }
}
