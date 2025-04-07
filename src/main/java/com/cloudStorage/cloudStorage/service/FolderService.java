package com.cloudStorage.cloudStorage.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.cloudStorage.cloudStorage.dto.in.FolderInDto;
import com.cloudStorage.cloudStorage.dto.out.StorageElementOutDto;
import com.cloudStorage.cloudStorage.entity.File;
import com.cloudStorage.cloudStorage.entity.Folder;
import com.cloudStorage.cloudStorage.entity.StorageElement;
import com.cloudStorage.cloudStorage.entity.User;
import com.cloudStorage.cloudStorage.mapper.StorageElementToStorageElementOutDto;
import com.cloudStorage.cloudStorage.repository.FolderRepository;
import com.cloudStorage.exception.ApiErrorException;

import lombok.AllArgsConstructor;



@Service
@AllArgsConstructor
public class FolderService {

    private final FolderRepository folderRepository;
    private final UserService userService;
    private final SystemFileService systemFileService;
    private final StorageElementToStorageElementOutDto storageElementToStorageElementOutDto;

    public void save(Folder folder) {
        folderRepository.save(folder);
    }

    public Folder findFolder(Integer userId, String parentFolderId){
        User user = userService.findById(userId).orElseThrow(() -> new ApiErrorException("User not found"));
        Folder mainFolder = user.getMainFolder();
        Folder folder = mainFolder.findFolder(parentFolderId);
        if(folder == null)
            throw new ApiErrorException("Folder not found");
        return folder;
    }

    public void create(Integer userId, FolderInDto folderInDto) {
        String parentFolderId = folderInDto.getParentFolderId();
        Folder parentFolder = findFolder(userId, parentFolderId);
        Folder folder = new Folder();
        folder.setName(folderInDto.getName());
        folder.setUniqueId(UUID.randomUUID().toString());
        parentFolder.addElement(folder);
        folderRepository.save(parentFolder);
    }

    public File findFile(Integer userId, String fileId) {
        User user = userService.findById(userId).orElseThrow(() -> new ApiErrorException("User not found"));
        Folder mainFolder = user.getMainFolder();
        File file = mainFolder.findFile(fileId);
        if(file == null)
            throw new ApiErrorException("File not found");
        return file;
    }

    private Folder findParentFolder(Integer userId, String uniqueId){
        User user = userService.findById(userId).orElseThrow(() -> new ApiErrorException("User not found"));
        Folder mainFolder = user.getMainFolder();
        return mainFolder.findParentFolder(uniqueId);

    }

    public void deleteElement(Integer userId, String uniqueId) {
        Folder parentFolder = this.findParentFolder(userId, uniqueId);
        if(parentFolder == null){
            throw new ApiErrorException("Parent folder not found");
        }
        Folder folder = folderRepository.findByUniqueId(uniqueId).orElseThrow(() -> new ApiErrorException("Folder not found"));
        List<File> files = folder.getAllFiles();
        Boolean remove = parentFolder.deleteElement(uniqueId);
        if(!remove)
            throw new ApiErrorException("Folder not found");
        this.save(folder);
        for(File file: files)
            systemFileService.delete(file);
    }

    public List<StorageElementOutDto> findAllelementsOfFolder(Integer userId, String folderId) {
        Folder parentFolder = this.findFolder(userId, folderId);
        List<StorageElement> elements = parentFolder.getElements();
        List<StorageElementOutDto> outElements = new ArrayList<>();
        for(StorageElement se: elements){
            StorageElementOutDto out = storageElementToStorageElementOutDto.map(se);
            outElements.add(out);
        }
        return outElements;
    }
    

}
