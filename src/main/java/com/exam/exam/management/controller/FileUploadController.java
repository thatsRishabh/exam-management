package com.exam.exam.management.controller;


import com.exam.exam.management.response.FileUploadResponse;
import com.exam.exam.management.service.FileUploadService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/file")
public class FileUploadController {

    @Autowired
    FileUploadService fileUploadService;

    @Value("${project.upload}")
    private String path;

    @PostMapping("/fileupload")
    public ResponseEntity<FileUploadResponse> fileUpload(@RequestParam("uploadFile") MultipartFile uploadFile)  {

        String fileName = null;
        String downloadURl = null;
        String fileType = null;
        Long fileSize = null;
        try {
            fileSize=uploadFile.getSize();
            fileType=uploadFile.getContentType();
           fileName=this.fileUploadService.uploadImage(path,uploadFile);
            downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/file/download/")
                    .path(fileName)
                    .toUriString();
        }catch (IOException e){
            e.printStackTrace();
            return new ResponseEntity<>(new FileUploadResponse(null,null, fileType,fileSize,"file not uploaded") , HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new FileUploadResponse(fileName,downloadURl, fileType, fileSize,"file  uploaded successfully"), HttpStatus.OK);
    }



//        @GetMapping("/download/{fileName}")
//        public void downloadFile(@PathVariable("fileName") String fileName, HttpServletResponse response) throws IOException {
//            InputStream resource=this.fileUploadService.getResource(path,fileName);
////            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
////            response.setContentType(MediaType.APPLICATION_PDF_VALUE);
//            StreamUtils.copy(resource,response.getOutputStream());
//    }

}
