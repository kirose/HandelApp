package com.handel.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.handel.entity.Usuario;

import java.util.List;

/**
 * Created by Marco Antonio on 10/03/2018.
 */
@Dao
public interface UsuarioDAO extends AbstractDAO<Usuario>{

    @Query("SELECT * FROM usuario")
    List<Usuario> getAll();

    @Query("SELECT * FROM usuario WHERE id_usuario = :idUsuario")
    Usuario findUnique(final Long idUsuario);
}
