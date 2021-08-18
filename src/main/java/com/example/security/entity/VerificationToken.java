package com.example.security.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String token;

    @OneToOne(targetEntity = User.class , fetch = FetchType.EAGER)
    @JoinColumn(nullable = false,name = "user_id")
    private User user;


    public VerificationToken(String token, User user) {
        this.token=token;
        this.user=user;
    }

    public VerificationToken() {

    }
}
