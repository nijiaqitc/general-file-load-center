package com.njq.file.load.api.model;

import org.apache.commons.fileupload.FileItem;

import java.io.Serializable;

/**
 * @author: nijiaqi
 * @date: 2019/3/21
 */
public class UpBannerRequest implements Serializable {
    private FileItem item;
    private Boolean debugFlag = false;

    public FileItem getItem() {
        return item;
    }

    public void setItem(FileItem item) {
        this.item = item;
    }

    public Boolean getDebugFlag() {
        return debugFlag;
    }

    public void setDebugFlag(Boolean debugFlag) {
        this.debugFlag = debugFlag;
    }
}
