package com.handel.handel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.handel.adapter.MetodoPagoAdapter;

import com.handel.util.UtilLayout;
import com.handel.vo.ParametersVO;
import com.handel.vo.MetodoPagoVO;
import com.handel.vo.ResponseVO;
import com.handel.ws.HandlerWS;
import com.handel.ws.MetodoPagoWS;
import com.handel.ws.UsuarioWS;

/**
 * Created by Marco Antonio on 17/03/2018.
 */

public class MetodosPagoActivity extends AbstractActivity {
    private static final String LOG_CLASS = "MetodosPagoActivity";
    private MetodoPagoWS wsMetodoPago;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.metodos_pago_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.metodos_pago_toolbar);
        toolbar.setTitle(getString(R.string.title_activity_metodos_pago));
        setSupportActionBar(toolbar);
        // For back button in toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        wsMetodoPago = new MetodoPagoWS();

        wsMetodoPago.findMultipleAsync(new HandlerWS() {
                @Override
                public void onSuccess(ResponseVO vo) {
                    onFindMetodosPagoHandler(vo);
                }
                @Override
                public void onError(Exception e) {
                    runOnUiThread(() -> {
                        UtilLayout.showPromptMsg(MetodosPagoActivity.this, getMessageException(e));
                    });
                }
            },
            new ParametersVO().add("idUsuario", UsuarioWS.getUsuarioSesion().getIdUsuario())
        );
    }
    private void onFindMetodosPagoHandler(ResponseVO r){
        runOnUiThread(() -> {
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.metodos_pago_recycler_view);
            MetodoPagoAdapter adapter = new MetodoPagoAdapter(r.getDataAsList(MetodoPagoVO.class));
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        });
    }
    public void onClickMetodoPagoItem(View view){

        TextView textIdMetodoPago = view.findViewById(R.id.metodo_pago_adapter_id_metodo_pago);
        Log.i(LOG_CLASS,"getting IdProfile: "+textIdMetodoPago.getText());
        wsMetodoPago.findUniqueAsync(new HandlerWS() {
                @Override
                public void onSuccess(ResponseVO vo) {
                    onFindMetodoPagoHandler(vo);
                }
                @Override
                public void onError(Exception e) {
                    runOnUiThread(() -> {
                        UtilLayout.showPromptMsg(MetodosPagoActivity.this, getMessageException(e));
                    });
                }
            },
            new ParametersVO().add("idMetodoPago", Long.valueOf(textIdMetodoPago.getText().toString()))
        );
    }
    public void onFindMetodoPagoHandler(final ResponseVO response) {
        Log.i("BuscarMetodoPago","handle");
        runOnUiThread(() -> {
            Log.i("VO",response.toString());
            MetodoPagoVO vo = response.getDataAs(MetodoPagoVO.class);
            Log.i("MetodoPago",vo.toString());
            Intent intent = new Intent(this, MetodoPagoActivity.class);
            intent.putExtra("metodoPago",vo);
            startActivity(intent);
        });
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
