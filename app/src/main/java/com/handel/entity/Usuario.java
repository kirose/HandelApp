package com.handel.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

/**
 * Created by Marco Antonio on 10/02/2018.
 */
@Entity(tableName = "usuario" ,foreignKeys =
    {
        @ForeignKey(entity = UsuarioEstatus.class,parentColumns = "id_estatus",childColumns = "id_estatus"),
        @ForeignKey(entity = UsuarioTipo.class,parentColumns = "id_tipo",childColumns = "id_tipo")
    }
)
public class Usuario extends AbstractEntity{
    @PrimaryKey
    @ColumnInfo(name = "id_usuario")
    private Long idUsuario;
    @ColumnInfo(name = "id_estatus")
    private Integer  idEstatus;
    @ColumnInfo(name = "id_tipo")
    private Integer  idTipo;
    @ColumnInfo(name = "usuario")
    private String usuario;
    @ColumnInfo(name = "email")
    private String email;
    @ColumnInfo(name = "celular")
    private String celular;
    @ColumnInfo(name = "contrasenia")
    private String contrasenia;
    @ColumnInfo(name = "fecha_registro")
    private Date fechaRegistro;
    @ColumnInfo(name = "nombre")
    private String nombre;
    @ColumnInfo(name = "img_perfil")
    private String imgPerfil;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(Integer idEstatus) {
        this.idEstatus = idEstatus;
    }

    public Integer getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImgPerfil() {
        return imgPerfil;
    }

    public void setImgPerfil(String imgPerfil) {
        this.imgPerfil = imgPerfil;
    }

    @Override
    public String getPrimaryKeyName() {
        return "idUsuario";
    }
}
