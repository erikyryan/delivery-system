package br.com.delivery.pidao.entities;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@RequiredArgsConstructor
public class Menu implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "menuidentifier")
    private String menuIdentifier = UUID.randomUUID().toString();

    private String restaurantIdentifier;

}
