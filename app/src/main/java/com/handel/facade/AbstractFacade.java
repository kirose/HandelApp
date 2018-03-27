package com.handel.facade;

import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.util.Log;

import com.handel.dao.AbstractDAO;
import com.handel.db.AppDatabase;
import com.handel.entity.AbstractEntity;
import com.handel.util.UtilReflect;
import com.handel.util.UtilString;
import com.handel.vo.AbstractVO;
import com.handel.ws.AbstractWS;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by Marco Antonio on 22/03/2018.
 */

public abstract class AbstractFacade<T extends AbstractDAO,R extends AbstractEntity, U extends AbstractWS, V extends AbstractVO>{

    private static final String LOG_CLASS = "AbstractFacade";

    private Class<T> classDAO;
    private Class<R> classEntity;
    private Class<U> classWS;
    private Class<V> classVO;
    private T dao;
    private U ws;
    private AppDatabase db;

    protected AbstractFacade(Context applicationContext, Class<T> classDAO, Class<R> classEntity, Class<U> classWS, Class<V> classVO) {

        this.classDAO = classDAO;
        this.classEntity = classEntity;
        this.classWS = classWS;
        this.classVO = classVO;
        this.db = AppDatabase.getInstance(applicationContext);

        String getDAO = "get"+classDAO.getSimpleName();
        try {
            this.dao = (T)this.db.getClass().getMethod(getDAO).invoke(this.db);
        } catch (NoSuchMethodException e) {
            Log.e(LOG_CLASS,"No existe el metodo: "+getDAO,e);
        } catch (IllegalAccessException e) {
            Log.e(LOG_CLASS,String.format("El metodo %s no es publico",getDAO),e);
        } catch (InvocationTargetException e) {
            Log.e(LOG_CLASS,String.format("El metodo %s no puede ser invocado",getDAO),e);
        }
        try {
            this.ws = classWS.newInstance();
        } catch (InstantiationException e) {
            Log.e(LOG_CLASS,"No existe constructor default",e);
        } catch (IllegalAccessException e) {
            Log.e(LOG_CLASS,"Constructor default no es publico",e);
        }
    }

    public T getDao() {
        return dao;
    }

    public U getWs() {
        return ws;
    }

    public AppDatabase getDb() {
        return db;
    }

    public V saveOrUpdate(V vo){

        List<R> entities = vo.getEntities();
        for (R e: entities) {
            String getPK = UtilReflect.getFieldWithAnnotation(classEntity, PrimaryKey.class).getName();
            AbstractDAO daoEntity = getDAOByEntity(e);
            if (UtilReflect.invoke(daoEntity,"findUnique",UtilReflect.invoke(e,getPK)) == null){
                daoEntity.insert(e);
            } else {
                daoEntity.update(e);
            }
        }

        return vo;
    }
    public void saveOrUpdate(List<AbstractEntity> entities){
        if (entities == null || entities.isEmpty()){
            return;
        }
        for (AbstractEntity e : entities){
            AbstractDAO dao = getDAOByEntity(e);
            //Log.i(LOG_CLASS,e.getClass()+"");
            //String getPK = UtilReflect.getFieldWithAnnotation(e.getClass(), PrimaryKey.class).getName();
            String getPK = "get"+ UtilString.capitalize(e.getPrimaryKeyName());
            if (getPK == null){
                continue;
            }
            if (UtilReflect.invoke(dao,"findUnique",UtilReflect.invoke(e,getPK)) == null){
                dao.insert(e);
            } else {
                dao.update(e);
            }
        }
    }

    public R saveOrUpdate(R entity){
        String getPK = UtilReflect.getFieldWithAnnotation(classEntity, PrimaryKey.class).getName();
        if (getPK == null){
            return null;
        }
        if (UtilReflect.invoke(dao,"findUnique",UtilReflect.invoke(entity,getPK)) == null){
            dao.insert(entity);
        } else {
            dao.update(entity);
        }
        return entity;
    }

    private AbstractDAO getDAOByEntity(AbstractEntity entity){
        if (entity == null){
            throw new NullPointerException();
        }
        String getDAO = "get"+entity.getClass().getSimpleName() + "DAO";
        return (AbstractDAO)UtilReflect.invoke(db,getDAO);
    }
}
