### Neo Character API

![classes.png](misc%2Fclasses.png)

Hello, player!
This is the Neo Character API. It allows you to:
* Create your own character, chosing between WARRIOR, MAGE and THIEF classes.
* See your character's information and stats.
* See a list of all characters created.
* Run an exciting battle between two of your characters.


## 🏛️ Architecture

```
📁 study.notification
├── 📁 adapters
│   └── 📁 controllers              # 🔹 Controllers – REST endpoints
│   └── 📁 dto                      # 🔹 DTOs
│
├── 📁 application
│   ├── 📁 exceptions              # Domain-related exceptions
│   ├── 📁 mappers                 # Entity<->DTO conversion
│   └── 📁 usecases                # 🔹 Use Cases – Business logic
│   └── 📁 repositories            # 🔹 Repository – Data store and management In-Memory
│
├── 📁 domain
│   ├── 📁 entities               # 🔹 Domain – Core entities
│   ├── 📁 enums                  # 🔹 Domain – Enums (e.g., JobTypes, Statuses)
│   ├── 📁 interfaces
│   │   ├── 📁 repositories       # Domain repository contracts
│   │   └── 📁 usecases           # Use case contracts
│   └── 📁 valueobjects           # 🔹 Domain – Value Objects 
│ 
├── 📁 infrastructure
│   └── 📁 config                 # 🔹 Spring Beans configurations
│
└── CharacterApplication.java     # Main app entry point
```

## 🛠 Technology

- 🧠 Java 21
- 🌱 Spring Boot 3+
- 📦 Maven
- 🧪 JUnit 5 + Mockito + RestAssured (Testing)

## ▶️ How to Run

### Prerequisites

- Java 21 or newer

### Starting the app

1. Open the app on your preferred IDE (e.g., IntelliJ IDEA).
2. Add the app as maven project by right clicking the `pom.xml` file.
3. Install dependencies and build the app:

```bash
mvn clean package 
```

4. Run the app via Maven or directly from the main class `CharacterApplication`:

```bash
./mvnw spring-boot:run
```

### Accessing the app

- API Base URL: [http://localhost:8080](http://localhost:8080)

4. Access the Swagger UI at [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html) to see endpoints and payloads
---

## Notes

- The app validates name length and limits the use of charcaters to letters and underscore (_)
- The app only accepts characters with the job classes listed above
- Battles can only be fought between two characters that are alive
- Tests cover controllers, facades, gateways, use cases and senders.

---

Made with ☕  by @GHBAlbuquerque
