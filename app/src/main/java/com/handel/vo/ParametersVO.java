package com.handel.vo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Marco Antonio on 17/02/2018.
 */

public class ParametersVO extends AbstractVO{
    private Map<String,Object> map;

    public ParametersVO() {
        map = new HashMap<>();
    }
    public ParametersVO add(String name, Object value){
        map.put(name, value);
        return this;
    }

    public Map<String, Object> getMap() {
        return map;
    }
    public Object get(String param){
        return map.get(param);
    }
    @Override
    public String toString() {
        return map == null ? null : map.toString();
    }

    public String toJSON(){
        if (map == null){
            return null;
        }
        try {
            return new ObjectMapper().writeValueAsString(map);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
