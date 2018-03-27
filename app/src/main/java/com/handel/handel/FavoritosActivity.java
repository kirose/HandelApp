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

import com.handel.adapter.ServicioAdapter;
import com.handel.util.UtilLayout;
import com.handel.vo.ParametersVO;
import com.handel.vo.ServicioVO;
import com.handel.vo.ResponseVO;
import com.handel.ws.FavoritoWS;
import com.handel.ws.HandlerWS;
import com.handel.ws.ServicioWS;
import com.handel.ws.UsuarioWS;

public class FavoritosActivity extends AbstractActivity {
    private static final String LOG_CLASS = "FavoritosActivity";
    private ServicioWS wsServicio;
    private FavoritoWS wsFavorito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favoritos_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.favoritos_toolbar);
        toolbar.setTitle(getString(R.string.title_activity_favoritos));
        setSupportActionBar(toolbar);
        // For back button in toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        wsServicio = new ServicioWS();
        wsFavorito = new FavoritoWS();

        wsFavorito.findMultipleAsync(new HandlerWS() {
            @Override
            public void onSuccess(ResponseVO vo) {
                onFindFavoritosHandler(vo);
            }
            @Override
            public void onError(Exception e) {
                runOnUiThread(() -> {
                    UtilLayout.showPromptMsg(FavoritosActivity.this, getMessageException(e));
                });
            }
        },
                new ParametersVO().add("idUsuario", UsuarioWS.getUsuarioSesion().getIdUsuario())
        );
    }
    private void onFindFavoritosHandler(ResponseVO r){
        runOnUiThread(() -> {
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.favoritos_recycler_view);
            ServicioAdapter adapter = new ServicioAdapter(r.getDataAsList(ServicioVO.class));
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        });
    }
    public void onClickServicioItem(View view){
        TextView textIdServicio = view.findViewById(R.id.servicio_adapter_id_servicio);
        Log.i(LOG_CLASS,"getting IdProfile: "+textIdServicio.getText().toString());
        wsServicio.findUniqueAsync(new HandlerWS() {
               @Override
               public void onSuccess(ResponseVO vo) {
                   onFindServicioHandler(vo);
               }
               @Override
               public void onError(Exception e) {
                   runOnUiThread(() -> {
                       UtilLayout.showPromptMsg(FavoritosActivity.this, getMessageException(e));
                   });
               }
            },
            new ParametersVO().add("idServicio", Long.valueOf(textIdServicio.getText().toString()))
        );
    }
    public void onFindServicioHandler(final ResponseVO vo) {
        Log.i("BuscarServicio","handle");
        runOnUiThread(() -> {
            Log.i("VO",vo.toString());
            ServicioVO servicio = vo.getDataAs(ServicioVO.class);
            Log.i("Servicio",servicio.toString());
            Intent intent = new Intent(this, ServicioActivity.class);
            intent.putExtra("servicio",servicio);
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
