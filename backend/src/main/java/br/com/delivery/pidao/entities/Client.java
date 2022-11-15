package br.com.delivery.pidao.entities;

import com.sun.istack.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.UUID;

@Data
@Entity
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private User user;

    @OneToMany
    private List<ClientOrder> OrderClient;

//    private String clientIdentifier = UUID.randomUUID().toString();

}
