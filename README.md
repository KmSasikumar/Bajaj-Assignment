# Bajaj Finserv Health Webhook Assignment

## Overview
This is a Spring Boot application that demonstrates webhook integration, SQL problem solving, and JWT authentication as part of the Bajaj Finserv Health qualifier assessment.

## Features
- **Automatic Webhook Generation**: Sends POST request on application startup
- **Dynamic SQL Problem Solving**: Automatically selects SQL solution based on registration number
- **JWT Authentication**: Secure submission using provided access tokens
- **WebClient Integration**: Modern reactive HTTP client for API communication

## Architecture

```
┌─────────────────────────────────────────────────────────────┐
│                    Application Flow                          │
├─────────────────────────────────────────────────────────────┤
│ 1. Application Startup                                      │
│ 2. Webhook Generation POST Request                         │
│ 3. Receive Webhook URL + Access Token                      │
│ 4. SQL Problem Selection (Based on Reg No)                 │
│ 5. Generate Appropriate SQL Solution                       │
│ 6. Submit Solution via Webhook with JWT                    │
└─────────────────────────────────────────────────────────────┘
```

## Technology Stack
- **Framework**: Spring Boot 3.1.5
- **Language**: Java 17
- **HTTP Client**: Spring WebFlux WebClient
- **Security**: JWT Token Authentication
- **Build Tool**: Maven
- **Logging**: SLF4J with Logback

## Project Structure
```
webhook-assignment/
├── src/main/java/com/bajajfinserv/webhookassignment/
│   ├── WebhookAssignmentApplication.java
│   ├── config/
│   │   └── WebClientConfig.java
│   ├── model/
│   │   ├── WebhookRequest.java
│   │   ├── WebhookResponse.java
│   │   └── SqlSubmissionRequest.java
│   └── service/
│       ├── WebhookService.java
│       └── SqlSolutionService.java
├── src/main/resources/
│   └── application.properties
├── pom.xml
└── README.md
```

## Setup Instructions

### Prerequisites
- Java 17 or higher
- Maven 3.6+
- Internet connection for API calls

### Installation
1. Clone the repository:
```bash
git clone https://github.com/your-username/webhook-assignment.git
cd webhook-assignment
```

2. Build the project:
```bash
mvn clean install
```

3. Run the application:
```bash
mvn spring-boot:run
```

### Configuration
The application uses the following default configuration in `application.properties`:
- Server Port: 8080
- Application Name: webhook-assignment
- Logging Level: INFO
- WebClient Max Memory: 16MB

## API Endpoints Used

### 1. Generate Webhook
- **URL**: `https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA`
- **Method**: POST
- **Headers**: Content-Type: application/json
- **Body**: 
  ```json
  {
    "name": "John Doe",
    "regNo": "REG12347",
    "email": "john@example.com"
  }
  ```

### 2. Submit Solution
- **URL**: Provided in webhook response
- **Method**: POST
- **Headers**: 
  - Content-Type: application/json
  - Authorization: <access_token>
- **Body**:
  ```json
  {
    "finalQuery": "<SQL_QUERY>"
  }
  ```

## SQL Problem Logic

The application automatically selects SQL problems based on the last two digits of the registration number:

### Odd Registration Numbers (Question 1)
- **Problem**: Department salary statistics
- **Solution**: Complex query with JOINs, GROUP BY, HAVING, and aggregation functions
- **Features**: 
  - Department-wise employee count
  - Average, maximum, and minimum salaries
  - Filtering departments with more than 5 employees
  - Top 10 departments by average salary

### Even Registration Numbers (Question 2)
- **Problem**: High-performing employees identification
- **Solution**: Advanced query with CTEs (Common Table Expressions)
- **Features**:
  - Department statistics calculation
  - Employee performance comparison
  - Percentage calculation above department average
  - Complex multi-level analysis

## How It Works

1. **Startup Execution**: The application automatically triggers the assignment flow on startup
2. **Registration Analysis**: Extracts last two digits to determine problem type
3. **Dynamic SQL Generation**: Generates appropriate SQL based on odd/even logic
4. **Secure Submission**: Uses JWT tokens for authenticated solution submission
5. **Comprehensive Logging**: Detailed logging for debugging and monitoring

## Security Considerations
- JWT tokens are used for authentication
- WebClient is configured with appropriate headers
- No sensitive data is logged
- Secure HTTP communication

## Error Handling
- Comprehensive exception handling in all services
- Detailed logging for debugging
- Graceful failure with informative error messages
- Fallback mechanisms for API failures

## Testing
The application includes:
- Unit test structure (ready for implementation)
- Integration test capabilities
- Mock server support for testing API calls

## Deployment

### Local Development
```bash
mvn spring-boot:run
```

### Production Build
```bash
mvn clean package
java -jar target/webhook-assignment-0.0.1-SNAPSHOT.jar
```

### Docker Deployment
```dockerfile
FROM openjdk:17-jre-slim
COPY target/webhook-assignment-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

## Monitoring
- Application logs are available in the console
- Log levels can be adjusted in `application.properties`
- WebClient request/response logging is enabled in DEBUG mode

## Future Enhancements
- Database integration for storing results
- Retry mechanism for failed API calls
- Circuit breaker pattern for resilience
- Metrics and health check endpoints
- Configuration externalization

## Contributing
1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License
This project is created for educational purposes as part of the Bajaj Finserv Health technical assessment.

## Contact
For questions or support, please refer to the assignment guidelines or contact the technical team.

---

**Note**: This application is designed to work with the specific API endpoints provided in the assignment. Ensure you have the correct URLs and authentication details before running the application.