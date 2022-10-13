package br.com.delivery.pidao.entities;


import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

@Entity
@IdClass(SuperUser.class)
public class SuperUser implements Serializable {

    @Id
    private Long id;

    @NotNull
    private String email;

    @NotNull
    private String senha;

}
