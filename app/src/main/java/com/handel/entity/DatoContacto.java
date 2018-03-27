package com.handel.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Marco Antonio on 10/02/2018.
 */
@Entity(tableName = "dato_contacto" ,foreignKeys =
    {
        @ForeignKey(entity = Usuario.class,parentColumns = "id_usuario",childColumns = "id_usuario"),
        @ForeignKey(entity = DatoContactoTipo.class,parentColumns = "id_tipo",childColumns = "id_tipo")
    }
)
public class DatoContacto extends AbstractEntity{
    @PrimaryKey
    @ColumnInfo(name = "id_dato_contacto")
    private Long idDatoContacto;
    @ColumnInfo(name = "id_usuario")
    private Integer idUsuario;
    @ColumnInfo(name = "id_tipo")
    private Integer idTipo;
    @ColumnInfo(name = "dato_contacto")
    private String datoContacto;

    public Long getIdDatoContacto() {
        return idDatoContacto;
    }

    public void setIdDatoContacto(Long idDatoContacto) {
        this.idDatoContacto = idDatoContacto;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public String getDatoContacto() {
        return datoContacto;
    }

    public void setDatoContacto(String datoContacto) {
        this.datoContacto = datoContacto;
    }

    @Override
    public String getPrimaryKeyName() {
        return "idDatoContacto";
    }
}
