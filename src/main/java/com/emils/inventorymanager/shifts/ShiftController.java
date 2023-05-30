package com.emils.inventorymanager.shifts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/shifts")
public class ShiftController {
    @Autowired
    private ShiftRepository shiftRepository;

    @GetMapping("/{date}")
    public Shift getShiftsByDate(@PathVariable String date){
        return shiftRepository.findByDate(date);
    }

//    @GetMapping
//    public ArrayList<Map<String, String>> getShifts() {
//        List<Shift> allPeople = shiftRepository.findAll();
//        ArrayList<Map<String, String>> uniqueDates = new ArrayList<>();
//        for (Shift temp: allPeople) {
//            String date = temp.getDate();
//            boolean uniqueOrNot = true;
//            for (Map<String, String> maps : uniqueDates) {
//                if (date == maps.values().toArray()[0]) {
//                    uniqueOrNot = false;
//                    break;
//                }
//            }
//            if (uniqueOrNot) {
//                Map<String, String > payload = new HashMap<>();
//                payload.put("date", date);
//                uniqueDates.add(payload);
//            }
//        }
//        return uniqueDates;
//    }

    @GetMapping
    public List<Shift> getShifts() {
        return shiftRepository.findAll();
    }
    @PostMapping
    public void addShift(@RequestBody final List<Shift> shifts) {
        shiftRepository.saveAll(shifts);
    }
    @DeleteMapping("(/{id}")
    public void deleteShift(@PathVariable String id){
        shiftRepository.deleteById(id);
    }

    @DeleteMapping("/{date}")
    public void deleteShiftByDate(@PathVariable String date){
        Shift shiftToDelete = shiftRepository.findByDate(date);

        if (shiftToDelete != null){
            shiftRepository.delete(shiftToDelete);
        }
    }
}
