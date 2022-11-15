package br.com.delivery.pidao.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
public class Client extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String addressIdentifier;

    private String clientOrderIdentifier;

    private String clientIdentifier = UUID.randomUUID().toString();

//    @OneToMany
//    private List<Rating> rating;

}
