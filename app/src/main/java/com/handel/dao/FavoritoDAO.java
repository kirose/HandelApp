package com.handel.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.handel.entity.Favorito;

import java.util.List;

/**
 * Created by Marco Antonio on 10/03/2018.
 */

@Dao
public interface FavoritoDAO extends AbstractDAO<Favorito>{

    @Query("SELECT * FROM favorito")
    List<Favorito> getAll();

    @Query("SELECT * FROM favorito WHERE id_favorito = :idFavorito")
    Favorito findUnique(final Long idFavorito);
}
