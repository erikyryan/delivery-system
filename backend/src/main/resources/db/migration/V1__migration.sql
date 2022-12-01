-- public.address definition

-- Drop table

-- DROP TABLE public.address;

CREATE TABLE public.address (
                                id bytea NOT NULL,
                                city varchar(255) NULL,
                                details varchar(255) NULL,
                                neighborhood varchar(255) NULL,
                                "number" varchar(255) NULL,
                                public_place varchar(255) NULL,
                                restaraunt_id bytea NULL,
                                state varchar(255) NULL,
                                user_id bytea NULL,
                                zip_code varchar(255) NULL,
                                CONSTRAINT address_pkey PRIMARY KEY (id)
);


-- public.category definition

-- Drop table

-- DROP TABLE public.category;

CREATE TABLE public.category (
                                 id bytea NOT NULL,
                                 details varchar(255) NULL,
                                 menu_id bytea NULL,
                                 CONSTRAINT category_pkey PRIMARY KEY (id)
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


-- public.item definition

-- Drop table

-- DROP TABLE public.item;

CREATE TABLE public.item (
                             id bytea NOT NULL,
                             description varchar(255) NULL,
                             "name" varchar(255) NULL,
                             value float8 NULL,
                             category_id bytea NULL,
                             CONSTRAINT item_pkey PRIMARY KEY (id),
                             CONSTRAINT fk2n9w8d0dp4bsfra9dcg0046l4 FOREIGN KEY (category_id) REFERENCES public.category(id)
);


-- public.category_item definition

-- Drop table

-- DROP TABLE public.category_item;

CREATE TABLE public.category_item (
                                      category_id bytea NOT NULL,
                                      item_id bytea NOT NULL,
                                      CONSTRAINT uk_phkndilpkprh9p1w07pw4xejv UNIQUE (item_id),
                                      CONSTRAINT fkcq2n0opf5shyh84ex1fhukcbh FOREIGN KEY (category_id) REFERENCES public.category(id),
                                      CONSTRAINT fku8b4lwqutcdq3363gf6mlujq FOREIGN KEY (item_id) REFERENCES public.item(id)
);


-- public.restaurant definition

-- Drop table

-- DROP TABLE public.restaurant;

CREATE TABLE public.restaurant (
                                   id bytea NOT NULL,
                                   address_restaurant varchar(255) NULL,
                                   address_id bytea NULL,
                                   cellphone varchar(255) NULL,
                                   "date" varchar(255) NULL,
                                   menu_id bytea NULL,
                                   "name" varchar(255) NULL,
                                   socials_security varchar(255) NULL,
                                   transaction_id bytea NULL,
                                   CONSTRAINT restaurant_pkey PRIMARY KEY (id)
);


-- public."transaction" definition

-- Drop table

-- DROP TABLE public."transaction";

CREATE TABLE public."transaction" (
                                      id bytea NOT NULL,
                                      client_order_id bytea NULL,
                                      restaurant_id bytea NULL,
                                      user_id bytea NULL,
                                      CONSTRAINT transaction_pkey PRIMARY KEY (id)
);


-- public.users definition

-- Drop table

-- DROP TABLE public.users;

CREATE TABLE public.users (
                              id bytea NOT NULL,
                              address_id bytea NULL,
                              cellphone varchar(255) NULL,
                              "date" varchar(255) NULL,
                              email varchar(255) NULL,
                              is_admin bool NULL,
                              "name" varchar(255) NULL,
                              "password" varchar(255) NULL,
                              socials_security varchar(255) NULL,
                              "type" int4 NULL,
                              restaurant_id bytea NULL,
                              transaction_id bytea NULL,
                              CONSTRAINT users_pkey PRIMARY KEY (id)
);


-- public.restaurant foreign keys

ALTER TABLE public.restaurant ADD CONSTRAINT fkc77ul3amstbhj2dpbsfu1m74o FOREIGN KEY (transaction_id) REFERENCES public."transaction"(id);


-- public."transaction" foreign keys

ALTER TABLE public."transaction" ADD CONSTRAINT fk3oa74eeyhpt9hyc75kuuu360d FOREIGN KEY (client_order_id) REFERENCES public.client_order(id);
ALTER TABLE public."transaction" ADD CONSTRAINT fkanjpo5tiapru7an6cw4cu37y4 FOREIGN KEY (user_id) REFERENCES public.users(id);
ALTER TABLE public."transaction" ADD CONSTRAINT fknvdps9exnqq5vmfhm26gs2dn5 FOREIGN KEY (restaurant_id) REFERENCES public.restaurant(id);


-- public.users foreign keys

ALTER TABLE public.users ADD CONSTRAINT fkcn7awwo916vg0my6knhmkn03j FOREIGN KEY (restaurant_id) REFERENCES public.restaurant(id);
ALTER TABLE public.users ADD CONSTRAINT fksc6cuxsk496hl02gey5kivxih FOREIGN KEY (transaction_id) REFERENCES public."transaction"(id);