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
import com.handel.vo.ServicioVO;

import java.util.List;

/**
 * Created by Marco Antonio on 01/01/2018.
 */

public class ServicioAdapter extends RecyclerView.Adapter<ServicioAdapter.ServicioHolder>{
    public static final String LOG_CLASS = "ServicioAdapter";
    private List<ServicioVO> servicios;

    public ServicioAdapter(List<ServicioVO> servicios) {
        this.servicios = servicios;
    }
    public class ServicioHolder extends RecyclerView.ViewHolder {
        TextView idServicio;
        TextView servicio;
        TextView descripcion;
        TextView calificacionProveedor;
        TextView nombreProveedor;
        TextView emailProveedor;
        TextView telefonoProveedor;


        public ServicioHolder(View view) {
            super(view);
            idServicio = (TextView) view.findViewById(R.id.servicio_adapter_id_servicio);
            servicio = (TextView) view.findViewById(R.id.servicio_adapter_servicio);
            descripcion = (TextView) view.findViewById(R.id.servicio_adapter_descripcion);
            calificacionProveedor = (TextView) view.findViewById(R.id.servicio_adapter_calificacion_proveedor);
            nombreProveedor = (TextView) view.findViewById(R.id.servicio_adapter_nombre_proveedor);
            emailProveedor = (TextView) view.findViewById(R.id.servicio_adapter_email_proveedor);
            telefonoProveedor = (TextView) view.findViewById(R.id.servicio_adapter_telefono_proveedor);
        }

    }

    @Override
    public ServicioAdapter.ServicioHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.servicio_adapter, parent, false);

        return new ServicioAdapter.ServicioHolder(itemView);
    }
    @Override
    public void onViewAttachedToWindow(ServicioAdapter.ServicioHolder holder){
        super.onViewAttachedToWindow(holder);

        // Animator
        View view = holder.itemView;
        //itemView.clearAnimation();
        Animator animator = ViewAnimationUtils.createCircularReveal(view,0,0,0, Math.max(view.getWidth(),view.getHeight()));
        view.setVisibility(View.VISIBLE);
        animator.start();
    }
    @Override
    public void onBindViewHolder(ServicioAdapter.ServicioHolder holder, int position) {
        ServicioVO vo = servicios.get(position);

        holder.idServicio.setText(""+vo.getIdServicio());
        Log.i(LOG_CLASS,"setting IdServicio: "+vo.getIdServicio());
        holder.servicio.setText(vo.getServicio());
        holder.descripcion.setText(vo.getDescripcion());
        holder.calificacionProveedor.setText(vo.getCalificacionProveedor());
        holder.nombreProveedor.setText(vo.getNombreProveedor());
        holder.emailProveedor.setText(vo.getEmailProveedor());
        holder.telefonoProveedor.setText(vo.getTelefonoProveedor());
    }

    @Override
    public int getItemCount() {
        return servicios == null ? 0 : servicios.size();
    }

}
