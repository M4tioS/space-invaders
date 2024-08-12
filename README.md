# Space Invaders Game

## Overview

This Space Invaders project is a practice exercise to explore Java programming and JavaFX for creating graphical user interfaces. The game is a simple clone of the classic "Space Invaders," where players control a spaceship and defend against waves of incoming ~~enemies~~ meteors. The project focuses on learning and experimenting with code, rather than producing a fully polished or commercial product.

## Features

- **Classic Gameplay**: Control a spaceship and shoot down meteorss.
- **Keyboard Controls**: Use the keyboard to maneuver the spaceship and fire projectiles at enemies.
- **Simple Graphics**: The game features straightforward graphics, including basic shapes and freely usable images.
- **Graphical User Interface**: The game is built using JavaFX.

## Purpose

This project was developed primarily as a practice exercise. The goal was to gain hands-on experience with Java and JavaFX, experiment with game mechanics, and understand the basics of game development. It serves as a stepping stone for more complex projects.

## Project Structure

- **src/main/java/vidmot**: Contains the controller classes responsible for managing the game's GUI and user interactions.
  - `SpaceInvadersController.java`: Manages the main game logic, including player controls, enemy movement, and collision detection.

- **src/main/java/vinnsla**: Contains the core logic and data handling for the game.
  - `Game.java`: Implements the main game mechanics, including the game's update loop, scoring, and handling game states.
  - `Data.java`: Manages game-related data, such as player scores and game configurations.

- **src/main/resources**: Includes FXML files for defining the layout of the GUI and stylesheets for customizing the appearance.
  - **vidmot**: Contains FXML files for different game views like `menu-view.fxml`, `gameover-view.fxml`, and `spaceinvaders-view.fxml`.
  - **stylesheets**: Contains CSS files and image assets used to style the game and its components, such as `background.gif`, `banner.png`, and `testship.png`.

## Getting Started

### Prerequisites

- **Java Development Kit (JDK)**: Ensure you have JDK 8 or higher installed on your system.
- **JavaFX**: This project uses JavaFX for the GUI, so make sure you have JavaFX set up.

### Running the Game

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/Pebble32/space-invaders.git
   ```
2. 
