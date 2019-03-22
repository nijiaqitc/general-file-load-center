package com.njq.file.load.api.model;

import com.njq.common.enumreg.channel.ChannelType;

/**
 * @author: nijiaqi
 * @date: 2019/3/22
 */
public final class ByteRequestBuilder {
    private String name;
    private byte[] data;
    private ChannelType type;
    private Boolean debugFlag = false;

    private ByteRequestBuilder() {
    }

    public static ByteRequestBuilder aByteRequest() {
        return new ByteRequestBuilder();
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

    public ByteRequestBuilder ofDebugFlag(Boolean debugFlag) {
        this.debugFlag = debugFlag;
        return this;
    }

    public ByteRequest build() {
        ByteRequest byteRequest = new ByteRequest();
        byteRequest.setName(name);
        byteRequest.setData(data);
        byteRequest.setType(type);
        byteRequest.setDebugFlag(debugFlag);
        return byteRequest;
    }
}
