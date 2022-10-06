package br.com.delivery.pidao.dao;

import br.com.delivery.pidao.entities.Category;
import br.com.delivery.pidao.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class CategoryDAO {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category isPresent(String details,Long idMenu){
        Optional<Category> category = categoryRepository.findByDetailsAndIdmenu(details,idMenu);
        return category.isPresent() ? category.get() : null;
    }

}
