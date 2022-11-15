INSERT INTO public.manager (cellphone, date, email, name, password, socials_security, user_identifier, department, restaurant_identifier) VALUES('(88) 98824-0365', '1999-09-24', 'israellima@gmail.com', 'Israel Lima', 'israel1234', '352.787.610-32', 'd69baea9-600d-43fb-bb6e-e9ed0b06a1a3', 'Departamento 2', '8492df54-cf7f-4e03-a7e2-214d09a7c46a');

INSERT INTO public.client (cellphone, date, email, name, password, socials_security, user_identifier, address_identifier) VALUES('(88) 99924-0365', '2002-10-12', 'mariarita@gmail.com', 'Maria Rita Lima', 'mariarita123', '494.702.410-02', 'e5f33e59-a959-4b48-812b-8500d457ca9b', '7e0a28e0-e44b-40fd-9b47-622c39da8808');
INSERT INTO public.address (address_identifier, city, client_identifier, details, neighborhood, number, public_place, restaraunt_identifier, state, zip_code) VALUES('7e0a28e0-e44b-40fd-9b47-622c39da8808', 'Russas', 'e5f33e59-a959-4b48-812b-8500d457ca9b', 'Em frente ao Mercadinho UFC', 'Bairro de Fátima', '67', 'Rua Dom Lino', '', 'Ceará', '62900-000');

INSERT INTO public.client (cellphone, date, email, name, password, socials_security, user_identifier, address_identifier) VALUES('(85) 99920-0166', '2000-11-02', 'joseraimundo@gmail.com', 'José Raimundo Nogueira', 'jose12345', '731.485.580-30', '37493e86-10a2-486f-88c6-92a7adf449b3', '58fb344e-1fb3-470c-842c-0c8155f3df34');
INSERT INTO public.address (address_identifier, city, client_identifier, details, neighborhood, number, public_place, restaraunt_identifier, state, zip_code) VALUES('58fb344e-1fb3-470c-842c-0c8155f3df34', 'Fortaleza', '37493e86-10a2-486f-88c6-92a7adf449b3', 'Em frente a UFC', 'Meireles', '566', 'Rua Acaraú', '', 'Ceará', '60160-180');

INSERT INTO public.delivery (cellphone, date, email, name, password, socials_security, user_identifier, status) VALUES('(85) 99827-0056', '2001-05-09', 'gilvanmoreira@gmail.com', 'Gilvan Moreira Sousa', 'gilvan1234', '410.031.990-89', '7c25d296-06aa-46ea-b406-9523e8ff2b18', 0);

INSERT INTO public.restaurant (address_restaurant, cellphone, date, manager_identifier, menu_identifier, name, restaurant_identifier, socials_security) VALUES('4575b501-cd6e-4e31-8423-4fdccb8f9bbb', '4902-8922', '2022-09-14', 'd69baea9-600d-43fb-bb6e-e9ed0b06a1a3', '72cca745-63e0-4d38-88b2-74ac54fa7458', 'Terraço 271', '8492df54-cf7f-4e03-a7e2-214d09a7c46a', '21.569.858/0001-41');
INSERT INTO public.address (address_identifier, city, client_identifier, details, neighborhood, number, public_place, restaraunt_identifier, state, zip_code) VALUES('4575b501-cd6e-4e31-8423-4fdccb8f9bbb', 'Russas', '', 'Em frente a Praça Matriz', 'Centro', '271', 'Rua Padre Zacarias Ramalho', '8492df54-cf7f-4e03-a7e2-214d09a7c46a', 'Ceará', '62900-000');

INSERT INTO public.menu (menuidentifier, restaurant_identifier) VALUES('2632b1bc-142e-401c-a1b4-0365b8240546', '8492df54-cf7f-4e03-a7e2-214d09a7c46a');

INSERT INTO public.category (categoryidentifier, details, menu_identifier) VALUES('d69baea9-600d-43fb-bb6e-e9ed0b06a1a3', 'Pizza', '2632b1bc-142e-401c-a1b4-0365b8240546');
INSERT INTO public.category (categoryidentifier, details, menu_identifier) VALUES('b9fb8b33-03cb-4076-924d-5fef046ca9aa', 'Hambuguer', '2632b1bc-142e-401c-a1b4-0365b8240546');


INSERT INTO public.item (category_identifier, description, item_identifier, name, value) VALUES('d69baea9-600d-43fb-bb6e-e9ed0b06a1a3', 'Molho de tomate, mussarela, lombo canadense fatiado, milho, bacon e alho tostado.', 'c87a212f-13c2-414d-a141-41105d1927ab', 'Sertaneja', 34.99);
INSERT INTO public.item (category_identifier, description, item_identifier, name, value) VALUES('b9fb8b33-03cb-4076-924d-5fef046ca9aa', 'bacon bife de hambúrguer, presunto, queijo mussarela, ovo frito, tomate, alface, hambúrguer, maionese, catchup', '95699ce4-b52f-46e7-acf9-2f3c6186b530', 'X Tudo', 14.99);

INSERT INTO public.category (categoryidentifier, details, menu_identifier) VALUES('b741f887-f3d5-4f08-b929-f85a9f587cf1', 'Vegetariana', '3e203b46-49f1-46bd-957e-d02f375a71db');
INSERT INTO public.manager (cellphone, "date", email, "name", "password", socials_security, user_identifier, department, restaurant_identifier) VALUES('(85) 99523-6127', '2022-09-25', 'manager@gmail.com', 'Bianca', 'manager123', '5bb908cc-9ba2-45fc-b2d3-d734eec9cb6c','48b2bae7-3226-4170-9825-c371bf7fdf73', 'Departamento 1', 'd8d6357d-4e8d-45c8-9977-c9dd417aa683');
INSERT INTO public.menu (menuidentifier, restaurant_identifier) VALUES('3e203b46-49f1-46bd-957e-d02f375a71db', 'd8d6357d-4e8d-45c8-9977-c9dd417aa683');
INSERT INTO public.restaurant (address_restaurant, cellphone, "date", manager_identifier, menu_identifier, "name", restaurant_identifier, socials_security) VALUES('4575b501-cd6e-4e31-8423-4fdccb8f9bbb', '(85) 2639-4259', '2022-09-21', '48b2bae7-3226-4170-9825-c371bf7fdf73', '3b54d4ab-6d5f-4ab4-95ff-d20fe3ebb2e1', 'Fornazzo', 'd8d6357d-4e8d-45c8-9977-c9dd417aa683', '8fbba881-fa8a-44de-8a14-26fa16119f7d');
