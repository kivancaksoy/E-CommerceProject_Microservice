package com.kivanc.ecommerce.productservice.helper;

import java.util.UUID;

public class FileNameHelper {
    public static String generateRandomFileName(String originalFileName) {
        String fileExtension = originalFileName.trim()
                .substring(originalFileName.trim().lastIndexOf(".") + 1);

        return String.format(UUID.randomUUID()+ "." + fileExtension);
    }
}
