package com.handel.handel;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.handel.dao.ProfileDAO;
import com.handel.model.Profile;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static final String LOG_CLASS = "MenuActivity";

    private Profile selected;
    private boolean cameraTurnOn;
    private static Camera camera;

    public Profile getSelected() {
        return selected;
    }

    public void setSelected(Profile selected) {
        this.selected = selected;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"Gallery",Toast.LENGTH_SHORT);
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        selected = new Profile();
        selected.setImg("MiImagenPerfil");
        selected.setName("Marco Antonio Salazar");
        selected.setEmail("marcosalazar@hotmail.com");


        ConstraintLayout layout = (ConstraintLayout)findViewById(R.id.contentLayout);
        TextView text = new TextView(this);
        text.setText("TextViwe added dynamically");
        //ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams();
        //LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT,1.0f);
        //text.setLayoutParams(params);
        //layout.addView(text);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.profiles_recycler_view);

        MenuAdapter adapter = new MenuAdapter(ProfileDAO.findProfiles(null));
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    @Override
    public void onStart(){
        super.onStart();
        Log.i(LOG_CLASS,"Started");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile, menu);

        ((TextView)findViewById(R.id.txtName)).setText(selected.getName());
        ((TextView)findViewById(R.id.txtEmail)).setText(selected.getEmail());

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

        if (id == R.id.nav_camera) {

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        Log.i(LOG_CLASS,"Click Item Menu "+item.getItemId());
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
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
        /*try {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED){
                Log.i("INFO", "No hay permisos sobre la camara");
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, 1);
            } else{
                Log.i("INFO", "Si hay permisos sobre la camara");
            }
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                if (this.getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
                    if (!cameraTurnOn) {
                        Log.i("INFO", "Numero de camaras: " + Camera.getNumberOfCameras());
                        camera = Camera.open();
                        Camera.Parameters p = camera.getParameters();
                        p.setFlashMode(Camera.Parameters.FLASH_MODE_ON);
                        camera.setParameters(p);
                        camera.startPreview();
                        cameraTurnOn = true;
                        Log.i("INFO", "INICIO NORMALMENTE");
                    } else {
                        Log.i("INFO", "DETENIENDO CAMARA");
                        camera.stopPreview();
                        camera.release();
                        cameraTurnOn = false;
                    }
                } else {
                    Log.e("Error", "No camera");
                }
            }
        //} catch(Exception e) {
          //  Log.e("Error", ""+e);
        //}*/
        return true;
    }
    public void onClickProfileItem(View view){
        TextView textIdProfile = view.findViewById(R.id.profile_item_id_profile);
        Log.i(LOG_CLASS,"getting IdProfile: "+textIdProfile.getText().toString());
        Profile profile = ProfileDAO.find(Long.valueOf(textIdProfile.getText().toString()));
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra("profile",profile);
        startActivity(intent);
    }
}
