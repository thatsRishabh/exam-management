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

    @Value("${project.image}")
    private String path;

    @PostMapping("/fileupload")
    public ResponseEntity<FileUploadResponse> fileUpload(@RequestParam("image") MultipartFile image)  {

        String fileName = null;
        String downloadURl = null;
        String fileType = null;
        Long fileSize = null;
        try {
            fileSize=image.getSize();
            fileType=image.getContentType();
           fileName=this.fileUploadService.uploadImage(path,image);
            downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/file/images/")
                    .path(fileName)
                    .toUriString();
        }catch (IOException e){
            e.printStackTrace();
            return new ResponseEntity<>(new FileUploadResponse(null,null, fileType,fileSize,"file not uploaded") , HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new FileUploadResponse(fileName,downloadURl, fileType, fileSize,"file  uploaded successfully"), HttpStatus.OK);
//        return new ResponseEntity<>(new FileResponse(downloadURl,fileSize ), HttpStatus.OK);
    }

//    @PostMapping("/upload")
//    public FileUploadResponse uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
//        FileUploadEntity attachment = null;
//        String downloadURl = "";
//        attachment = fileUploadService.saveAttachment(file);
//        downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/download/")
//                .path(attachment.getId())
//                .toUriString();
//
//        return new FileUploadResponse(attachment.getFileName(),
//                downloadURl,
//                file.getContentType(),
//                file.getSize());
//    }

    // below function will be used to host the file


        @GetMapping("/images/{imageName}")
        public void downloadFile(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {
            InputStream resource=this.fileUploadService.getResource(path,imageName);
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            StreamUtils.copy(resource,response.getOutputStream());
    }


//    @GetMapping("/download/{fileId}")
//    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws Exception {
//        FileUploadEntity attachment = null;
//        attachment = fileUploadService.getAttachment(fileId);
//        return  ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(attachment.getFileType()))
//                .header(HttpHeaders.CONTENT_DISPOSITION,
//                        "attachment; filename=\"" + attachment.getFileName()
//                                + "\"")
//                .body(new ByteArrayResource(attachment.getData()));
//    }

}
