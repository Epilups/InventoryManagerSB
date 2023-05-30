package com.emils.inventorymanager.db.stock;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface StockRepository extends MongoRepository<Stock, String> {
    Stock findItemById(String id);
}
