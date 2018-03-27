package com.handel.dao;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

import com.handel.entity.AbstractEntity;


/**
 * Created by Marco Antonio on 10/03/2018.
 */

public interface AbstractDAO <T extends AbstractEntity>{

    @Insert
    void insert(T entity);

    @Update
    void update(T ... entities);

    @Delete
    void delete(T... entities);

    /*
    *   **************************************************************
    *   Importante este medoto debe de implementarse en todos los daos
    *   Room no  permite declarar unmetodo sin la anotacio Query, Insert, Update
    *   **************************************************************
    */
    //T findUnique(Long id);

    //@Query("select * from T limit 1")
    //List<T> findAll();
}
