package com.handel.handel;

import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.handel.vo.ResponseVO;
import com.handel.ws.HandlerWS;
import com.handel.ws.UsuarioWS;

public class MenuActivity extends AbstractActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static final String LOG_CLASS = "MenuActivity";

    private boolean cameraTurnOn;
    private static Camera camera;
    private DrawerLayout drawerLayout;
    private ProgressBar progressView;
    private UsuarioWS wsUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.menu_toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.menu_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.menu_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        progressView = (ProgressBar)findViewById(R.id.menu_progress);
        wsUsuario = new UsuarioWS();

        ConstraintLayout layout = (ConstraintLayout)findViewById(R.id.menu_content_layout);
        TextView text = new TextView(this);
        text.setText("TextView added dynamically"+getString(R.string.unauthorized));
        //ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams();
        //LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT,1.0f);
        //text.setLayoutParams(params);
        //layout.addView(text);

        /*
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.menu_recomendados_recycler_view);

        ServicioAdapter adapter = new ServicioAdapter(ServicioWS.findServicios(null));
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        */
    }
    @Override
    public void onStart(){
        super.onStart();
        Log.i(LOG_CLASS,"Started");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.menu_drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_options, menu);

        ((TextView)findViewById(R.id.txtName)).setText("Marco Antonio Salazar");
        ((TextView)findViewById(R.id.txtEmail)).setText("marco.salazar@gmail.com");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_buys) {

        } else if (id == R.id.nav_favorites) {
            Log.i(LOG_CLASS,"Favoritos");
        } else if (id == R.id.nav_messages) {

        } else if (id == R.id.nav_payment_methods) {

        } else if (id == R.id.nav_cfg_account) {

        } else if (id == R.id.nav_legal) {

        } else if (id == R.id.nav_exit) {

        }
        Log.i(LOG_CLASS,"Click Item Menu "+item.getItemId());
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    public boolean onClickTools(MenuItem item){
        Toast.makeText(this,"Tools",Toast.LENGTH_SHORT);
        Log.i(LOG_CLASS,"Click Tools");
        return true;
    }
    public boolean onClickGallery(MenuItem item){
        Toast.makeText(this,"Gallery",Toast.LENGTH_SHORT);
        Log.i(LOG_CLASS,"Click Gallery");
        // This code snippet will cause the phone to vibrate "SOS" in Morse Code
        // In Morse Code, "s" = "dot-dot-dot", "o" = "dash-dash-dash"
        // There are pauses to separate dots/dashes, letters, and words
        // The following numbers represent millisecond lengths
        int dot = 200;      // Length of a Morse Code "dot" in milliseconds
        int dash = 500;     // Length of a Morse Code "dash" in milliseconds
        int short_gap = 200;    // Length of Gap Between dots/dashes
        int medium_gap = 500;   // Length of Gap Between Letters
        int long_gap = 1000;    // Length of Gap Between Words
        long[] pattern = {
                0,  // Start immediately
                dot, short_gap, dot, short_gap, dot,    // s
                medium_gap,
                dash, short_gap, dash, short_gap, dash, // o
                medium_gap,
                dot, short_gap, dot, short_gap, dot,    // s
                long_gap
        };
        // Only perform this pattern one time (-1 means "do not repeat")
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator.hasVibrator()) {
            vibrator.vibrate(pattern, -1);
        }

        return true;
    }
    public boolean onClickCamera(MenuItem item){
        Toast.makeText(this,"Camera",Toast.LENGTH_SHORT);
        Log.i(LOG_CLASS,"Click Camera");
        Intent intent = new Intent(this, CameraActivity.class);
        startActivity(intent);
        return true;
    }
    public void onClickServicioItem(View view){
        /*
        TextView textIdServicio = view.findViewById(R.id.servicio_item_id_servicio);
        Log.i(LOG_CLASS,"getting IdProfile: "+textIdServicio.getText().toString());
        ServicioVO servicio = ServicioWS.find(Long.valueOf(textIdServicio.getText().toString()));
        Intent intent = new Intent(this, ServicioActivity.class);
        intent.putExtra("servicio",servicio);
        startActivity(intent);
        */
    }
    public void onSearchServicios(View view){
        startActivity(new Intent(this, BusquedaServicioActivity.class));
    }
    public boolean onClickFavoritos(MenuItem item){
        startActivity(new Intent(this, FavoritosActivity.class));
        return true;
    }
    public boolean onClickServicios(MenuItem item){
        startActivity(new Intent(this, ServiciosActivity.class));
        return true;
    }
    public boolean onClickMetodosPago(MenuItem item){
        startActivity(new Intent(this, MetodosPagoActivity.class));
        return true;
    }
    public boolean onClickMapa(MenuItem item){
        startActivity(new Intent(this, MapsActivity.class));
        return true;
    }
    public boolean onClickExit(MenuItem item){
        drawerLayout.closeDrawer(GravityCompat.START);
        progressView.setVisibility(View.VISIBLE);
        wsUsuario.closeSesionAsync(new HandlerWS() {
            @Override
            public void onSuccess(ResponseVO vo) {
                onCloseSesion(vo);
            }
            @Override
            public void onError(Exception e) {
                runOnUiThread(() -> {
                    progressView.setVisibility(View.GONE);
                    showErrorPrompt(e);
                });
            }
        },null);
        return true;
    }
    private void onCloseSesion(ResponseVO r){
        runOnUiThread(() -> {
            progressView.setVisibility(View.GONE);
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });
    }

}
