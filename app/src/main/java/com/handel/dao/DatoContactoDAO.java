package com.handel.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.handel.entity.DatoContacto;

import java.util.List;
/**
 * Created by Marco Antonio on 10/03/2018.
 */

@Dao
public interface DatoContactoDAO extends AbstractDAO<DatoContacto>{

    @Query("SELECT * FROM dato_contacto")
    List<DatoContacto> getAll();

    @Query("SELECT * FROM dato_contacto WHERE id_dato_contacto = :idDatoContacto")
    DatoContacto findUnique(final Long idDatoContacto);
}
