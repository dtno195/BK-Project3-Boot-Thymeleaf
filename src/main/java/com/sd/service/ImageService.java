package com.sd.service;

import com.sd.util.Constant;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class ImageService {

    private ResourceLoader resourceLoader;

    @Autowired
    UploadPathService uploadPathService;

    public Resource findOneImage(String fileName){
         return resourceLoader.getResource("file:"+Constant.UPLOAD_FOLDER+"/"+fileName);
    }

    public String getImage(MultipartFile file ){
        String fileName = file.getOriginalFilename();
        String modifiedFileName = FilenameUtils.getBaseName(fileName)+"_"+System.currentTimeMillis()+"."+FilenameUtils.getExtension(fileName);
        File storeFile = uploadPathService.getFilePath(modifiedFileName,"image");
        if(storeFile!=null){

        }
        return null;
    }
}
