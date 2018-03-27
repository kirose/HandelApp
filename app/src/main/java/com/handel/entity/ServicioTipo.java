package com.handel.entity;

/**
 * Created by Marco Antonio on 10/02/2018.
 */

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "servicio_tipo")
public class ServicioTipo extends AbstractEntity{
    @PrimaryKey
    @ColumnInfo(name = "id_tipo")
    private Integer idTipo;
    @ColumnInfo(name = "tipo")
    private String tipo;

    public Integer getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String getPrimaryKeyName() {
        return "idTipo";
    }
}
