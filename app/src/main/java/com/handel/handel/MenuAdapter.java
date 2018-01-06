package com.handel.handel;

import android.animation.Animator;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.TextView;

import com.handel.model.Profile;

import java.util.List;

/**
 * Created by Marco Antonio on 01/01/2018.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ProfileHolder>{
    public static final String LOG_CLASS = "MenuAdapter";
    private List<Profile> profiles;

    public MenuAdapter(List<Profile> profiles) {
        this.profiles = profiles;
    }
    public class ProfileHolder extends RecyclerView.ViewHolder {
        public TextView idProfile;
        public TextView score;
        public TextView name;
        public TextView email;
        public TextView phone;

        public ProfileHolder(View view) {
            super(view);
            idProfile = (TextView) view.findViewById(R.id.profile_item_id_profile);
            score = (TextView) view.findViewById(R.id.profile_item_score);
            name = (TextView) view.findViewById(R.id.profile_item_name);
            email = (TextView) view.findViewById(R.id.profile_item_email);
            phone = (TextView) view.findViewById(R.id.profile_item_phone);
        }

    }

    @Override
    public MenuAdapter.ProfileHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_profile_item, parent, false);

        return new MenuAdapter.ProfileHolder(itemView);
    }
    @Override
    public void onViewAttachedToWindow(MenuAdapter.ProfileHolder holder){
        super.onViewAttachedToWindow(holder);

        // Animator
        View view = holder.itemView;
        //itemView.clearAnimation();
        Animator animator = ViewAnimationUtils.createCircularReveal(view,0,0,0, Math.max(view.getWidth(),view.getHeight()));
        view.setVisibility(View.VISIBLE);
        animator.start();
    }
    @Override
    public void onBindViewHolder(MenuAdapter.ProfileHolder holder, int position) {
        Profile profile = profiles.get(position);
        holder.idProfile.setText(""+profile.getIdProfile());
        Log.i(LOG_CLASS,"setting IdProfile: "+profile.getIdProfile());
        holder.score.setText(profile.getScore());
        holder.name.setText(profile.getName());
        holder.email.setText(profile.getEmail());
        holder.phone.setText(profile.getPhone());
    }

    @Override
    public int getItemCount() {
        return profiles == null ? 0 : profiles.size();
    }
}
