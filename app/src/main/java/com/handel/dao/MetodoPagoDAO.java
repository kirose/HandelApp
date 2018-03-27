package com.handel.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.handel.entity.MetodoPago;

import java.util.List;

/**
 * Created by Marco Antonio on 10/03/2018.
 */

@Dao
public interface MetodoPagoDAO extends AbstractDAO<MetodoPago>{

    @Query("SELECT * FROM metodo_pago")
    List<MetodoPago> getAll();

    @Query("SELECT * FROM metodo_pago WHERE id_metodo_pago = :idMetodoPago")
    MetodoPago findUnique(final Long idMetodoPago);
}
