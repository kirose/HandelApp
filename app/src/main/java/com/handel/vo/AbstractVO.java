package com.handel.vo;

import android.util.Log;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.handel.entity.AbstractEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Marco Antonio on 14/02/2018.
 */

public abstract class AbstractVO implements Serializable{

    public String toJSON(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        try {
            return ow.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            Log.e("AbstractVO","No es posible convertir a JSON el vo"+this.toString());
            return null;
        }
    }
    public <T extends AbstractEntity> List<T> getEntities() {
        return null;
    }
}
