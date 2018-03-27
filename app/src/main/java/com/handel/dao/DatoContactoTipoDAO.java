package com.handel.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.handel.entity.DatoContactoTipo;

import java.util.List;

/**
 * Created by Marco Antonio on 10/03/2018.
 */

@Dao
public interface DatoContactoTipoDAO extends AbstractDAO<DatoContactoTipo>{

    @Query("SELECT * FROM dato_contacto_tipo")
    List<DatoContactoTipo> getAll();

    @Query("SELECT * FROM dato_contacto_tipo WHERE id_tipo = :idTipo")
    DatoContactoTipo findUnique(final Long idTipo);
}
