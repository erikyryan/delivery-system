package br.com.delivery.pidao.service;

import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.delivery.pidao.entities.Category;
import br.com.delivery.pidao.entities.Menu;
import br.com.delivery.pidao.entities.dto.CategoryDTO;
import br.com.delivery.pidao.repositories.CategoryRepository;
import br.com.delivery.pidao.services.CategoryService;
import br.com.delivery.pidao.services.MenuService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class CategoryServiceTest {
    
    private CategoryService categoryService;

    @Mock
    private MenuService menuService;

    @Mock
    private CategoryRepository categoryRepository;
    
    @BeforeEach
    public void setUp(){
        categoryService = new CategoryService(menuService, categoryRepository);
    }

    @Test 
    public void shouldCreateCategoryIfMenuIdentifierIsValidThenReturnIdentifierCategory(){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setDetails("Doces");
        categoryDTO.setMenuIdentifier("123");

        Menu menu = new Menu();
        menu.setMenuIdentifier("123");

        Category category = new Category();
        category.setMenuIdentifier(categoryDTO.getMenuIdentifier());
        
        when(menuService.findMenuByIdentifier(categoryDTO.getMenuIdentifier())).thenReturn(menu);

        when(categoryRepository.findByDetailsAndMenuIdentifier(categoryDTO.getDetails(), categoryDTO.getMenuIdentifier()))
                .thenReturn(Optional.of(category));

        try{
            categoryService.addCategory(categoryDTO);
        }catch(Exception ex){
            Assert.assertEquals(ex.getMessage(),"Categoria já existente");
        }  

    }

}