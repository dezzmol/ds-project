package com.digitalspirit.project.message.entity;

import com.digitalspirit.project.user.User;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "user_id"))
    private User user;

    @Column(name = "message", nullable = false, columnDefinition = "TEXT")
    private String message;
}
