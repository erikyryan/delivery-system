package br.com.delivery.pidao.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
public class Client extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID uuid;

    private String addressIdentifier;

    private String clientOrderIdentifier;

}
