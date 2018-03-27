package com.handel.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.handel.entity.Cliente;

import java.util.List;
/**
 * Created by Marco Antonio on 10/03/2018.
 */

@Dao
public interface ClienteDAO extends AbstractDAO<Cliente>{

    @Query("SELECT * FROM cliente")
    List<Cliente> getAll();

    @Query("SELECT * FROM cliente WHERE id_cliente = :idCliente")
    Cliente findUnique(final Long idCliente);
}
