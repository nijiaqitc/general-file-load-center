package com.njq.file.load.api.model;

import com.njq.common.enumreg.channel.ChannelType;

import java.io.Serializable;

/**
 * @author: nijiaqi
 * @date: 2019/3/19
 */
public class UpFileInfoRequest extends BaseRequest implements Serializable {
    private String url;
    private ChannelType type;
    private String realSavePlace;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ChannelType getType() {
        return type;
    }

    public void setType(ChannelType type) {
        this.type = type;
    }

    public String getRealSavePlace() {
        return realSavePlace;
    }

    public void setRealSavePlace(String realSavePlace) {
        this.realSavePlace = realSavePlace;
    }

}
