package com.handel.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

/**
 * Created by Marco Antonio on 10/02/2018.
 */
@Entity(tableName = "metodo_pago" ,foreignKeys =
    {
        @ForeignKey(entity = Usuario.class,parentColumns = "id_usuario",childColumns = "id_usuario"),
        @ForeignKey(entity = MetodoPagoEstatus.class,parentColumns = "id_estatus",childColumns = "id_estatus")
    }
)
public class MetodoPago extends AbstractEntity{
    @PrimaryKey
    @ColumnInfo(name = "id_metodo_pago")
    private Long idMetodoPago;
    @ColumnInfo(name = "id_estatus")
    private Integer idEstatus;
    @ColumnInfo(name = "id_usuario")
    private Long idUsuario;
    @ColumnInfo(name = "id_tipo")
    private String tipo;
    @ColumnInfo(name = "no_tarjeta")
    private String noTarjeta;
    @ColumnInfo(name = "ccv")
    private String ccv;
    @ColumnInfo(name = "fecha_exipracion")
    private Date fechaExpiracion;

    public Long getIdMetodoPago() {
        return idMetodoPago;
    }

    public void setIdMetodoPago(Long idMetodoPago) {
        this.idMetodoPago = idMetodoPago;
    }

    public Integer getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(Integer idEstatus) {
        this.idEstatus = idEstatus;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNoTarjeta() {
        return noTarjeta;
    }

    public void setNoTarjeta(String noTarjeta) {
        this.noTarjeta = noTarjeta;
    }

    public String getCcv() {
        return ccv;
    }

    public void setCcv(String ccv) {
        this.ccv = ccv;
    }

    public Date getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(Date fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    @Override
    public String getPrimaryKeyName() {
        return "idMetodoPago";
    }
}
