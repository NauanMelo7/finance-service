Visão do domínio

O sistema gerencia fluxo de caixa de pequenos negócios, permitindo controlar contas, categorias, receitas, despesas e transferências, com foco em consistência do saldo e rastreabilidade das movimentações.

Linguagem do domínio

Account: conta financeira onde o saldo é controlado
Category: classificação de uma movimentação
Transaction: lançamento financeiro de entrada ou saída
Transfer: movimentação entre duas contas do sistema
Budget: limite planejado de gasto por categoria e período
Saldo atual: resultado do saldo inicial + transações pagas + transferências

Entidades do domínio

- Account
id
name
initialBalance
active
createdAt
updatedAt

- Category
id
name
type
active
createdAt
updatedAt

- Transaction
id
type
status
amount
occurrenceDate
description
accountId
categoryId
createdAt

- Transfer
id
sourceAccountId
destinationAccountId
amount
occurrenceDate
description
createdAt

- Budget
id
categoryId
month
year
amount
createdAt
updatedAt

- Relacionamentos

uma Account pode possuir várias Transaction
uma Category pode estar associada a várias Transaction
uma Transfer sempre referencia uma conta de origem e uma conta de destino
um Budget pertence a uma Category

- Cálculos do domínio

O saldo atual de uma conta é calculado a partir de:
saldo inicial da conta
soma das transações do tipo receita com status PAGO
subtração das transações do tipo despesa com status PAGO
valores recebidos por transferências
valores enviados por transferências


- Ciclo de vida / transições
Transaction
PENDING -> PAID
PENDING -> CANCELED
PAID -> não pode ser editada
CANCELED -> não impacta saldo

- Decisões do domínio
saldo será calculado, não armazenado
exclusão física de contas não será permitida; apenas inativação
associação entre entidades será feita por id, não por nome
transferências impactam saldo imediatamente