package br.com.delivery.pidao.entities;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
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
    private UUID uuid;

    @NotNull
    private String details;

    private UUID menuUuid;

    @OneToMany
    private List<Item> items;

    public Category(String details,UUID menuUuid){
        this.details = details;
        this.menuUuid = menuUuid;
    }
}
