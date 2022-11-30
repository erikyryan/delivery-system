-- public.address definition

-- Drop table

-- DROP TABLE public.address;

CREATE TABLE public.address (
                                id bytea NOT NULL,
                                city varchar(255) NULL,
                                client_identifier varchar(255) NULL,
                                details varchar(255) NULL,
                                neighborhood varchar(255) NULL,
                                "number" varchar(255) NULL,
                                public_place varchar(255) NULL,
                                restaraunt_identifier varchar(255) NULL,
                                state varchar(255) NULL,
                                zip_code varchar(255) NULL,
                                CONSTRAINT address_pkey PRIMARY KEY (id)
);


-- public.category definition

-- Drop table

-- DROP TABLE public.category;

CREATE TABLE public.category (
                                 id bytea NOT NULL,
                                 details varchar(255) NULL,
                                 menu_identifier varchar(255) NULL,
                                 CONSTRAINT category_pkey PRIMARY KEY (id)
);


-- public.client definition

-- Drop table

-- DROP TABLE public.client;

CREATE TABLE public.client (
                               id bytea NOT NULL,
                               cellphone varchar(255) NULL,
                               "date" varchar(255) NULL,
                               email varchar(255) NULL,
                               "name" varchar(255) NULL,
                               "password" varchar(255) NULL,
                               socials_security varchar(255) NULL,
                               user_identifier bytea NULL,
                               address_identifier varchar(255) NULL,
                               client_order_identifier varchar(255) NULL,
                               CONSTRAINT client_pkey PRIMARY KEY (id)
);


-- public.client_order definition

-- Drop table

-- DROP TABLE public.client_order;

CREATE TABLE public.client_order (
                                     id bytea NOT NULL,
                                     client_identifier varchar(255) NULL,
                                     "comment" varchar(255) NULL,
                                     "name" varchar(255) NULL,
                                     status int4 NULL,
                                     value varchar(255) NULL,
                                     CONSTRAINT client_order_pkey PRIMARY KEY (id)
);


-- public.delivery definition

-- Drop table

-- DROP TABLE public.delivery;

CREATE TABLE public.delivery (
                                 id bytea NOT NULL,
                                 cellphone varchar(255) NULL,
                                 "date" varchar(255) NULL,
                                 email varchar(255) NULL,
                                 "name" varchar(255) NULL,
                                 "password" varchar(255) NULL,
                                 socials_security varchar(255) NULL,
                                 user_identifier bytea NULL,
                                 status int4 NULL,
                                 CONSTRAINT delivery_pkey PRIMARY KEY (id)
);


-- public.item definition

-- Drop table

-- DROP TABLE public.item;

CREATE TABLE public.item (
                             id bytea NOT NULL,
                             category_identifier varchar(255) NOT NULL,
                             description varchar(255) NOT NULL,
                             "name" varchar(255) NOT NULL,
                             value float8 NOT NULL,
                             CONSTRAINT item_pkey PRIMARY KEY (id, category_identifier, description, name, value),
                             CONSTRAINT uk_acsft7jsqopjaisbkjnm2e3rc UNIQUE (id)
);


-- public.loginsession definition

-- Drop table

-- DROP TABLE public.loginsession;

CREATE TABLE public.loginsession (
                                     id bytea NOT NULL,
                                     expirationdate timestamp NOT NULL,
                                     logindate timestamp NOT NULL,
                                     logoutdate timestamp NULL,
                                     "token" varchar(255) NOT NULL,
                                     useridentifier varchar(255) NULL,
                                     CONSTRAINT loginsession_pkey PRIMARY KEY (id)
);


-- public.manager definition

-- Drop table

-- DROP TABLE public.manager;

CREATE TABLE public.manager (
                                id bytea NOT NULL,
                                cellphone varchar(255) NULL,
                                "date" varchar(255) NULL,
                                email varchar(255) NULL,
                                "name" varchar(255) NULL,
                                "password" varchar(255) NULL,
                                socials_security varchar(255) NULL,
                                user_identifier bytea NULL,
                                department varchar(255) NULL,
                                restaurant_identifier varchar(255) NULL,
                                CONSTRAINT manager_pkey PRIMARY KEY (id)
);


-- public.menu definition

-- Drop table

-- DROP TABLE public.menu;

CREATE TABLE public.menu (
                             id bytea NOT NULL,
                             restaurant_identifier varchar(255) NULL,
                             CONSTRAINT menu_pkey PRIMARY KEY (id)
);


-- public.rating definition

-- Drop table

-- DROP TABLE public.rating;

CREATE TABLE public.rating (
                               id bytea NOT NULL,
                               rating int8 NULL,
                               CONSTRAINT rating_pkey PRIMARY KEY (id)
);


-- public.restaurant definition

-- Drop table

-- DROP TABLE public.restaurant;

CREATE TABLE public.restaurant (
                                   id bytea NOT NULL,
                                   address_restaurant varchar(255) NULL,
                                   cellphone varchar(255) NULL,
                                   "date" varchar(255) NULL,
                                   manager_identifier varchar(255) NULL,
                                   menu_identifier varchar(255) NULL,
                                   "name" varchar(255) NULL,
                                   socials_security varchar(255) NULL,
                                   CONSTRAINT restaurant_pkey PRIMARY KEY (id)
);


-- public.super_user definition

-- Drop table

-- DROP TABLE public.super_user;

-- CREATE TABLE public.super_user (
--                                    id int8 NOT NULL,
--                                    email varchar(255) NOT NULL,
--                                    senha varchar(255) NOT NULL,
--                                    CONSTRAINT super_user_pkey PRIMARY KEY (id, email, senha)
-- );