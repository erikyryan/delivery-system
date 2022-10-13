package br.com.delivery.pidao.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public abstract class User {
    private String email;
    private String password;
    private String date;
    private String name;
    private String socialsSecurity;
    private String cellphone;
    public User get(String name2) {
        return null;
    }
}
