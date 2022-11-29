package br.com.delivery.pidao.service;

import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.Silent.class)
public class MenuServiceTest {

//    private MenuService menuService;
//
//    @Mock
//    private RestaurantService restaurantService;
//
//    @Mock
//    private MenuRepository menuRepository;
//
//    @Before
//    public void setUp(){
//        menuService = new MenuService(menuRepository,restaurantService);
//    }
//
//    @Test
//    public void shouldCreateMenuIfTheDataIsValidThenReturnRestaurant(){
//        MenuDTO menuDTO = new MenuDTO(UUID.randomUUID().toString());
//        when(menuService.addMenu(menuDTO).getRestaurantIdentifier())
//                .thenReturn(anyString());
//
//        Assert.assertEquals(menuDTO.getRestaurantIdentifier(),menuService.addMenu(menuDTO).getRestaurantIdentifier());
//    }
//
//    @Test
//    public void shouldFindMenuByIdentifierThenReturnMenu(){
//        Menu menu = new Menu();
//        menu.setMenuIdentifier(UUID.randomUUID().toString());
//
//        when(menuRepository.findByMenuIdentifier(anyString()))
//                .thenReturn(Optional.of(menu));
//
//        Assert.assertEquals(menu,menuService.findMenuByIdentifier(menu.getMenuIdentifier()));
//    }
//
//    @Test
//    public void shouldDeleteMenuByIdentifierThenReturnTrue(){
//        Menu menu = new Menu();
//        menu.setMenuIdentifier(UUID.randomUUID().toString());
//
//        when(menuRepository.findByMenuIdentifier(anyString()))
//                .thenReturn(Optional.of(menu));
//
//        Assert.assertEquals(true,menuService.deleteMenu(menu.getMenuIdentifier()));
//    }
//
//    @Test
//    public void shouldGetRestaurantByIdentifierThenReturnRestaurant(){
//        Menu menu = new Menu();
//        menu.setMenuIdentifier(UUID.randomUUID().toString());
//
//        Restaurant restaurant = new Restaurant();
//        restaurant.setRestaurantIdentifier(UUID.randomUUID().toString());
//
//        menu.setRestaurantIdentifier(restaurant.getRestaurantIdentifier());
//
//        when(menuRepository.findByMenuIdentifier(anyString()))
//                .thenReturn(Optional.of(menu));
//
//
//        when(restaurantService.findByRestaurantIdentifier(restaurant.getRestaurantIdentifier()))
//                .thenReturn(restaurant);
//
//        Assert.assertEquals(restaurant,menuService.getRestaurantFromIdentifier(menu.getMenuIdentifier()));
//    }

}