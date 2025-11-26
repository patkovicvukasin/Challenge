# Student Canteen Reservation System

This application is implemented as part of the **5 Dana u Oblacima 2025 Challenge**.

---

## Technologies Used

| Technology         | Version |
| ------------------ | ------- |
| Java               | 17      |
| Spring Boot        | 3.3.4   |
| Spring Web         | 3.3.4   |
| Spring Data JPA    | 3.3.4   |
| Jakarta Validation | 3.0+    |
| H2 Database        | 2.2.x   |
| Maven              | 3.8+    |

All technologies used are open source.

---

## Environment Setup

Before building or running the application, install:

### Required Software

* **Java JDK 17**
* **Maven 3.8+**
* Optional: **IntelliJ IDEA** (recommended)

### Verify Installation

```bash
java -version
mvn -version
```

### Clone Repository

```bash
git clone <repository-url>
cd <project-folder>
```

---

## Build Instructions

To build the project:

```bash
mvn clean install
```

This will:

* download all dependencies
* compile the project
* generate the JAR file under `/target`

---

## Running the Application

The application can be started in any of the following ways:

### 1. Using Maven

```bash
mvn spring-boot:run
```

### 2. Running the JAR file

```bash
java -jar target/Challenge-0.0.1-SNAPSHOT.jar
```

### 3. From IntelliJ IDEA

Run the main class:

```
com.challenge.ChallengeApplication
```

### Application URL

```
http://localhost:8080
```

---

## H2 In-Memory Database

The application uses an in-memory H2 database that resets on each restart.

### H2 Console URL

```
http://localhost:8080/h2-console
```

### Default credentials

| Key      | Value              |
| -------- | ------------------ |
| JDBC URL | jdbc:h2:mem:testdb |
| Username | sa                 |
| Password | (empty)            |

---

## Running Unit Tests

*(None are included in this project, but Maven supports running them with:)*

```bash
mvn test
```
