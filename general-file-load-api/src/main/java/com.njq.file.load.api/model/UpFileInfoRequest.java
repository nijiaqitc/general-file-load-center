package com.njq.file.load.api.model;

import com.njq.common.enumreg.channel.ChannelType;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @author: nijiaqi
 * @date: 2019/3/19
 */
public class UpFileInfoRequest implements Serializable {
    private String url;
    private ChannelType type;
    private String realSavePlace;
    private Boolean debugFlag = false;

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

    public Boolean getDebugFlag() {
        return debugFlag;
    }

    public void setDebugFlag(Boolean debugFlag) {
        this.debugFlag = debugFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("url", url)
                .append("type", type)
                .append("realSavePlace", realSavePlace)
                .append("debugFlag", debugFlag)
                .toString();
    }
}
