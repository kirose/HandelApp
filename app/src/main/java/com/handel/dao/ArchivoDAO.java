package com.handel.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.handel.entity.Archivo;

import java.util.List;

/**
 * Created by Marco Antonio on 10/03/2018.
 */

@Dao
public interface ArchivoDAO extends AbstractDAO<Archivo>{

    @Query("SELECT * FROM archivo")
    List<Archivo> getAll();

    @Query("SELECT * FROM archivo WHERE id_archivo = :idArchivo")
    Archivo findUnique(final Long idArchivo);
}
