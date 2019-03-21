package com.njq.file.load.core.service;

/**
 * @author: nijiaqi
 * @date: 2019/3/21
 */
public class PropertiesFactory {
    public static String getImagePlace(Boolean flag) {
        if (flag) {
            return SpringContextUtil.getValue("debug.image.place");
        } else {
            return SpringContextUtil.getValue("image.place");
        }
    }

    public static  String getImageUrl(Boolean flag) {
        if (flag) {
            return SpringContextUtil.getValue("debug.image.url");
        } else {
            return SpringContextUtil.getValue("image.url");
        }
    }

    public static  String getFilePlace(Boolean flag) {
        if (flag) {
            return SpringContextUtil.getValue("debug.file.place");
        } else {
            return SpringContextUtil.getValue("file.place");
        }
    }


}
