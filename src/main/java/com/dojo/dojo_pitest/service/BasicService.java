package com.dojo.dojo_pitest.service;

import java.util.List;

public interface BasicService<T> {

    T get(long id);
    List<T> getAll();
    boolean delete(long id);
}
