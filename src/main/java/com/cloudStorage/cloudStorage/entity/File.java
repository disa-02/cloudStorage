package com.cloudStorage.cloudStorage.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class File extends StorageElement {

    private String type;
    

    @Override
    public List<File> getAllFiles() {
        List<File> files = new ArrayList<>();
        files.add(this);
        return files;
    }


    @Override
    public Folder findFolder(String uniqueId) {
        return null;
    }


    @Override
    public File findFile(String fileId) {
        if(fileId.equals(this.getUniqueId()))
            return this;
        return null;
    }


    @Override
    public StorageElement findParentFolder(String uniqueId) {
        return null;
    }


    



    
}
