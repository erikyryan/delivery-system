package br.com.delivery.pidao.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
public class Manager implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Users users;

    private String Department;

    private String restaurantIdentifier;
}
