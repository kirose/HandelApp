package com.handel.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Marco Antonio on 10/02/2018.
 */
@Entity(tableName = "servicio" ,foreignKeys =
        {
                @ForeignKey(entity = Proveedor.class,parentColumns = "id_proveedor",childColumns = "id_proveedor"),
                @ForeignKey(entity = ServicioTipo.class,parentColumns = "id_tipo",childColumns = "id_tipo")
        }
)
public class Servicio extends AbstractEntity{
    @PrimaryKey
    @ColumnInfo(name = "id_servicio")
    private Long idServicio;
    @ColumnInfo(name = "id_proveedor")
    private Long idProveedor;
    @ColumnInfo(name = "id_tipo")
    private Integer idTipo;
    @ColumnInfo(name = "servicio")
    private String servicio;
    @ColumnInfo(name = "descripcion")
    private String descripcion;
    @ColumnInfo(name = "requiere_cotiacion")
    private Boolean requiereCotizacion;
    @ColumnInfo(name = "precio")
    private Double precio;

    public Long getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Long idServicio) {
        this.idServicio = idServicio;
    }

    public Long getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Long idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Integer getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getRequiereCotizacion() {
        return requiereCotizacion;
    }

    public void setRequiereCotizacion(Boolean requiereCotizacion) {
        this.requiereCotizacion = requiereCotizacion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    @Override
    public String getPrimaryKeyName() {
        return "idServicio";
    }
}
