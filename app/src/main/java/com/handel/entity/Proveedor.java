package com.handel.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Marco Antonio on 10/02/2018.
 */
@Entity(tableName = "proveedor" ,foreignKeys =
    {
        @ForeignKey(entity = Usuario.class,parentColumns = "id_usuario",childColumns = "id_proveedor")
    }
)
public class Proveedor extends AbstractEntity{
    @PrimaryKey
    @ColumnInfo(name = "id_proveedor")
    private Long idProveedor;

    public Long getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Long idProveedor) {
        this.idProveedor = idProveedor;
    }

    @Override
    public String getPrimaryKeyName() {
        return "idProveedor";
    }
}
