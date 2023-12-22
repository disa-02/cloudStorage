package com.cloudStorage.cloudStorage.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cloudStorage.cloudStorage.dto.out.StorageElementOutDto;
import com.cloudStorage.cloudStorage.entity.File;
import com.cloudStorage.cloudStorage.entity.Folder;
import com.cloudStorage.cloudStorage.entity.StorageElement;
import com.cloudStorage.cloudStorage.entity.User;
import com.cloudStorage.cloudStorage.mapper.StorageElementToStorageElementOutDto;
import com.cloudStorage.cloudStorage.repository.FileRepository;
import com.cloudStorage.exception.ApiErrorException;


@Service
public class FileService {



    private FolderService folderService;
    private StorageElementToStorageElementOutDto storageElementToStorageElementOutDto;
    private SystemFileService systemFileService;
    private FileRepository fileRepository;

    public FileService(FolderService folderService,StorageElementToStorageElementOutDto storageElementToStorageElementOutDto,SystemFileService systemFileService, FileRepository fileRepository){
        this.storageElementToStorageElementOutDto = storageElementToStorageElementOutDto;
        this.folderService = folderService;
        this.systemFileService = systemFileService;
        this.fileRepository = fileRepository;
    }

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
