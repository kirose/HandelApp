package com.handel.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.handel.entity.Compra;

import java.util.List;

/**
 * Created by Marco Antonio on 10/03/2018.
 */

@Dao
public interface CompraDAO extends AbstractDAO<Compra>{

    @Query("SELECT * FROM compra")
    List<Compra> getAll();

    @Query("SELECT * FROM compra WHERE id_compra = :idCompra")
    Compra findUnique(final Long idCompra);
}
