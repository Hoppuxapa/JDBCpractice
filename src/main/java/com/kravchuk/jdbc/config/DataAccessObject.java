package com.kravchuk.jdbc.config;

import java.util.List;

public abstract class DataAccessObject <T extends DataTransferObject> {
    public abstract void createOrUpdate(T object);
   /* public abstract void update(T object);*/
    public abstract T getById(int id);
    public abstract List<T> getAll();
    public abstract void remove(int id);
}

