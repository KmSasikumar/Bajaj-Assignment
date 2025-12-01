package com.bajajfinserv.webhookassignment.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SqlSolutionService {

    public String getSqlSolution(String regNo) {
        // Extract last two digits from registration number
        String lastTwoDigits = regNo.substring(Math.max(0, regNo.length() - 2));
        int lastTwoDigitsInt = Integer.parseInt(lastTwoDigits);

        log.info("Registration number: {}, Last two digits: {}", regNo, lastTwoDigitsInt);

        // Determine which SQL problem to solve based on odd/even
        if (lastTwoDigitsInt % 2 == 1) {
            log.info("Odd registration number - solving Question 1");
            return solveQuestion1();
        } else {
            log.info("Even registration number - solving Question 2");
            return solveQuestion2();
        }
    }

    private String solveQuestion1() {
        // Question 1: Find the department(s) with the highest average salary
        // among departments with at least 5 employees.

        return """
                WITH DeptStats AS (
                    SELECT d.department_name, AVG(e.salary) as avg_salary
                    FROM departments d
                    JOIN employees e ON d.department_id = e.department_id
                    GROUP BY d.department_id, d.department_name
                    HAVING COUNT(e.employee_id) >= 5
                )
                SELECT department_name, avg_salary
                FROM DeptStats
                WHERE avg_salary = (SELECT MAX(avg_salary) FROM DeptStats);
                """;
    }

    private String solveQuestion2() {
        // Question 2: Find the top 3 highest-paid employees in each department.

        return """
                WITH RankedEmployees AS (
                    SELECT
                        d.department_name,
                        e.first_name || ' ' || e.last_name as full_name,
                        e.salary,
                        DENSE_RANK() OVER (PARTITION BY d.department_id ORDER BY e.salary DESC) as rank
                    FROM employees e
                    JOIN departments d ON e.department_id = d.department_id
                )
                SELECT department_name, full_name, salary
                FROM RankedEmployees
                WHERE rank <= 3
                ORDER BY department_name, salary DESC;
                """;
    }
}