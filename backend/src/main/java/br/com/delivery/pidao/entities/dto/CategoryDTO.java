package br.com.delivery.pidao.entities.dto;

import com.sun.istack.NotNull;

import br.com.delivery.pidao.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    @NotNull
    private String details;

    @NotNull
    private String menuUuid;

    public Category dtoToEntity() {
        Category category = new Category();
        category.setMenuUuid(UUID.fromString(menuUuid));
        category.setDetails(details);
        return category;
    }

}
