package com.handel.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.handel.vo.ParametersVO;
import com.handel.ws.UsuarioWS;

/**
 * Created by Marco Antonio on 15/03/2018.
 * Este servicio se invoca al cerrar la aplicacion
 * Cierre automatico de sesion
 */

public class CloseSesionService extends IntentService {

    private static final String LOG_CLASS = "CloseSesionService";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */
    public CloseSesionService() {
        super("CloseSesionService");
    }


    @Override
    protected void onHandleIntent(Intent workIntent) {
        Long idSesion = workIntent.getLongExtra("idSesion",0L);
        Log.i(LOG_CLASS,"Cierre de Sesion automatico, idSesion: "+idSesion);
        if (idSesion.equals(0L)){
            Log.i(LOG_CLASS,"No se envio idSesion");
            return;
        }
        new UsuarioWS().closeSesion(new ParametersVO().add("idSesion",idSesion));
        Log.i(LOG_CLASS,"Sesion cerrada idSesion: "+idSesion);
        //CloseSesionService.this.stopSelf();
    }

}
