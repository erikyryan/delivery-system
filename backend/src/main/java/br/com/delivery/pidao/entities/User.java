package br.com.delivery.pidao.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class User {
    private String email;
    private String password;
    private String date;
    private String name;
    private String socialsSecurity;
    private String cellphone;
    public User get() {
        return null;
    }
}
