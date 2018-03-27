package com.handel.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.handel.entity.MetodoPagoEstatus;

import java.util.List;

/**
 * Created by Marco Antonio on 10/03/2018.
 */

@Dao
public interface MetodoPagoEstatusDAO extends AbstractDAO<MetodoPagoEstatus>{

    @Query("SELECT * FROM metodo_pago_estatus")
    List<MetodoPagoEstatus> getAll();

    @Query("SELECT * FROM metodo_pago_estatus WHERE id_estatus = :idEstatus")
    MetodoPagoEstatus findUnique(final Long idEstatus);
}

