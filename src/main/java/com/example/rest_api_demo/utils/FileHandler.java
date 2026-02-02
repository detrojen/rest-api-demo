package com.example.rest_api_demo.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class FileHandler {
    public static String Save(MultipartFile file, String folderName){
        String filePath = System.getProperty("user.dir") + File.separator + "Uploads" + File.separator + folderName + File.separator + file.getOriginalFilename();
        File file1 = new File(filePath);
        try {
            file.transferTo(file1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return folderName + File.separator + file.getOriginalFilename();
    }
}
