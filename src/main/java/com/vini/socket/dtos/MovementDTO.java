package com.vini.socket.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vini.game.lib.Position;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MovementDTO {

    @JsonIgnore private Position from = new Position(null, null);
    @JsonIgnore private Position to = new Position(null, null);

    @JsonProperty("from")
    private void unpackNestedFrom(Map<String, String> from) {
        this.from.x = Integer.parseInt(from.get("x"));
        this.from.y = Integer.parseInt(from.get("y"));
    }

    @JsonProperty("to")
    private void unpackNestedTo(Map<String, String> to) {
        this.to.x = Integer.parseInt(to.get("x"));
        this.to.y = Integer.parseInt(to.get("y"));
    }

    public Position getFrom() {
        return this.from;
    }

    public Position getTo() {
        return this.to;
    }
}
