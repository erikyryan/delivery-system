package br.com.delivery.pidao.services;


import br.com.delivery.pidao.entities.Category;
import br.com.delivery.pidao.entities.Menu;
import br.com.delivery.pidao.entities.dto.CategoryDTO;
import br.com.delivery.pidao.exceptions.CategoryNotFound;
import br.com.delivery.pidao.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CategoryService {

    private RestaurantService restaurantService;

    private CategoryRepository categoryRepository;

    public String addCategory(CategoryDTO categoryDTO) {
        UUID menu = restaurantService.findMenuByIdentifier(categoryDTO.getMenuUuid().toString());
        Optional<Category> category = categoryRepository.findByDetailsAndAndMenuUuid(categoryDTO.getDetails(), menu);

        if (category.isPresent()) {
            throw new IllegalArgumentException("Categoria já existente");
        }

        Category categorySaved = categoryRepository.save(new Category(categoryDTO.getDetails(),menu));
        return categorySaved.getUuid().toString();
    }

    public String updateCategory(CategoryDTO categoryDTO) {
        UUID menu = restaurantService.findMenuByIdentifier(categoryDTO.getMenuUuid().toString());

        Optional<Category> category = categoryRepository.findByDetailsAndAndMenuUuid(categoryDTO.getDetails(), menu);

        if (!category.isPresent()) {
            throw new CategoryNotFound("Categoria não existente");
        }

        if(categoryDTO.getDetails().isBlank()){
            throw new IllegalArgumentException("Details inválido");
        }

        category.get().setDetails(categoryDTO.getDetails());
        categoryRepository.save(category.get());
        return category.get().getUuid().toString();
    }

    public CategoryDTO findByIdentifier(String categoryIdentifier) {
        UUID uuid = UUID.fromString(categoryIdentifier);
        Optional<Category> category = categoryRepository.findByUuid(uuid);
        if (!category.isPresent()) {
            throw new CategoryNotFound("Categoria não existente");
        }

        CategoryDTO categoryDTO = new CategoryDTO(category.get().getDetails(), category.get().getMenuUuid().toString());

        return categoryDTO;
    }

    public Boolean deleteCategory(String categoryIdentifier) {
        UUID uuid = UUID.fromString(categoryIdentifier);
        Optional<Category> category = categoryRepository.findByUuid(uuid);
        if (!category.isPresent()) {
            throw new CategoryNotFound("Categoria não existente");
        }

        categoryRepository.delete(category.get());
        return true;
    }

    public Category isPresent(String details){
        Optional<Category> category = categoryRepository.findByDetails(details);
        return category.isPresent() ? category.get() : null;
    }

    List<Category> findByMenuIdentifier(String menuIdentifier){
        UUID uuid = UUID.fromString(menuIdentifier);
        List<Category> categories = categoryRepository.findByMenuUuid(uuid);
        if(!categories.isEmpty()){
            return categories;
        }
        throw new CategoryNotFound("Categorias não existente");

    }
}