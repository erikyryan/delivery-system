package br.com.delivery.pidao.entities;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User {

    private UUID userIdentifier;

    @NotNull
    private String email;

    @NotNull
    private String password;

    private String date;

    private String name;

    private String socialsSecurity;

    private String cellphone;

}
