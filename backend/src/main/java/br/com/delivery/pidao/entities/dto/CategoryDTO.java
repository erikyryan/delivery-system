package br.com.delivery.pidao.entities.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    @NotNull
    private String details;

    @NotNull
    private String menuIdentifier;

}
