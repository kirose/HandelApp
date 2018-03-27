package com.handel.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Marco Antonio on 10/02/2018.
 */
@Entity(tableName = "cliente" ,foreignKeys =
    {
        @ForeignKey(entity = Usuario.class,parentColumns = "id_usuario",childColumns = "id_cliente")
    }
)
public class Cliente extends AbstractEntity{

    @PrimaryKey
    @ColumnInfo(name = "id_cliente")
    private Long idCliente;

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public String getPrimaryKeyName() {
        return "idCliente";
    }
}
