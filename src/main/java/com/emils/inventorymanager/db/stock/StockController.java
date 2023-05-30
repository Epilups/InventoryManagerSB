package com.emils.inventorymanager.db.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stock")
public class StockController {
    @Autowired
    private StockRepository stockRepository;
    @GetMapping
    public List<Stock> getItemsInStock(){
        return stockRepository.findAll();
    }
    @PostMapping
    public void addItemToStock(@RequestBody final List<Stock> stocks){
        stockRepository.saveAll(stocks);
    }
    @GetMapping("/{id}")
    public Optional<Stock> getItemById(@PathVariable String id){
        return Optional.ofNullable(stockRepository.findItemById(id));
    }
    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable String id){
        stockRepository.deleteById(id);
    }
    @PutMapping("/{id}")
    public void updateStock(@RequestBody Stock stock){
        Stock savedStock = stockRepository.findById(stock.getId())
                .orElseThrow(() -> new RuntimeException(
                        String.format("Cannot find stock item by Id" + stock.getId())));
        savedStock.setName(stock.getName());
        savedStock.setPrice(stock.getPrice());
        savedStock.setQuantity(stock.getQuantity());
        savedStock.setNet_weight(stock.getNet_weight());
        savedStock.setZone(stock.getZone());
        savedStock.setCategory(stock.getCategory());
        savedStock.setManufacturer(stock.getManufacturer());

        stockRepository.save(stock);
    }
}
