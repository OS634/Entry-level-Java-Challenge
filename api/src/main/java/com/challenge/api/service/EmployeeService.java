/*
 * A service layer to handle business logic for Employee operations.
 * Use a HashMap to simulate a database.
 * Created 5 mock employees using @PostConstruct.
 * Created three functions: getAllEmployees(), getEmployeebyUuid(), and createEmployee().
 */
package com.challenge.api.service;

import com.challenge.api.model.Employee;
import com.challenge.api.model.EmployeeImpl;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private Map<UUID, Employee> employeeDatabase = new HashMap<>();

    @PostConstruct
    public void initializeMockData() {
        Employee employee1 = createMockEmployee("John", "Doe", 75000, 30, "Software Engineer", "john.doe@example.com");
        Employee employee2 = createMockEmployee("Jane", "Smith", 85000, 35, "Senior Software Engineer", "jane.smith@example.com");
        Employee employee3 = createMockEmployee("Bob", "Johnson", 65000, 28, "Junior Developer", "bob.johnson@example.com");
        Employee employee4 = createMockEmployee("Alice", "Williams", 95000, 40, "Tech Lead", "alice.williams@example.com");
        Employee employee5 = createMockEmployee("Charlie", "Brown", 70000, 32, "DevOps Engineer", "charlie.brown@example.com");

        employeeDatabase.put(employee1.getUuid(), employee1);
        employeeDatabase.put(employee2.getUuid(), employee2);
        employeeDatabase.put(employee3.getUuid(), employee3);
        employeeDatabase.put(employee4.getUuid(), employee4);
        employeeDatabase.put(employee5.getUuid(), employee5);
    }

    private Employee createMockEmployee(String firstName, String lastName, Integer salary, Integer age, String jobTitle, String email) {
        Employee employee = new EmployeeImpl();
        employee.setUuid(UUID.randomUUID());
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setSalary(salary);
        employee.setAge(age);
        employee.setJobTitle(jobTitle);
        employee.setEmail(email);
        employee.setContractHireDate(Instant.now().minusSeconds(365 * 24 * 60 * 60));
        return employee;
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employeeDatabase.values());
    }

    public Employee getEmployeeByUuid(UUID uuid) {
        return employeeDatabase.get(uuid);
    }

    public Employee createEmployee(Employee employee) {
        if (employee.getUuid() == null) {
            employee.setUuid(UUID.randomUUID());
        }
        if (employee.getContractHireDate() == null) {
            employee.setContractHireDate(Instant.now());
        }
        employeeDatabase.put(employee.getUuid(), employee);
        return employee;
    }
}