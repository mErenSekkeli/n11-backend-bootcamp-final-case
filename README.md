# N11 Bootcamp Final Case Project

## Project Description
This project is a comprehensive web application designed to manage customer and company data efficiently. The application provides a user-friendly interface for adding, updating, and deleting customer and company records. It leverages modern web technologies to ensure a seamless and interactive user experience.

## Used Technologies
* Backend
  * Spring Boot
  * Spring Data JPA
  * Spring Web
  * Hibernate
  * Lombok
  * OpenAPI
  * OpenFeign
  * MapStruct
  * Swagger
  * JUnit
  * Kafka
  * Zookeeper
* Database
  * PostgreSQL
  * Apache Solr
* Frontend
    * React
* Build Tools & Deployment
    * Docker
    * Maven
    * Git

## Project Structure
This project is structured to represent a comprehensive system for managing customer and company (restaurant) interactions, alongside implementing review and recommendation features. Here’s an overview of the project structure and the key components that have been developed:

### Entities
* **Customer**: Represents the customers of the application. Customers can interact with restaurants by providing reviews.
* **Review**: Captures the reviews made by customers for different restaurants. It includes ratings and comments to express customer satisfaction.
* **Company (Restaurant)**: Represents the restaurants within the application. Each restaurant can be reviewed by customers and recommended based on the given criteria.

### Core Functionalities
* **Review and Recommendation System**: Customers review restaurants, and based on these reviews, along with the geographical distance, the application recommends up to three restaurants. The recommendation algorithm uses a weighted average where 70% is given to the rating and 30% to the proximity to the customer.
* **Logging with Kafka**: All operations and errors within the application are logged using Kafka. This provides a robust mechanism for tracking user actions and system errors, aiding in debugging and monitoring the application's health.
* **Clean Code Architecture**: The entire codebase adheres to the principles of clean code and SOLID principles. This ensures that the code is well-organized, maintainable, and scalable.
* **Dockerization**: Each service in the project like kafka, solr or all services, including the backend services has been containerized using Docker. This facilitates easy deployment, consistent testing environments, and scalability.


## How to Run the Project
Running this project involves setting up the backend and frontend services, as well as the supporting infrastructure like databases and message brokers. The project is dockerized, so you can easily run it using Docker and Docker Compose. Here are the steps to get the project up and running:

### Prerequisites
* Docker: Install Docker from the official website [here](https://www.docker.com/get-started).

### Steps
* Clone the project repository to your local machine using the following command:

```bash
git clone https://github.com/mErenSekkeli/n11-backend-bootcamp-final-case.git
````
* Docker Compose:
  Navigate to the root directory of the project where the docker-compose.yml file is located.
* Run the following command to start the project:

```bash
docker-compose up -d
```
## Very Important Notes

* **The project uses the following ports**:
  * 8080: CustomerReviewService
  * 8081: CompanyService
  * 8082: LogService
  * 3000: Frontend
  * 5433: PostgreSQL
  * 8983: Apache Solr
  * 9092: Kafka
  * 2181: Zookeeper
* **Docker Notes**:
   * This project built on windows. So I have to change main base url **localhost** to **host.docker.internal**. If you are using a different operating system, you may need to change the base url in the services.
     * You can use **localhost** for swagger-ui or frontend project.
    
* **Database Notes**:
   * You can use Solr db for CompanyService in the [solrdata](https://github.com/mErenSekkeli/n11-backend-bootcamp-final-case/tree/main/solrdata). Also you can use Postgresql data's in the (init.sql)[https://github.com/mErenSekkeli/n11-backend-bootcamp-final-case/blob/main/init.sql]. Thus you can test all services with ready data. When you run docker-compose, it will create all data's automatically.
* **Swagger UI**:
  * You can access the Swagger UI for the backend services using the following URLs:
    * **CustomerReviewService**: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
    * **CompanyService**: [http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)
* **Kafka Topics**:
  * For logging changes and errors, Kafka are used. Configurations has been made in the **LogService**.
