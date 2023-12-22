package com.cloudStorage.cloudStorage.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cloudStorage.cloudStorage.entity.File;

@Repository
public interface FileRepository extends JpaRepository<File,Integer> {

    void deleteAllByUniqueId(String folderId);

    Optional<File> getByUniqueId(String fileId);

    
}
