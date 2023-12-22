package com.cloudStorage.cloudStorage.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cloudStorage.cloudStorage.entity.Folder;

@Repository
public interface FolderRepository extends JpaRepository<Folder,Integer> {

    Optional<Folder> findByUniqueId(String uniqueId);



}
