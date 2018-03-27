package com.handel.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Marco Antonio on 10/02/2018.
 */
@Entity(tableName = "archivo" ,foreignKeys =
    {
        @ForeignKey(entity = ArchivoTipo.class,parentColumns = "id_tipo",childColumns = "id_tipo")
    }
)
public class Archivo extends AbstractEntity{
    @PrimaryKey
    @ColumnInfo(name = "id_archivo")
    private Long idArchivo;
    @ColumnInfo(name = "id_tipo")
    private Integer idTipo;
    @ColumnInfo(name = "archivo")
    private String archivo;

    public Long getIdArchivo() {
        return idArchivo;
    }

    public void setIdArchivo(Long idArchivo) {
        this.idArchivo = idArchivo;
    }

    public Integer getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    @Override
    public String getPrimaryKeyName() {
        return "idArchivo";
    }
}
