package com.handel.dao;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.handel.entity.UsuarioTipo;

import java.util.List;

/**
 * Created by Marco Antonio on 10/03/2018.
 */

@Dao
public interface UsuarioTipoDAO extends AbstractDAO<UsuarioTipo>{

    @Query("SELECT * FROM usuario_tipo")
    List<UsuarioTipo> getAll();

    @Query("SELECT * FROM usuario_tipo WHERE id_tipo = :idTipo")
    UsuarioTipo findUnique(final Long idTipo);
}