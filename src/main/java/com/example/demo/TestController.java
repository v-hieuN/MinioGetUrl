package com.example.demo;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.http.Method;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
public class TestController {
    @Resource
    private MinioClient minioClient;

    // Corresponding to background bucket name
    private static final String TEST_BUCKET = "albums";

//    @PostMapping("upload")
//    public String upload(@RequestParam(name = "file") MultipartFile file) {
//        try {
//            int idx = Objects.requireNonNull(file.getOriginalFilename()).lastIndexOf(".");
//            String suffix = file.getOriginalFilename().substring(idx + 1);
//            String fileName = UUID.randomUUID() + "." + suffix;
//
//            // Save file
//            minioClient.putObject(PutObjectArgs.builder()
//                    .stream(file.getInputStream(), file.getSize(), PutObjectArgs.MIN_MULTIPART_SIZE)
//                    .object(fileName)
//                    .contentType(file.getContentType())
//                    .bucket(TEST_BUCKET)
//                    .build());
//            return fileName;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "";
//        }
//    }

    @GetMapping("getUrl")
    public String getUrl(@RequestParam(name = "path") String path) {
        try {
            // Getting the file access address is invalid for 7 days
                String url = minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                    .bucket(TEST_BUCKET)
                    .object(path).
                    method(Method.GET)
                    .expiry(7, TimeUnit.DAYS).build());
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}