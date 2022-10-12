package br.com.delivery.pidao.dao;

import br.com.delivery.pidao.entities.Category;
import br.com.delivery.pidao.repositories.CategoryRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CategoryDAO {

    private CategoryRepository categoryRepository;

    public Category isPresent(String details){
        Optional<Category> category = categoryRepository.findByDetails(details);
        return category.isPresent() ? category.get() : null;
    }

    public Category getCategory(Long idMenu, String categoryDetails){
        Optional<Category> category = categoryRepository.findByDetailsAndIdmenu(categoryDetails,idMenu);
        if (category.isPresent()) {
            return category.get();
        }
        throw new RuntimeException("Categoria não existente!");
    }

    private List<Category> getCategoriesFromMenuAndDetails(Long idMenu, List<String> categoryDetails){
        return categoryDetails.stream().map( details ->
                getCategory(idMenu,details)).collect(Collectors.toList());

    }

    public Category getCategoryByIdentifier(String identifier){
        Optional<Category> category = categoryRepository.findByCategoryIdentifier(identifier);
        if(category.isPresent()){
            return category.get();
        }
        throw new RuntimeException("Categoria inválida!");
    }

}
