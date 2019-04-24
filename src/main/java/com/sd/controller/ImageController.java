package com.sd.controller;

import com.sd.service.ImageService;
import com.sd.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class ImageController {
    @Autowired
    private ImageService imageService;

    private BufferedInputStream bis;
    @GetMapping("/image/{img}")
    public void showImage(@PathVariable("img") String img , HttpServletResponse response){
        try {
            FileInputStream fis = new FileInputStream(new File(Constant.UPLOAD_FOLDER+File.separator+img));
            bis = new BufferedInputStream(fis);
            response.setContentType("image/*");
            BufferedOutputStream output = new BufferedOutputStream(response.getOutputStream());
            for (int data; (data = bis.read()) > -1;) {
                output.write(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        Resource file = imageService.findOneImage(img);
//        try {
//            return ResponseEntity.ok()
//                    .contentLength(file.contentLength())
//                    .contentType(MediaType.ALL)
//                    .body(new InputStreamResource(file.getInputStream()));
//        } catch (IOException e) {
//           return ResponseEntity.badRequest().body("Could not file "+img+"=>"+e.getMessage());
//        }
    }



}
