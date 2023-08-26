package com.exam.exam.management.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface FileUploadService {
//    FileUploadEntity saveAttachment( MultipartFile file) throws Exception;
//
//    FileUploadEntity getAttachment(String fileId) throws Exception;

    String uploadImage(String path, MultipartFile file) throws IOException;

    InputStream getResource(String path, String fileName) throws FileNotFoundException;
}
