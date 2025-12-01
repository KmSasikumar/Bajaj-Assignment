package com.bajajfinserv.webhookassignment.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SqlSolutionServiceTest {

    @Autowired
    private SqlSolutionService sqlSolutionService;

    @Test
    void testOddRegistrationNumber() {
        String regNo = "REG12347";
        String sql = sqlSolutionService.getSqlSolution(regNo);
        
        assertNotNull(sql);
        assertTrue(sql.contains("SELECT"));
        assertTrue(sql.contains("FROM"));
        assertTrue(sql.contains("departments"));
        System.out.println("Odd Registration SQL: " + sql);
    }

    @Test
    void testEvenRegistrationNumber() {
        String regNo = "REG12346";
        String sql = sqlSolutionService.getSqlSolution(regNo);
        
        assertNotNull(sql);
        assertTrue(sql.contains("WITH"));
        assertTrue(sql.contains("department_stats"));
        System.out.println("Even Registration SQL: " + sql);
    }

    @Test
    void testRegistrationNumberExtraction() {
        // Test with different registration numbers
        String[] testRegs = {"REG12345", "REG12346", "REG12347", "REG12348"};
        
        for (String reg : testRegs) {
            String sql = sqlSolutionService.getSqlSolution(reg);
            assertNotNull(sql);
            System.out.println("Registration " + reg + " generated valid SQL");
        }
    }
}