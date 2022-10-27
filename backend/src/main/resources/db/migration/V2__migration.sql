INSERT INTO public.address (adress_identifier, city, client_identifier, details, neighborhood, "number", public_place, restaraunt_identifier, state, zip_code) VALUES('', '', '', '', '', '', '', '', '', '');
INSERT INTO public.category (categoryidentifier, details, menu_identifier) VALUES('', '', '');
INSERT INTO public.client (cellphone, "date", email, "name", "password", socials_security, user_identifier, adress_identifier) VALUES('', '', '', '', '', '', '', '');
INSERT INTO public.delivery (cellphone, "date", email, "name", "password", socials_security, user_identifier, status) VALUES('', '', '', '', '', '', '', 0);
INSERT INTO public.item (category_identifier, description, item_identifier, "name", value) VALUES('', '', '', '', 0);
INSERT INTO public.manager (cellphone, "date", email, "name", "password", socials_security, user_identifier, department, restaurant_identifier) VALUES('', '', '', '', '', '', '', '', '');
INSERT INTO public.menu (menuidentifier, restaurant_identifier) VALUES('', '');
INSERT INTO public.restaurant (adress_restaurant, cellphone, "date", manager_identifier, menu_identifier, "name", restaurant_identifier, socials_security) VALUES(0, '', '', '', '', '', '', '');
