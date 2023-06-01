[![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.1.1-brightgreen)](https://www.thymeleaf.org/)
[![Java](https://img.shields.io/badge/Java-17-blue)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.1-brightgreen)](https://spring.io/projects/spring-boot)
[![HTML](https://img.shields.io/badge/HTML-5-orange)](https://www.w3.org/TR/html52/)
[![CSS](https://img.shields.io/badge/CSS-3-blue)](https://www.w3.org/Style/CSS/)
[![JavaScript](https://img.shields.io/badge/JavaScript-ES6-yellow)](https://developer.mozilla.org/en-US/docs/Web/JavaScript)

# Black Jack

A Black Jack application built using Spring Boot and Thymeleaf. It provides the basic game play for Black Jack as the player competes with the dealer.


## Features
- Interactive user interface and seamless gameplay.
- Real-time game updates.
- Internal scoring system.
- Persistence of game state using H2 and Spring Data JPA.

### Getting Started
- Java 17 or higher
- Maven

### Installation
1. Clone the repository
2. Build the project using Maven: 'mvn clean install'
3. Run the application: 'java -jar target/BlackJack-1.0.0.jar

## Usage
1. Launch the application.
2. Navigate to the homepage at 'http://localhost:8080'.
3. Initially the dealer and the player will have one card.
4. The player will have a choice to either 'hit' or 'stand'
5. If the player hits 21, has an equal score as the dealer, or has a greater score less than 21. The player wins, otherwise the game will continue or the dealer wins.

## Screenshots

![Screenshot 1](/src/main/resources/static/assets/screenshot1.PNG)
*Start of game with actions to hit and stand*

![Screenshot 2](/src/main/resources/static/assets/screenshot2.PNG)
*Picture of dealer beating player*

![Screenshot 3](/src/main/resources/static/assets/screenshot3.PNG)
*Picture of player winning by dealer going bust (card values over 21)*
