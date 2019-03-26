package com.njq.file.load.api.model;

import org.apache.commons.fileupload.FileItem;

/**
 * @author: nijiaqi
 * @date: 2019/3/26
 */
public final class UpBannerRequestBuilder {
    private Boolean debugFlag = false;
    private String cookieStr;
    private FileItem item;

    private UpBannerRequestBuilder() {
    }

    public static UpBannerRequestBuilder anUpBannerRequest() {
        return new UpBannerRequestBuilder();
    }

    public UpBannerRequestBuilder ofDebugFlag(Boolean debugFlag) {
        this.debugFlag = debugFlag;
        return this;
    }

    public UpBannerRequestBuilder ofCookieStr(String cookieStr) {
        this.cookieStr = cookieStr;
        return this;
    }

    public UpBannerRequestBuilder ofItem(FileItem item) {
        this.item = item;
        return this;
    }

    public UpBannerRequest build() {
        UpBannerRequest upBannerRequest = new UpBannerRequest();
        upBannerRequest.setDebugFlag(debugFlag);
        upBannerRequest.setCookieStr(cookieStr);
        upBannerRequest.setItem(item);
        return upBannerRequest;
    }
}
