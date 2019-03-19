package com.njq.file.load.api.model;

import org.apache.commons.lang3.tuple.Pair;

import java.io.Serializable;

/**
 * @author: nijiaqi
 * @date: 2019/3/19
 */
public class SaveFileInfo implements Serializable {
    private String fileNewName;

    private String fileOldName;

    private String filePlace;

    private String RealPlace;

    private Pair<Boolean, String> resultPair;

    private String oldSrc;

    public String getFileNewName() {
        return fileNewName;
    }

    public void setFileNewName(String fileNewName) {
        this.fileNewName = fileNewName;
    }

    public String getFileOldName() {
        return fileOldName;
    }

    public void setFileOldName(String fileOldName) {
        this.fileOldName = fileOldName;
    }

    public String getFilePlace() {
        return filePlace;
    }

    public void setFilePlace(String filePlace) {
        this.filePlace = filePlace;
    }

    public String getRealPlace() {
        return RealPlace;
    }

    public void setRealPlace(String realPlace) {
        RealPlace = realPlace;
    }

    public Pair<Boolean, String> getResultPair() {
        return resultPair;
    }

    public void setResultPair(Pair<Boolean, String> resultPair) {
        this.resultPair = resultPair;
    }

    public String getOldSrc() {
        return oldSrc;
    }

    public void setOldSrc(String oldSrc) {
        this.oldSrc = oldSrc;
    }
}
