package com.cloudStorage.cloudStorage.mapper;

import org.springframework.stereotype.Component;

import com.cloudStorage.cloudStorage.dto.out.StorageElementOutDto;
import com.cloudStorage.cloudStorage.entity.StorageElement;

@Component
public class StorageElementToStorageElementOutDto implements IMapper<StorageElement,StorageElementOutDto>{

    @Override
    public StorageElementOutDto map(StorageElement in) {
        StorageElementOutDto storageElementOutDto = new StorageElementOutDto();
        storageElementOutDto.setName(in.getName());
        storageElementOutDto.setUniqueId(in.getUniqueId());
        return storageElementOutDto;
    }
    
}
