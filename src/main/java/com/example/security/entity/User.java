package com.example.security.entity;

import com.example.security.validation.ValidPassword;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //@ValidPassword
    private String password;

    @Transient
    private String passwordConfirmation;

    private String email;

    private boolean enabled;

    public void setPasswordConfirmation(final String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }



   //    private Calendar created= Calendar.getInstance();
}
