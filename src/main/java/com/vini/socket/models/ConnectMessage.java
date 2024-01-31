package com.vini.socket.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vini.socket.enums.GameModeEnum;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConnectMessage {

    private String mode;
    private GameModeEnum modeEnum = null;

    public GameModeEnum getType() throws IllegalArgumentException {
        if (this.modeEnum == null) {
            this.modeEnum = GameModeEnum.valueOf(this.mode);
        }
        return this.modeEnum;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
