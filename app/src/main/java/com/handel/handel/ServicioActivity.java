package com.handel.handel;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.handel.util.UtilFormat;
import com.handel.util.UtilLayout;
import com.handel.vo.ParametersVO;
import com.handel.vo.ServicioVO;
import com.handel.web.HandlerFM;
import com.handel.web.FileManager;

import java.util.List;

public class ServicioActivity extends AbstractActivity {
    public static final String LOG_CLASS = "ServicioActivity";

    private FileManager fileManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.servicio_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ServicioVO servicio = (ServicioVO)getIntent().getSerializableExtra("servicio");
        if (servicio == null){
            return;
        }
        fileManager = new FileManager(this);
        Log.i(LOG_CLASS,servicio.toString());
        List<String> imgs =  servicio.getImgs();
        if (imgs != null && !imgs.isEmpty()){
            fileManager.executeAsync(
                imgs.get(0),
                new HandlerFM() {
                    @Override
                    public void onSuccess(Bitmap img) {
                        runOnUiThread(() -> {
                            Log.i(LOG_CLASS,"Image downloaded: " + imgs.get(0));
                            ((ImageView) findViewById(R.id.servicio_image)).setImageBitmap(img);
                        });
                    }
                    @Override
                    public void onError(Exception e) {
                        runOnUiThread(() -> {
                            UtilLayout.showPromptMsg(ServicioActivity.this,getMessageException(e));
                        });
                    }
                },
                new ParametersVO().add("height",300).add("width",420)
            );
        }
        ((TextView)findViewById(R.id.servicio_servicio)).setText(servicio.getServicio());
        ((TextView)findViewById(R.id.servicio_calificacion_proveedor)).setText(servicio.getCalificacionProveedor());
        ((TextView)findViewById(R.id.servicio_nombre_proveedor)).setText(servicio.getNombreProveedor());
        ((TextView)findViewById(R.id.servicio_precio)).setText(UtilFormat.money(servicio.getPrecio()));
        ((TextView)findViewById(R.id.servicio_descripcion)).setText(servicio.getDescripcion());
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
