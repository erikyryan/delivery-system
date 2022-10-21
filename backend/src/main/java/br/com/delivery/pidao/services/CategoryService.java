package br.com.delivery.pidao.services;


import br.com.delivery.pidao.entities.Category;
import br.com.delivery.pidao.entities.Menu;
import br.com.delivery.pidao.entities.dto.CategoryDTO;
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

    public String addCategory(CategoryDTO categoryDTO) {
        Optional<Menu> menu = menuRepository.findByMenuIdentifier(categoryDTO.getMenuIdentifier());
        if (!menu.isPresent()) {
            throw new RuntimeException("Menu não encontrado!");
        }

        Optional<Category> category = categoryRepository.findByDetailsAndIdmenu(categoryDTO.getDetails(), menu.get().getId());

        if (category.isPresent()) {
            throw new RuntimeException("Categoria já existente!");
        }

        Category categorySaved = categoryRepository.save(new Category(categoryDTO.getDetails(),menu.get()));
        return categorySaved.getCategoryIdentifier();
    }

    public String updateCategory(CategoryDTO categoryDTO) {
        Optional<Menu> menu = menuRepository.findByMenuIdentifier(categoryDTO.getMenuIdentifier());
        if (!menu.isPresent()) {
            throw new RuntimeException("Menu não encontrado!");
        }

        Optional<Category> category = categoryRepository.findByDetailsAndIdmenu(categoryDTO.getDetails(), menu.get().getId());

        if (!category.isPresent()) {
            throw new RuntimeException("Categoria não existente!");
        }

        if(categoryDTO.getDetails().isBlank()){
            throw new RuntimeException("Details inválido!");
        }

        category.get().setDetails(categoryDTO.getDetails());
        categoryRepository.save(category.get());
        return category.get().getCategoryIdentifier();
    }

    public CategoryDTO findByIdentifier(String categoryIdentifier) {
        Optional<Category> category = categoryRepository.findByCategoryIdentifier(categoryIdentifier);
        if (!category.isPresent()) {
            throw new RuntimeException("Categoria não existente!");
        }

        CategoryDTO categoryDTO = new CategoryDTO(category.get().getDetails(), category.get().getMenu().getMenuIdentifier());

        return categoryDTO;
    }

    public Boolean deleteCategory(String categoryIdentifier) {
        Optional<Category> category = categoryRepository.findByCategoryIdentifier(categoryIdentifier);
        if (!category.isPresent()) {
            throw new RuntimeException("Categoria não existente!");
        }

        categoryRepository.delete(category.get());
        return true;
    }

    Category getCategoryByIdentifier(String categoryIdentifier){
        Optional<Category> category = categoryRepository.findByCategoryIdentifier(categoryIdentifier);

        if (category.isPresent()) {
            return category.get();
        }
        throw new RuntimeException("Categoria não existente!");
    }

    public Category isPresent(String details){
        Optional<Category> category = categoryRepository.findByDetails(details);
        return category.isPresent() ? category.get() : null;
    }
}