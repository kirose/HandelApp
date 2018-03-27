package com.handel.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.handel.entity.ServicioTipo;

import java.util.List;
/**
 * Created by Marco Antonio on 10/03/2018.
 */

@Dao
public interface ServicioTipoDAO extends AbstractDAO<ServicioTipo>{

    @Query("SELECT * FROM servicio_tipo")
    List<ServicioTipo> getAll();

    @Query("SELECT * FROM servicio_tipo WHERE id_tipo = :idTipo")
    ServicioTipo findUnique(final Long idTipo);
}