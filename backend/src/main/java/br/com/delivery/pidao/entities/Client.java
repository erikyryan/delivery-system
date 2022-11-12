package br.com.delivery.pidao.entities;

import com.sun.istack.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Client extends User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String adressIdentifier;

    private String clientOrderIdentifier;

//    private String clientIdentifier = UUID.randomUUID().toString();

}
