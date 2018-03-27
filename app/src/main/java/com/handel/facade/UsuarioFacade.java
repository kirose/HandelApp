package com.handel.facade;

import android.content.Context;
import android.util.Log;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.handel.dao.AbstractDAO;
import com.handel.dao.UsuarioDAO;
import com.handel.entity.AbstractEntity;
import com.handel.entity.Usuario;
import com.handel.util.UtilReflect;
import com.handel.util.UtilString;
import com.handel.vo.ParametersVO;
import com.handel.vo.ResponseVO;
import com.handel.vo.UsuarioVO;
import com.handel.ws.HandlerWS;
import com.handel.ws.UsuarioWS;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Marco Antonio on 21/03/2018.
 */

public final class UsuarioFacade extends AbstractFacade<UsuarioDAO, Usuario, UsuarioWS, UsuarioVO>{

    private static final String LOG_CLASS = "UsuarioFacade";
    private static UsuarioFacade instance;

    private UsuarioFacade(Context applicationContext) {
        super(applicationContext, UsuarioDAO.class, Usuario.class, UsuarioWS.class, UsuarioVO.class);
    }

    /**
     * Songleton method for get instance
     * @param applicationContext
     * @return instance
     */
    public static UsuarioFacade getInstance(Context applicationContext){
        if (instance == null){
            instance = new UsuarioFacade(applicationContext);
        }
        return instance;
    }
    public void updatePerfilAsync(HandlerWS handler){
        boolean downloadAll = getDao().findUnique(UsuarioWS.getUsuarioSesion().getIdUsuario()) == null;
        getWs().updatePerfilAsync(new HandlerWS() {
            @Override
            public void onSuccess(ResponseVO vo) {

                List<HashMap> tables = vo.getDataAsList(HashMap.class);
                List<AbstractEntity> entities = null;
                ObjectMapper mapper = new ObjectMapper();
                mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                if (tables == null || tables.isEmpty()){
                    if (handler != null){
                        handler.onSuccess(vo);
                    }
                    return;
                }
                for (Map t: tables) {
                    String entityName = ""+t.get("entity");
                    String entityClassName = AbstractEntity.class.getName().substring(0,AbstractEntity.class.getName().lastIndexOf('.'))+"."+entityName;
                    Class<? extends AbstractEntity> clazzEntity = null;
                    try {
                        clazzEntity = (Class<? extends AbstractEntity>) Class.forName(entityClassName);
                    } catch (ClassNotFoundException e) {
                        Log.e(LOG_CLASS,"No se encontro clase: "+entityClassName,e);
                    }
                    String jsonEntity = (String)t.get("data");

                    try {
                        entities = mapper.readValue(jsonEntity, mapper.getTypeFactory().constructCollectionType(List.class, clazzEntity));
                        saveOrUpdate(entities);
                    } catch (IOException e) {
                        Log.e(LOG_CLASS,"No se pudo decodificar entity: : "+entityName+", json: "+jsonEntity,e);
                    }
                }

                if (handler != null){
                    handler.onSuccess(vo);
                }
            }
            @Override
            public void onError(Exception e) {
                if (handler != null){
                    handler.onError(e);
                }
            }
        },new ParametersVO().add("downloadAll",downloadAll));
    }
}
