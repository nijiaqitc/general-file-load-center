package com.njq.file.load.api.model;

/**
 * @author: nijiaqi
 * @date: 2019/3/26
 */
public final class DownLoadFileRequestBuilder {
    private Boolean debugFlag = false;
    private String file;
    private String cookieStr;
    private String shortname;
    private String dfolder;

    private DownLoadFileRequestBuilder() {
    }

    public static DownLoadFileRequestBuilder aDownLoadFileRequest() {
        return new DownLoadFileRequestBuilder();
    }

    public DownLoadFileRequestBuilder ofDebugFlag(Boolean debugFlag) {
        this.debugFlag = debugFlag;
        return this;
    }

    public DownLoadFileRequestBuilder ofFile(String file) {
        this.file = file;
        return this;
    }

    public DownLoadFileRequestBuilder ofCookieStr(String cookieStr) {
        this.cookieStr = cookieStr;
        return this;
    }

    public DownLoadFileRequestBuilder ofShortname(String shortname) {
        this.shortname = shortname;
        return this;
    }

    public DownLoadFileRequestBuilder ofDfolder(String dfolder) {
        this.dfolder = dfolder;
        return this;
    }

    public DownLoadFileRequest build() {
        DownLoadFileRequest downLoadFileRequest = new DownLoadFileRequest();
        downLoadFileRequest.setDebugFlag(debugFlag);
        downLoadFileRequest.setFile(file);
        downLoadFileRequest.setCookieStr(cookieStr);
        downLoadFileRequest.setShortname(shortname);
        downLoadFileRequest.setDfolder(dfolder);
        return downLoadFileRequest;
    }
}
