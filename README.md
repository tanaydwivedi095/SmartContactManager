# Smart Contact Manager

## Overview

Smart Contact Manager is a web application built using Spring Boot and Java. It provides a platform to manage contacts, users, and associated services such as email notifications and image handling. The application features user registration, login, contact management, and email functionality.

## Project Structure

### Entities

- **`Contact`**: Represents a contact with attributes like ID, name, email, phone number, address, picture, description, and social links.
- **`SocialLinks`**: Represents social links associated with a contact.
- **`User`**: Represents a user with attributes such as ID, name, email, password, and profile details.
- **`Providers`**: Enum for authentication providers (SELF, GOOGLE, GITHUB).

### Forms

- **`ContactForm`**: Form for creating or updating contacts, including fields for name, email, phone number, address, description, and contact image.
- **`HelpForm`**: Form for submitting help requests, including name, subject, and message.
- **`UserForm`**: Form for user registration, including name, email, phone number, password, and about.

### Controllers

- **`HelpMailController`**: Handles sending help request emails.
- **`PageController`**: Manages page routing for home, about, service, login, signup, and contact pages.
- **`RootController`**: Adds the logged-in user to the model for access across the application.
- **`UserController`**: Manages user-specific pages, like the user profile.
- **`ApiController`**: Manages user-specific contact, like fetching a contact based on contactId.
- **`AuthController`**: Manages user authorization with the help of email token sent to user's email.
- **`ContactController`**: Manages contacts for the logged in user, like adding, updating, viewing and deleting a contact.

### Repositories

- **`ContactRepository`**: Provides CRUD operations for `Contact` entities.
- **`UserRepository`**: Provides CRUD operations for `User` entities.

### Services

- **`ContactService`**: Interface for contact-related operations, including save, update, delete, and retrieval of contacts.
- **`EmailService`**: Interface for sending emails, including plain text, HTML, and attachments.
- **`ImageService`**: Interface for image handling, including uploading images and generating URLs from public IDs.
- **`UserService`**: Interface for user-related operations, including registration, update, deletion, and user existence checks.
- **`SecurityCustomUserDetailService`**: Custom implementation for user details service in Spring Security.

### Implementations

- **`ContactServiceImplementation`**: Implements `ContactService` with operations for saving, updating, deleting, and retrieving contacts.
- **`EmailServiceImplementation`**: Implements `EmailService` for sending plain text emails.
- **`ImageServiceImplementation`**: Implements `ImageService` using Cloudinary for image uploading and URL generation.
- **`UserServiceImplementation`**: Implements `UserService` for user management, including registration, updates, and retrievals.
- **`SecurityCustomUserDetailService`**: Implements `UserDetailsService` for custom user details loading.

### Helpers

- **`AppConstants`**: Contains application-wide constants, such as image dimensions and role names.
- **`EmailHelper`**: Provides methods for generating email verification links.
- **`Message`**: Represents messages with content and type.
- **`MessageType`**: Enum for different message types (blue, green, red, yellow).
- **`SessionHelper`**: Utility for managing session attributes.

### Thymeleaf Templates

- **`base.html`**: Base layout template providing the common HTML structure for the application. Includes placeholders for title and content.
- **`contact.html`**: Contact page template with a form for submitting help requests.
- **`home.html`**: Home page template showcasing the application’s features and providing navigation links.
- **`login.html`**: Login page template with a form for user authentication.
- **`signup.html`**: User registration page template with a form for creating a new user account.
- **`service.html`**: Service page template describing the features and services offered by the application.
- **`message.html`**: Template for displaying messages with different types (info, success, error).
- **`navbar.html`**: Navbar template providing navigation links and buttons for the application.

## Setup and Configuration

1. **Clone the Repository**: 

   ```bash
   git clone https://github.com/tanaydwivedi095/SmartContactManager.git
   cd SmartContactManager
   ```

2. **Install Dependencies**: 

   Make sure you have Maven or Gradle installed, then run:

   ```bash
   mvn install
   ```

3. **Configure Application Properties**: 

   Update `src/main/resources/application.properties` with your database and email configurations.

4. **Run the Application**: 

   ```bash
   mvn spring-boot:run
   ```

5. **Access the Application**: 

   Open your web browser and navigate to `http://localhost:8081`.

## Usage

- **User Registration**: Navigate to `/signup` to create a new user account.
- **Login**: Access the login page at `/login`.
- **Contact Management**: After logging in, manage your contacts through the application interface.
- **Help Requests**: Use the contact form at `/contact` to send help requests.
