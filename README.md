| CS-665    | Software Design & Patterns |
| --------- | -------------------------- |
| Name      | Dingnan Hsu                |
| Date      | 11/15/2023                 |
| Course    | 2023 Fall                  |
| Project # | Final Project              |

# Project Overview

- This advanced travel planning system is specifically tailored for airplane owners, and lovers, offering a comprehensive suite of tools for efficient and environmentally friendly journey planning. It enables users to determine potential destinations based on their current location and airplane type, factoring in the airplane's fuel range to explore options without refueling.
- The system analyzes flight details, including duration and reachability, by considering aircraft-specific parameters like range, fuel capacity, and consumption rates. Additionally, it assesses environmental impact by estimating fuel consumption and CO2 emissions, taking into account the airplane's efficiency, travel distance, and prevailing weather conditions. Cost estimation is another key feature, providing users with anticipated fuel costs to aid in budgeting.
- Comprehensive reporting gives users a detailed overview of each destination, including essential aspects like flight duration, fuel usage, CO2 emissions, and estimated costs, facilitating informed and sustainable travel decisions.

# Solved What Problem?

1. `Travel Planning Efficiency`: Streamlines the process of determining feasible destinations based on specific airplane capabilities and current location.

2. `Environmental Impact Awareness`: Helps users understand and minimize the ecological footprint of their air travels by providing detailed emissions data.

3. `Cost Management`: Offers precise fuel cost estimates, allowing for better financial planning and budgeting for trips.

By providing comprehensive reports on various travel aspects, it empowers users to make well-informed decisions that align with their preferences. This project, therefore, serves not only as a travel planning tool but also as a platform for promoting responsible and sustainable aviation practices among private airplane owners.

# Tasks - Flexibility

- `Adding or Removing Object Types`: By leveraging the `AbstractAirplane` class and the `Airplane` interface, adding or removing airplane types is made efficient. To introduce a new airplane type, simply extend AbstractAirplane and tailor specific behaviors as needed. Removing a type involves just deactivating or deleting its class.

- `Database and Data Loading`: The database integration and the `FileLoader` class enhance the system's adaptability. Modifying data sources, whether it involves switching databases or altering file formats, can be efficiently managed within these components, ensuring the rest of the system remains unaffected by such changes.

# Tasks - The Simplicity And Understandability

- `Code Organization and Naming Conventions`: Organized into distinct packages and classes, each with a clear responsibility. The naming conventions for classes, methods, and variables are intuitive, making it easy to understand what each part of the code is intended to do.

- `Comments and Documentation`: Detailed comments and method descriptions enhances the readability and maintainability of the code.

# Tasks - Avoided Duplicated Code

- `Code Reusability`: Through the use of inheritance (`AbstractAirplane class`) and interfaces (`Airplane`). This approach promotes code reusability and makes maintenance easier. For instance, common properties and methods of airplanes are defined in the `AbstractAirplane` class, from which specific airplane types inherit.

# Tasks - Design Patterns Overview

- `Builder Pattern`: The `FlightDataBuilder` class demonstrates the `Builder pattern`, streamlining complex `FlightData` object construction. It handles `flight duration, fuel consumption, CO2 emissions, and cost calculations` in a structured way. By using `ExecutorService` for concurrent calculations, it enhances performance and scalability. This pattern shows how to create complex objects with interdependent attributes in a modular and maintainable manner, effectively managing flight plan components like destinations, durations, and fuel usage.

- `Repository Pattern`: Ideal for managing database interactions. This pattern provides a separation between the data access logic and the business logic of your application, making it easier to manage data operations and changes.
- Separate Database Connection Handling: The connection handling should be isolated from the repository logic. The Database class should not know how to create tables or how to insert specific data.

Create Specific Repositories: For each entity (e.g., Location, Airplane, Weather), create a repository class that handles all database operations related to that entity.

Use Data Access Objects (DAOs): These objects abstract and encapsulate all access to the data source. The DAO manages the connection with the data source to obtain and store data.

Each repository class (e.g., LocationRepository, AirplaneRepository, WeatherRepository) encapsulates the logic for database operations related to a specific entity. This provides a clear separation of concerns where your business logic can interact with data through these abstractions without depending on the details of the data access implementation.

- `Lazy Loading Pattern`: This pattern can be beneficial for your application in managing resource usage efficiently, especially when dealing with large sets of flight data that might not all need to be loaded into memory at once.
- By applying lazy loading, you're optimizing the resource usage of your application, ensuring that the database connection is only established when necessary and not before. This can lead to reduced memory footprint and potentially improved performance, especially if the database operations are not the central focus of your application.
- Process and transfer data from file-based storage to a database

# GitHub Repository Link:

https://github.com/dddingnan/cs-665-project

## UML Diagram

![UML Diagram](UML.png)

# Maven Commands

We'll use Apache Maven to compile and run this project. You'll need to install Apache Maven (https://maven.apache.org/) on your system.

Apache Maven is a build automation tool and a project management tool for Java-based projects. Maven provides a standardized way to build, package, and deploy Java applications.

## Compile

Type on the command line:

```bash
mvn clean compile
```

## Run

Type on the command line:

```bash
mvn exec:java
```

## JUnit Tests

JUnit is a popular testing framework for Java. JUnit tests are automated tests that are written to verify that the behavior of a piece of code is as expected.

In JUnit, tests are written as methods within a test class. Each test method tests a specific aspect of the code and is annotated with the @Test annotation. JUnit provides a range of assertions that can be used to verify the behavior of the code being tested.

To run, use the following command:

```bash
mvn clean test
```

## Spotbugs

SpotBugs is a static code analysis tool for Java that detects potential bugs in your code. It is an open-source tool that can be used as a standalone application or integrated into development tools such as Eclipse, IntelliJ, and Gradle.

SpotBugs performs an analysis of the bytecode generated from your Java source code and reports on any potential problems or issues that it finds. This includes things like null pointer exceptions, resource leaks, misused collections, and other common bugs.

Use the following command:

```bash
mvn spotbugs:gui
```

For more info see
https://spotbugs.readthedocs.io/en/latest/maven.html

SpotBugs https://spotbugs.github.io/ is the spiritual successor of FindBugs.

## Checkstyle

Checkstyle is a development tool for checking Java source code against a set of coding standards. It is an open-source tool that can be integrated into various integrated development environments (IDEs), such as Eclipse and IntelliJ, as well as build tools like Maven and Gradle.

Checkstyle performs static code analysis, which means it examines the source code without executing it, and reports on any issues or violations of the coding standards defined in its configuration. This includes issues like code style, code indentation, naming conventions, code structure, and many others.

The following command will generate a report in HTML format that you can open in a web browser.

```bash
mvn checkstyle:checkstyle
```

The HTML page will be found at the following location:
`target/site/checkstyle.html`
