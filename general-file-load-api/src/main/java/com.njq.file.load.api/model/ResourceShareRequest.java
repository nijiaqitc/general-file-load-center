package com.njq.file.load.api.model;

import java.io.Serializable;

/**
 * @author: nijiaqi
 * @date: 2019/3/21
 */
public class ResourceShareRequest extends BaseRequest implements Serializable {
    private String imgStr;
    private String name;
    private String place;
    private Long shareTypeOne;
    private Long shareTypeTwo;
    private Long shareTypeThree;
    private int width;
    private int height;
    private String skeletonize;

    public String getImgStr() {
        return imgStr;
    }

    public void setImgStr(String imgStr) {
        this.imgStr = imgStr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Long getShareTypeOne() {
        return shareTypeOne;
    }

    public void setShareTypeOne(Long shareTypeOne) {
        this.shareTypeOne = shareTypeOne;
    }

    public Long getShareTypeTwo() {
        return shareTypeTwo;
    }

    public void setShareTypeTwo(Long shareTypeTwo) {
        this.shareTypeTwo = shareTypeTwo;
    }

    public Long getShareTypeThree() {
        return shareTypeThree;
    }

    public void setShareTypeThree(Long shareTypeThree) {
        this.shareTypeThree = shareTypeThree;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getSkeletonize() {
        return skeletonize;
    }

    public void setSkeletonize(String skeletonize) {
        this.skeletonize = skeletonize;
    }
}
