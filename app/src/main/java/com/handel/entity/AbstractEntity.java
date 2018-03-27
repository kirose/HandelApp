package com.handel.entity;

import android.util.Log;

import com.handel.vo.AbstractVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marco Antonio on 20/03/2018.
 */

public abstract class AbstractEntity extends AbstractVO {
    private static final String LOG_CLASS = "AbstractEntity";
    public <T extends AbstractVO>T as(T vo){
        T instance = null;
        try {
            instance = (T) vo.getClass().newInstance();
        } catch(InstantiationException e){
            Log.e(LOG_CLASS, "No tiene constructor default",e);
        } catch(IllegalAccessException e){
            Log.e(LOG_CLASS, "El constructor no es publico",e);
        }
        return instance;
    }

    @Override
    public <T extends AbstractEntity> List<T> getEntities() {
        List<T> entities = new ArrayList<>();
        entities.add((T) this);
        return entities;
    }
    public abstract String getPrimaryKeyName();
}
