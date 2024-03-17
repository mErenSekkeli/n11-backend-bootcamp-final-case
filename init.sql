CREATE SEQUENCE IF NOT EXISTS review_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS review (
    id BIGINT PRIMARY KEY NOT NULL DEFAULT nextval('review_id_seq'),
    create_date TIMESTAMP NOT NULL,
    update_date TIMESTAMP NOT NULL,
    customer_id BIGINT NOT NULL,
    company_id VARCHAR(255) NOT NULL,
    rate INT NOT NULL,
    comment VARCHAR(500)
);

ALTER SEQUENCE review_id_seq OWNED BY review.id;

CREATE SEQUENCE IF NOT EXISTS customer_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE IF NOT EXISTS customer_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS customer (
    id BIGINT PRIMARY KEY NOT NULL DEFAULT nextval('customer_id_seq'),
    create_date TIMESTAMP NOT NULL,
    update_date TIMESTAMP NOT NULL,
    name VARCHAR(100) NOT NULL,
    surname VARCHAR(100) NOT NULL,
    birth_date DATE,
    username VARCHAR(100) NOT NULL UNIQUE,
    phone_number VARCHAR(20) NOT NULL,
    email VARCHAR(100),
    status VARCHAR(30) NOT NULL,
    latitude DOUBLE PRECISION NOT NULL,
    longitude DOUBLE PRECISION NOT NULL
);

ALTER SEQUENCE customer_id_seq OWNED BY customer.id;


INSERT INTO public.customer (id, create_date, update_date, birth_date, email, latitude, longitude, name, phone_number, status, surname, username) VALUES (2, '2024-03-15 16:55:10.703244', '2024-03-15 16:55:10.703244', '2000-05-08', 'ahmet@mail.com', 41.0403732, 28.8098076, 'Ahmet', '+905555555555', 'ACTIVE', 'Yilmaz', 'ahmet123');
INSERT INTO public.customer (id, create_date, update_date, birth_date, email, latitude, longitude, name, phone_number, status, surname, username) VALUES (3, '2024-03-15 17:00:43.317342', '2024-03-15 17:00:43.317342', '1993-01-08', 'immas01@mail.com', 41.013948, 28.8673311, 'Sami', '+905555555555', 'ACTIVE', 'Sekkeli', 'immas01');
INSERT INTO public.customer (id, create_date, update_date, birth_date, email, latitude, longitude, name, phone_number, status, surname, username) VALUES (4, '2024-03-15 17:01:42.788781', '2024-03-15 17:01:42.788781', '1991-01-08', 'murat@mail.com', 40.9802386, 28.718877, 'Murat', '+905555555555', 'ACTIVE', 'Kara', 'muratkara');
INSERT INTO public.customer (id, create_date, update_date, birth_date, email, latitude, longitude, name, phone_number, status, surname, username) VALUES (5, '2024-03-16 17:24:05.183187', '2024-03-16 17:24:05.183187', '2024-01-05', 'ayse@mail.com', 41.03805, 28.898998, 'Ayşe Duru', '+905555555555', 'ACTIVE', 'Şekkeli', 'ayseduru');
INSERT INTO public.customer (id, create_date, update_date, birth_date, email, latitude, longitude, name, phone_number, status, surname, username) VALUES (1, '2024-03-16 22:21:59.188999', '2024-03-16 22:21:59.188999', '2002-03-08', 'erensekkeli46@gmail.com', 41.0405249, 28.9459741, 'Muhammed Eren', '+905315842165', 'ACTIVE', 'Şekkeli', 'erensekkeli');



INSERT INTO public.review (id, create_date, update_date, comment, company_id, customer_id, rate) VALUES (2, '2024-03-15 18:43:56.220620', '2024-03-15 18:43:56.220620', 'kötüydü', '8b0b253b-3e83-475b-803d-566d289c0f65', 1, 0);
INSERT INTO public.review (id, create_date, update_date, comment, company_id, customer_id, rate) VALUES (3, '2024-03-15 18:44:58.245468', '2024-03-15 18:44:58.245468', '', '8b0b253b-3e83-475b-803d-566d289c0f65', 2, 2);
INSERT INTO public.review (id, create_date, update_date, comment, company_id, customer_id, rate) VALUES (4, '2024-03-15 18:47:12.585410', '2024-03-15 18:47:12.585410', 'beğenmedim', '8b0b253b-3e83-475b-803d-566d289c0f65', 3, 1);
INSERT INTO public.review (id, create_date, update_date, comment, company_id, customer_id, rate) VALUES (5, '2024-03-15 18:47:35.706269', '2024-03-15 18:47:35.706269', 'ortalamaydı', '8b0b253b-3e83-475b-803d-566d289c0f65', 4, 3);
INSERT INTO public.review (id, create_date, update_date, comment, company_id, customer_id, rate) VALUES (6, '2024-03-15 18:48:39.210426', '2024-03-15 18:48:39.210426', 'Çok iyiydi', 'a9a3ab20-f8da-4713-9fe0-2dc253614810', 1, 4);
INSERT INTO public.review (id, create_date, update_date, comment, company_id, customer_id, rate) VALUES (7, '2024-03-15 18:48:53.587360', '2024-03-15 18:48:53.587360', 'Baya güzeldi', 'a9a3ab20-f8da-4713-9fe0-2dc253614810', 2, 3);
INSERT INTO public.review (id, create_date, update_date, comment, company_id, customer_id, rate) VALUES (9, '2024-03-15 18:49:11.666872', '2024-03-15 18:49:11.666872', '', 'a9a3ab20-f8da-4713-9fe0-2dc253614810', 4, 4);
INSERT INTO public.review (id, create_date, update_date, comment, company_id, customer_id, rate) VALUES (10, '2024-03-15 21:36:03.020350', '2024-03-15 21:36:03.020350', 'iyi', '68fa3b9b-9a3a-481c-92fd-d4119538cbd5', 1, 3);
INSERT INTO public.review (id, create_date, update_date, comment, company_id, customer_id, rate) VALUES (11, '2024-03-15 21:36:17.042327', '2024-03-15 21:36:17.042327', 'iyi', '68fa3b9b-9a3a-481c-92fd-d4119538cbd5', 2, 2);
INSERT INTO public.review (id, create_date, update_date, comment, company_id, customer_id, rate) VALUES (12, '2024-03-15 21:36:29.731744', '2024-03-15 21:36:29.731744', 'güzeldi', '68fa3b9b-9a3a-481c-92fd-d4119538cbd5', 3, 4);
INSERT INTO public.review (id, create_date, update_date, comment, company_id, customer_id, rate) VALUES (13, '2024-03-15 21:36:42.309683', '2024-03-15 21:36:42.309683', 'beğenmedim', '68fa3b9b-9a3a-481c-92fd-d4119538cbd5', 4, 1);
INSERT INTO public.review (id, create_date, update_date, comment, company_id, customer_id, rate) VALUES (14, '2024-03-15 21:37:08.942421', '2024-03-15 21:37:08.942421', 'çok kötü', '1a810fb3-ec34-45dc-94bc-c1370a3b3c63', 1, 0);
INSERT INTO public.review (id, create_date, update_date, comment, company_id, customer_id, rate) VALUES (15, '2024-03-15 21:37:19.553091', '2024-03-15 21:37:19.553091', '', '1a810fb3-ec34-45dc-94bc-c1370a3b3c63', 2, 3);
INSERT INTO public.review (id, create_date, update_date, comment, company_id, customer_id, rate) VALUES (16, '2024-03-15 21:37:26.310053', '2024-03-15 21:37:26.310053', '', '1a810fb3-ec34-45dc-94bc-c1370a3b3c63', 3, 2);
INSERT INTO public.review (id, create_date, update_date, comment, company_id, customer_id, rate) VALUES (17, '2024-03-15 21:37:49.485073', '2024-03-15 21:37:49.485073', 'çok iyi', '1a02f90c-21fe-4380-adf8-1c8ad2309cef', 1, 4);
INSERT INTO public.review (id, create_date, update_date, comment, company_id, customer_id, rate) VALUES (18, '2024-03-15 21:38:08.357729', '2024-03-15 21:38:08.357729', '', '1a02f90c-21fe-4380-adf8-1c8ad2309cef', 2, 4);
INSERT INTO public.review (id, create_date, update_date, comment, company_id, customer_id, rate) VALUES (19, '2024-03-15 21:38:13.226190', '2024-03-15 21:38:13.226190', '', '1a02f90c-21fe-4380-adf8-1c8ad2309cef', 3, 3);
INSERT INTO public.review (id, create_date, update_date, comment, company_id, customer_id, rate) VALUES (20, '2024-03-15 21:38:38.578650', '2024-03-15 21:38:38.578650', '', '1a02f90c-21fe-4380-adf8-1c8ad2309cef', 4, 4);
INSERT INTO public.review (id, create_date, update_date, comment, company_id, customer_id, rate) VALUES (21, '2024-03-15 21:43:04.520042', '2024-03-15 21:43:04.520042', 'Çok güzel', 'a1027060-2861-4aca-9613-f1a3ff4f94d9', 1, 4);
INSERT INTO public.review (id, create_date, update_date, comment, company_id, customer_id, rate) VALUES (23, '2024-03-15 23:13:51.871947', '2024-03-15 23:13:51.871947', 'Çok iyi', 'a1027060-2861-4aca-9613-f1a3ff4f94d9', 2, 4);
INSERT INTO public.review (id, create_date, update_date, comment, company_id, customer_id, rate) VALUES (24, '2024-03-15 23:14:02.392678', '2024-03-15 23:14:02.392678', '', 'a1027060-2861-4aca-9613-f1a3ff4f94d9', 4, 3);
INSERT INTO public.review (id, create_date, update_date, comment, company_id, customer_id, rate) VALUES (25, '2024-03-15 23:14:21.079061', '2024-03-15 23:14:21.079061', 'Berbat', 'a465882e-a7dc-4205-ae13-bfafd05c3cd3', 4, 1);
INSERT INTO public.review (id, create_date, update_date, comment, company_id, customer_id, rate) VALUES (26, '2024-03-15 23:14:29.356275', '2024-03-15 23:14:29.356275', 'meh', 'a465882e-a7dc-4205-ae13-bfafd05c3cd3', 3, 2);
INSERT INTO public.review (id, create_date, update_date, comment, company_id, customer_id, rate) VALUES (27, '2024-03-15 23:14:38.177723', '2024-03-15 23:14:38.177723', '', 'a465882e-a7dc-4205-ae13-bfafd05c3cd3', 1, 2);
INSERT INTO public.review (id, create_date, update_date, comment, company_id, customer_id, rate) VALUES (28, '2024-03-16 00:21:40.967266', '2024-03-16 00:21:40.967266', 'kötü', '055a57db-bb1d-4804-93ee-266c6caf13e4', 1, 1);
INSERT INTO public.review (id, create_date, update_date, comment, company_id, customer_id, rate) VALUES (29, '2024-03-16 00:21:51.688651', '2024-03-16 00:21:51.688651', 'ortalama', '055a57db-bb1d-4804-93ee-266c6caf13e4', 2, 2);
INSERT INTO public.review (id, create_date, update_date, comment, company_id, customer_id, rate) VALUES (30, '2024-03-16 00:22:01.598202', '2024-03-16 00:22:01.598202', '', '055a57db-bb1d-4804-93ee-266c6caf13e4', 3, 2);
INSERT INTO public.review (id, create_date, update_date, comment, company_id, customer_id, rate) VALUES (31, '2024-03-16 17:13:53.145235', '2024-03-16 17:13:53.145235', 'ortalama', '24c18239-59a4-45a5-a554-dc1c36a680c8', 1, 2);
INSERT INTO public.review (id, create_date, update_date, comment, company_id, customer_id, rate) VALUES (32, '2024-03-16 17:14:28.170589', '2024-03-16 17:14:28.170589', 'guzel', '24c18239-59a4-45a5-a554-dc1c36a680c8', 2, 3);
INSERT INTO public.review (id, create_date, update_date, comment, company_id, customer_id, rate) VALUES (33, '2024-03-16 17:14:37.122902', '2024-03-16 17:14:37.122902', '', '24c18239-59a4-45a5-a554-dc1c36a680c8', 4, 2);
INSERT INTO public.review (id, create_date, update_date, comment, company_id, customer_id, rate) VALUES (34, '2024-03-16 17:14:46.221791', '2024-03-16 17:14:46.221791', '', '24c18239-59a4-45a5-a554-dc1c36a680c8', 3, 3);
