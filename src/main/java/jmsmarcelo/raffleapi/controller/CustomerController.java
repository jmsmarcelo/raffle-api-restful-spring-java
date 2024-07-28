package jmsmarcelo.raffleapi.controller;

import jmsmarcelo.raffleapi.model.Customer;
import jmsmarcelo.raffleapi.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> add(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.create(customer));
    }
    @GetMapping
    public ResponseEntity<List<Customer>> getAll() {
        return ResponseEntity.ok(customerService.findAll());
    }
    @GetMapping("/{cpf}")
    public ResponseEntity<Customer> getById(@PathVariable String cpf) {
        return ResponseEntity.ok(customerService.findById(cpf));
    }
    @PutMapping
    public ResponseEntity<Customer> set(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.update(customer));
    }
}
