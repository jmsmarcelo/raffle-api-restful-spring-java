package jmsmarcelo.raffleapi.customer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity(name = "customers")
public class Customer {
    @Id
    @Column(length = 11)
    @Pattern(regexp = "\\d{11}", message = "must contain 11 numbers")
    private String cpf;
    @NotBlank
    @Column(nullable = false, length = 50)
    private String name;
    @NotBlank
    @Column(nullable = false, length = 15)
    private String phone;
    @Column(length = 15)
    private String alternativePhone;
    @Pattern(regexp = "([^\"]+@[A-z0-9\\-]+\\.[A-z0-9-]+(\\.[A-z0-9\\-]+)?)$|")
    private String email;

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf.replaceAll("\\D", "");
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAlternativePhone() {
        return alternativePhone;
    }
    public void setAlternativePhone(String alternativePhone) {
        this.alternativePhone = alternativePhone;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
