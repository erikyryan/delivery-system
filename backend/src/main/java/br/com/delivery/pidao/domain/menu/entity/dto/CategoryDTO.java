package br.com.delivery.pidao.domain.menu.entity.dto;

import com.sun.istack.NotNull;

import br.com.delivery.pidao.domain.menu.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    @NotNull
    private String details;

    @NotNull
    @Column(name = "menu_id")
    private String menuUuid;

    public Category dtoToEntity() {
        Category category = new Category();
        category.setMenuUuid(UUID.fromString(menuUuid));
        category.setDetails(details);
        return category;
    }

}
