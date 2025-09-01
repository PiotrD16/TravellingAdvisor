# TravellingAdvisor

Travelling Advisor is an app written in Java using the Spring Boot framework that enables CRUD operations for a tourist place. The web application allows reading information about different cities around the world along with the country and city they are located in.

## App Features
- Retrieve all places
- Add a new place (by country and city name)
- Update a place
- Delete a place

## Tech stack
- Spring Boot 3, Java 17+
- Spring Data JPA (with PostgreSQL database)
- Simple frontend HTML + JavaScript in Thymeleaf + static JS/CSS files

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/PiotrD16/TravellingAdvisor.git
   cd TravellingAdvisor
   
2. Configure the database in the file src/main/resources/application.properties:
   ```properties
   spring.datasource.username=your_username
   spring.datasource.password=your_password
3. Import the database *into pgAdmin 4*, the .sql file is located in the directory: *database.TravellingDB.sql*
4. Run the app:
   `mvn spring-boot:run`
   You can also run it using IntelliJ Idea: clone the repo, connect to a database and run the app.
   The app should be available at `http://localhost:8080/`. Thanks to the user interface, the app’s functionalities can be easily tested.

## Selected REST API endpoints:
- 'http://localhost:8080/api/place/v1' - shows all places,
- 'http://localhost:8080/api/place/v1/savePlace' - adds a new place to the database,
- 'http://localhost:8080/api/place/v1/updatePlace' - updates existing place or creates a new one if it doesn't exist,
- 'http://localhost:8080/api/place/v1/byId/{id}' - finds a place by its ID,
- 'http://localhost:8080/api/place/v1/byName/{name}' - finds a place by its name,
- 'http://localhost:8080/api/place/v1/{id}/deletePlace' - removes a place by its ID.
