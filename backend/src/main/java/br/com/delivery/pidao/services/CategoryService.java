package br.com.delivery.pidao.services;


import br.com.delivery.pidao.entities.Category;
import br.com.delivery.pidao.entities.Menu;
import br.com.delivery.pidao.entities.dto.CategoryDTO;
import br.com.delivery.pidao.exceptions.CategoryNotFound;
import br.com.delivery.pidao.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {

    private MenuService menuService;

    private CategoryRepository categoryRepository;

    public String addCategory(CategoryDTO categoryDTO) {
        Menu menu = menuService.findMenuByIdentifier(categoryDTO.getMenuIdentifier());
        Optional<Category> category = categoryRepository.findByDetailsAndMenuIdentifier(categoryDTO.getDetails(), menu.getMenuIdentifier());

        if (category.isPresent()) {
            throw new IllegalArgumentException("Categoria já existente");
        }

        Category categorySaved = categoryRepository.save(new Category(categoryDTO.getDetails(),menu.getMenuIdentifier()));
        return categorySaved.getCategoryIdentifier();
    }

    public String updateCategory(CategoryDTO categoryDTO) {
        Menu menu = menuService.findMenuByIdentifier(categoryDTO.getMenuIdentifier());

        Optional<Category> category = categoryRepository.findByDetailsAndMenuIdentifier(categoryDTO.getDetails(), menu.getMenuIdentifier());

        if (!category.isPresent()) {
            throw new CategoryNotFound("Categoria não existente");
        }

        if(categoryDTO.getDetails().isBlank()){
            throw new IllegalArgumentException("Details inválido");
        }

        category.get().setDetails(categoryDTO.getDetails());
        categoryRepository.save(category.get());
        return category.get().getCategoryIdentifier();
    }

    public CategoryDTO findByIdentifier(String categoryIdentifier) {
        Optional<Category> category = categoryRepository.findByCategoryIdentifier(categoryIdentifier);
        if (!category.isPresent()) {
            throw new CategoryNotFound("Categoria não existente");
        }

        CategoryDTO categoryDTO = new CategoryDTO(category.get().getDetails(), category.get().getMenuIdentifier());

        return categoryDTO;
    }

    public Boolean deleteCategory(String categoryIdentifier) {
        Optional<Category> category = categoryRepository.findByCategoryIdentifier(categoryIdentifier);
        if (!category.isPresent()) {
            throw new CategoryNotFound("Categoria não existente");
        }

        categoryRepository.delete(category.get());
        return true;
    }

    Category getCategoryByIdentifier(String categoryIdentifier){
        Optional<Category> category = categoryRepository.findByCategoryIdentifier(categoryIdentifier);

        if (category.isPresent()) {
            return category.get();
        }
        throw new CategoryNotFound("Categoria não existente!");
    }

    public Category isPresent(String details){
        Optional<Category> category = categoryRepository.findByDetails(details);
        return category.isPresent() ? category.get() : null;
    }

    List<Category> findByMenuIdentifier(String menuIdentifier){
        List<Category> categories = categoryRepository.findByMenuIdentifier(menuIdentifier);
        if(!categories.isEmpty()){
            return categories;
        }
        throw new CategoryNotFound("Categorias não existente");

    }
}