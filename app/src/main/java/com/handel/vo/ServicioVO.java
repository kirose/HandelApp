package com.handel.vo;

import com.handel.entity.Servicio;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Marco Antonio on 11/02/2018.
 */

public class ServicioVO extends Servicio{

    private String imgProveedor;
    private String nombreProveedor;
    private String emailProveedor;
    private String telefonoProveedor;
    private String calificacionProveedor;
    private List<String> imgs;
    private String oracion;

    public ServicioVO() {
    }

    public String getImgProveedor() {
        return imgProveedor;
    }

    public void setImgProveedor(String imgProveedor) {
        this.imgProveedor = imgProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getEmailProveedor() {
        return emailProveedor;
    }

    public void setEmailProveedor(String emailProveedor) {
        this.emailProveedor = emailProveedor;
    }

    public String getTelefonoProveedor() {
        return telefonoProveedor;
    }

    public void setTelefonoProveedor(String telefonoProveedor) {
        this.telefonoProveedor = telefonoProveedor;
    }

    public String getCalificacionProveedor() {
        return calificacionProveedor;
    }

    public void setCalificacionProveedor(String calificacionProveedor) {
        this.calificacionProveedor = calificacionProveedor;
    }

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }

    public String getOracion() {
        return oracion;
    }

    public void setOracion(String oracion) {
        this.oracion = oracion;
    }
}
