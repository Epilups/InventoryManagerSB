package com.emils.inventorymanager.db.employee;

import org.springframework.data.mongodb.repository.MongoRepository;
public interface EmployeeRepository extends MongoRepository<EmployeeAccount, String> {
    EmployeeAccount findByUsername(String username);
}
