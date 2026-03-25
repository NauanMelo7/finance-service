API Contract

Base URL

/api

Formato de dados

- Request e response em JSON
- Datas no formato ISO-8601
- Valores monetários em decimal
- Todas as entidades serão identificadas por id

Convenções gerais

- GET para consulta
- POST para criação
- PUT para atualização
- PATCH para mudança parcial de estado
- DELETE não será usado para exclusão física de Account
- Recursos inativos continuam existindo para preservar histórico

Códigos de status esperados

- 200 OK -> operação realizada com sucesso
- 201 Created -> recurso criado com sucesso
- 204 No Content -> operação realizada sem corpo de resposta
- 400 Bad Request -> erro de validação ou regra de negócio
- 404 Not Found -> recurso não encontrado
- 409 Conflict -> conflito de estado ou duplicidade
- 500 Internal Server Error -> erro inesperado

Formato padrão de erro

{
  "timestamp": "2026-03-25T10:00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed",
  "path": "/api/accounts"
}

Enums do domínio

Account
- ACTIVE
- INACTIVE

CategoryType
- INCOME
- EXPENSE

TransactionType
- INCOME
- EXPENSE

TransactionStatus
- PENDING
- PAID
- CANCELED

Account

POST /accounts

Objetivo
Criar uma nova conta financeira.

Request
{
  "name": "Conta Principal",
  "initialBalance": 1000.00
}

Response 201
{
  "id": 1,
  "name": "Conta Principal",
  "initialBalance": 1000.00,
  "active": true,
  "createdAt": "2026-03-25T10:00:00",
  "updatedAt": "2026-03-25T10:00:00"
}

Erros possíveis
- 400 se o nome estiver vazio
- 409 se já existir outra conta ativa com o mesmo nome

GET /accounts

Objetivo
Listar todas as contas.

Response 200
[
  {
    "id": 1,
    "name": "Conta Principal",
    "initialBalance": 1000.00,
    "active": true,
    "createdAt": "2026-03-25T10:00:00",
    "updatedAt": "2026-03-25T10:00:00"
  }
]

GET /accounts/{id}

Objetivo
Buscar uma conta por id.

Response 200
{
  "id": 1,
  "name": "Conta Principal",
  "initialBalance": 1000.00,
  "active": true,
  "createdAt": "2026-03-25T10:00:00",
  "updatedAt": "2026-03-25T10:00:00"
}

Erros possíveis
- 404 se a conta não existir

PATCH /accounts/{id}/inactivate

Objetivo
Inativar uma conta.

Response 200
{
  "id": 1,
  "name": "Conta Principal",
  "initialBalance": 1000.00,
  "active": false,
  "createdAt": "2026-03-25T10:00:00",
  "updatedAt": "2026-03-25T11:00:00"
}

Erros possíveis
- 404 se a conta não existir
- 409 se a conta já estiver inativa

GET /accounts/{id}/balance

Objetivo
Consultar o saldo atual da conta.

Response 200
{
  "accountId": 1,
  "balance": 1350.00,
  "calculatedAt": "2026-03-25T12:00:00"
}

Erros possíveis
- 404 se a conta não existir

GET /accounts/{id}/statement?startDate=2026-03-01&endDate=2026-03-31

Objetivo
Consultar o extrato da conta por período.

Response 200
{
  "accountId": 1,
  "startDate": "2026-03-01",
  "endDate": "2026-03-31",
  "entries": [
    {
      "entryType": "TRANSACTION",
      "id": 10,
      "transactionType": "INCOME",
      "status": "PAID",
      "amount": 500.00,
      "occurrenceDate": "2026-03-10",
      "description": "Venda do dia"
    },
    {
      "entryType": "TRANSFER_OUT",
      "id": 3,
      "amount": 100.00,
      "occurrenceDate": "2026-03-15",
      "description": "Transferência para caixa"
    }
  ]
}

Erros possíveis
- 404 se a conta não existir
- 400 se o período for inválido

Category

POST /categories

Objetivo
Criar uma nova categoria.

Request
{
  "name": "Marketing",
  "type": "EXPENSE"
}

Response 201
{
  "id": 1,
  "name": "Marketing",
  "type": "EXPENSE",
  "active": true,
  "createdAt": "2026-03-25T10:00:00",
  "updatedAt": "2026-03-25T10:00:00"
}

Erros possíveis
- 400 se nome ou tipo não forem informados
- 409 se já existir outra categoria ativa com o mesmo nome e tipo

GET /categories

Objetivo
Listar categorias.

Response 200
[
  {
    "id": 1,
    "name": "Marketing",
    "type": "EXPENSE",
    "active": true,
    "createdAt": "2026-03-25T10:00:00",
    "updatedAt": "2026-03-25T10:00:00"
  }
]

GET /categories/{id}

Objetivo
Buscar categoria por id.

Response 200
{
  "id": 1,
  "name": "Marketing",
  "type": "EXPENSE",
  "active": true,
  "createdAt": "2026-03-25T10:00:00",
  "updatedAt": "2026-03-25T10:00:00"
}

Erros possíveis
- 404 se a categoria não existir

PATCH /categories/{id}/inactivate

Objetivo
Inativar uma categoria.

Response 200
{
  "id": 1,
  "name": "Marketing",
  "type": "EXPENSE",
  "active": false,
  "createdAt": "2026-03-25T10:00:00",
  "updatedAt": "2026-03-25T11:00:00"
}

Erros possíveis
- 404 se a categoria não existir
- 409 se a categoria já estiver inativa

Transaction

POST /transactions

Objetivo
Registrar uma nova transação.

Request
{
  "type": "EXPENSE",
  "amount": 120.00,
  "occurrenceDate": "2026-03-25",
  "description": "Internet",
  "accountId": 1,
  "categoryId": 2
}

Response 201
{
  "id": 1,
  "type": "EXPENSE",
  "status": "PENDING",
  "amount": 120.00,
  "occurrenceDate": "2026-03-25",
  "description": "Internet",
  "accountId": 1,
  "categoryId": 2,
  "createdAt": "2026-03-25T10:00:00"
}

Erros possíveis
- 400 se o valor for menor ou igual a zero
- 400 se a data não for informada
- 404 se a conta não existir
- 404 se a categoria não existir
- 409 se a conta estiver inativa
- 409 se a categoria estiver inativa
- 409 se o tipo da categoria for incompatível com o tipo da transação

GET /transactions

Objetivo
Listar transações com filtros simples.

Query params opcionais
- accountId
- categoryId
- type
- status
- startDate
- endDate

Exemplo
GET /transactions?accountId=1&type=EXPENSE&status=PENDING&startDate=2026-03-01&endDate=2026-03-31

Response 200
[
  {
    "id": 1,
    "type": "EXPENSE",
    "status": "PENDING",
    "amount": 120.00,
    "occurrenceDate": "2026-03-25",
    "description": "Internet",
    "accountId": 1,
    "categoryId": 2,
    "createdAt": "2026-03-25T10:00:00"
  }
]

GET /transactions/{id}

Objetivo
Buscar transação por id.

Response 200
{
  "id": 1,
  "type": "EXPENSE",
  "status": "PENDING",
  "amount": 120.00,
  "occurrenceDate": "2026-03-25",
  "description": "Internet",
  "accountId": 1,
  "categoryId": 2,
  "createdAt": "2026-03-25T10:00:00"
}

Erros possíveis
- 404 se a transação não existir

PUT /transactions/{id}

Objetivo
Editar uma transação pendente.

Request
{
  "type": "EXPENSE",
  "amount": 150.00,
  "occurrenceDate": "2026-03-25",
  "description": "Internet atualizada",
  "accountId": 1,
  "categoryId": 2
}

Response 200
{
  "id": 1,
  "type": "EXPENSE",
  "status": "PENDING",
  "amount": 150.00,
  "occurrenceDate": "2026-03-25",
  "description": "Internet atualizada",
  "accountId": 1,
  "categoryId": 2,
  "createdAt": "2026-03-25T10:00:00"
}

Erros possíveis
- 404 se a transação não existir
- 409 se a transação estiver PAID
- 409 se a transação estiver CANCELED
- 400 ou 409 para as mesmas validações de criação

PATCH /transactions/{id}/pay

Objetivo
Marcar uma transação pendente como paga.

Response 200
{
  "id": 1,
  "type": "EXPENSE",
  "status": "PAID",
  "amount": 150.00,
  "occurrenceDate": "2026-03-25",
  "description": "Internet atualizada",
  "accountId": 1,
  "categoryId": 2,
  "createdAt": "2026-03-25T10:00:00"
}

Erros possíveis
- 404 se a transação não existir
- 409 se a transação já estiver PAID
- 409 se a transação estiver CANCELED

PATCH /transactions/{id}/cancel

Objetivo
Cancelar uma transação pendente.

Response 200
{
  "id": 1,
  "type": "EXPENSE",
  "status": "CANCELED",
  "amount": 150.00,
  "occurrenceDate": "2026-03-25",
  "description": "Internet atualizada",
  "accountId": 1,
  "categoryId": 2,
  "createdAt": "2026-03-25T10:00:00"
}

Erros possíveis
- 404 se a transação não existir
- 409 se a transação já estiver PAID
- 409 se a transação já estiver CANCELED

Transfer

POST /transfers

Objetivo
Registrar uma transferência entre contas.

Request
{
  "sourceAccountId": 1,
  "destinationAccountId": 2,
  "amount": 300.00,
  "occurrenceDate": "2026-03-25",
  "description": "Movimentação para caixa"
}

Response 201
{
  "id": 1,
  "sourceAccountId": 1,
  "destinationAccountId": 2,
  "amount": 300.00,
  "occurrenceDate": "2026-03-25",
  "description": "Movimentação para caixa",
  "createdAt": "2026-03-25T10:00:00"
}

Erros possíveis
- 400 se o valor for menor ou igual a zero
- 400 se a data não for informada
- 404 se a conta de origem não existir
- 404 se a conta de destino não existir
- 409 se a conta de origem estiver inativa
- 409 se a conta de destino estiver inativa
- 409 se a conta de origem e destino forem a mesma

GET /transfers

Objetivo
Listar transferências.

Query params opcionais
- sourceAccountId
- destinationAccountId
- startDate
- endDate

Response 200
[
  {
    "id": 1,
    "sourceAccountId": 1,
    "destinationAccountId": 2,
    "amount": 300.00,
    "occurrenceDate": "2026-03-25",
    "description": "Movimentação para caixa",
    "createdAt": "2026-03-25T10:00:00"
  }
]

GET /transfers/{id}

Objetivo
Buscar transferência por id.

Response 200
{
  "id": 1,
  "sourceAccountId": 1,
  "destinationAccountId": 2,
  "amount": 300.00,
  "occurrenceDate": "2026-03-25",
  "description": "Movimentação para caixa",
  "createdAt": "2026-03-25T10:00:00"
}

Erros possíveis
- 404 se a transferência não existir

Budget

Observação
Se Budget ficar fora do MVP v1, esta seção pode ser removida ou movida para futura versão.

POST /budgets

Objetivo
Criar orçamento por categoria e período.

Request
{
  "categoryId": 2,
  "month": 3,
  "year": 2026,
  "amount": 500.00
}

Response 201
{
  "id": 1,
  "categoryId": 2,
  "month": 3,
  "year": 2026,
  "amount": 500.00,
  "createdAt": "2026-03-25T10:00:00",
  "updatedAt": "2026-03-25T10:00:00"
}

Erros possíveis
- 400 se o valor for menor ou igual a zero
- 404 se a categoria não existir
- 409 se a categoria estiver inativa
- 409 se a categoria não for do tipo EXPENSE
- 409 se já existir orçamento para a mesma categoria no mesmo período

GET /budgets?categoryId=2&month=3&year=2026

Objetivo
Consultar orçamento por categoria e período.

Response 200
{
  "id": 1,
  "categoryId": 2,
  "month": 3,
  "year": 2026,
  "amount": 500.00,
  "createdAt": "2026-03-25T10:00:00",
  "updatedAt": "2026-03-25T10:00:00"
}

Erros possíveis
- 404 se não existir orçamento para os filtros informados

Pontos em aberto

- Definir se haverá paginação na listagem de accounts, categories, transactions e transfers
- Definir se statement retornará saldo parcial por linha
- Definir se budgets terão endpoint de atualização na v1
- Definir se categorias poderão ser apenas inativadas, sem delete físico