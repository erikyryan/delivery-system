package br.com.delivery.pidao.service;

import br.com.delivery.pidao.entities.Menu;
import br.com.delivery.pidao.entities.Restaurant;
import br.com.delivery.pidao.entities.dto.MenuDTO;
import br.com.delivery.pidao.repositories.MenuRepository;
import br.com.delivery.pidao.repositories.RestaurantRepository;
import br.com.delivery.pidao.services.MenuService;
import br.com.delivery.pidao.services.RestaurantService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class MenuServiceTest {

    private MenuService menuService;

    @Mock
    private RestaurantService restaurantService;

    @Mock
    private MenuRepository menuRepository;

    @Before
    public void setUp(){
        menuService = new MenuService(menuRepository,restaurantService);
    }

    @Test
    public void shouldCreateMenuIfTheDataIsValidThenReturnRestaurant(){
        MenuDTO menuDTO = new MenuDTO(UUID.randomUUID().toString());
        when(menuService.addMenu(menuDTO).getRestaurantIdentifier())
                .thenReturn(anyString());

        Assert.assertEquals(menuDTO.getRestaurantIdentifier(),menuService.addMenu(menuDTO).getRestaurantIdentifier());
    }

    @Test
    public void shouldFindMenuByIdentifierThenReturnMenu(){
        Menu menu = new Menu();
        menu.setMenuIdentifier(UUID.randomUUID().toString());

        when(menuRepository.findByMenuIdentifier(anyString()))
                .thenReturn(Optional.of(menu));

        Assert.assertEquals(menu,menuService.findMenuByIdentifier(menu.getMenuIdentifier()));
    }

    @Test
    public void shouldDeleteMenuByIdentifierThenReturnTrue(){
        Menu menu = new Menu();
        menu.setMenuIdentifier(UUID.randomUUID().toString());

        when(menuRepository.findByMenuIdentifier(anyString()))
                .thenReturn(Optional.of(menu));

        Assert.assertEquals(true,menuService.deleteMenu(menu.getMenuIdentifier()));
    }

    @Test
    public void shouldGetRestaurantByIdentifierThenReturnRestaurant(){
        Menu menu = new Menu();
        menu.setMenuIdentifier(UUID.randomUUID().toString());

        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantIdentifier(UUID.randomUUID().toString());

        menu.setRestaurantIdentifier(restaurant.getRestaurantIdentifier());

        when(menuRepository.findByMenuIdentifier(anyString()))
                .thenReturn(Optional.of(menu));


        when(restaurantService.findByRestaurantIdentifier(restaurant.getRestaurantIdentifier()))
                .thenReturn(restaurant);

        Assert.assertEquals(restaurant,menuService.getRestaurantFromIdentifier(menu.getMenuIdentifier()));
    }

}