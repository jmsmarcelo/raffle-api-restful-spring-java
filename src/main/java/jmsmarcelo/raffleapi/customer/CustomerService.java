package jmsmarcelo.raffleapi.customer;


import jmsmarcelo.raffleapi.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer create(Customer customer) {
        if(customerRepository.existsById(customer.getCpf()))
            throw new ValidationException(
                    "status::" + HttpStatus.CONFLICT, "field::cpf", "error::Customer already registered");

        return customerRepository.save(customer);
    }
    public List<Customer> findAll() {
        List<Customer> customers = customerRepository.findAll();

        if(customers.isEmpty())
            throw new ValidationException(
                    "status::" + HttpStatus.NOT_FOUND, "path::/customers", "error::there are no customers");

        return customers;
    }
    public Customer findById(String cpf) {
        if(!customerRepository.existsById(cpf))
            throw new ValidationException(
                    "status::" + HttpStatus.NOT_FOUND, "path::/customers/" + cpf, "error::Customer not found"
            );

        return customerRepository.findById(cpf).orElse(null);
    }
    public Customer update(Customer customer) {
        if(!customerRepository.existsById(customer.getCpf()))
            throw new ValidationException(
                    "status::" + HttpStatus.NOT_FOUND, "field::cpf", "error::Customer not found"
            );

        return customerRepository.save(customer);
    }
}
