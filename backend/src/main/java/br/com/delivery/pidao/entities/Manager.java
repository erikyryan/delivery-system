package br.com.delivery.pidao.entities;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;


@Data
@Entity
public class Manager extends User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID uuid;

    private String Department;

    private String restaurantIdentifier;
}
