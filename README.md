# Venda Veículo API

API REST para cadastro de clientes, veículos e registro de compras.

## Tecnologias

- Java 21
- Spring Boot 3.5.13
- Spring Data JPA
- Spring Validation
- Spring Cache
- H2 Database (arquivo persistido em `./data/veiculodb`)
- Lombok
- Springdoc OpenAPI (Swagger UI)

## Como executar

```bash
./mvnw spring-boot:run
```

- API: `http://localhost:8080`
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- H2 Console: `http://localhost:8080/h2-console`

## Endpoints

### Clientes `/clientes`
| Método | Rota | Descrição |
|--------|------|-----------|
| POST | `/clientes` | Cadastrar cliente |
| GET | `/clientes` | Listar (paginado) |
| GET | `/clientes/{id}` | Buscar por ID |
| PUT | `/clientes/{id}` | Atualizar completo |
| PATCH | `/clientes/{id}` | Atualizar parcial |
| DELETE | `/clientes/{id}` | Deletar |

### Veículos `/veiculos`
| Método | Rota | Descrição |
|--------|------|-----------|
| POST | `/veiculos` | Cadastrar veículo |
| GET | `/veiculos` | Listar (paginado) |
| GET | `/veiculos/{id}` | Buscar por ID |
| PUT | `/veiculos/{id}` | Atualizar completo |
| PATCH | `/veiculos/{id}` | Atualizar parcial |
| DELETE | `/veiculos/{id}` | Deletar |

### Compras `/compras`
| Método | Rota | Descrição |
|--------|------|-----------|
| POST | `/compras` | Registrar compra |
| GET | `/compras` | Listar (paginado) |
| GET | `/compras/{id}` | Buscar por ID |
| PUT | `/compras/{id}` | Atualizar compra |
| PATCH | `/compras/{id}` | Atualizar parcial |
| DELETE | `/compras/{id}` | Deletar compra |

## Regras de negócio implementadas

- Um mesmo cliente não pode comprar o mesmo veículo duas vezes
- Respostas com cache por entidade (Spring Cache)

## Exemplos de payload

**Cliente:**
```json
{ "nome": "João Silva", "cpf": "12345678900" }
```

**Veículo:**
```json
{ "marca": "Toyota", "modelo": "Corolla", "valor": 120000.00 }
```

**Compra:**
```json
{ "clienteId": 1, "veiculoId": 1 }
```

## TO-DO

- Validação de CPF (formato, somente números, unicidade)
- Integração com API da FIPE para preço do veículo
