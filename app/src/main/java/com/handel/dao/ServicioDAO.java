package com.handel.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.handel.entity.Servicio;

import java.util.List;
/**
 * Created by Marco Antonio on 10/03/2018.
 */

@Dao
public interface ServicioDAO extends AbstractDAO<Servicio>{

    @Query("SELECT * FROM servicio")
    List<Servicio> getAll();

    @Query("SELECT * FROM servicio WHERE id_servicio = :idServicio")
    Servicio findUnique(final Long idServicio);
}