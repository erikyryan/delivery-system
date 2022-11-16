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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID uuid;

    private String restaurantIdentifier;

    public Menu(String restaurantIdentifier) {
        this.restaurantIdentifier = restaurantIdentifier;
    }
}
