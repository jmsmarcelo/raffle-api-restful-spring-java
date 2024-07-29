# Raffle API RESTful
![Status do Projeto](https://img.shields.io/badge/status-em%20produção-brightgreen)

### Desafio DIO Bootcamp
- **Desafio:** Desenvolver uma API RESTful com Spring Framework e Java
- **Tema:** A livre escolha
- **Bootcamp:** Desenvolvimento Java com IA

## Tecnologias utilizadas:
- Java 21 LTS
- Spring Boot 3.3.2
- Gradle - Groovy
- JPA
- H2 Database
- OpenAPI Swagger

## Detalhes do Projeto:
    API que armazena rifas, clientes, bilhetes e realiza o sorteio.

- ### Raffle: 
  - **Armazena e obtém os dados da rifa no Banco de Dados**
  - **ID** *(Gerado automaticamente pelo o banco de dados)*
  - **Name** *(Nome da Rifa, não é permitido nomes repetidos)*
  - **Date** *(Data e hora do sorteio)*
  - **Price** *(Valor da rifa)*
  - **Status** *(Pendente, Cancelado, A Realizar e Realizado)*
  - **Award** *(Prêmios a ser Ganhado)*
  - **Tickets** *(Contendo a lista de Bilhetes adqueridos)*

- ### Customer:
  - Armazena e obtém os dados do cliente no Banco de Dados
  - **CPF** *(Será a ID no banco de dados, portanto é obrigatório)*
  - **Name** *(Nome do cliente, é obrigatório)*
  - **Phone** *(Telefone, é obrigatório)*
  - **alternativePhone** *(Telefone alternativo, é pcional)*
  - **Email** *(Opcional)*

- ### Ticket:
  - Armazena e obtém os dados do bilhete no Banco de Dados
  - **ID** *(Gerado automaticamento pelo banco de dados)*
  - **Raffle** *(Pertencente)*
  - **Customer** *(Proprietário)*
  - **Status** *(Participando, Cancelado e Ganhador)*

- ### RaffleDraw:
  - Realiza o sorteio
  - Atualiza o Status da Rifa para Realizado
  - Atualiza o Status do Bilhete para Ganhador

## Progresso do Projeto:
- [x] Implementação do modelo `Customer`
- [x] Implementação do modelo `Raffle`
- [x] Implementação do modelo `Ticket`
- [x] Criação dos endpoints de CRUD para `Customer`
- [x] Criação dos endpoints de CRUD para `Raffle`
- [x] Criação dos endpoints de CRUD para `Ticket`
- [x] Implementação do H2 Database e Console
- [x] Testes de CRUD para `Raffle`
- [x] Testes de CRUD para `Customer`
- [x] Testes de CRUD para `Ticket`
- [x] Implementação da Documentação Swagger
- [x] Implementação do controlador `RaffleDraw`
- [x] Implementação do serviço `RaffleDraw`
- [ ] Configuração da validação de dados
- [ ] Tratamento das excessões
- [ ] Implementação de segurança com autenticação baseada em Token
- [ ] ~~Adição de testes unitários~~
- [ ] ~~Configuração do CI/CD~~

## Diagrama UML:

```mermaid
classDiagram
    class Customer {
        - String cpf
        - String name
        - String phone
        - String alternativePhone
        - String email
        + getCpf()
        + setCpf(String cpf)
        + getName()
        + setName(String name)
        + getPhone()
        + setPhone(String phone)
        + getAlternativePhone()
        + setAlternativePhone(String alternativePhone)
        + getEmail()
        + setEmail(String email)
    }

    class Raffle {
        - Long id
        - String name
        - Date drawDate
        - Double price
        - String award
        - RaffleStatus status
        - List~Ticket~ tickets
        + getId()
        + setId(Long id)
        + getName()
        + setName(String name)
        + getDrawDate()
        + setDrawDate(Date drawDate)
        + getPrice()
        + setPrice(Double price)
        + getAward()
        + setAward(String award)
        + getStatus()
        + setStatus(RaffleStatus status)
        + getTickets()
        + setTickets(List~Ticket~ tickets)
    }

    class Ticket {
        - Long id
        - Raffle raffle
        - Customer customer
        - TicketStatus status
        + getId()
        + setId(Long id)
        + getRaffle()
        + setRaffle(Raffle raffle)
        + getCustomer()
        + setCustomer(Customer customer)
        + getStatus()
        + setStatus(TicketStatus status)
    }

    class RaffleStatus {
        <<enumeration>>
        PENDING
        CANCELLED
        UPCOMING
        COMPLETED
    }

    class TicketStatus {
        <<enumeration>>
        PARTICIPATING
        CANCELLED
        WINNER
    }

    class CustomerRepository {
        <<interface>>
    }

    class RaffleRepository {
        <<interface>>
    }

    class TicketRepository {
        <<interface>>
        + List~Ticket~ findAllByRaffleId(Long raffleId)
        + List~Ticket~ findAllByCustomerCpf(String customerCpf)
        + List~Ticket~ findAllByRaffleIdAndCustomerCpf(Long raffleId, String customerCpf)
    }

    class CustomerService {
        - CustomerRepository customerRepository
        + create(Customer customer)
        + findAll()
        + findById(String cpf)
        + update(Customer customer)
    }

    class RaffleService {
        - RaffleRepository raffleRepository
        + create(Raffle raffle)
        + findAll()
        + findById(Long id)
        + update(Raffle raffle)
    }

    class TicketService {
        - TicketRepository ticketRepository
        + create(Ticket ticket)
        + findById(Long id)
        + findAllByRaffleId(Long raffleId)
        + findAllByCustomerCpf(String customerCpf)
        + findAllByRaffleIdAndCustomerCpf(Long raffleId, String customerCpf)
        + update(Ticket ticket)
    }

    class RaffleDrawService {
        - RaffleRepository raffleRepository
        - TicketRepository ticketRepository
        + drawTicket(Raffle raffle)
    }

    class CustomerController {
        - CustomerService customerService
        + add(Customer customer)
        + getAll()
        + getById(String cpf)
        + set(Customer customer)
    }

    class RaffleController {
        - RaffleService raffleService
        + add(Raffle raffle)
        + getAll()
        + getById(Long id)
        + set(Raffle raffle)
    }

    class TicketController {
        - TicketService ticketService
        + create(Ticket ticket)
        + get(Long id)
        + getAllByRaffleId(Long id)
        + getAllByCustomerCPf(String cpf)
        + getAllByRaffleIdAndCustomerCPf(Long id, String cpf)
        + set(Ticket ticket)
    }

    class RaffleDrawController {
        - RaffleDrawService raffleDrawService
        + drawTicket(Raffle raffle)
    }

    CustomerController --> CustomerService
    RaffleController --> RaffleService
    TicketController --> TicketService
    RaffleDrawController --> RaffleDrawService

    CustomerService --> CustomerRepository
    RaffleService --> RaffleRepository
    TicketService --> TicketRepository
    RaffleDrawService --> RaffleRepository
    RaffleDrawService --> TicketRepository

    CustomerRepository --> Customer
    RaffleRepository --> Raffle
    TicketRepository --> Ticket

    Raffle --> "1" RaffleStatus
    Ticket --> "1" TicketStatus
    Raffle --> "1..*" Ticket
    Ticket --> "1" Raffle
    Ticket --> "1" Customer
```