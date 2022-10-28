INSERT INTO public.address (adress_identifier, city, client_identifier, details, neighborhood, number, public_place, restaraunt_identifier, state, zip_code) VALUES('', '', '', '', '', '', '', '', '', '');
INSERT INTO public.category (categoryidentifier, details, menu_identifier) VALUES('', '', '');
INSERT INTO public.client (cellphone, date, email, name, password, socials_security, user_identifier, adress_identifier) VALUES('', '', '', '', '', '', '', '');
INSERT INTO public.delivery (cellphone, date, email, name, password, socials_security, user_identifier, status) VALUES('', '', '', '', '', '', '', 0);
INSERT INTO public.item (category_identifier, description, item_identifier, name, value) VALUES('', '', '', '', 0);
INSERT INTO public.manager (cellphone, date, email, name, password, socials_security, user_identifier, department, restaurant_identifier) VALUES('', '', '', '', '', '', '', '', '');
INSERT INTO public.menu (menuidentifier, restaurant_identifier) VALUES('', '');
INSERT INTO public.restaurant (adress_restaurant, cellphone, date, manager_identifier, menu_identifier, name, restaurant_identifier, socials_security) VALUES(0, '', '', '', '', '', '', '');


INSERT INTO public.client (cellphone, date, email, name, password, socials_security, user_identifier, adress_identifier) VALUES('(88) 99924-0365', '2002-10-12', 'mariarita@gmail.com', 'Maria Rita Lima', 'mariarita123', '494.702.410-02', 'e5f33e59-a959-4b48-812b-8500d457ca9b', '7e0a28e0-e44b-40fd-9b47-622c39da8808');
INSERT INTO public.address (adress_identifier, city, client_identifier, details, neighborhood, number, public_place, restaraunt_identifier, state, zip_code) VALUES('7e0a28e0-e44b-40fd-9b47-622c39da8808', 'Russas', 'e5f33e59-a959-4b48-812b-8500d457ca9b', 'Em frente ao Mercadinho UFC', '', '67', '', '', 'Ceará', '62900-000');

INSERT INTO public.client (cellphone, date, email, name, password, socials_security, user_identifier, adress_identifier) VALUES('(85) 99920-0166', '2000-11-02', 'joseraimundo@gmail.com', 'José Raimundo Nogueira', 'jose12345', '731.485.580-30', '37493e86-10a2-486f-88c6-92a7adf449b3', '58fb344e-1fb3-470c-842c-0c8155f3df34');
INSERT INTO public.address (adress_identifier, city, client_identifier, details, neighborhood, number, public_place, restaraunt_identifier, state, zip_code) VALUES('58fb344e-1fb3-470c-842c-0c8155f3df34', 'Fortaleza', '37493e86-10a2-486f-88c6-92a7adf449b3', 'Em frente a UFC', '', '566', '', '', 'Ceará', '06803-440');

INSERT INTO public.delivery (cellphone, date, email, name, password, socials_security, user_identifier, status) VALUES('(85) 99827-0056', '2001-05-09', 'gilvanmoreira@gmail.com', 'Gilvan Moreira Sousa', 'gilvan1234', '410.031.990-89', '7c25d296-06aa-46ea-b406-9523e8ff2b18', 0);

INSERT INTO public.restaurant (adress_restaurant, cellphone, date, manager_identifier, menu_identifier, name, restaurant_identifier, socials_security) VALUES(0, '', '', '', '', '', '', '');

INSERT INTO public.menu (menuidentifier, restaurant_identifier) VALUES('', '');

INSERT INTO public.category (categoryidentifier, details, menu_identifier) VALUES('d69baea9-600d-43fb-bb6e-e9ed0b06a1a3', 'Pizza', '');
