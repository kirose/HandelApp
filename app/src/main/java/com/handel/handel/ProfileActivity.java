package com.handel.handel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.handel.model.Profile;

public class ProfileActivity extends AppCompatActivity {
    public static final String LOG_CLASS = "ProfileActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);
        Profile profile = (Profile)getIntent().getSerializableExtra("profile");
        ((TextView)findViewById(R.id.profile_score)).setText(profile.getScore());
        ((TextView)findViewById(R.id.profile_name)).setText(profile.getName());
        ((TextView)findViewById(R.id.profile_email)).setText(profile.getEmail());
        ((TextView)findViewById(R.id.profile_phone)).setText(profile.getPhone());
    }
}
