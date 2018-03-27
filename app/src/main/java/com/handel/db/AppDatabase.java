package com.handel.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.handel.dao.*;
import com.handel.entity.*;
import com.handel.db.converter.Converters;

/**
 * Created by Marco Antonio on 10/03/2018.
 */
@Database(entities = {
        ArchivoTipo.class,
        Archivo.class,
        Idioma.class,
        Cliente.class,
        Compra.class,
        CompraEstatus.class,
        DatoContacto.class,
        DatoContactoTipo.class,
        Favorito.class,
        MetodoPago.class,
        MetodoPagoEstatus.class,
        Proveedor.class,
        Servicio.class,
        ServicioTipo.class,
        Usuario.class,
        UsuarioEstatus.class,
        UsuarioTipo.class,
    },
    exportSchema = false,
    version = 1
)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase{
    public static final String DATABASE_NAME = "handel";

    private static volatile AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }
    private static AppDatabase create(final Context context) {
        context.getApplicationContext().deleteDatabase(DATABASE_NAME);
        return Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME).build();
    }

    public abstract IdiomaDAO getIdiomaDAO();
    public abstract ArchivoTipoDAO getArchivoTipoDAO();
    public abstract ArchivoDAO getArchivoDAO();
    public abstract ClienteDAO getClienteDAO();
    public abstract CompraDAO getCompraDAO();
    public abstract CompraEstatusDAO getCompraEstatusDAO();
    public abstract DatoContactoDAO getDatoContactoDAO();
    public abstract DatoContactoTipoDAO getDatoContactoTipoDAO();
    public abstract FavoritoDAO getFavoritoDAO();
    public abstract MetodoPagoDAO getMetodoPagoDAO();
    public abstract MetodoPagoEstatusDAO getMetodoPagoEstatusDAO();
    public abstract ProveedorDAO getProveedorDAO();
    public abstract ServicioDAO getServicioDAO();
    public abstract ServicioTipoDAO getServicioTipoDAO();
    public abstract UsuarioDAO getUsuarioDAO();
    public abstract UsuarioEstatusDAO getUsuarioEstatusDAO();
    public abstract UsuarioTipoDAO getUsuarioTipoDAO();

}
