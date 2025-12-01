# Bajaj Finserv Health Qualifier 1 - Java Submission

**Student Name:** Sasikumar  
**Registration Number:** 22BCE11638  
**University:** VIT Bhopal  
**Email:** Kommamani012@gmail.com  

---

## Overview
This project is my submission for the **Bajaj Finserv Health Qualifier 1**. It is a Spring Boot application designed to automate the process of interacting with the Bajaj Health hiring API. 

The application handles the full flow: generating a webhook, receiving a unique token, solving the assigned SQL problem based on my Registration Number, and submitting the final answer securely.

## Logic & Flow
Since my Registration Number (**22BCE11638**) ends in **38** (Even), the application is programmed to solve **Question 2**.

1.  **Startup:** The app initializes and prepares the payload with my student details.
2.  **Webhook Generation:** Sends a POST request to `generateWebhook`.
3.  **Token Parsing:** Extracts the `accessToken` and `webhookUrl` from the JSON response.
4.  **SQL Selection:** Detects the even registration number and selects the solution for **Question 2** (High-performing employees identification).
5.  **Submission:** Sends the SQL query to the `testWebhook` endpoint using the JWT token in the Authorization header.

## Technology Stack
* **Java 17**
* **Spring Boot 3.x**
* **Maven**
* **RestTemplate** (for API calls)

## Application Flow Diagram

[Start]
   |
   v
[POST /generateWebhook] ----> { "regNo": "22BCE11638", ... }
   |
   v
[Receive Response] <--------- { "accessToken": "...", "url": "..." }
   |
   v
[Select Logic] -------------> REG NO ends in 38 (Even) -> Question 2
   |
   v
[Submit Solution] ----------> POST /testWebhook with JWT
   |
   v
[Success]


## Setup & Execution

### Prerequisites
* Java 17 or higher
* Maven installed

### How to Run
1.  Clone this repository:
    ```bash
    git clone [https://github.com/KmSasikumar/Bajaj-Assignment.git](https://github.com/KmSasikumar/Bajaj-Assignment.git)
    ```
2.  Navigate to the directory:
    ```bash
    cd Bajaj-Assignment
    ```
3.  Build the project:
    ```bash
    mvn clean package -DskipTests
    ```
4.  Run the JAR file:
    ```bash
    java -jar target/demo-0.0.1-SNAPSHOT.jar
    ```

## API Details
The application uses the following payload to identify me during the webhook generation:

```json
{
  "name": "Sasikumar",
  "regNo": "22BCE11638",
  "email": "Kommamani012@gmail.com"
}
