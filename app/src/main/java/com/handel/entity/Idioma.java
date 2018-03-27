package com.handel.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Marco Antonio on 10/02/2018.
 */
@Entity(tableName = "idioma")
public class Idioma extends AbstractEntity{
    @PrimaryKey
    @ColumnInfo(name = "id_idioma")
    private Long idIdioma;
    @ColumnInfo(name = "idioma")
    private String idioma;
    @ColumnInfo(name = "descripcion")
    private String descripcion;

    public Long getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(Long idIdioma) {
        this.idIdioma = idIdioma;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String getPrimaryKeyName() {
        return "idIdioma";
    }
}
