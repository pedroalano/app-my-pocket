# My Pocket

My Pocket é uma aplicação de controle financeiro pessoal desenvolvida em Java com Spring Boot. O objetivo é permitir o gerenciamento de contas, categorias e transações financeiras de forma simples e segura.

👉 Frontend do projeto: [my-pocket-frontend](https://github.com/pedroalano/my-pocket-frontend)

## Sumário

- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Como Executar](#como-executar)
- [Endpoints Principais](#endpoints-principais)
- [Como Rodar os Testes](#como-rodar-os-testes)
- [Contribuição](#contribuição)

## Tecnologias Utilizadas

- Java
- Spring Boot
- Spring Security (JWT)
- Maven
- Docker / Docker Compose
- PostgreSQL

## Estrutura do Projeto

```
├── docker-compose.yml         # Configuração de containers
├── pom.xml                    # Dependências Maven
├── src/
│   ├── main/
│   │   ├── java/br/com/pedroalano/my_pocket/
│   │   │   ├── controller/    # Controllers REST
│   │   │   ├── dto/           # Data Transfer Objects
│   │   │   ├── model/         # Entidades JPA
│   │   │   ├── repository/    # Repositórios Spring Data
│   │   │   ├── service/       # Lógica de negócio
│   │   │   ├── config/        # Configurações (ex: segurança)
│   │   │   ├── security/      # JWT e filtros de autenticação
│   │   └── resources/
│   │       ├── application.properties # Configurações da aplicação
│   │       ├── static/        # Recursos estáticos
│   │       └── templates/     # Templates (caso use)
│   └── test/                  # Testes automatizados
```

## Como Executar

### Pré-requisitos

- Java 17+
- Maven
- Docker (opcional, para banco de dados)

### Subindo o banco de dados com Docker

```sh
docker-compose up -d
```

### Rodando a aplicação

```sh
./mvnw spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`.

## Endpoints Principais

- `/auth` - Autenticação (login, registro)
- `/accounts` - Gerenciamento de contas
- `/categories` - Gerenciamento de categorias
- `/transactions` - Gerenciamento de transações

> Os endpoints seguem o padrão REST e utilizam autenticação JWT.

## Como Rodar os Testes

```sh
./mvnw test
```

## Contribuição

Pull requests são bem-vindos! Para maiores informações, abra uma issue ou entre em contato.

---

👨‍💻 Desenvolvido por [Pedro Alano](https://github.com/pedroalano)
