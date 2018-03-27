package com.handel.handel;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.handel.adapter.ServicioAdapter;
import com.handel.util.UtilLayout;
import com.handel.vo.ParametersVO;
import com.handel.vo.ResponseVO;
import com.handel.vo.ServicioVO;
import com.handel.ws.HandlerWS;
import com.handel.ws.ServicioWS;

import java.util.List;

public class BusquedaServicioActivity extends AbstractActivity {
    public static final String LOG_CLASS = "BusquedaServicioAct";
    private RecyclerView serviciosRV;
    private AppCompatEditText filterTxt;
    private ServicioWS wsServicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busqueda_servicio_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.busqueda_servicio_toolbar);
        wsServicio = new ServicioWS();
        serviciosRV = (RecyclerView) findViewById(R.id.busqueda_servicio_recycler_view);
        filterTxt = (AppCompatEditText) findViewById(R.id.busqueda_servicio_text);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        progressView = findViewById(R.id.busqueda_servicio_progress);

        filterTxt.setImeActionLabel(getString(R.string.search), KeyEvent.KEYCODE_ENTER);
        filterTxt.setOnEditorActionListener(
            (TextView v, int actionId, KeyEvent event) -> this.onFilterTextEditorAction(v, actionId, event)
        );

        Intent intent = getIntent();
        Log.i("INFO","Action: "+intent.getAction());
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //doSearch(query);
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    public boolean onFilterTextEditorAction(TextView v, int actionId, KeyEvent event){
        Log.i("INFO","Key: " + actionId);
        Log.i("INFO","Event: " + event);

        if (event == null) {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                // Capture soft enters in a singleLine EditText that is the last EditText
                // This one is useful for the new list case, when there are no existing ListItems
                filterTxt.clearFocus();
                //InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                //inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
            } else if (actionId == EditorInfo.IME_ACTION_NEXT) {
                // Capture soft enters in other singleLine EditTexts
            } else if (actionId == EditorInfo.IME_ACTION_GO) {
            } else {
                // Let the system handle all other null KeyEvents
                return false;
            }
        } else if (actionId == EditorInfo.IME_NULL) {
            // Capture most soft enters in multi-line EditTexts and all hard enters;
            // They supply a zero actionId and a valid keyEvent rather than
            // a non-zero actionId and a null event like the previous cases.
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                // We capture the event when the key is first pressed.
            } else {
                // We consume the event when the key is released.
                if (KeyEvent.KEYCODE_ENTER == event.getKeyCode()){

                    InputMethodManager inputMethodManager = (InputMethodManager)  getSystemService(Activity.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    serviciosRV.setVisibility(View.GONE);
                    progressView.setVisibility(View.VISIBLE);
                    showProgress(true);
                    ((ProgressBar)progressView).getIndeterminateDrawable().setColorFilter(Color.argb(255,63,81,181), PorterDuff.Mode.SRC_IN);

                    Log.i("INFO","Entered Key ENTER");
                    wsServicio.findMultipleAsync(new HandlerWS() {
                        @Override
                        public void onSuccess(ResponseVO vo) {
                            onFindServiceiosHandler(vo);
                        }
                        @Override
                        public void onError(Exception e) {
                            runOnUiThread(() -> {
                                progressView.setVisibility(View.GONE);
                                serviciosRV.setVisibility(View.VISIBLE);
                                UtilLayout.showPromptMsg(BusquedaServicioActivity.this, getMessageException(e));
                            });
                        }
                    },
                        new ParametersVO().add("oracion",filterTxt.getText().toString())
                    );
                }
                return true;
            }
        } else {
            // We let the system handle it when the listener is triggered by something that
            // wasn't an enter.
            return false;
        }
        return true;
    }
    public void onFindServiceiosHandler(final ResponseVO vo) {
        Log.i("BuscarServicio","handle");
        runOnUiThread(() -> {
            //AppCompatTextView tv = (AppCompatTextView)findViewById(R.id.busqueda_servicio_body);
            //tv.setText(vo.getDataAsList(ServicioVO.class).toString());
            Log.i("VO:",vo.toString());
            List<ServicioVO> servicios = vo.getDataAsList(ServicioVO.class);
            ServicioAdapter adapter = new ServicioAdapter(servicios);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            serviciosRV.setLayoutManager(mLayoutManager);
            serviciosRV.setItemAnimator(new DefaultItemAnimator());
            serviciosRV.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            showProgress(false);
            serviciosRV.setVisibility(View.VISIBLE);
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
                    UtilLayout.showPromptMsg(BusquedaServicioActivity.this, getMessageException(e));
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
}
