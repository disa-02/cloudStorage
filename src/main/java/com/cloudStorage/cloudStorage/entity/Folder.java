package com.cloudStorage.cloudStorage.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Folder extends StorageElement {

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    private List<StorageElement> elements = new ArrayList<>();

    public void addElement(StorageElement se){
        elements.add(se);
    }


    @Override
    public List<File> getAllFiles() {
        List<File> files = new ArrayList<>();
        for(StorageElement se: elements){
            files.addAll(se.getAllFiles());
        }
        return files;
    }

    public List<Folder> getFolders() {
        List<Folder> folders = new ArrayList<>();
        for(StorageElement se: elements){
            if(se instanceof Folder)
                folders.add((Folder) se);
        }
        return folders;
    }

    public List<File> getFiles() {
        List<File> files = new ArrayList<>();
        for(StorageElement se: elements){
            if(se instanceof File)
                files.add((File) se);
        }
        return files;
    }

    public Boolean deleteElement(String uniqueId){
        for(StorageElement se: elements){
            if(uniqueId.equals(se.getUniqueId())){
                elements.remove(se);
                return true;
            }
        }
        return false;
    }

    @Override
    public Folder findFolder(String uniqueId) {
        if(uniqueId.equals(this.getUniqueId()))
            return this;
        for(StorageElement se: elements){
            Folder folder = se.findFolder(uniqueId);
            if(folder != null)
                return folder;
        }
        return null;
    }


    @Override
    public File findFile(String fileId) {
        for(StorageElement se: elements){
            File file = se.findFile(fileId);
            if(file != null)
                return file;
        }
        return null;
    }


    public Folder findParentFolder(String uniqueId) {
        for(StorageElement se: elements){
            if(se.getUniqueId().equals(uniqueId))
                return this;
            Folder folder = (Folder) se.findParentFolder(uniqueId);
            if(folder != null)
                return folder;
        }
        return null;
    }


    
}
