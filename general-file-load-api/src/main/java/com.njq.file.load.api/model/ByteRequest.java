package com.njq.file.load.api.model;

import java.io.Serializable;

/**
 * @author: nijiaqi
 * @date: 2019/3/22
 */
public class ByteRequest extends BaseRequest implements Serializable {
    private String name;
    private byte[] data;
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
