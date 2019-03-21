package com.njq.file.load.core.service;

import com.njq.common.util.encrypt.Base64Util;
import com.njq.common.util.grab.UrlChangeUtil;
import com.njq.common.util.string.IdGen;
import com.njq.file.load.api.FileLoadService;
import com.njq.file.load.api.model.SaveFileInfo;
import com.njq.file.load.api.model.UpFileInfoRequest;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: nijiaqi
 * @date: 2019/3/19
 */
@Service
public class FileLoadServiceImpl implements FileLoadService {
    private static final Logger logger = LoggerFactory.getLogger(FileLoadServiceImpl.class);

    @Override
    public SaveFileInfo loadFile(UpFileInfoRequest request) {
        SaveFileInfo info = new SaveFileInfo();
        try {
            String fileOldName = "";
            String src = request.getUrl();
            String shortName = request.getType().getValue();
            String savePlace = PropertiesFactory.getFilePlace(request.getDebugFlag());
            String place = getFilePlace(shortName, savePlace, fileOldName);
            fileOldName = URLDecoder.decode(getOldName(src), "UTF-8");
            UrlChangeUtil.downLoad(src, savePlace + place, shortName);
            Pair.of(true, "");
            info.setFileNewName(fileOldName);
            info.setFileOldName(fileOldName);
            info.setFilePlace(getSrc(shortName, savePlace) + "/downLoadFile?file=" + fileOldName);
            info.setRealPlace(savePlace + place);
            info.setOldSrc(request.getUrl());
            info.setResultPair(Pair.of(true, ""));
        } catch (Exception e) {
            info.setResultPair(Pair.of(false, e.getMessage()));
            logger.error("下载出错", e);
        }
        return info;
    }

    @Override
    public SaveFileInfo reload(UpFileInfoRequest request) {
        SaveFileInfo info = new SaveFileInfo();
        try {
            UrlChangeUtil.downLoad(request.getUrl(), request.getRealSavePlace(), request.getType().getValue());
            info.setResultPair(Pair.of(true, ""));
        } catch (Exception e) {
            info.setResultPair(Pair.of(false, e.getMessage()));
        }
        return info;
    }

    @Override
    public SaveFileInfo loadPic(UpFileInfoRequest request) {
        String imageSavePlace = PropertiesFactory.getImagePlace(request.getDebugFlag());
        String imageUrl = PropertiesFactory.getImageUrl(request.getDebugFlag());
        String fileOldName = getOldName(request.getUrl());
        String fileNewName = getNewName(fileOldName);
        String place = getFilePlace(request.getType().getValue(), imageSavePlace, fileNewName);
        SaveFileInfo info = new SaveFileInfo();
        try {
            UrlChangeUtil.downLoad(request.getUrl(), imageSavePlace + place, request.getType().getValue());
            info.setFileNewName(fileNewName);
            info.setFileOldName(fileOldName);
            info.setFilePlace(imageUrl + place);
            info.setRealPlace(imageSavePlace + place);
            info.setOldSrc(request.getUrl());
            info.setResultPair(Pair.of(true, ""));
        } catch (Exception e) {
            info.setResultPair(Pair.of(false, e.getMessage()));
        }
        return info;
    }

    @Override
    public SaveFileInfo loadBase64(UpFileInfoRequest request) {
        String imageUrl = PropertiesFactory.getImageUrl(request.getDebugFlag());
        String imageSavePlace = PropertiesFactory.getImagePlace(request.getDebugFlag());
        String picName = IdGen.get().toString();
        String picPlace = Base64Util.GenerateImage(request.getUrl().split("base64,")[1], picName, imageSavePlace);
        Pair<Boolean, String> resultPair = null;
        if (!StringUtils.isEmpty(picPlace)) {
            resultPair = Pair.of(true, "");
        } else {
            resultPair = Pair.of(false, "base64位图片生成失败！");
        }
        SaveFileInfo info = new SaveFileInfo();
        info.setFileNewName(picName);
        info.setFileOldName(picName);
        info.setFilePlace(imageUrl + picPlace);
        info.setRealPlace(imageSavePlace + picPlace);
        info.setOldSrc("base64");
        info.setResultPair(resultPair);
        return info;
    }


    public static String getOldName(String src) {
        String[] img = src.split("\\?")[0].split("\\/");
        return img[img.length - 1];
    }

    public static String getNewName(String oldFileName) {
        String fileName = String.valueOf(IdGen.get().nextId());
        String[] imgName = oldFileName.split("\\.");
        if (imgName.length > 1) {
            fileName += "." + imgName[1];
        } else {
            fileName += ".png";
        }
        return fileName;
    }


    public static String getFilePlace(String shortName, String savePlace, String fileName) {
        return getSrc(shortName, savePlace) + "/" + fileName;
    }

    private static String getSrc(String shortName, String savePlace) {
        Date timeCur = new Date();
        SimpleDateFormat fmtYY = new SimpleDateFormat("yyyy");
        SimpleDateFormat fmtMM = new SimpleDateFormat("MM");
        SimpleDateFormat fmtDD = new SimpleDateFormat("dd");
        String strYY = fmtYY.format(timeCur);
        String strMM = fmtMM.format(timeCur);
        String strDD = fmtDD.format(timeCur);
        String url = "/" + shortName + "/" + strYY + strMM + strDD;
        File dir = new File(savePlace + url);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return url;
    }
}
