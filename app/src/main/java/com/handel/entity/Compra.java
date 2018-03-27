package com.handel.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

/**
 * Created by Marco Antonio on 10/03/2018.
 */
@Entity(tableName = "compra" ,foreignKeys =
        {
                @ForeignKey(entity = Cliente.class,parentColumns = "id_cliente",childColumns = "id_cliente"),
                @ForeignKey(entity = Servicio.class,parentColumns = "id_servicio",childColumns = "id_servicio"),
                @ForeignKey(entity = CompraEstatus.class,parentColumns = "id_estatus",childColumns = "id_estatus"),
                @ForeignKey(entity = MetodoPago.class,parentColumns = "id_metodo_pago",childColumns = "id_metodo_pago")
        }
)
public class Compra extends AbstractEntity{
    @PrimaryKey
    @ColumnInfo(name = "id_compra")
    private Long idCompra;
    @ColumnInfo(name = "id_cliente")
    private Long idCliente;
    @ColumnInfo(name = "id_servicio")
    private Long idServicio;
    @ColumnInfo(name = "id_estatus")
    private Integer idEstatus;
    @ColumnInfo(name = "id_metodo_pago")
    private Long idMetodoPago;
    @ColumnInfo(name = "monto")
    private Double monto;
    @ColumnInfo(name = "cantidad")
    private Double cantidad;
    @ColumnInfo(name = "calificadaCliente")
    private Boolean calificadaCliente;
    @ColumnInfo(name = "calificadaProveedor")
    private Boolean calificadaProveedor;
    @ColumnInfo(name = "fechaSolicitud")
    private Date fechaSolicitud;
    @ColumnInfo(name = "fechaConcluye")
    private Date fechaConcluye;

    public Long getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Long idCompra) {
        this.idCompra = idCompra;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Long idServicio) {
        this.idServicio = idServicio;
    }

    public Integer getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(Integer idEstatus) {
        this.idEstatus = idEstatus;
    }

    public Long getIdMetodoPago() {
        return idMetodoPago;
    }

    public void setIdMetodoPago(Long idMetodoPago) {
        this.idMetodoPago = idMetodoPago;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Boolean getCalificadaCliente() {
        return calificadaCliente;
    }

    public void setCalificadaCliente(Boolean calificadaCliente) {
        this.calificadaCliente = calificadaCliente;
    }

    public Boolean getCalificadaProveedor() {
        return calificadaProveedor;
    }

    public void setCalificadaProveedor(Boolean calificadaProveedor) {
        this.calificadaProveedor = calificadaProveedor;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Date getFechaConcluye() {
        return fechaConcluye;
    }

    public void setFechaConcluye(Date fechaConcluye) {
        this.fechaConcluye = fechaConcluye;
    }

    @Override
    public String getPrimaryKeyName() {
        return "idCompra";
    }
}
