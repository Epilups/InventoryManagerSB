package com.emils.inventorymanager.shifts;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface ShiftRepository extends MongoRepository<Shift, String> {

    Shift findByDate(String date);

}
