package com.example.demorestapi.mappers;

public interface Mapper<T,E>{
    T toDto(E entity);
    E toEntity(T dto);
}

