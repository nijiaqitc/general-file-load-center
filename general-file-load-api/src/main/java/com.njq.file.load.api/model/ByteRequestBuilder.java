package com.njq.file.load.api.model;

import com.njq.common.enumreg.channel.ChannelType;

/**
 * @author: nijiaqi
 * @date: 2019/3/26
 */
public final class ByteRequestBuilder {
    private Boolean debugFlag = false;
    private String cookieStr;
    private String name;
    private byte[] data;
    private ChannelType type;

    private ByteRequestBuilder() {
    }

    public static ByteRequestBuilder aByteRequest() {
        return new ByteRequestBuilder();
    }

    public ByteRequestBuilder ofDebugFlag(Boolean debugFlag) {
        this.debugFlag = debugFlag;
        return this;
    }

    public ByteRequestBuilder ofCookieStr(String cookieStr) {
        this.cookieStr = cookieStr;
        return this;
    }

    public ByteRequestBuilder ofName(String name) {
        this.name = name;
        return this;
    }

    public ByteRequestBuilder ofData(byte[] data) {
        this.data = data;
        return this;
    }

    public ByteRequestBuilder ofType(ChannelType type) {
        this.type = type;
        return this;
    }

    public ByteRequest build() {
        ByteRequest byteRequest = new ByteRequest();
        byteRequest.setDebugFlag(debugFlag);
        byteRequest.setCookieStr(cookieStr);
        byteRequest.setName(name);
        byteRequest.setData(data);
        byteRequest.setType(type);
        return byteRequest;
    }
}
