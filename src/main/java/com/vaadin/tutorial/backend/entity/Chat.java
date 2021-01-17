package com.vaadin.tutorial.backend.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "chat")
public class Chat {

    @Id
    @Column(name = "id_chat")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer chatId;

    @ManyToOne
    @JoinColumn(name = "id_patient")
    Patient patient;

    @ManyToOne
    @JoinColumn(name = "id_doctor")
    Doctor doctor;

    @OneToMany(mappedBy = "chat")
    private Set<ChatLine> lines;
}
