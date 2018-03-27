package com.handel.ws;

import com.handel.vo.ParametersVO;
import com.handel.vo.UsuarioVO;

/**
 * Created by Marco Antonio on 28/02/2018.
 */

public class UsuarioWS extends AbstractWS {
    private static UsuarioVO usuarioSesion;
    public static void setUsuarioSesion(UsuarioVO usuarioSesion){
        UsuarioWS.usuarioSesion = usuarioSesion;
    }
    public static UsuarioVO getUsuarioSesion(){
        return usuarioSesion;
    }
    public UsuarioWS() {
        super();
    }

    public void openSesionAsync(HandlerWS handler, ParametersVO params){
        executeAsync("openSesion", handler, params);
    }
    public void closeSesionAsync(HandlerWS handler, ParametersVO params){
        executeAsync("closeSesion", handler, params);
    }
    public void closeSesion(ParametersVO params){
        execute("closeSesion", params);
    }
    public void updatePerfilAsync(HandlerWS handler, ParametersVO params){
        executeAsync("updatePerfil", handler, params);
    }
    public void updateClienteAsync(HandlerWS handler, ParametersVO params){
        executeAsync("updateCliente", handler, params);
    }
    public void updateProveedorAsync(HandlerWS handler, ParametersVO params){
        executeAsync("updateProveedor", handler, params);
    }
}
