package com.emils.inventorymanager.db.employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @PostMapping
    public ResponseEntity<?> addEmployee(@RequestBody EmployeeAccount employee) {
        try {
            employeeRepository.save(employee);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding employee");
        }
    }
    @GetMapping
    public List<EmployeeAccount> findEmployee(){
        return employeeRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteEmployeeAccount(@PathVariable String id){
        employeeRepository.deleteById(id);
    }
    @PutMapping("/{id}")
    public void updateEmployeeAccount(@RequestBody EmployeeAccount employeeAccount){
        EmployeeAccount savedAccount = employeeRepository.findById(employeeAccount.getId())
                .orElseThrow(() -> new RuntimeException(
                        String.format("Cannot find employee by Id" + employeeAccount.getId())));
        savedAccount.setName(employeeAccount.getName());
        savedAccount.setLastName(employeeAccount.getLastName());
        savedAccount.setUsername(employeeAccount.getUsername());
        savedAccount.setPassword(employeeAccount.getPassword());

        employeeRepository.save(employeeAccount);
    }
}
