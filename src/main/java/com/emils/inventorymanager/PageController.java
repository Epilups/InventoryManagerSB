package com.emils.inventorymanager;

import com.emils.inventorymanager.db.employee.EmployeeAccount;
import com.emils.inventorymanager.db.employee.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {
    private final EmployeeRepository employeeRepository;
    @Autowired
    public PageController(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }
    @GetMapping ("/")
    public String  getHomePage() {
        return "index";
    }
    @PostMapping ("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model){
        EmployeeAccount employee = employeeRepository.findByUsername(username);
        if (employee != null && employee.getPassword().equals(password)){
            model.addAttribute("error", "");

            if (username.equals("admin") && password.equals("1111")) {
                model.addAttribute("isAdmin", true);
            } else {
                model.addAttribute("isAdmin", false);
            }
            return "success";
        }else {
            model.addAttribute("error", "Invalid username or password");
            return "index";
        }
    }
    @GetMapping("/stock")
    public String getStockPage(){
        return "stock";
    }
    @GetMapping("/stock-man")
    public String getStockManPage(){
        return "stock-man";
    }
    @GetMapping("/employees")
    public String getEmployeesPage(){
        return "employee-man";
    }
    @GetMapping("/shifts")
    public String getShiftManPage(){
        return "shifts-man";
    }
}
