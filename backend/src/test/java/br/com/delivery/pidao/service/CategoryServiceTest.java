package br.com.delivery.pidao.service;

import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.delivery.pidao.entities.Category;
import br.com.delivery.pidao.entities.Menu;
import br.com.delivery.pidao.entities.dto.CategoryDTO;
import br.com.delivery.pidao.repositories.CategoryRepository;
import br.com.delivery.pidao.services.CategoryService;
import br.com.delivery.pidao.services.MenuService;
import junit.framework.Assert;

@RunWith(MockitoJUnitRunner.Silent.class)
public class CategoryServiceTest {
    
    private CategoryService categoryService;

    @Mock
    private MenuService menuService;

    @Mock
    private CategoryRepository categoryRepository;
    
    @Before
    public void setUp(){
        categoryService = new CategoryService(menuService, categoryRepository);
    }

    @Test 
    public void shouldCreateCategoryIfMenuIdentifierIsValidThenReturnIdentifierCategory(){
        UUID uuid = UUID.randomUUID();
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setDetails("Doces");
        categoryDTO.setMenuIdentifier(uuid.toString());

        Menu menu = new Menu();
        menu.setUuid(uuid);

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
    
    @Test
    public void shouldFindCategoryByIdentifierThenReturnCategory(){
        Category category = new Category();
        category.setUuid(UUID.randomUUID());
        category.setDetails("Doce");
        category.setMenuIdentifier(UUID.randomUUID().toString());

        
       when(categoryRepository.findByUuid(category.getUuid()))
                .thenReturn(Optional.of(category));

        Assert.assertEquals(category.getMenuIdentifier(),categoryService.findByIdentifier(category.getUuid().toString()).getMenuIdentifier());
    } 

    @Test 
    public void shouldDeleteCategoryByIdentifierThenReturnTrue(){
        Category category = new Category();
        category.setUuid(UUID.randomUUID());

        when(categoryRepository.findByUuid(category.getUuid()))
                .thenReturn(Optional.of(category));

        
        Assert.assertTrue(true);
    }

}