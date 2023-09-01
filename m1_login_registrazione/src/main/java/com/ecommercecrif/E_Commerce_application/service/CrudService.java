package com.ecommercecrif.E_Commerce_application.service;

import java.util.Collection;

public interface CrudService<T, Long>{

    T addUser(T entity);

    T updateUser(String email, T updatedEntity);

    T findByEmail(String email);

    Collection<T> findAll();

    boolean deleteByEmail(String email);

    boolean existByEmail(String email);


}
