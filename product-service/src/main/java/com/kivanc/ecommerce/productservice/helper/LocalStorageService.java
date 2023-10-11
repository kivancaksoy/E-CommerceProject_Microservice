package com.kivanc.ecommerce.productservice.helper;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

@Component
public class LocalStorageService {
    public void uploadImage(MultipartFile file, String folderPath, String fileName) throws IOException {

        // Klasörü kontrol etme ve yoksa oluşturma
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // Resim dosyasını kaydetme
        //TODO cover with try catch
        byte[] bytes = file.getBytes();
        Path filePath = Paths.get(folderPath, fileName);
        //TODO cover with try catch
        Files.write(filePath, bytes);

    }


}
