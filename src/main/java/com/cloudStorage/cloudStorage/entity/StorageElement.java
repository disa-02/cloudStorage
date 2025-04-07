package com.cloudStorage.cloudStorage.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class StorageElement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String uniqueId;

    public abstract List<File> getAllFiles();
    public abstract Folder findFolder(String uniqueId);
    public abstract File findFile(String fileId);
    public abstract StorageElement findParentFolder(String uniqueId);
}
