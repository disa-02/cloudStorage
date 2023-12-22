package com.cloudStorage.cloudStorage.mapper;

public interface IMapper <I, O> {
    public O map(I in);
}
