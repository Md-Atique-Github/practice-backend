package com.example.practice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin("*")
@RestController
@RequestMapping("/employee")
public class Controller {

    @Autowired
    IEmpRepo iEmpRepo;

    @PostMapping(value = "post")
    public Employee postMethodName(@RequestBody Employee employee) {
        return iEmpRepo.save(employee);
    }

    @GetMapping(value = "getAll")
    public List<Employee> getMethod() {
        return iEmpRepo.findAll();
    }

    @GetMapping(value = "get/{id}")
    public Optional<Employee> getById(@PathVariable Long id) {
        return iEmpRepo.findById(id);

    }

    @PutMapping(value = "update/{id}")
    public ResponseEntity<Employee> updatemethod(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        Optional<Employee> employeeOptional = iEmpRepo.findById(id);

        if (employeeOptional.isPresent()) {

            Employee employee = employeeOptional.get();

            employee.setFirstName(employeeDetails.getFirstName());
            employee.setLastName(employeeDetails.getLastName());
            employee.setEmailId(employeeDetails.getEmailId());

            Employee updatedEmployee = iEmpRepo.save(employee);

            return ResponseEntity.ok(updatedEmployee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        Optional<Employee> employeeOptional = iEmpRepo.findById(id);
        if (employeeOptional.isPresent()) {
            iEmpRepo.deleteById(id);
            return ResponseEntity.ok().body("Deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "delete")
    public void deleteAll() {
        iEmpRepo.deleteAll();
    }

}