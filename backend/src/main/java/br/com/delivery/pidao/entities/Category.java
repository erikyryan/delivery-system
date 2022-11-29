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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "categoryidentifier")
    private String categoryIdentifier = UUID.randomUUID().toString();

    @NotNull
    private String details;

    private String menuIdentifier;

    private UUID menuUuid;

    public Category(String details,String menuIdentifier){
        this.details = details;
        this.menuIdentifier = menuIdentifier;
    }
}
