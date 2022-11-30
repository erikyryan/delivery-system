package br.com.delivery.pidao.domain.menu.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID uuid;

    @NotNull
    private String details;

    @Column(name = "menu_id")
    private UUID menuUuid;

    @OneToMany
    private List<Item> item;

    public Category(String details,UUID menuUuid){
        this.details = details;
        this.menuUuid = menuUuid;
    }
}
