package br.com.delivery.pidao.entities;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@RequiredArgsConstructor
public class Rating implements Serializable {

    @Id
    private Long id;

//    @ManyToOne
//    private Item item;

    @Nullable
    private Long rating;

//    @ManyToOne
//    private Cl ient client;

}
