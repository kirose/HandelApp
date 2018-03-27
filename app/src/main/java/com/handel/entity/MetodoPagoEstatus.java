package com.handel.entity;

/**
 * Created by Marco Antonio on 10/02/2018.
 */

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "metodo_pago_estatus")
public class MetodoPagoEstatus extends AbstractEntity{
    @PrimaryKey
    @ColumnInfo(name = "id_estatus")
    private Integer idEstatus;
    @ColumnInfo(name = "estatus")
    private String estatus;

    public Integer getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(Integer idEstatus) {
        this.idEstatus = idEstatus;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    @Override
    public String getPrimaryKeyName() {
        return "idEstatus";
    }
}
