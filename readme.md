# Project Name: Event Management System

## Author

Bayram Yılmaz

## Domain Explanation

This project focuses on an event management system where various entities such as `Buildings`, `Rooms`, and `Events` are interconnected:

- **Buildings**: Represent physical structures that host events. Each building contains multiple rooms.
- **Rooms**: Specific locations within a building where events can take place. Each room has attributes like capacity and a unique number.
- **Events**: Activities scheduled to occur in specific rooms during designated timeframes. Events can span multiple rooms.
- **SessionHistory**: Tracks user activity, such as page visits and timestamps, for analytics or session tracking purposes.

## Profiles

This project supports multiple profiles for configuration:

- **Development (dev)**: For development using JPA with Entity Manager.
- **JDBC (jdbc)**: For JDBC Template usage in repositories.
- **Production (prod)**: For deployment using JPA with Entity Manager and PostgreSQL.
- **Spring Data JPA (spring-data-jpa)**: For repositories extending Spring Data JPA.

## Database Configuration

- **Development (dev)**: Uses an H2 in-memory database for rapid testing and development.

    - URL: `jdbc:h2:mem:testdb`
    - Username: `sa`
    - Password: none (empty)

- **JDBC (jdbc)**: Uses an H2 in-memory database tailored for JDBC operations.

    - URL: `jdbc:h2:mem:event`
    - Username: `sa`
    - Password: none (empty)

- **Production (prod)**: Uses PostgreSQL for deployment in a production environment.

    - URL: `jdbc:postgresql://localhost:5432/programming-3`
    - Username: `postgres`
    - Password: `root`

- **Spring Data JPA (spring-data-jpa)**: Uses PostgreSQL with Spring Data JPA repository methods.

    - URL: `jdbc:postgresql://localhost:5432/programming-3`
    - Username: `postgres`
    - Password: `root`

## Extra Information for Smooth Operation

1. Ensure the appropriate profile is active based on the environment. For development, use `spring.profiles.active=dev`. For production, use `spring.profiles.active=prod`.
2. Initialize the database schema and data using the provided SQL files (`schema-*.sql` and `data-*.sql`) located in `classpath`.
3. Access the H2 console for development at `/h2-console`.

## Start URL for the Web Application

The web application can be accessed at:

- [http://localhost:8080](http://localhost:8080)

## Project Completion Status

| Week | Topic                                                    | Status    |
| ---- | -------------------------------------------------------- | --------- |
| 1    | Gradle - Git - Streams                                   | Completed |
| 2    | Layered Architecture - Spring Intro                      | Completed |
| 3    | Application context - Logging - Spring MVC               | Completed |
| 4    | Thymeleaf                                                | Completed |
| 5    | Bootstrap                                                | Completed |
| 6    | Controller Details (Params, ViewModels, Validation, ...) | Completed |
| 7    | JDBC - JdbcTemplate - Profiles                           | Completed |
| 8    | Relationships                                            | Completed |
| 9    | ORM with JPA                                             | Completed |
| 10   | Spring Data repositories - Inheritance                   | Completed |
| 11   | Exception Handling                                       | Completed |
| 12   | JSON - Generics                                          | Completed |

## Instructions

1. Clone the repository:
   ```bash
   git clone https://gitlab.com/bayram.yilmaz/programming-3
   ```
2. Navigate to the project directory:
   ```bash
   cd event-management-system
   ```
3. Run the application:
   ```bash
   ./gradlew bootRun
   ```

