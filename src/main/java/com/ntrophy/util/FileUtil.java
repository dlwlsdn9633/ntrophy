package com.ntrophy.util;

import com.ntrophy.domain.attacheFile.AttacheFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
public class FileUtil {
    @Value("${file.dir}")
    private String fileDir;
    public String getFullPath(String filename) {
        return Paths.get(fileDir, filename).toString();
    }
    public List<AttacheFile> storeFiles(List<MultipartFile> multipartFiles) {
        List<AttacheFile> files = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                try {
                    AttacheFile attacheFile = storeFile(multipartFile);
                    if (attacheFile != null) {
                        files.add(attacheFile);
                    }
                } catch (IOException e) {
                    log.error("Fail To Store File: {}. Error: {}", multipartFile.getOriginalFilename(), e.getMessage());
                }
            }
        }
        return files;
    }
    public AttacheFile storeFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty() || "".equals(Function.isNull(multipartFile.getOriginalFilename()))) {
            return null;
        }
        String originalFilename = multipartFile.getOriginalFilename();
        String storeFilename = createStoreFilename(originalFilename);
        multipartFile.transferTo(new File(getFullPath(storeFilename)));
        return AttacheFile.builder()
                .originalFilename(originalFilename)
                .storeFilename(storeFilename)
                .registerDate(LocalDateTime.now())
                .build();
    }
    private String createStoreFilename(String originalFilename) {
        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }
    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }

}
