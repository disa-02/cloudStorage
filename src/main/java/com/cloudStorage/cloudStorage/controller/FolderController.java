package com.cloudStorage.cloudStorage.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloudStorage.cloudStorage.dto.in.FolderInDto;
import com.cloudStorage.cloudStorage.dto.out.StorageElementOutDto;
import com.cloudStorage.cloudStorage.service.FolderService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;




@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/api/folder")
@AllArgsConstructor
public class FolderController {

    private final FolderService folderService;

    @PostMapping("/{userId}")
    @PreAuthorize("#userId == principal.id")
    public ResponseEntity<?> create(@RequestParam Integer userId,@RequestBody FolderInDto folderInDto) {
        folderService.create(userId, folderInDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @GetMapping("/{fileId}/{userId}")
    @PreAuthorize("#userId == principal.id")
    public ResponseEntity<?> getElementsOfFile(@RequestParam Integer userId,@RequestParam String folderId){
        List<StorageElementOutDto> elements = folderService.findAllelementsOfFolder(userId,folderId);
        return new ResponseEntity<>(elements,HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/{uniqueId}")
    @PreAuthorize("#userId == principal.id")
    @Transactional
    public ResponseEntity<?> deleteElement(@RequestParam Integer userId, @RequestParam String uniqueId){
        folderService.deleteElement(userId,uniqueId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    
}
