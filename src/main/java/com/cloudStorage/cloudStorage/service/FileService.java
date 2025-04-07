package com.cloudStorage.cloudStorage.service;


import org.springframework.stereotype.Service;

import com.cloudStorage.cloudStorage.entity.File;
import com.cloudStorage.cloudStorage.entity.Folder;
import com.cloudStorage.cloudStorage.repository.FileRepository;
import com.cloudStorage.exception.ApiErrorException;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class FileService {



    private final FolderService folderService;
    private final SystemFileService systemFileService;
    private final FileRepository fileRepository;

    public void add(Integer userId, File newFile, String parentFolderId) {
        Folder parentFolder = folderService.findFolder(userId, parentFolderId);
        parentFolder.addElement(newFile);
        folderService.save(parentFolder);
    }



    public java.io.File dowload(Integer userId, String fileId) {
        File file = folderService.findFile(userId,fileId);
        
        java.io.File systemFile = systemFileService.downloadFile(file);

        return systemFile;
        
    }

    public void delete(Integer userId, String fileId, String folderId) {
        File file = fileRepository.getByUniqueId(fileId).orElseThrow(() -> new ApiErrorException("File not found"));
        Folder folder = folderService.findFolder(userId, folderId);
        Boolean remove = folder.deleteElement(fileId);
        if(!remove)
            throw new ApiErrorException("File not found");
        folderService.save(folder);
        systemFileService.delete(file);
    }


    
}
