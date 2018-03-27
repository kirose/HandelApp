package com.handel.vo;

import android.util.Log;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.handel.deserializer.DataDeserializer;
import com.handel.entity.AbstractEntity;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Marco Antonio on 12/02/2018.
 */

public class ResponseVO extends AbstractVO {

    private String message;
    private String status;
    private String codeLog;
    private Integer size;
    private Integer maxSize;
    private String token;
    // *********** Se deserializa como string ************
    @JsonDeserialize(using = DataDeserializer.class)
    private Object data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCodeLog() {
        return codeLog;
    }

    public void setCodeLog(String codeLog) {
        this.codeLog = codeLog;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(Integer maxSize) {
        this.maxSize = maxSize;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseVO{" +
                "token='"+token+'\''+
                "message='" + message + '\'' +
                ", status=" + status +
                ", codeLog='" + codeLog + '\'' +
                ", size=" + size +
                ", maxSize=" + maxSize +
                ", data=" + data +
                '}';
    }

    public <T> T getDataAs(Class<T> clazz){
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        try {
            if (data == null){
                return null;
            }
            return mapper.readValue(data.toString(), clazz);
            //return mapper.readValue(data.toString(), new TypeReference<clazz>(){});

        } catch (IOException e) {
            Log.e("WSResponse","Error decodificando ResponseVO.data",e);
            return null;
        }
    }
    public <T> List<T> getDataAsList(Class<T> clazz){
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        try {
            //return mapper.readValue(data.toString(), new TypeReference<List<T>>(){});
            if (data == null){
                return null;
            }
            return mapper.readValue(data.toString(), mapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (IOException e) {
            Log.e("WSResponse","Error decodificando ResponseVO.data:"+data,e);
            return null;
        }
    }
}
