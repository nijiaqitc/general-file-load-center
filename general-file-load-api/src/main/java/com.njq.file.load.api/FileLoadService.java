package com.njq.file.load.api;

import com.njq.file.load.api.model.DownLoadFileRequest;
import com.njq.file.load.api.model.ReBackFileInfo;
import com.njq.file.load.api.model.SaveFileInfo;
import com.njq.file.load.api.model.UpFileInfoRequest;

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
}
