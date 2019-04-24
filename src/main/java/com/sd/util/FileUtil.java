package com.sd.util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;

public class FileUtil {
    public static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);
    public String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : partHeader.split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    public String uploadFile(HttpServletRequest request, String uploadPath, String parameter) {
        String fullPath = "";
        try {
            Part file;
            file = request.getPart(parameter);
            String fileName = getFileName(file);
            OutputStream fileOutputStream;
            fileOutputStream = new FileOutputStream(new File(uploadPath + "/" + fileName));
            fullPath = uploadPath + "/" + fileName;
            InputStream fileInputStream = file.getInputStream();
            int read = 0;
            final byte[] bytes = new byte[4096];

            while ((read = fileInputStream.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, read);
            }
            fileInputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (ServletException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return fullPath;

    }
}
