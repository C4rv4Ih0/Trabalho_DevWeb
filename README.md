<h1 align="center"> Projeto de Gerenciamento de Jogadores Mensalistas</h1>
<h2 align="center"> Cleberson de Carvalho, Ana Luiza Batistel Scorsim </h2>

<h1 align="center">🚀 Tecnologias Utilizadas</h1>
<p align="center">
  <a>Java 21</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a>SpringBoot</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a>Maven</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a>PostgreSQL</a>
</p>
<br> 
<br> 
<h2 align="center">💻Projeto</h2>
<p align="center">
API RESTful java usando SpringBoot, com funcionalidades CRUD (Criar, Ler, Atualizar e Excluir), para gerenciar jogadores mensalistas de jogos de futebol e seus pagamentos .<br/>
</p>
<br> 
<br> 

<h2 align="center">Funcionalidades</h2>

### Gerencia Jogadores

-   **Cria um Jogador**: Adiciona um novo jogador ao banco de dados.
-   **Busca um Jogador**: Permite encontrar jogadores pelo ID ou pelo nome.
-   **Lista todos os Jogadores existentes**: Retorna uma lista de todos os jogadores já cadastrados.
-   **Atualiza Jogador**: Atualiza as informações de um jogador existente.
-   **Exclui Jogador**: Remove um jogador do sistema (se ele tiver pagamentos, os pagamentos devm ser excluidos primeiro).

### Gerencia Pagamentos

-   **Cria um Pagamento**: Registra um novo pagamento para um jogador existente.
-   **Busca um Pagamento**: Retorna as informações de um pagamento específico pelo ID do pagamento e pelo id do jogador.
-   **Lista todos os Pagamentos**: Retorna uma lista de todos os pagamentos.
-   **Atualiza Pagamento**: Atualiza as informações de um pagamento existente.
-   **Exclui Pagamento**: Remove um pagamento do sistema.

<h1 align="center">Endpoint</h1>

### 1. Listar Jogadores

- **GET:**  `/jogador/`
    - Descrição: Retorna uma lista de todos os jogadores, se for passado nome como parametro, ele só irá retornar o jogador e seus pagamentos.

### 2. Obter Jogador por ID

- **GET:** `/jogador/{id}`
    - Descrição: Retorna um jogador específico e os seus dados através do seu ID.

### 3. Criar Jogador

- **POST:** `/jogador/`
    - Descrição: Cria um novo jogador, passando nome, email, e data de nascimento.

### 4. Atualizar Jogador

- **PUT:** `/jogador/{id}`
    - Descrição: Atualiza os dados de um jogador existente, passando nome, email e data de nascimento, com as modificações feitas.
   
### 5. Deletar Jogador por ID

- **DELETE:** `/jogador/{id}`
    - Descrição: Deleta um jogador pelo seu ID, caso o  jogador tenha realizado pagamentos, ele deverá excluir os pagamentos primeiro para que seja possível excluir o jogador..
    
### Rotas de Pagamentos

### 1. Listar Todos os Pagamentos

- **GET:** `/pagamento/`
    - Descrição: Retorna uma lista com todos os pagamentos.

### 2. Obter Pagamento por ID

- **GET:** `/pagamento/{id}`
    - Descrição: Retorna os detalhes de um pagamento específico passando o  ID do jogador como parâmetro.
   
### 3. Criar Pagamento

- **POST:** `/pagamento/`
    - Descrição: Cria um novo pagamento para um jogador, passando ano, mês, valor e o ID do jogador em questão.    

### 4. Atualizar Pagamento

- **PUT:** `/pagamento/{id}`
    - Descrição: Atualiza os dados de um pagamento.
    - JSON da atualização:
        
	      {   
	        "ano": 2024,   
	        "mes": 8,   
	        "valor": 200.00,   
	        "jogador": 
            {
	            "cod_jogador": 1,      
            } 
          }

### 5. Deletar Pagamento por ID

- **DELETE:** `/pagamento/{id}`
    - Descrição: Deleta um pagamento específico passando o ID do pagamento.
  
### 6. Deletar Todos os Pagamentos

- **DELETE:** `/pagamento/`
    - Descrição: Deleta todos os pagamentos registrados no banco, seja o mesmo de qualquer jogador.
 
