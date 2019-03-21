package com.njq.file.load.api.model;

import java.io.Serializable;

/**
 * @author: nijiaqi
 * @date: 2019/3/21
 */
public class DownLoadFileRequest implements Serializable {
    private String file;
    private String shortname;
    private String dfolder;

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getDfolder() {
        return dfolder;
    }

    public void setDfolder(String dfolder) {
        this.dfolder = dfolder;
    }
}
