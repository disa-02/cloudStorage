package com.cloudStorage.cloudStorage.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloudStorage.cloudStorage.dto.out.StorageElementOutDto;
import com.cloudStorage.cloudStorage.entity.File;
import com.cloudStorage.cloudStorage.service.FileService;
import com.cloudStorage.cloudStorage.service.SystemFileService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/api/file")
@CrossOrigin
public class FileController {

    private SystemFileService systemFileService;
    private FileService fileService;


    public FileController(SystemFileService systemFileService, FileService fileService){
        this.systemFileService = systemFileService;
        this.fileService = fileService;
    }
    
    @PostMapping("")
    @PreAuthorize("#userId == principal.id")
    @Transactional
    public ResponseEntity<?> add(@RequestParam Integer userId,@RequestParam MultipartFile file,@RequestParam String parentFolderId) throws IOException {
        String fileName = file.getOriginalFilename();
        String fileId = UUID.randomUUID().toString();
        File newFile = new File();
        newFile.setName(fileName);
        newFile.setUniqueId(fileId);

        //BD
        fileService.add(userId,newFile,parentFolderId);
        //system
        systemFileService.add(fileId,file,parentFolderId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    
    
    @GetMapping("/{fileId}/dowloadFile/{userId}")
    @PreAuthorize("#userId == principal.id")
    public ResponseEntity<?> get(@RequestParam String fileId,@RequestParam Integer userId) throws IOException {
        java.io.File file = fileService.dowload(userId,fileId);

        byte[] fileContent = Files.readAllBytes(file.toPath());

        return new ResponseEntity<>(fileContent,HttpStatus.OK);
    }
    
    @DeleteMapping("/{fileId}/{userId}/{folderId}")
    @PreAuthorize("#userId == principal.id")
    @Transactional
    public ResponseEntity<?> delete( @PathVariable String fileId,@PathVariable Integer userId,@PathVariable String folderId){
        fileService.delete(userId,fileId,folderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
