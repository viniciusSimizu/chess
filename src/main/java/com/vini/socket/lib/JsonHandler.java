package com.vini.socket.lib;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonHandler {

    private JsonHandler() {}

    private static ObjectMapper mapper = new ObjectMapper();

    public static <T> T readValue(String json, Class<T> parser)
            throws JsonMappingException, JsonProcessingException {
        return mapper.readValue(json, parser);
    }

    public static String writeValueAsString(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new Error("Invalid Json Object Stringfy");
        }
    }
}
