package com.njq.file.load.api.model;

import com.njq.common.enumreg.channel.ChannelType;

/**
 * @author: nijiaqi
 * @date: 2019/3/21
 */
public final class UpFileInfoRequestBuilder {
    private String url;
    private ChannelType type;
    private String realSavePlace;
    private Boolean debugFlag = false;

    public UpFileInfoRequestBuilder() {
    }

    public static UpFileInfoRequestBuilder anUpFileInfoRequest() {
        return new UpFileInfoRequestBuilder();
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

    public UpFileInfoRequestBuilder ofDebugFlag(Boolean debugFlag) {
        this.debugFlag = debugFlag;
        return this;
    }

    public UpFileInfoRequest build() {
        UpFileInfoRequest upFileInfoRequest = new UpFileInfoRequest();
        upFileInfoRequest.setUrl(url);
        upFileInfoRequest.setType(type);
        upFileInfoRequest.setRealSavePlace(realSavePlace);
        upFileInfoRequest.setDebugFlag(debugFlag);
        return upFileInfoRequest;
    }
}
