package jmsmarcelo.raffleapi.service;


import jmsmarcelo.raffleapi.model.Customer;
import jmsmarcelo.raffleapi.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }
    public Customer findById(String cpf) {
        return customerRepository.findById(cpf).orElse(null);
    }
    public Customer update(Customer customer) {
        return customerRepository.save(customer);
    }
}
