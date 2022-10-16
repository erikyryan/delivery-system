package br.com.delivery.pidao.services;


import br.com.delivery.pidao.entities.Category;
import br.com.delivery.pidao.entities.Menu;
import br.com.delivery.pidao.entities.dto.CategoryDTO;
import br.com.delivery.pidao.entities.dto.UserDTO;
import br.com.delivery.pidao.repositories.CategoryRepository;
import br.com.delivery.pidao.repositories.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private MenuRepository menuRepository;

    private CategoryRepository categoryRepository;

    private ItemService itemService;

    public CategoryDTO addCategory(CategoryDTO categoryDTO, UserDTO userDTO) {

        itemService.getRestaurantIfTheUserIsAManagerFromUserDTO(userDTO);
        Optional<Menu> menu = menuRepository.findByMenuIdentifier(categoryDTO.getMenuIdentifier());
        if(!menu.isPresent()){
            throw new RuntimeException("Menu não encontrado!");
        }

        Optional<Category> category = categoryRepository.findByDetailsAndIdmenu(categoryDTO.getDetails(), menu.get().getId());

        if(category.isPresent()){
            throw new RuntimeException("Categoria já existente!");
        }

        categoryRepository.save(category.get());
        return new CategoryDTO(category.get().getDetails(),
                category.get().getMenu().getMenuIdentifier());

    }
}
