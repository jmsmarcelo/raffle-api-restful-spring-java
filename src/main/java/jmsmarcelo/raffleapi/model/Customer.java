package jmsmarcelo.raffleapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity(name = "customers")
public class Customer {
    @Id
    @NotBlank
    @Column(length = 11)
    private String cpf;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, length = 15)
    private String phone;
    @Column(length = 15)
    private String alternativePhone;
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
