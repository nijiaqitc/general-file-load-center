package com.njq.file.load.api.model;

/**
 * @author: nijiaqi
 * @date: 2019/3/21
 */
public final class DownLoadFileRequestBuilder {
    private String file;
    private String shortname;
    private String dfolder;

    private DownLoadFileRequestBuilder() {
    }

    public static DownLoadFileRequestBuilder aDownLoadFileRequest() {
        return new DownLoadFileRequestBuilder();
    }

    public DownLoadFileRequestBuilder ofFile(String file) {
        this.file = file;
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
        downLoadFileRequest.setFile(file);
        downLoadFileRequest.setShortname(shortname);
        downLoadFileRequest.setDfolder(dfolder);
        return downLoadFileRequest;
    }
}
