# Xubank

O XuBank é uma fintech emergente que busca um sistema modular e confiável para administrar as contas de seus clientes. Desenvolva um sistema de informação aplicando conceitos avançados de programação orientada a objetos aprendidos até hoje na disciplina. Dessa forma, seu sistema deve controlar as contas dos clientes, descontando mensalmente as taxas, impostos e coisas do tipo. O nosso banco não pode levar prejuízo, por exemplo,  permitindo operações sem descontar as taxas e impostos. 

## Requisitos

### Clientes
1. Atributos: nome, CPF e senha.
2. Tipos de cliente: regular, gold e vip.
3. Todos os clientes podem possuir múltiplos tipos de contas

#### Operações Permitidas para Clientes Regulares:

- Consulta de saldo: Pode consultar o saldo de qualquer uma de suas contas a qualquer momento.
- Consulta de extrato: Pode consultar o extrato das suas transações dos últimos 30 dias.
- Depósito: Pode depositar qualquer quantia em suas contas.
- Saque: Pode sacar até o limite de saldo disponível em sua conta-corrente. Para a conta poupança, o saque é limitado 
  ao saldo atual. Para contas de renda fixa e investimento, os saques podem incorrer em penalidades ou impostos.
- Transferências: Pode realizar transferências entre suas contas e para contas de outros clientes, desde que não 
  ultrapasse o saldo disponível.

### Tipos de Conta

1. **Conta Corrente**:
   - Taxa mensal exclusiva de R$20.
   - Saque especial até R$200 além do saldo.
2. **Poupança**:
   - Sem taxa mensal.
   -  Rendimento fixo de 0,5% no dia 5 de cada mês.
3. **Renda fixa**:
   - Sem taxa mensal.
   - Rendimento contratado no momento da criação ( veja a explicação no final).
   - Imposto de 15% sobre o rendimento no saque.
4. **Investimento**:
   - Rendimento diário (positivo ou negativo). Veja a explicação no final do documento.
   - Imposto de 15% e taxa de 1,5% sobre o rendimento no saque.
### Fidelidade de Cliente

1. **Clientes Gold**: Taxa mensal de R$10. Acumulam 10 pontos de fidelidade mensalmente e 10 pontos para cada R$1.
   000 de 
saldo.
2. **Clientes Vip**: Mensalidade de R$30. Acumulam 35 pontos mensalmente e 30 pontos a cada R$2.000 de saldo.
3. **Sistema de Recompensas**: Troca de pontos acumulados por prêmios, descontos em serviços ou conversão em dinheiro.

### Visão de Diretoria

1. Total em custódia para cada tipo de conta.
2. Saldo médio de todas as contas.
3. Número de clientes com saldo total negativo.
4. Clientes com maior e menor saldo total.


### Detalhes e dicas

#### Rendimento contratado no momento da criação

"Rendimento contratado no momento da criação" refere-se à taxa de retorno ou juro acordado entre o banco e o cliente no momento em que a conta de renda fixa é criada. Ao contrário de contas de investimento cujo rendimento pode variar diariamente, as contas de renda fixa têm uma taxa de juro pré-determinada.

Por exemplo:

Suponha que um cliente decide abrir uma conta de renda fixa no XuBank e, naquele momento, o banco está oferecendo um rendimento de 5% ao ano para essa modalidade. Esse 5% ao ano é o "rendimento contratado no momento da criação". Assim, independentemente das variações do mercado financeiro, o cliente receberá 5% de rendimento sobre o capital investido ao longo do ano.

#### Rendimento diário (positivo ou negativo).

"Rendimento diário (positivo ou negativo)" refere-se à variação diária no valor ou retorno de um investimento, que pode ser tanto positiva (ganho) quanto negativa (perda). Esse conceito é frequentemente aplicado a investimentos de mercado variável, onde o valor do investimento não é fixo e pode flutuar com base em diversos fatores de mercado:

1. **Rendimento Diário Positivo**: Se o valor de seu investimento aumenta em um determinado dia, você teve um 
   rendimento diário positivo. Por exemplo, se você investiu R$1.000 em um fundo de investimento e, no final do dia, o valor de seu investimento subiu para R$1.010, você teve um rendimento positivo de 1% naquele dia.

2. **Rendimento Diário Negativo**: Se o valor de seu investimento diminui em um determinado dia, você teve um 
   rendimento diário negativo. Usando o exemplo anterior, se o valor de seu investimento cai para R$990 no final do dia, você teve uma perda ou rendimento negativo de 1% naquele dia.

_Exemplo_:

**Dia 1**:

João abre uma "Conta de Investimento" no XuBank com um depósito inicial de R$10.000.
Ele decide investir em um fundo que acompanha o desempenho de um índice da bolsa de valores.


**Dia 2**:

O índice da bolsa de valores teve uma boa performance, subindo 2%.
O investimento de João na "Conta de Investimento" também sobe 2%.
Novo saldo: R$10.000 + 2% de R$10.000 = R$10.200
Rendimento diário positivo de R$200.


**Dia 3**:

O mercado tem um dia ruim, e o índice da bolsa cai 3%.
O investimento de João reflete essa queda, reduzindo seu saldo em 3%.
Novo saldo: R$10.200 - 3% de R$10.200 = R$9.894
Rendimento diário negativo de R$306.


**Dia 4**:

O mercado se recupera levemente, com o índice da bolsa subindo 1%.
O investimento de João também sobe 1%.
Novo saldo: R$9.894 + 1% de R$9.894 = R$9.992,94
Rendimento diário positivo de R$98,94.


Neste exemplo, João teve dois dias de rendimentos positivos e um dia de rendimento negativo. É importante observar que, em investimentos de mercado variável (como a "Conta de Investimento" neste exemplo), o valor do principal pode tanto crescer quanto diminuir, dependendo da performance dos ativos subjacentes. João, como investidor, deve estar ciente da volatilidade e dos riscos associados a esse tipo de investimento e fazer escolhas informadas sobre onde colocar seu dinheiro. Por isso, sempre é recomendado que investidores consultem especialistas ou assessores financeiros antes de tomar decisões importantes sobre investimentos.

**Instruções**:

Use herança para criar classes base para Cliente e Conta, e subclasses para tipos específicos.
Implemente interfaces para garantir que todas as contas possuam os métodos necessários.
Aplique polimorfismo onde for relevante.
Desenvolva um menu no terminal para operações.
Elabore um diagrama de classes.
Assegure a encapsulação dos dados.
Mantenha boas práticas da Programação Modular.

**Entrega**:

Trabalho em grupo de 3 a 5 pessoas, com colaboração ativa na escrita do código-fonte.
Repositório no GitHub: Submeta o link no sistema Canvas com código-fonte e diagrama de classes.
Commits: Todos no grupo devem realizar commits, evidenciando sua contribuição. Commits após a data de entrega não serão aceitos.
O diagrama de classes precisa estar no repositório.











