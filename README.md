# Finance Service

Backend API for managing the cash flow of small businesses, allowing control of income, expenses, accounts, categories, and transfers, with a focus on data consistency and a clear view of financial movements.

## Objective

The goal of this project is to practice and deepen backend development skills using Java and Spring Boot, applying concepts such as:

- REST APIs
- Business rules
- Object-Oriented Programming
- Persistence with JPA/Hibernate
- SQL and relational databases
- Automated testing
- API documentation

## Scope (v1)

The first version of the system includes:

- Financial account management
- Transaction category management
- Recording income and expenses
- Transfers between accounts
- Statement queries by period
- Current balance calculation per account
- Transaction listing with simple filters

## Out of Scope

In this first version, the system does not include:

- Complex authentication
- Integration with real banks
- Investments
- Advanced installment handling
- Frontend

## Main Entities

- Account
- Category
- Transaction
- Transfer
- Budget

## Technologies Used

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven

## Tools and Support

- Flyway
- Bean Validation
- Swagger / OpenAPI
- JUnit
- Lombok

## Documentation

Project documentation is organized in the `docs/` folder:

- `project-brief.md` — overview, scope, and objective
- `domain.md` — domain, entities, and business rules
- `use-cases.md` — main use cases
- `api-contract.md` — initial endpoint definitions

## Project Status

In development.

## Notes

This project was created with a focus on learning, practice, and preparation for junior Java backend opportunities, prioritizing domain clarity, business rule consistency, and clean code organization.