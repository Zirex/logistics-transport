package com.ingeneo.logistics.services;

import java.util.List;

public interface ICRUDService<T, ID> {
    List<T> findAll(String state);
    T findById(ID id);
    T create(T o);
    T update(T o);
}
