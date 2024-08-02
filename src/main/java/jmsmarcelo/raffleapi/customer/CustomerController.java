package jmsmarcelo.raffleapi.customer;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@SecurityRequirement(name = "bearer-key")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> add(@RequestBody @Valid Customer customer) {
        return ResponseEntity.ok(customerService.create(customer));
    }
    @GetMapping
    public ResponseEntity<List<Customer>> getAll() {
        return ResponseEntity.ok(customerService.findAll());
    }
    @GetMapping("/{cpf}")
    public ResponseEntity<Customer> getById(@PathVariable @Valid String cpf) {
        return ResponseEntity.ok(customerService.findById(cpf));
    }
    @PutMapping
    public ResponseEntity<Customer> set(@RequestBody @Valid Customer customer) {
        return ResponseEntity.ok(customerService.update(customer));
    }
}
