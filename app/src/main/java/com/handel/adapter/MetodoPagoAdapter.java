package com.handel.adapter;

import android.animation.Animator;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.TextView;

import com.handel.handel.R;
import com.handel.vo.MetodoPagoVO;

import java.util.List;

/**
 * Created by Marco Antonio on 17/03/2018.
 */

public class MetodoPagoAdapter extends RecyclerView.Adapter<MetodoPagoAdapter.MetodoPagoHolder>{
    public static final String LOG_CLASS = "MetodoPagoAdapter";
    private List<MetodoPagoVO> metodosPago;

    public MetodoPagoAdapter(List<MetodoPagoVO> vos) {
        this.metodosPago = vos;
    }
    public class MetodoPagoHolder extends RecyclerView.ViewHolder {

        TextView idMetodoPago;
        TextView noTarjeta;

        public MetodoPagoHolder(View view) {
            super(view);
            idMetodoPago = (TextView) view.findViewById(R.id.metodo_pago_adapter_id_metodo_pago);
            noTarjeta = (TextView) view.findViewById(R.id.metodo_pago_adapter_no_tarjeta);
        }

    }

    @Override
    public MetodoPagoAdapter.MetodoPagoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.metodo_pago_adapter, parent, false);

        return new MetodoPagoAdapter.MetodoPagoHolder(itemView);
    }
    @Override
    public void onViewAttachedToWindow(MetodoPagoAdapter.MetodoPagoHolder holder){
        super.onViewAttachedToWindow(holder);

        // Animator
        View view = holder.itemView;
        //itemView.clearAnimation();
        Animator animator = ViewAnimationUtils.createCircularReveal(view,0,0,0, Math.max(view.getWidth(),view.getHeight()));
        view.setVisibility(View.VISIBLE);
        animator.start();
    }
    @Override
    public void onBindViewHolder(MetodoPagoAdapter.MetodoPagoHolder holder, int position) {
        MetodoPagoVO vo = metodosPago.get(position);
        if (vo == null){
            return;
        }
        Log.i(LOG_CLASS,"setting IdMetodoPago: "+vo.getIdMetodoPago());
        holder.idMetodoPago.setText(""+vo.getIdMetodoPago());
        holder.noTarjeta.setText(vo.getNoTarjeta());
    }

    @Override
    public int getItemCount() {
        return metodosPago == null ? 0 : metodosPago.size();
    }

}
