package com.handel.entity;

/**
 * Created by Marco Antonio on 10/03/2018.
 */

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "compra_estatus")
public class CompraEstatus extends AbstractEntity{
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
