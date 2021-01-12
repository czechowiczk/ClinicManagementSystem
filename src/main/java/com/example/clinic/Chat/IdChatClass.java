package com.example.clinic.Chat;

import javax.persistence.Column;
import java.io.Serializable;

public class IdChatClass implements Serializable {

    private Integer chatId;
    private Integer patientId;
    private Integer doctorId;

    public IdChatClass() {
    }

    public IdChatClass(Integer chatId, Integer patientId, Integer doctorId) {
        this.chatId = chatId;
        this.patientId = patientId;
        this.doctorId = doctorId;
    }
}
