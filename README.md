# Raffle API RESTful
![Status do Projeto](https://img.shields.io/badge/status-concluído-brightgreen)

<img align="right" width="200" src="./dio-desenvolvimento.java.com.ia.png">

### Desafio DIO Bootcamp

- **Desafio:** Desenvolver uma API RESTful com Spring Framework e Java
- **Projeto:** A livre escolha
- **Bootcamp:** [Coding The Future GFT - Desenvolvimento Java com IA](https://www.dio.me/bootcamp/coding-future-gft-desenvolvimento-java-com-ia)

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
  - **Status** *(Pendente, Cancelado, Disponível e Realizado)*
  - **Award** *(Prêmios a ser Ganhado)*
  - **Tickets** *(Contendo a lista de Bilhetes adqueridos)*

- ### Customer:
  - Armazena e obtém os dados do cliente no Banco de Dados
  - **CPF** *(Será a ID no banco de dados, portanto é obrigatório)*
  - **Name** *(Nome do cliente, é obrigatório)*
  - **Phone** *(Telefone, é obrigatório)*
  - **alternativePhone** *(Telefone alternativo, é opcional)*
  - **Email** *(é Opcional)*

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

- ### Spring Security:
  - As senhas dos usuários são criptografadas com **BCrypt** e armazenadas no banco de dados
  - Adicionei duas hierarquias: **ADMIN** que tem acesso total e o **USER** que somente ver as Rifas.
  - Permissão de acesso via Token, que é gerado via login
  - Não adicionei cadastro, terá que ser feito manualmente no banco de dados

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
- [x] Configuração da validação de dados
- [x] Tratamento das excessões
- [x] Implementação de segurança com autenticação baseada em Token
- [ ] ~~Adição de testes unitários~~
- [ ] ~~Configuração do CI/CD~~

## Como usar:
- **Swagger-UI:**
  - `http://localhost:8080/swagger-ui.html`
- **Console do Banco de Dados:**
  - `http://localhost:8080/h2-console/`
- **Usuário/Administrador:**
  - Como o Banco de Dados somente está em memória adicionei a Class `UserForTest` para criar automaticamente um ADMIN (user: `adminTest`, pass: `12345678`) e um USER (user: `userTest`, pass: `12345678`) para testes de hierarquia
  - 

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
    class CustomerController {
        - CustomerService customerService
        + add(Customer customer)
        + getAll()
        + getById(String cpf)
        + set(Customer customer)
    }
    class CustomerRepository {
        <<interface>>
    }
    class CustomerService {
        - CustomerRepository customerRepository
        + create(Customer customer)
        + findAll()
        + findById(String cpf)
        + update(Customer customer)
    }

    class Raffle {
        - Long id
        - String name
        - Date drawDate
        - Double price
        - String award
        - RaffleStatus status
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
    class RaffleController {
        - RaffleService raffleService
        + add(Raffle raffle)
        + getAll()
        + getById(Long id)
        + set(Raffle raffle)
    }
    class RaffleRepository {
        <<interface>>
    }
    class RaffleService {
        - RaffleRepository raffleRepository
        + create(Raffle raffle)
        + findAll()
        + findById(Long id)
        + update(Raffle raffle)
    }
    class RaffleStatus {
        <<enumeration>>
        PENDING
        CANCELLED
        UPCOMING
        COMPLETED
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
    class TicketController {
        - TicketService ticketService
        + create(Ticket ticket)
        + get(Long id)
        + getAllByRaffleId(Long id)
        + getAllByCustomerCPf(String cpf)
        + getAllByRaffleIdAndCustomerCPf(Long id, String cpf)
        + set(Ticket ticket)
    }
    class TicketRepository {
        <<interface>>
        + List~Ticket~ findAllByRaffleId(Long raffleId)
        + List~Ticket~ findAllByCustomerCpf(String customerCpf)
        + List~Ticket~ findAllByRaffleIdAndCustomerCpf(Long raffleId, String customerCpf)
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
    class TicketStatus {
        <<enumeration>>
        PARTICIPATING
        CANCELLED
        WINNER
    }

    class RaffleDrawController {
        - RaffleDrawService raffleDrawService
        + drawTicket(Raffle raffle)
    }
    class RaffleDrawService {
        - RaffleRepository raffleRepository
        - TicketRepository ticketRepository
        + drawTicket(Raffle raffle)
    }
    
    class GlobalExceptionHandler {
        + handle(Exception e)
    }
    class ValidationException {
        - ExceptionDetails
        + ValidationException()
        + getExceptionDetails()
    }
    class ExceptionDetails {
    }

    CustomerController --> CustomerService
    CustomerService --> CustomerRepository
    CustomerRepository --> Customer
    CustomerService --> ValidationException

    RaffleController --> RaffleService
    RaffleService --> RaffleRepository
    RaffleRepository --> Raffle
    Raffle --> "1" RaffleStatus
    RaffleService --> ValidationException

    TicketController --> TicketService
    TicketService --> TicketRepository
    TicketRepository --> Ticket
    Ticket --> "1" TicketStatus
    Ticket "0..*" --> "1" Raffle
    Ticket "0..*" --> "1" Customer
    TicketService --> ValidationException

    RaffleDrawController --> RaffleDrawService
    RaffleDrawService --> RaffleRepository
    RaffleDrawService --> TicketRepository
    RaffleDrawService --> ValidationException
    
    GlobalExceptionHandler --> ValidationException
    GlobalExceptionHandler --> ExceptionDetails
    ValidationException --> ExceptionDetails
```