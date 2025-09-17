/*
 * Added @Autiwired.
 * Implemented getAllEmployees(), getEmployeeByUuid(), and createEmployee().
 * Added necessary imports.
 */
package com.challenge.api.controller;

import com.challenge.api.dto.CreateEmployeeRequest;
import com.challenge.api.model.Employee;
import com.challenge.api.model.EmployeeImpl;
import com.challenge.api.service.EmployeeService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * Fill in the missing aspects of this Spring Web REST Controller. Don't forget to add a Service layer.
 */
@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * @implNote Need not be concerned with an actual persistence layer. Generate mock Employee models as necessary.
     * @return One or more Employees.
     */
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    /**
     * @implNote Need not be concerned with an actual persistence layer. Generate mock Employee model as necessary.
     * @param uuid Employee UUID
     * @return Requested Employee if exists
     */
    @GetMapping("/{uuid}")
    public Employee getEmployeeByUuid(@PathVariable UUID uuid) {
        Employee employee = employeeService.getEmployeeByUuid(uuid);
        if (employee == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found with UUID: " + uuid);
        }
        return employee;
    }

    /**
     * @implNote Need not be concerned with an actual persistence layer.
     * @param requestBody hint!
     * @return Newly created Employee
     */
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody CreateEmployeeRequest requestBody) {
        Employee newEmployee = new EmployeeImpl();
        newEmployee.setFirstName(requestBody.getFirstName());
        newEmployee.setLastName(requestBody.getLastName());
        newEmployee.setSalary(requestBody.getSalary());
        newEmployee.setAge(requestBody.getAge());
        newEmployee.setJobTitle(requestBody.getJobTitle());
        newEmployee.setEmail(requestBody.getEmail());
        newEmployee.setContractHireDate(requestBody.getContractHireDate());

        Employee createdEmployee = employeeService.createEmployee(newEmployee);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
    }
}
