package com.example.clinic.Chat;

import javax.persistence.Column;
import java.io.Serializable;

public class IdChatLineClass implements Serializable {
    private Integer lineId;
    private Integer chatId;

    public IdChatLineClass() {
    }

    public IdChatLineClass(Integer lineId, Integer chatId) {
        this.lineId = lineId;
        this.chatId = chatId;
    }
}
