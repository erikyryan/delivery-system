package br.com.delivery.pidao.enums;

import br.com.delivery.pidao.entities.Client;
import br.com.delivery.pidao.entities.Item;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Rating {

    @Id
    @NotNull
    @ManyToOne
    private Item item;

    @Nullable
    private Long rating;

    @Id
    @NotNull
    @ManyToOne
    private Client client;

}
