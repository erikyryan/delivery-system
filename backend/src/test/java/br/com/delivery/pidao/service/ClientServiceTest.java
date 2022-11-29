package br.com.delivery.pidao.service;

import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ClientServiceTest {
/* 
    private ClientService clientService;

    @Mock
    private UserDAO userDAO;

    @Mock
    private UserRepository userRepository;

    @Mock
    private SessionService sessionService;

    @Mock
    private AdressService adressService;

    @Mock
    private AdressRepository adressRepository;

    @Before
    public void setUp(){
        clientService = new ClientService(userDAO,userRepository,sessionService,adressService,adressRepository);
    }

    @Test
    public void shouldCreateUserClientThenReturnAClientDTO(){
        ClientDTO clientDTO = new ClientDTO();
        AddressCustomerDTO addressClientDTO = new AddressCustomerDTO("publicPlace","number","zipCode","neighborhood","state","city","details");
        clientDTO.setAddressDTO(addressClientDTO);
        clientDTO.setEmail("joseraimundo@gmail.com");
        clientDTO.setPassword("JoseKSGDFD@1723!2345");
        clientDTO.setSocialsSecurity("731485.580-30");
        Customer client = clientDTO.dtoToEntity();
        AddressDTO addressDTO = new AddressDTO();

        when(adressService.addAdress(addressDTO)).thenReturn(addressDTO);
        when(clientRepository.save(client)).thenReturn(client);

        Assert.assertEquals(clientService.createUserClient(clientDTO),clientDTO);
    }


    @Test
    public void shouldCreateUserManagerThenReturnAManagerDTO(){
        ManagerDTO managerDTO = new ManagerDTO();
        AdressRestaurantDTO adressRestaurantDTO = new AdressRestaurantDTO("publicPlace","number","zipCode","neighborhood","state","city","details");
        managerDTO.setAddressDTO(adressRestaurantDTO);
        managerDTO.setEmail("joseraimundo@gmail.com");
        managerDTO.setPassword("JoseKSGDFD@1723!2345");
        managerDTO.setSocialsSecurity("731485.580-30");
        AddressDTO addressDTO = adressRestaurantDTO
                .dtoAndRestaurantIdentifierToAdressDTO(UUID.randomUUID().toString());

        when(adressService.addAdress(addressDTO)).thenReturn(addressDTO);
        when(managerRepository.save(managerDTO.dtoToEntity())).thenReturn(managerDTO.dtoToEntity());

        Assert.assertEquals(clientService.createUserManager(managerDTO),managerDTO);
    }

    @Test
    public void shouldWhenManagerEmailIsInvalidThenExpectException(){
        ManagerDTO managerDTO = new ManagerDTO();
        AdressRestaurantDTO adressRestaurantDTO = new AdressRestaurantDTO("publicPlace","number","zipCode","neighborhood","state","city","details");
        managerDTO.setAddressDTO(adressRestaurantDTO);
        managerDTO.setEmail("joseraimundo@gmail.com");
        managerDTO.setPassword("JoseKSGDFD@1723!2345");
        managerDTO.setSocialsSecurity("731.485.580-30");

        AddressDTO addressDTO = adressRestaurantDTO.dtoAndRestaurantIdentifierToAdressDTO(UUID.randomUUID().toString());

        when(adressService.addAdress(addressDTO)).thenReturn(addressDTO);
        when(managerRepository.save(managerDTO.dtoToEntity())).thenReturn(managerDTO.dtoToEntity());

        try{
            clientService.createUserManager(managerDTO);
        }catch (Exception e){
            Assert.assertEquals(e.getMessage(),"Email ínvalido");

        }
    }

    @Test
    public void shouldWhenManagerEmailIsSavedThenExpectMessage(){
        ManagerDTO managerDTO = new ManagerDTO();
        String email = "joseraimundo@gmail.com";
        AdressRestaurantDTO adressRestaurantDTO = new AdressRestaurantDTO("publicPlace","number","zipCode","neighborhood","state","city","details");
        managerDTO.setAddressDTO(adressRestaurantDTO);
        managerDTO.setEmail(email);
        managerDTO.setPassword("JoseKSGDFD@1723!2345");
        managerDTO.setSocialsSecurity("731.485.580-30");

        AddressDTO addressDTO = adressRestaurantDTO.dtoAndRestaurantIdentifierToAdressDTO(UUID.randomUUID().toString());

        when(adressService.addAdress(addressDTO)).thenReturn(addressDTO);
        when(managerRepository.findByEmail(email)).thenReturn(Optional.of(managerDTO.dtoToEntity()));
        when(managerRepository.save(managerDTO.dtoToEntity())).thenReturn(managerDTO.dtoToEntity());

        try{
            clientService.createUserManager(managerDTO);
        }catch (Exception e){
            Assert.assertEquals(e.getMessage(),"Email já Existente");
        }
    }
    
    @Test
	public void shouldWhenClientIsSavedThenExpectMessage() {
		ClientDTO clientDTO = new ClientDTO();
		String email = "joseraimundo@gmail.com";
        AddressCustomerDTO addressClientDTO = new AddressCustomerDTO("publicPlace", "number", "zipCode",
				"neighborhood", "state", "city", "details");
		clientDTO.setAddressDTO(addressClientDTO);
		clientDTO.setEmail(email);
		clientDTO.setPassword("JoseKSGDFD@1723!2345");
		clientDTO.setSocialsSecurity("731.485.580-30");

		AddressDTO addressDTO = addressClientDTO.dtoAndClientIdentifierToAdressDTO(UUID.randomUUID().toString());

		when(adressService.addAdress(addressDTO)).thenReturn(addressDTO);
		when(clientRepository.findByEmail(email)).thenReturn(Optional.of(clientDTO.dtoToEntity()));
		when(clientRepository.save(clientDTO.dtoToEntity())).thenReturn(clientDTO.dtoToEntity());

		try {
			clientService.createUserClient(clientDTO);
		} catch (Exception e) {
			Assert.assertEquals(e.getMessage(), "Email já Existente");
		}
	}
*/
}
