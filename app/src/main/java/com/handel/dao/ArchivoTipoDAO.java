package com.handel.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.handel.entity.ArchivoTipo;

import java.util.List;

/**
 * Created by Marco Antonio on 10/03/2018.
 */

@Dao
public interface ArchivoTipoDAO extends AbstractDAO<ArchivoTipo>{

    @Query("SELECT * FROM archivo_tipo")
    List<ArchivoTipo> getAll();

    @Query("SELECT * FROM archivo_tipo WHERE id_tipo = :idTipo")
    ArchivoTipo findUnique(final Integer idTipo);
}
