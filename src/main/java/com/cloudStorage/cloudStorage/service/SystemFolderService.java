package com.cloudStorage.cloudStorage.service;

import java.io.File;

import org.springframework.stereotype.Service;

import com.cloudStorage.exception.ApiErrorException;

@Service
public class SystemFolderService {
    private String mainUrl = "src/main/resources/files";

    public SystemFolderService(){
        File folder = new File(mainUrl);
        if(!folder.exists()){
            folder.mkdir();
        }
    }
    
    public void createFolder(String url){
        File folder = new File(mainUrl + "/" + url);
        if(folder.exists()){
            throw new ApiErrorException("The folder is already exist");
        }
        boolean created = folder.mkdir();
        if(!created){
            throw new ApiErrorException("The folder parent is not correct");
        }
    }
}
