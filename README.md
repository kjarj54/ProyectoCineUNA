# CineUNA - Cinema Billing Application

CineUNA is a Java-based cinema billing application that allows customers to easily purchase movie tickets, select seats, and order food and beverages. The application is designed for scalability and flexibility, supporting various sales methods and technologies such as mobile or web.

## Table of Contents
- [Introduction](#introduction)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Installation](#installation)
- [Configuration](#configuration)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Introduction
The CineUNA project is aimed at providing cinema owners with a robust tool for managing ticket sales, movie information, and theater configurations. The application includes modules for both cinema administrators and customers, offering features such as user registration, movie maintenance, theater management, and ticket reservations.

## Technologies Used
- Java
- JavaFX
- CSS
- JPA (Java Persistence API)
- Web Services (SOAP or REST)
- Oracle 21c XE Database
- Payara Server

## Project Structure
The project follows a modular structure with distinct modules for administrators and customers. The codebase is organized according to Java best practices and design patterns, ensuring maintainability and scalability.

└── 📁CineUNA
    └── 📁config
        └── properties.ini
    └── nbactions.xml
    └── pom.xml
    └── 📁src
        └── 📁main
            └── 📁java
                └── 📁cr
                    └── 📁ac
                        └── 📁una
                            └── 📁cineuna                            
                                └── 📁controller                                    
                                └── 📁model                                    
                                └── 📁service                                    
                                └── 📁util
                └── module-info.java
            └── 📁resources
                └── 📁cr
                    └── 📁ac
                        └── 📁una
                            └── 📁cineuna
                                └── 📁resources                                    
                                    └── 📁fonts                                        
                                    └── 📁i18n
                                └── 📁view                                    
        └── 📁test
            ├── java
└── 📁WsCineUNA
    └── nb-configuration.xml
    └── pom.xml
    └── 📁src
        └── 📁main
            └── 📁java
                └── 📁cr
                    └── 📁ac
                        └── 📁una
                            └── 📁wscineuna
                                └── 📁controller                                    
                                └── 📁model                                    
                                └── 📁service                                    
                                └── 📁util                                    
            └── 📁resources
                └── 📁META-INF
                    └── persistence.xml
            └── 📁webapp
                └── index.html
                └── 📁WEB-INF
                    └── beans.xml
                    └── glassfish-resources.xml
                    └── web.xml
        └── 📁test
            ├── java
    └── 📁target


## Installation
To install and run the application locally, follow these steps:

1. Clone the repository: `git clone https://github.com/your-username/CineUNA.git`
2. Open the project in Netbeans.
3. Build and run the application.

## Configuration
Ensure the following configurations are set up before running the application:

### Database Configuration
1. Create a database schema named `CineUNA`.
2. Set up a connection pool named `CinePool` for managing database connections.
3. Define a JDBC resource named `jdbcCine` to grant the application access to the connection pool.

### Web Service Configuration
1. Develop a web service application named `WsCineUNA` with the necessary methods for the CineUNA application.
2. Ensure the web service can communicate with the Payara Server.

## Usage
1. Launch the application and select the appropriate user module (admin or client).
2. Follow the on-screen instructions for user registration, movie maintenance, theater configuration, and ticket reservations.
3. Enjoy the dynamic and intuitive interface designed for touch devices.

## Contributing
Contributions to the CineUNA project are welcome. To contribute, please follow these steps:
1. Fork the repository.
2. Create a new branch: `git checkout -b feature/new-feature`.
3. Commit your changes: `git commit -am 'Add new feature'`.
4. Push to the branch: `git push origin feature/new-feature`.
5. Submit a pull request.



