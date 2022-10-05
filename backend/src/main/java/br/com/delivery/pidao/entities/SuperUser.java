package br.com.delivery.pidao.entities;


import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SuperUser {

    @Id
    private Long id;

    @NotNull
    private String email;

    @NotNull
    private String senha;

}
