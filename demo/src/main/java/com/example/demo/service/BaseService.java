package com.example.demo.service;

import java.util.List;

public interface BaseService<T> {
    T create(T DTO);
    <ID> T getByID(ID idDTO);
    List<T> getAll();
    T update(T DTO);
    <ID> void delete(ID idDTO);
}
