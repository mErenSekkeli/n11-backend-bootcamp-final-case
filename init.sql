CREATE USER erensekkeli WITH ENCRYPTED PASSWORD '123456789';
CREATE DATABASE "n11-company-service";
CREATE DATABASE "n11-customer-service";
GRANT ALL PRIVILEGES ON DATABASE "n11-company-service" TO erensekkeli;
GRANT ALL PRIVILEGES ON DATABASE "n11-customer-service" TO erensekkeli;