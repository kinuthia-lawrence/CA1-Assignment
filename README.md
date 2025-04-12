# Confectionery Oven Management System

## Project Overview
This Java application models a confectionery business that manages a rectangular oven for baking five different types of cakes: Pineapple, Strawberry, Chocolate, Vanilla, and Plain. The oven has two doors (front and back) with different operational modes.

## Features
- Add cakes to the oven with name, weight, and best-before date
- Remove cakes from the oven based on selected data structure mode
- Switch between LIFO (Stack) and FIFO (Queue) modes
- Track capacity (maximum 5 cakes)
- Monitor currently baking cakes
- Display the cake at the top/front of the oven

## System Architecture

### Data Structure Choice
The application implements both Stack (LIFO) and Queue (FIFO) data structures using a `Deque` in Java:
- **Stack Mode**: When the baker uses only the front door (Last In, First Out)
- **Queue Mode**: When the baker adds from the front door and removes from the opposite door (First In, First Out)

This approach provides flexibility while maintaining the core business requirements.

## Technical Implementation

### Model Classes
- `Cake.java` - Entity class for cake objects
- `OvenMode.java` - Enum defining the operational modes
- `IOvenOperations.java` - Interface defining oven operations
- `OvenManager.java` - Implementation of the oven logic with data structure

### Utility Classes
- `InputValidator.java` - Validates user inputs
- `DateFormatter.java` - Formats dates for display

### UI Components
- `Main.java` - Application entry point
- `MainController.java` - Controller for the UI
- `main-view.fxml` - JavaFX interface layout
- `styles.css` - Style definitions for the UI

## Setup Instructions

1. Ensure Java 21 and Maven are installed on your system
2. Clone the repository
3. Navigate to the project directory
4. Run the application:
   ```
   mvn clean javafx:run
   ```

## Usage Instructions

1. **Select Oven Mode**: Choose between Stack (LIFO) or Queue (FIFO)
2. **Add Cake**:
    - Select cake type from dropdown
    - Enter weight in grams
    - Select best-before date (within 2 weeks)
    - Click "Add Cake"
3. **View Cakes**: The list shows all cakes currently in the oven
4. **Remove Cake**: Click "Remove Cake" to remove according to the selected mode
5. **Exit**: Click "Exit" to close the application

## Testing Guidelines

When testing the application:
1. Verify that at most 5 cakes can be added
2. Test input validation (invalid weights, best-before dates)
3. Confirm correct operation in both Stack and Queue modes
4. Check error handling when trying to remove from an empty oven
5. Verify UI responsiveness and information display

## Assignment Requirements
- Implementation of suitable data structure (10%)
- OOP principles (Classes, Interfaces, Inheritance) (40%)
- Application testing and validation (10%)
- Poster presentation with video recording (40%)