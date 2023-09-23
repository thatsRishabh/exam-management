package com.exam.exam.management.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface FileUploadService {

    String uploadImage(String path, MultipartFile uploadFile) throws IOException;

//    InputStream getResource(String path, String fileName) throws FileNotFoundException;
}