package com.njq.file.load.api;

import com.njq.file.load.api.model.ByteRequest;
import com.njq.file.load.api.model.DownLoadFileRequest;
import com.njq.file.load.api.model.ReBackFileInfo;
import com.njq.file.load.api.model.ResourceShareRequest;
import com.njq.file.load.api.model.SaveFileInfo;
import com.njq.file.load.api.model.UpBannerRequest;
import com.njq.file.load.api.model.UpBase64Request;
import com.njq.file.load.api.model.UpFileInfoRequest;
import org.apache.commons.fileupload.FileItem;

/**
 * @author: nijiaqi
 * @date: 2019/3/19
 */
public interface FileLoadService {


    SaveFileInfo loadFile(UpFileInfoRequest request);

    SaveFileInfo loadPic(UpFileInfoRequest request);

    SaveFileInfo loadBase64(UpFileInfoRequest request);

    SaveFileInfo reload(UpFileInfoRequest request);

    ReBackFileInfo readFile(DownLoadFileRequest request);

    SaveFileInfo upBase64(UpBase64Request request);

    SaveFileInfo upBanner(UpBannerRequest request);

    @Deprecated
    SaveFileInfo upYxlFile(UpBannerRequest request);

    SaveFileInfo upShareFile(ResourceShareRequest request);

    SaveFileInfo upYxlByteFile(ByteRequest request);


}
