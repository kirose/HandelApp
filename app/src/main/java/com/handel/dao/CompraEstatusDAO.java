package com.handel.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.handel.entity.CompraEstatus;

import java.util.List;

/**
 * Created by Marco Antonio on 10/03/2018.
 */

@Dao
public interface CompraEstatusDAO extends AbstractDAO<CompraEstatus>{

    @Query("SELECT * FROM compra_estatus")
    List<CompraEstatus> getAll();

    @Query("SELECT * FROM compra_estatus WHERE id_estatus = :idEstatus")
    CompraEstatus findUnique(final Long idEstatus);
}
