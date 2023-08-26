package com.exam.exam.management.service.impl;

import com.exam.exam.management.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileUploadServiceImpl implements FileUploadService {


    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

//        file name
        String name = file.getOriginalFilename();

        String randomId= UUID.randomUUID().toString();
        String name1=randomId.concat(name.substring(name.lastIndexOf(".")));
//        full path
        String filePath = path + File.separator + name1;

//        create a folder if not created
        File f= new File(path);
        if (!f.exists()){
            f.mkdir();
        }

//        file copy
        Files.copy(file.getInputStream(), Paths.get(filePath));

        return name1;
    }


//
//    @Override
//    public FileUploadEntity saveAttachment(MultipartFile file) throws Exception {
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//        try {
//            if(fileName.contains("..")) {
//                throw  new Exception("Filename contains invalid path sequence "
//                        + fileName);
//            }
//
//            FileUploadEntity attachment
//                    = new FileUploadEntity(fileName,
//                    file.getContentType());
////                    file.getBytes());
//            return fileUploadRepository.save(attachment);
//
//        } catch (Exception e) {
//            throw new Exception("Could not save File: " + fileName);
//        }
//    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullPath =path + File.separator +fileName;
        InputStream iS= new FileInputStream(fullPath);
        return iS;
    }

//    @Override
//    public FileUploadEntity getAttachment(String fileId) throws Exception {
//        return fileUploadRepository
//                .findById(fileId)
//                .orElseThrow(
//                        () -> new Exception("File not found with Id: " + fileId));
//    }
}
