package com.njq.file.load.core.service;

import com.njq.common.util.date.DateUtil;
import com.njq.common.util.encrypt.Base64Util;
import com.njq.common.util.grab.UrlChangeUtil;
import com.njq.common.util.image.ImageUtil;
import com.njq.common.util.image.UpPicUtil;
import com.njq.common.util.string.IdGen;
import com.njq.common.util.string.StringUtil;
import com.njq.file.load.api.FileLoadService;
import com.njq.file.load.api.model.ByteRequest;
import com.njq.file.load.api.model.DownLoadFileRequest;
import com.njq.file.load.api.model.ReBackFileInfo;
import com.njq.file.load.api.model.ResourceShareRequest;
import com.njq.file.load.api.model.SaveFileInfo;
import com.njq.file.load.api.model.UpBannerRequest;
import com.njq.file.load.api.model.UpBase64Request;
import com.njq.file.load.api.model.UpFileInfoRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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

    @Resource
    private ThreadPoolTaskExecutor loadFileTaskExecutor;


    @Override
    public SaveFileInfo loadFile(UpFileInfoRequest request) {
        SaveFileInfo info = new SaveFileInfo();
        String fileUrl = PropertiesFactory.getFileUrl(request.getDebugFlag());
        try {
            String src = request.getUrl();
            String shortName = request.getType().getValue();
            String savePlace = PropertiesFactory.getFilePlace(request.getDebugFlag());
            String fileOldName = URLDecoder.decode(getOldName(src), "UTF-8");
            String place = getFilePlace(shortName, savePlace, fileOldName);
            info.setFileNewName(fileOldName);
            info.setFileOldName(fileOldName);
            info.setFilePlace(fileUrl + place);
            info.setRealPlace(savePlace + place);
            info.setOldSrc(request.getUrl());
            info.setResultPair(Pair.of(false, "正在读取..."));
//            info.setResultPair(Pair.of(true, ""));

            loadFileTaskExecutor.submit(() -> {
                try {
                    UrlChangeUtil.downLoad(src, savePlace + place, shortName);
                } catch (Exception e) {
                    logger.error("下载文件出错", e);
                }

            });
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
            SaveFileInfo s = fileQuery(request);
            if(StringUtil.isEmpty(s.getFileNewName())){
                info.setResultPair(Pair.of(true, ""));
                return info;
            }
            logger.info("reloadPic---:" + request.getUrl());
            UrlChangeUtil.downLoad(request.getUrl(), request.getRealSavePlace(), request.getType().getValue());
            info.setResultPair(Pair.of(true, ""));
        } catch (Exception e) {
            info.setResultPair(Pair.of(false, e.getMessage()));
        }
        return info;
    }

    @Override
    public SaveFileInfo fileQuery(UpFileInfoRequest request) {
        File f = new File(request.getRealSavePlace());
        SaveFileInfo info = new SaveFileInfo();
        if (f.exists()) {
            info.setFileNewName(f.getName());
        }else{
            loadFileTaskExecutor.submit(()->{
                reload(request);
            });
        }
        return info;
    }

    @Override
    public SaveFileInfo loadPic(UpFileInfoRequest request) {
        logger.info("loadPIc---:" + request.getUrl());
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
        logger.info("loadbase64Pic---:" + request.getUrl());
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
            if (imgName[1].length() > 3) {
                fileName += ".png";
            } else {
                fileName += "." + imgName[1];
            }
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


    @Override
    public ReBackFileInfo readFile(DownLoadFileRequest request) {
        InputStream is = null;
        String url = PropertiesFactory.getFilePlace(false) + "/" + request.getShortname() + "/" + request.getDfolder() + "/" + request.getFile();
        File f = new File(url);
        ReBackFileInfo reBackFileInfo = new ReBackFileInfo();
        /**
         * 传输文件大小不能超过1M
         */
        if (f.exists() && f.length() > 1048576) {
            return reBackFileInfo;
        }
        try {
            is = new FileInputStream(f);
            byte[] body = new byte[is.available()];
            is.read(body);
            reBackFileInfo.setBody(body);
            reBackFileInfo.setName(f.getName());
        } catch (Exception e) {
            logger.error("下载文件出错：" + e.getMessage());
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                logger.error("关闭流出错：" + e.getMessage());
            }
        }
        return reBackFileInfo;
    }

    @Override
    public SaveFileInfo upBase64(UpBase64Request request) {
        SaveFileInfo fileInfo = new SaveFileInfo();
        String picName = IdGen.get().toString();
        String[] spstr = request.getBase64Data().split("base64,");
        if (spstr.length < 2) {
            return fileInfo;
        }
        String imagePlace = PropertiesFactory.getImagePlace(true);
        String imageUrl = PropertiesFactory.getImageUrl(true);
        String picPlace = Base64Util.GenerateImage(spstr[1], picName, imagePlace);
        fileInfo.setFileNewName(picName);
        fileInfo.setFileOldName(picName);
        fileInfo.setFilePlace(imageUrl + picPlace);
        fileInfo.setRealPlace(imagePlace + picPlace);
        fileInfo.setResultPair(Pair.of(true, ""));
        return fileInfo;
    }

    @Override
    public SaveFileInfo upBanner(UpBannerRequest request) {
        SaveFileInfo fileInfo = new SaveFileInfo();
        try {
            if (!request.getItem().isFormField()) {
                // 获得文件名
                String fileName = request.getItem().getName();
                System.out.println(fileName);
                // 该方法在某些平台(操作系统),会返回路径+文件名/Users/njq
                fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
                String filePlace = PropertiesFactory.getImagePlace(false) + "/banner";
                File fileFolder = new File(filePlace);
                // 创建目录
                if (!fileFolder.exists()) {
                    fileFolder.mkdirs();
                }
                filePlace += "/" + fileName;
                File file = new File(filePlace);
                if (!file.exists()) {
                    request.getItem().write(file);
                    fileInfo.setFileOldName(fileName);
                    fileInfo.setFileNewName(fileName);
                    fileInfo.setFilePlace(PropertiesFactory.getImageUrl(false));
                }
            }
        } catch (Exception e) {
            logger.error("上传失败", e);
        }
        return fileInfo;
    }

    @Override
    public SaveFileInfo upYxlFile(UpBannerRequest request) {
        SaveFileInfo fileInfo = new SaveFileInfo();
        try {
            FileItem item = request.getItem();
            String dd = DateUtil.toDateString8(new Date());
            String fileName;
            // 如果是文件说明是上传的图片，否则就是网络图片
            if (!item.isFormField()) {
                fileName = UpPicUtil.upBlobPic(item, PropertiesFactory.getImagePlace(request.getDebugFlag()) + "/yxl/" + dd);
            } else if (item.getString().startsWith("data")) {
                fileName = UpPicUtil.upBase64Pic(item.getString(), PropertiesFactory.getImagePlace(request.getDebugFlag()) + "/yxl/" + dd);
            } else {
                // 上传前先判断当前图片是否是本站的图片
                String url = item.getString();
                if (url.startsWith(PropertiesFactory.getImageUrl(request.getDebugFlag()))) {
                    String s[] = url.split("dialog/emotion");
                    if (s.length > 1) {
                        fileName = PropertiesFactory.getImageUrl(request.getDebugFlag()) + "/uploadImage" + s[1];
                    } else {
                        // 进行图片下载
                        fileName = UpPicUtil.upIntenetPic(item.getString(),
                                PropertiesFactory.getImagePlace(request.getDebugFlag()) + "/yxl/" + dd);
                    }
                } else if (url.startsWith(PropertiesFactory.getImageUrl(request.getDebugFlag()))) {
                    fileName = url;
                } else {
                    // 进行图片下载
                    fileName = UpPicUtil.upIntenetPic(item.getString(),
                            PropertiesFactory.getImagePlace(request.getDebugFlag()) + "/yxl/" + dd);
                }
            }
            fileInfo.setFileNewName(item.getFieldName());
            fileInfo.setFileOldName(item.getFieldName());
            fileInfo.setFilePlace(PropertiesFactory.getImageUrl(request.getDebugFlag()) + "/uploadImage/yxl/" + dd + "/" + fileName);
        } catch (Exception e) {
            logger.error("上传失败", e);
        }
        return fileInfo;
    }

    @Override
    public SaveFileInfo upShareFile(ResourceShareRequest request) {
        String realPath = PropertiesFactory.getImagePlace(request.getDebugFlag())
                + "/shareResources/total/"
                + request.getShareTypeOne()
                + "/" + request.getShareTypeTwo()
                + "/" + request.getShareTypeThree();
        String picPlaceA = Base64Util.GenerateImage(request.getImgStr(),
                request.getName(), realPath, null);
        File file1 = new File(realPath + "/" + picPlaceA);
        //生成缩略图
        ImageUtil.scale(file1, realPath + "/" + request.getSkeletonize() + ".jpg", request.getWidth(), request.getHeight());
        file1.delete();
        SaveFileInfo fileInfo = new SaveFileInfo();
        String str = "/uploadImage" + "/shareResources/total/" + request.getShareTypeOne() + "/" + request.getShareTypeTwo() + "/" + request.getShareTypeThree() + "/";
        fileInfo.setFilePlace(str);
        return fileInfo;
    }

    @Override
    public SaveFileInfo upYxlByteFile(ByteRequest request) {
        String dd = DateUtil.toDateString8(new Date());
        String newName = getNewName(request.getName());
        return upByteFile(request, "/" + request.getType().getValue() + "/" + dd, newName);
    }

    @Override
    public SaveFileInfo upBannerByteFile(ByteRequest request) {
        return upByteFile(request, "/" + request.getType().getValue(), request.getName());
    }

    private SaveFileInfo upByteFile(ByteRequest request, String folder, String newName) {
        SaveFileInfo fileInfo = new SaveFileInfo();
        String tempFilePlace = PropertiesFactory.getImagePlace(request.getDebugFlag()) + folder;
        File fileFolder = new File(tempFilePlace);
        //创建目录
        if (!fileFolder.exists()) {
            fileFolder.mkdirs();
        }
        tempFilePlace += "/" + newName;
        fileInfo.setFileNewName(getNewName(request.getName()));
        fileInfo.setFileOldName(request.getName());
        File file = new File(tempFilePlace);
        if (!file.exists()) {
            UpPicUtil.upByte(request.getData(), tempFilePlace);
            fileInfo.setFilePlace(PropertiesFactory.getImageUrl(request.getDebugFlag()) + folder + "/" + newName);
            fileInfo.setRealPlace(tempFilePlace);
            fileInfo.setResultPair(Pair.of(true, ""));
        } else {
            fileInfo.setResultPair(Pair.of(false, "文件已存在，上传失败！"));
        }
        return fileInfo;
    }


}
