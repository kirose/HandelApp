package com.handel.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.handel.entity.UsuarioEstatus;

import java.util.List;
/**
 * Created by Marco Antonio on 10/03/2018.
 */

@Dao
public interface UsuarioEstatusDAO extends AbstractDAO<UsuarioEstatus>{

    @Query("SELECT * FROM usuario_estatus")
    List<UsuarioEstatus> getAll();

    @Query("SELECT * FROM usuario_estatus WHERE id_estatus = :idEstatus")
    UsuarioEstatus findUnique(final Long idEstatus);
}