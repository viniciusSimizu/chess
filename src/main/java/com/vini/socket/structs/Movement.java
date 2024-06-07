package com.vini.socket.structs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vini.game.structs.Position;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Movement {

    @JsonIgnore public Position from;
    @JsonIgnore public Position to;

    @JsonProperty("from")
    private void unpackNestedFrom(Map<String, Integer> from) {
        this.from = new Position(from.get("x"), from.get("y"));
    }

    @JsonProperty("to")
    private void unpackNestedTo(Map<String, Integer> to) {
        this.to = new Position(to.get("x"), to.get("y"));
    }
}
