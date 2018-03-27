package com.handel.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Marco Antonio on 10/03/2018.
 */
@Entity(tableName = "favorito" ,foreignKeys =
    {
        @ForeignKey(entity = Usuario.class,parentColumns = "id_usuario",childColumns = "id_usuario")
    }
)
public class Favorito extends AbstractEntity{
    @PrimaryKey
    @ColumnInfo(name = "id_favorito")
    private Long idFavorito;
    @ColumnInfo(name = "id_usuario")
    private Long idUsuario;
    @ColumnInfo(name = "id_servicio")
    private Long idServicio;

    public Long getIdFavorito() {
        return idFavorito;
    }

    public void setIdFavorito(Long idFavorito) {
        this.idFavorito = idFavorito;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Long idServicio) {
        this.idServicio = idServicio;
    }

    @Override
    public String getPrimaryKeyName() {
        return "idFavorito";
    }
}
