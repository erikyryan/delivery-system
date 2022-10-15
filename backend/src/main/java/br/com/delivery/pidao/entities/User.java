package br.com.delivery.pidao.entities;

import lombok.Data;

import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public class User {
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
