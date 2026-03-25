Use cases

UC-01 - Cadastrar conta

Objetivo
Permitir o cadastro de uma nova conta financeira no sistema.

Atores
Usuário do sistema

Pré-condições
Nenhuma

Fluxo principal

O usuário informa o nome da conta.
O usuário informa o saldo inicial da conta.
O sistema valida se já existe outra conta ativa com o mesmo nome.
O sistema cria a conta como ativa.
O sistema registra a data de criação.

Fluxos alternativos

Se já existir outra conta ativa com o mesmo nome, o cadastro deve ser rejeitado.
O saldo inicial pode ser negativo.

Resultado esperado
A conta é cadastrada com sucesso e fica disponível para uso em transações e transferências.

UC-02 - Inativar conta

Objetivo
Permitir que uma conta deixe de aceitar novas movimentações sem perder seu histórico.

Atores
Usuário do sistema

Pré-condições
A conta deve existir

Fluxo principal

O usuário seleciona uma conta existente.
O usuário solicita a inativação da conta.
O sistema altera o status da conta para inativa.
O sistema registra a data de atualização.

Fluxos alternativos

Se a conta não existir, a operação deve ser rejeitada.

Resultado esperado
A conta permanece no sistema, mas não pode receber novas transações nem participar de novas transferências.

UC-03 - Cadastrar categoria

Objetivo
Permitir o cadastro de categorias para classificar movimentações financeiras.

Atores
Usuário do sistema

Pré-condições
Nenhuma

Fluxo principal

O usuário informa o nome da categoria.
O usuário informa o tipo da categoria.
O sistema valida se já existe outra categoria ativa com o mesmo nome e tipo.
O sistema cria a categoria como ativa.
O sistema registra a data de criação.

Fluxos alternativos

Se já existir outra categoria ativa com o mesmo nome e tipo, o cadastro deve ser rejeitado.

Resultado esperado
A categoria é cadastrada com sucesso e pode ser utilizada em transações compatíveis com seu tipo.

UC-04 - Inativar categoria

Objetivo
Permitir que uma categoria deixe de ser usada em novas transações.

Atores
Usuário do sistema

Pré-condições
A categoria deve existir

Fluxo principal

O usuário seleciona uma categoria existente.
O usuário solicita a inativação da categoria.
O sistema altera o status da categoria para inativa.
O sistema registra a data de atualização.

Fluxos alternativos

Se a categoria não existir, a operação deve ser rejeitada.

Resultado esperado
A categoria deixa de poder ser usada em novas transações, mas continua existindo para manter o histórico.

UC-05 - Registrar transação

Objetivo
Permitir o registro de uma receita ou despesa vinculada a uma conta e a uma categoria.

Atores
Usuário do sistema

Pré-condições

A conta deve existir e estar ativa.
A categoria deve existir e estar ativa.
O tipo da categoria deve ser compatível com o tipo da transação.

Fluxo principal

O usuário informa o tipo da transação.
O usuário informa o valor da transação.
O usuário informa a data de ocorrência.
O usuário informa a conta.
O usuário informa a categoria.
O usuário informa a descrição, se desejar.
O sistema valida as regras da transação.
O sistema salva a transação com status inicial PENDING.
O sistema registra a data de criação.

Fluxos alternativos

Se o valor for menor ou igual a zero, o registro deve ser rejeitado.
Se a conta não existir ou estiver inativa, o registro deve ser rejeitado.
Se a categoria não existir ou estiver inativa, o registro deve ser rejeitado.
Se o tipo da categoria for incompatível com o tipo da transação, o registro deve ser rejeitado.

Resultado esperado
A transação é registrada no sistema, mas ainda não impacta o saldo enquanto estiver com status PENDING.

UC-06 - Marcar transação como paga

Objetivo
Permitir que uma transação pendente passe a impactar o saldo da conta.

Atores
Usuário do sistema

Pré-condições

A transação deve existir.
A transação deve estar com status PENDING.

Fluxo principal

O usuário seleciona uma transação pendente.
O usuário solicita a alteração de status para PAID.
O sistema valida se a transação pode ser alterada.
O sistema altera o status da transação para PAID.
O sistema considera a transação no cálculo do saldo.

Fluxos alternativos

Se a transação não existir, a operação deve ser rejeitada.
Se a transação já estiver com status PAID, a operação deve ser rejeitada.
Se a transação estiver com status CANCELED, a operação deve ser rejeitada.

Resultado esperado
A transação passa a impactar o saldo da conta conforme seu tipo: receita soma, despesa subtrai.

UC-07 - Cancelar transação

Objetivo
Permitir que uma transação pendente seja cancelada antes de impactar o saldo.

Atores
Usuário do sistema

Pré-condições

A transação deve existir.
A transação deve estar com status PENDING.

Fluxo principal

O usuário seleciona uma transação pendente.
O usuário solicita o cancelamento.
O sistema altera o status da transação para CANCELED.

Fluxos alternativos

Se a transação já estiver com status PAID, a operação deve ser rejeitada.
Se a transação já estiver com status CANCELED, a operação deve ser rejeitada.

Resultado esperado
A transação permanece registrada para histórico, mas não impacta o saldo.

UC-08 - Editar transação pendente

Objetivo
Permitir ajuste de dados de uma transação antes do pagamento.

Atores
Usuário do sistema

Pré-condições

A transação deve existir.
A transação deve estar com status PENDING.

Fluxo principal

O usuário seleciona uma transação pendente.
O usuário altera os campos permitidos.
O sistema valida novamente as regras da transação.
O sistema salva as alterações.

Fluxos alternativos

Se a transação estiver com status PAID, a edição deve ser rejeitada.
Se a transação estiver com status CANCELED, a edição deve ser rejeitada.
Se algum dado novo violar as regras do domínio, a edição deve ser rejeitada.

Resultado esperado
A transação é atualizada com sucesso sem impactar o saldo enquanto permanecer pendente.

UC-09 - Registrar transferência entre contas

Objetivo
Permitir a movimentação de valor entre duas contas do sistema.

Atores
Usuário do sistema

Pré-condições

A conta de origem deve existir e estar ativa.
A conta de destino deve existir e estar ativa.
A conta de origem e a conta de destino devem ser diferentes.

Fluxo principal

O usuário informa a conta de origem.
O usuário informa a conta de destino.
O usuário informa o valor da transferência.
O usuário informa a data da ocorrência.
O usuário informa a descrição, se desejar.
O sistema valida as regras da transferência.
O sistema registra a transferência.
O sistema aplica o débito na conta de origem e o crédito na conta de destino na mesma operação.

Fluxos alternativos

Se o valor for menor ou igual a zero, a operação deve ser rejeitada.
Se a conta de origem não existir ou estiver inativa, a operação deve ser rejeitada.
Se a conta de destino não existir ou estiver inativa, a operação deve ser rejeitada.
Se a conta de origem e a conta de destino forem a mesma, a operação deve ser rejeitada.
Se houver falha durante a operação, nenhuma alteração deve ser persistida.

Resultado esperado
A transferência é registrada com sucesso e o saldo das duas contas é atualizado de forma atômica.

UC-10 - Consultar extrato por período

Objetivo
Permitir a visualização das movimentações financeiras de uma conta em determinado intervalo de datas.

Atores
Usuário do sistema

Pré-condições
A conta deve existir

Fluxo principal

O usuário informa a conta.
O usuário informa a data inicial e a data final.
O sistema busca as transações e transferências da conta dentro do período informado.
O sistema retorna a lista de movimentações em ordem cronológica.

Fluxos alternativos

Se a conta não existir, a consulta deve ser rejeitada.
Se o período informado for inválido, a consulta deve ser rejeitada.

Resultado esperado
O usuário consegue visualizar o histórico financeiro da conta no período solicitado.

UC-11 - Consultar saldo atual da conta

Objetivo
Permitir a visualização do saldo atual de uma conta com base nas regras do domínio.

Atores
Usuário do sistema

Pré-condições
A conta deve existir

Fluxo principal

O usuário informa a conta.
O sistema recupera o saldo inicial da conta.
O sistema soma as transações do tipo receita com status PAID.
O sistema subtrai as transações do tipo despesa com status PAID.
O sistema soma os valores recebidos por transferências.
O sistema subtrai os valores enviados por transferências.
O sistema retorna o saldo atual calculado.

Fluxos alternativos

Se a conta não existir, a consulta deve ser rejeitada.

Resultado esperado
O usuário recebe o saldo atual calculado da conta, sem necessidade de armazenamento físico do saldo.

UC-12 - Listar transações com filtros simples

Objetivo
Permitir a consulta de transações aplicando filtros básicos.

Atores
Usuário do sistema

Pré-condições
Nenhuma

Fluxo principal

O usuário informa um ou mais filtros disponíveis.
O sistema aplica os filtros informados.
O sistema retorna as transações compatíveis com os critérios.

Filtros previstos

conta
categoria
tipo
status
período

Fluxos alternativos

Se nenhum filtro for informado, o sistema pode retornar todas as transações paginadas ou dentro de um limite padrão.
Se algum filtro for inválido, a consulta deve ser rejeitada.

Resultado esperado
O usuário consegue localizar transações específicas de forma simples.

UC-13 - Criar orçamento por categoria

Objetivo
Permitir o planejamento de limite de gasto por categoria e período.

Atores
Usuário do sistema

Pré-condições

A categoria deve existir e estar ativa.
A categoria deve ser do tipo despesa.

Fluxo principal

O usuário informa a categoria.
O usuário informa o mês e o ano de referência.
O usuário informa o valor do orçamento.
O sistema valida se já existe orçamento para a mesma categoria no mesmo período.
O sistema salva o orçamento.

Fluxos alternativos

Se o valor for menor ou igual a zero, a operação deve ser rejeitada.
Se a categoria estiver inativa, a operação deve ser rejeitada.
Se a categoria não for do tipo despesa, a operação deve ser rejeitada.
Se já existir orçamento para a mesma categoria no mesmo período, a operação deve ser rejeitada.

Resultado esperado
O orçamento é criado e fica disponível para consulta futura.

UC-14 - Consultar orçamento por categoria e período

Objetivo
Permitir a consulta do orçamento definido para uma categoria em um período específico.

Atores
Usuário do sistema

Pré-condições
O orçamento deve existir

Fluxo principal

O usuário informa a categoria.
O usuário informa o mês e o ano.
O sistema localiza o orçamento correspondente.
O sistema retorna os dados do orçamento.

Fluxos alternativos

Se não existir orçamento para a categoria e período informados, o sistema deve informar que não há resultado.

Resultado esperado
O usuário consegue visualizar o valor planejado para determinada categoria no período informado.
