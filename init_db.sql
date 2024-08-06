CREATE USER admin WITH PASSWORD 'provodromo_pass';

CREATE DATABASE db_provodromo OWNER admin;

GRANT ALL PRIVILEGES ON DATABASE db_provodromo TO admin;
