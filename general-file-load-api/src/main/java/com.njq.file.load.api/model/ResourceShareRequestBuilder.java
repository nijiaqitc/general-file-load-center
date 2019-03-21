package com.njq.file.load.api.model;

/**
 * @author: nijiaqi
 * @date: 2019/3/21
 */
public final class ResourceShareRequestBuilder {
    private String imgStr;
    private String name;
    private String place;
    private Long shareTypeOne;
    private Long shareTypeTwo;
    private Long shareTypeThree;
    private int width;
    private int height;
    private String skeletonize;
    private Boolean debugFlag = false;

    private ResourceShareRequestBuilder() {
    }

    public static ResourceShareRequestBuilder aResourceShareRequest() {
        return new ResourceShareRequestBuilder();
    }

    public ResourceShareRequestBuilder ofImgStr(String imgStr) {
        this.imgStr = imgStr;
        return this;
    }

    public ResourceShareRequestBuilder ofName(String name) {
        this.name = name;
        return this;
    }

    public ResourceShareRequestBuilder ofPlace(String place) {
        this.place = place;
        return this;
    }

    public ResourceShareRequestBuilder ofShareTypeOne(Long shareTypeOne) {
        this.shareTypeOne = shareTypeOne;
        return this;
    }

    public ResourceShareRequestBuilder ofShareTypeTwo(Long shareTypeTwo) {
        this.shareTypeTwo = shareTypeTwo;
        return this;
    }

    public ResourceShareRequestBuilder ofShareTypeThree(Long shareTypeThree) {
        this.shareTypeThree = shareTypeThree;
        return this;
    }

    public ResourceShareRequestBuilder ofWidth(int width) {
        this.width = width;
        return this;
    }

    public ResourceShareRequestBuilder ofHeight(int height) {
        this.height = height;
        return this;
    }

    public ResourceShareRequestBuilder ofSkeletonize(String skeletonize) {
        this.skeletonize = skeletonize;
        return this;
    }

    public ResourceShareRequestBuilder ofDebugFlag(Boolean debugFlag) {
        this.debugFlag = debugFlag;
        return this;
    }

    public ResourceShareRequest build() {
        ResourceShareRequest resourceShareRequest = new ResourceShareRequest();
        resourceShareRequest.setImgStr(imgStr);
        resourceShareRequest.setName(name);
        resourceShareRequest.setPlace(place);
        resourceShareRequest.setShareTypeOne(shareTypeOne);
        resourceShareRequest.setShareTypeTwo(shareTypeTwo);
        resourceShareRequest.setShareTypeThree(shareTypeThree);
        resourceShareRequest.setWidth(width);
        resourceShareRequest.setHeight(height);
        resourceShareRequest.setSkeletonize(skeletonize);
        resourceShareRequest.setDebugFlag(debugFlag);
        return resourceShareRequest;
    }
}
