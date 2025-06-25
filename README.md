# JUnit 5 Workshop

A comprehensive multi-project workshop demonstrating JUnit 5 features, testing best practices, and modern Java development techniques.

## Project Structure

This is a multi-project Gradle build with three main components:

### Main Project (`/`)
- **Focus**: JUnit 5 features and modern testing patterns
- **Testing Framework**: JUnit 5.13.2 (latest)
- **Key Features**: Parameterized tests, dynamic tests, nested tests, conditional tests, custom extensions
- **Additional Libraries**: AssertJ, Mockito, jqwik (property-based testing)

### Spring Subproject (`/spring`)
- **Focus**: Spring Boot integration with JUnit 5
- **Framework**: Spring Boot 3.5.3 with Spring Test
- **Database**: H2 in-memory database with JPA
- **Testing**: Repository testing, Spring context testing, database assertions

### Vintage Subproject (`/vintage`) 
- **Focus**: JUnit 4 legacy support demonstration
- **Purpose**: Training on migration patterns and backward compatibility
- **Testing Framework**: JUnit 4 via JUnit Vintage Engine

## Requirements

- **Java**: 17+ (uses Gradle toolchain)
- **Gradle**: 8.14.2 (wrapper included)
- **IDE**: Any Java IDE with Gradle support

## Getting Started

### Running All Tests
```bash
./gradlew test
```

### Running Specific Project Tests
```bash
# Main project (JUnit 5 examples)
./gradlew test

# Spring integration tests
./gradlew :spring:test

# JUnit 4 legacy tests
./gradlew :vintage:test
```

### Building the Project
```bash
./gradlew build
```

## Key Testing Features Demonstrated

### JUnit 5 Core Features
- **Basic Testing**: Assertions, assumptions, lifecycle methods
- **Parameterized Tests**: CSV sources, method sources, custom argument providers
- **Dynamic Tests**: Runtime test generation
- **Nested Tests**: Hierarchical test organization
- **Conditional Execution**: OS, JRE, environment-based test execution
- **Custom Extensions**: Lifecycle callbacks, parameter injection

### Advanced Testing Libraries
- **AssertJ**: Fluent assertions for better readability
- **Mockito**: Mock object creation and verification
- **jqwik**: Property-based testing for robust test coverage

### Spring Testing
- **Repository Testing**: JPA repository testing with test slices
- **Database Testing**: H2 in-memory database with test data
- **Spring Boot Test**: Full application context testing

## Project Dependencies

### Core Testing Stack
- JUnit 5.13.2 (with BOM management)
- AssertJ 3.27.3
- Mockito 5.15.2
- jqwik 1.9.3

### Spring Stack
- Spring Boot 3.5.3
- Spring Data JPA
- H2 Database

### Build Tools
- Gradle 8.14.2 with version catalogs
- Spotless code formatting
- GitHub Actions CI/CD

## Workshop Topics Covered

1. **JUnit 5 Fundamentals**
   - Migration from JUnit 4
   - New assertion patterns
   - Lifecycle management

2. **Advanced Test Patterns**
   - Parameterized testing strategies
   - Dynamic test generation
   - Nested test organization

3. **Testing Best Practices**
   - Test naming conventions
   - Assertion libraries usage
   - Mock object patterns

4. **Integration Testing**
   - Spring Boot test slices
   - Database testing strategies
   - Test configuration management

5. **Legacy Support**
   - JUnit 4 compatibility
   - Migration strategies
   - Vintage engine usage

## Development

### Code Formatting
```bash
./gradlew spotlessApply
```

### Dependency Updates
```bash
./gradlew dependencyUpdates
```

This project serves as a comprehensive reference for modern Java testing practices using JUnit 5 and related frameworks.
