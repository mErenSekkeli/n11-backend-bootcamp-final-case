DO
$do$
BEGIN
   IF NOT EXISTS (
      SELECT FROM pg_catalog.pg_roles 
      WHERE  rolname = 'erensekkeli') THEN

      CREATE ROLE erensekkeli LOGIN PASSWORD '123456789';
   END IF;
END
$do$;

CREATE DATABASE "n11-company-service";

CREATE DATABASE "n11-customer-service";

GRANT ALL PRIVILEGES ON DATABASE "n11-company-service" TO erensekkeli;
GRANT ALL PRIVILEGES ON DATABASE "n11-customer-service" TO erensekkeli;
