package com.handel.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

/**
 * Created by Marco Antonio on 14/02/2018.
 */

public class DataDeserializer extends StdDeserializer<Object> {

    public DataDeserializer() {
        this(null);
    }

    public DataDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Object deserialize(JsonParser p, DeserializationContext context) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        TreeNode node = mapper.readTree(p);
        return node.toString();
    }
}
