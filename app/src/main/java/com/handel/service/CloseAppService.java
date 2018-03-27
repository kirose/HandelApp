package com.handel.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Marco Antonio on 17/03/2018.
 */

public class CloseAppService extends Service {
    private static final String LOG_CLASS = "CloseAppService";
    private Long idSesion;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        this.idSesion = intent.getLongExtra("idSesion",0L);
        return Service.START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {

        Log.i(LOG_CLASS,"Invoke CloseSesionService idSesion: "+idSesion);
        Intent service = new Intent(getApplicationContext(), CloseSesionService.class);
        service.putExtra("idSesion",idSesion);
        startService(service);

        super.onTaskRemoved(rootIntent);
        //do something you want
        //stop service
        this.stopSelf();
    }

}
