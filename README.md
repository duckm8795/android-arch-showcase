# Android Architecture Showcase

## Overview

This project demonstrates modern Android app development best practices using Kotlin, Jetpack Compose, and a modular architecture. It includes examples of implementing Clean Architecture, dependency injection with Hilt, Jetpack Paging, and more.

## Features

- **Modular Architecture**: Divided into core and feature-specific modules.
- **Jetpack Compose**: Modern UI development with Compose.
- **Paging 3**: Efficient data pagination for large datasets.
- **Hilt**: Dependency injection for cleaner and testable code.
- **Coroutines and Flow**: Asynchronous programming for reactive and smooth user experiences.
- **Room Database**: Local data persistence with Room.
- **Remote Mediator**: Seamless integration of local and remote data sources.
- **Navigation**: Single Activity architecture with Jetpack Navigation.
- **Testing**: Unit and integration tests with MockK and JUnit.

## Project Structure

### Core Modules

- **core/common**: Shared utilities and base classes.
- **core/network**: Network-related functionality (e.g., API services).
- **core/testing**: Utilities and setup for testing.

### Feature Modules

- **feature/user/list**: Displays a paginated list of users.
- **feature/user/detail**: Shows detailed information about a user.
- **feature/user/domain**: Business logic and use cases for user-related features.
- **feature/user/data**: Data handling and repository implementations for user features.

### App Module

The app module serves as the entry point and orchestrates the features and core modules.

## Technologies Used

- **Kotlin**: Modern programming language for Android.
- **Jetpack Compose**: Declarative UI development.
- **Hilt**: Dependency injection framework.
- **Room**: Local data storage.
- **Retrofit**: Networking library for API calls.
- **Jetpack Paging 3**: Efficient data pagination.
- **JUnit, MockK**: Testing frameworks.

## Setup Instructions

1. Clone the repository:
   ```bash
   git clone https://github.com/your-repo/android-arch-showcase.git
   cd android-arch-showcase
   ```

2. Open the project in Android Studio (Arctic Fox or later).

3. Sync the Gradle project to install dependencies.

4. Run the app on an emulator or physical device.

## Key Components

### Dependency Injection

The project uses **Hilt** for dependency injection. Modules are defined in the `di` packages of each module.

### Navigation

Jetpack Navigation Component manages navigation across screens. Type-safe routes are implemented using `@Serializable` data classes.

### Paging

Jetpack Paging 3 is used to load user data efficiently with support for a Remote Mediator to sync local and remote sources.

### Testing

- **Unit Tests**: Core and feature modules include tests for business logic and data handling.
- **UI Tests**: Compose UI tests ensure proper rendering and interactions.

## License

[MIT License](LICENSE)

