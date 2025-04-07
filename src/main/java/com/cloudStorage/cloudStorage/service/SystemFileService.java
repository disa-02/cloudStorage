package com.cloudStorage.cloudStorage.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudStorage.exception.ApiErrorException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SystemFileService {
    private final String mainUrl = "src/main/resources/files";


    public void add(String fileId, MultipartFile file, String folderId) throws IOException {
        Path path = Paths.get(mainUrl);
        File folder = new File(mainUrl);
        if(!folder.exists()){
            folder.mkdir();
        }
            
        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        Path fullPath = path.resolve(fileId + extension);
        if(fullPath.toFile().exists())
            throw new ApiErrorException("Cannot upload the file, it already exists");
        
        byte[] bytes = file.getBytes();
        Files.write(fullPath, bytes);
    }


    public File downloadFile(com.cloudStorage.cloudStorage.entity.File file) {
        String extension = file.getName().substring(file.getName().lastIndexOf("."));
        String url = mainUrl + "/" + file.getUniqueId() + extension;
        File systemFile = new File(url);
        if(!systemFile.exists())
            throw new ApiErrorException("File not found in system");
        return systemFile;
    }


    public void delete(com.cloudStorage.cloudStorage.entity.File file) {
        String extension = file.getName().substring(file.getName().lastIndexOf("."));
        String url = mainUrl + "/" + file.getUniqueId() + extension;
        File systemFile = new File(url);
        if(!systemFile.exists())
            throw new ApiErrorException("File not found in system");
        Boolean deleted = systemFile.delete();
        if(!deleted)
            throw new ApiErrorException("Failed to delete the file");
    }


}

