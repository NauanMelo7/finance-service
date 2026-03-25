Objetivo

Uma API backend para gestão de fluxo de caixa de pequenos negócios, permitindo controlar entradas, saídas, contas, categorias e transferências, com foco em consistência de dados e visão clara da movimentação financeira.

Escopo

- Cadastro de contas financeiras
- Cadastro de categorias de movimentação
- Registro de receitas e despesas
- Transferência entre contas
- Consulta de extrato por período
- Cálculo de saldo atual por conta
- Listagem de movimentações com filtros simples

Fora do escopo

- Autenticação complexa
- Integração com banco real
- Investimentos
- Parcelamentos avançado
- Multiusuário
- Frontend

Entidades

- Account
- Category
- Transaction
- Transfer
- Budget


Regras de negócio

Account
- Um usuário pode ter multiplas contas
- Uma conta pode ser criada com saldo inicial negativo.
- Uma conta inativa não pode haver novas transações.
- Não pode existir outra conta ativa com o mesmo nome para o mesmo usuário.
- Contas não são excluídas, apenas inativadas.

Category
- Uma categoria de despesa não pode ser usada em receita
- O tipo da categoria deve ser compatível com o tipo da transação.
- Uma categoria que possui transação não pode ser deletada.
- Não pode existir outra categoria ativa com o mesmo nome e tipo.

Transaction
- Valor da transação não pode ser <= 0
- Toda transação só sofrerá impacto no saldo uma vez marcada como paga.
- Não é possível alterar uma transação com status PAGO.
- O tipo da transação define o saldo: receita soma, despesa subtrai.
- A data da transação deve ser definida pelo usuario
- Toda transação precisa ser associada com a conta.
- Toda transação deve estar vinculada a uma conta existente e a uma categoria válida.
- Toda transação deve estar associada a uma categoria ativa.

Transfer 
- Transferência só é permitida de contas ativas
- O valor da transferência não pode ser <= 0
- A data é preenchida pelo usuário (Pois não temos integração com entidade bancária).
- A transferência deve ser: ou debita e credita com sucesso, ou não altera nenhuma conta.


Budget
- O valor não pode ser <= 0
- Não pode inserir categoria inativa
- Só pode existir um orçamento por categoria por período.
- O orçamento deve estar associado a uma categoria do tipo despesa.

