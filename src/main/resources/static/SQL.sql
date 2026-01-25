DROP SCHEMA IF EXISTS dat109oblig2iterativt CASCADE;

CREATE SCHEMA dat109oblig2iterativt;

SET search_path TO dat109oblig2iterativt;

CREATE TABLE bilutleiefirma
(
    id SERIAL PRIMARY KEY
);
CREATE TABLE kunde
(
    id SERIAL PRIMARY KEY,
    bilutleiefirma_id BIGINT REFERENCES bilutleiefirma (id),
    brukernavn VARCHAR,
    salted_password VARCHAR,
    salt VARCHAR,
    navn VARCHAR
);
CREATE TABLE lokasjon
(
    id SERIAL PRIMARY KEY,
    bilutleiefirma_id BIGINT REFERENCES bilutleiefirma (id),
    adresse VARCHAR,
    telefonnummer VARCHAR
);
CREATE TABLE admin
(
    id SERIAL PRIMARY KEY,
    bilutleiefirma_id BIGINT REFERENCES bilutleiefirma (id),
    brukernavn VARCHAR,
    salted_password VARCHAR,
    salt VARCHAR,
    navn VARCHAR
);
CREATE TABLE bil
(
    id SERIAL PRIMARY KEY,
    lokasjon_id BIGINT REFERENCES lokasjon (id)
);

CREATE TABLE medarbeider
(
    id SERIAL PRIMARY KEY,
    lokasjon_id BIGINT REFERENCES lokasjon (id),
    brukernavn VARCHAR,
    salted_password VARCHAR,
    salt VARCHAR,
    navn VARCHAR

);

CREATE TABLE utleie
(
    id SERIAL PRIMARY KEY,
    kunde_id BIGINT REFERENCES kunde (id),
    bil_id BIGINT REFERENCES bil (id)
);


