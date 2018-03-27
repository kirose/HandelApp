package com.handel.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.handel.entity.Proveedor;

import java.util.List;

/**
 * Created by Marco Antonio on 10/03/2018.
 */

@Dao
public interface ProveedorDAO extends AbstractDAO<Proveedor>{

    @Query("SELECT * FROM proveedor")
    List<Proveedor> getAll();

    @Query("SELECT * FROM proveedor WHERE id_proveedor = :idProveedor")
    Proveedor findUnique(final Long idProveedor);
}