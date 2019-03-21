package com.njq.file.load.api.model;

import org.apache.commons.fileupload.FileItem;

/**
 * @author: nijiaqi
 * @date: 2019/3/21
 */
public final class UpBannerRequestBuilder {
    private FileItem item;
    private Boolean debugFlag = false;

    private UpBannerRequestBuilder() {
    }

    public static UpBannerRequestBuilder anUpBannerRequest() {
        return new UpBannerRequestBuilder();
    }

    public UpBannerRequestBuilder ofItem(FileItem item) {
        this.item = item;
        return this;
    }

    public UpBannerRequestBuilder ofDebugFlag(Boolean debugFlag) {
        this.debugFlag = debugFlag;
        return this;
    }

    public UpBannerRequest build() {
        UpBannerRequest upBannerRequest = new UpBannerRequest();
        upBannerRequest.setItem(item);
        upBannerRequest.setDebugFlag(debugFlag);
        return upBannerRequest;
    }
}
