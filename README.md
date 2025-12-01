# Bajaj Finserv Health Qualifier 1 - Java Submission

**Student Name:** Sasikumar  
**Registration Number:** 22BCE11638  
**University:** VIT Bhopal  
**Email:** Kommamani012@gmail.com  

---

# Bajaj Finserv Health Qualifier 1 - Java Submission

**Student Name:** Sasikumar  
**Registration Number:** 22BCE11638  
**University:** VIT Bhopal  
**Email:** Kommamani012@gmail.com  

---

This project is my submission for the **Bajaj Finserv Health Qualifier 1**. It is a Spring Boot application designed to automate the process of interacting with the Bajaj Health hiring API. 

The application handles the full flow: generating a webhook, receiving a unique token, solving the assigned SQL problem based on my Registration Number, and submitting the final answer securely.

## Logic & Flow
The application automatically determines the correct SQL problem to solve based on the Registration Number provided in the code.

1.  **Startup:** The app initializes and prepares the payload with student details.
2.  **Webhook Generation:** Sends a POST request to `generateWebhook`.
3.  **Token Parsing:** Extracts the `accessToken` and `webhookUrl` from the JSON response.
4.  **SQL Selection:** 
    *   **Odd Registration:** Solves Question 1 (Highest Avg Salary Dept).
    *   **Even Registration:** Solves Question 2 (Top 3 Employees per Dept).
    *   *My Registration (22BCE11638) is Even, so it solves Question 2.*
5.  **Submission:** Sends the SQL query to the `testWebhook` endpoint using the JWT token in the Authorization header.

## Technology Stack
* **Java 17**
* **Spring Boot 3.1.5**
* **Maven**
* **Spring WebFlux (WebClient)** - For reactive API calls

## Project Structure
The repository contains the following key files:

*   `src/` - Source code for the Spring Boot application.
*   `webhook-assignment.jar` - **Executable JAR file** (Ready to run).
*   `ANALYSIS.md` - Detailed technical analysis of the solution.
*   `SOLUTION_SUMMARY.md` - Summary of the implementation and features.
*   `index.html` - Project documentation page.
*   `architecture-diagram.png` - Visual representation of the flow.
*   `SQL Question 1 JAVA.pdf` - Problem statement for Odd registration numbers.
*   `SQL Qwestion 2 JAVA.pdf` - Problem statement for Even registration numbers.

## Setup & Execution

### Prerequisites
* Java 17 or higher
* Maven installed

### How to Run
1.  Clone this repository:
    ```bash
    git clone https://github.com/KmSasikumar/Bajaj-Assignment.git
    ```
2.  Navigate to the directory:
    ```bash
    cd Bajaj-Assignment
    ```
3.  **Run the JAR file directly:**
    ```bash
    java -jar webhook-assignment.jar
    ```

### Build from Source (Optional)
If you want to rebuild the project:
```bash
mvn clean package -DskipTests
java -jar target/webhook-assignment-0.0.1-SNAPSHOT.jar
```

## API Details
The application uses the following payload to identify me during the webhook generation:

```json
{
  "name": "Sasikumar",
  "regNo": "22BCE11638",
  "email": "Kommamani012@gmail.com"
}
```
