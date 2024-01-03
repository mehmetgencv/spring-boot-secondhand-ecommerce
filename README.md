# Spring Boot Secondhand E-Commerce

This Spring Boot project is designed for a secondhand e-commerce platform and includes two main models: "User" and "Advertisement."

## User Service

The `UserService` class provides functionalities related to user management. It includes methods to retrieve all users, get a user by email, create a new user, update user information, deactivate a user, activate a user, and delete a user. Additionally, the service checks if a user is active before performing certain operations.

### Methods:

- `getAllUsers()`: Retrieves a list of all users.

- `getUserByMail(String mail)`: Retrieves a user by email.

- `createUser(CreateUserRequest userRequest)`: Creates a new user.

- `updateUser(String mail, UpdateUserRequest updateUserRequest)`: Updates user information.

- `deactivateUser(Long id)`: Deactivates a user.

- `activateUser(Long id)`: Activates a user.

- `deleteUser(Long id)`: Deletes a user.

### Utility Methods:

- `changeActivateUser(Long id, Boolean isActive)`: Changes the activation status of a user.

- `findUserByMail(String mail)`: Finds a user by email.

- `findUserById(Long id)`: Finds a user by ID.

- `isUserIdExist(Long id)`: Checks if a user ID exists.

## Advertisement Service

The `AdvertisementService` class manages advertisements and includes a method to create a new advertisement.

### Methods:

- `createAdvertisement(CreateAdvertisementRequest request)`: Creates a new advertisement.

## Dependencies:

- Spring Boot
- Spring Data JPA
- Spring Web
- SLF4J (Simple Logging Facade for Java)
- RestTemplate

## How to Use:

1. Clone the repository.
2. Configure your database settings in `application.properties`.
3. Run the application.
4. Access the provided endpoints to interact with user and advertisement functionalities.

## Example Usage:

```java
// User Service Example
UserService userService = new UserService(userRepository, userDtoConverter);

// Retrieve all users
List<UserDto> allUsers = userService.getAllUsers();

// Create a new user
CreateUserRequest newUserRequest = new CreateUserRequest("newuser@example.com", "John", null, "Doe");
UserDto newUserDto = userService.createUser(newUserRequest);

// Update user information
UpdateUserRequest updateUserRequest = new UpdateUserRequest("Jane", null, "Doe");
UserDto updatedUserDto = userService.updateUser("newuser@example.com", updateUserRequest);

// Advertisement Service Example
AdvertisementService advertisementService = new AdvertisementService(advertisementRepository, restTemplate);

// Create a new advertisement
CreateAdvertisementRequest newAdvertisementRequest = new CreateAdvertisementRequest("Title", "Description", 100.0, 1L, "Fashion");
AdvertisementDto newAdvertisementDto = advertisementService.createAdvertisement(newAdvertisementRequest);
