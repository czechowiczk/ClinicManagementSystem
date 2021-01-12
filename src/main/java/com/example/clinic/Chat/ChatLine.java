package com.example.clinic.Chat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "chat_line")
public class ChatLine {

    @Id
    @Column(name = "id_line")
    private Integer lineId;
    @Column(name = "id_chat")
    private Integer chatId;
    @Column(name = "created_at")
    private LocalDateTime createTime;
    @Column(name = "text")
    private String text;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_chat")
    private Chat chat;
}
