package com.njq.file.load.api.model;

import com.njq.common.enumreg.channel.ChannelType;

/**
 * @author: nijiaqi
 * @date: 2019/3/26
 */
public final class UpFileInfoRequestBuilder {
    private Boolean debugFlag = false;
    private String cookieStr;
    private String url;
    private ChannelType type;
    private String realSavePlace;

    private UpFileInfoRequestBuilder() {
    }

    public static UpFileInfoRequestBuilder anUpFileInfoRequest() {
        return new UpFileInfoRequestBuilder();
    }

    public UpFileInfoRequestBuilder ofDebugFlag(Boolean debugFlag) {
        this.debugFlag = debugFlag;
        return this;
    }

    public UpFileInfoRequestBuilder ofCookieStr(String cookieStr) {
        this.cookieStr = cookieStr;
        return this;
    }

    public UpFileInfoRequestBuilder ofUrl(String url) {
        this.url = url;
        return this;
    }

    public UpFileInfoRequestBuilder ofType(ChannelType type) {
        this.type = type;
        return this;
    }

    public UpFileInfoRequestBuilder ofRealSavePlace(String realSavePlace) {
        this.realSavePlace = realSavePlace;
        return this;
    }

    public UpFileInfoRequest build() {
        UpFileInfoRequest upFileInfoRequest = new UpFileInfoRequest();
        upFileInfoRequest.setDebugFlag(debugFlag);
        upFileInfoRequest.setCookieStr(cookieStr);
        upFileInfoRequest.setUrl(url);
        upFileInfoRequest.setType(type);
        upFileInfoRequest.setRealSavePlace(realSavePlace);
        return upFileInfoRequest;
    }
}
