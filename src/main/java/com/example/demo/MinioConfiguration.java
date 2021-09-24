package com.example.demo;

import io.minio.MinioClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfiguration {
    private static final String END_POINT = "http://192.168.88.210:9000";

    private static final String USERNAME = "6b5Msx70wMj35sMAvxdV3AU5VpYjnVuc";

    private static final String PASSWORD = "vszqnhtdeRcQMVT5LGfUDr6YN4Lr5OsC";

    private static final  String nameBucket = "albums";

    @Bean
    public MinioClient minioClient() {
        return new MinioClient.Builder()
                .endpoint(END_POINT)
                .credentials(USERNAME, PASSWORD)
                .build();
    }

}
