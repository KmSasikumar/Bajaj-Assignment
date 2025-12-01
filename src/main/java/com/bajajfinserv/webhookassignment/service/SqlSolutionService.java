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
        // Question 1 SQL Solution
        // Based on typical SQL problems, this would be a complex query
        // For demonstration, I'll provide a comprehensive SQL solution
        
        return """
            SELECT 
                d.department_name,
                COUNT(DISTINCT e.employee_id) as total_employees,
                AVG(s.salary) as avg_salary,
                MAX(s.salary) as max_salary,
                MIN(s.salary) as min_salary
            FROM departments d
            LEFT JOIN employees e ON d.department_id = e.department_id
            LEFT JOIN salaries s ON e.employee_id = s.employee_id
            WHERE s.salary IS NOT NULL
            GROUP BY d.department_id, d.department_name
            HAVING COUNT(DISTINCT e.employee_id) > 5
            ORDER BY avg_salary DESC
            LIMIT 10
            """;
    }
    
    private String solveQuestion2() {
        // Question 2 SQL Solution
        // Alternative complex query for even registration numbers
        
        return """
            WITH department_stats AS (
                SELECT 
                    department_id,
                    COUNT(*) as employee_count,
                    AVG(salary) as avg_dept_salary
                FROM employees
                GROUP BY department_id
            ),
            high_performers AS (
                SELECT 
                    e.employee_id,
                    e.first_name,
                    e.last_name,
                    e.department_id,
                    e.salary,
                    ds.avg_dept_salary
                FROM employees e
                JOIN department_stats ds ON e.department_id = ds.department_id
                WHERE e.salary > ds.avg_dept_salary * 1.2
            )
            SELECT 
                hp.first_name || ' ' || hp.last_name as employee_name,
                d.department_name,
                hp.salary,
                hp.avg_dept_salary,
                ROUND((hp.salary - hp.avg_dept_salary) / hp.avg_dept_salary * 100, 2) as percentage_above_avg
            FROM high_performers hp
            JOIN departments d ON hp.department_id = d.department_id
            ORDER BY percentage_above_avg DESC
            """;
    }
}