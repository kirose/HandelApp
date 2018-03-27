package com.handel.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.handel.entity.Idioma;

import java.util.List;

/**
 * Created by Marco Antonio on 10/03/2018.
 */

@Dao
public interface IdiomaDAO extends AbstractDAO<Idioma>{

    @Query("SELECT * FROM idioma")
    List<Idioma> getAll();

    @Query("SELECT * FROM idioma WHERE id_idioma = :idIdioma")
    Idioma findUnique(final Long idIdioma);
}
