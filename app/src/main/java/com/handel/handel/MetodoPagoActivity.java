package com.handel.handel;


import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.handel.util.UtilFormat;
import com.handel.vo.MetodoPagoVO;

/**
 * Created by Marco Antonio on 17/03/2018.
 */

public class MetodoPagoActivity extends AbstractActivity {

    public static final String LOG_CLASS = "MetodoPagoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.metodo_pago_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        MetodoPagoVO vo = (MetodoPagoVO)getIntent().getSerializableExtra("metodoPago");
        if (vo == null){
            return;
        }
        Log.i(LOG_CLASS,vo.toString());
        ((TextView)findViewById(R.id.metodo_pago_id_metodo_pago)).setText(""+vo.getIdMetodoPago());
        ((TextView)findViewById(R.id.metodo_pago_no_tarjeta)).setText(vo.getNoTarjeta());
        ((TextView)findViewById(R.id.metodo_pago_fecha_expiracion)).setText(UtilFormat.date("MM/yy",vo.getFechaExpiracion()));
    }
    /**
     * android.support.v7.widget.Toolbar requied for back button in toolbar
     * @return
     */
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
