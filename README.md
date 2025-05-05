# Desafio Back-End Kotlin

Este projeto é uma API REST desenvolvida em Kotlin utilizando o framework Quarkus. Ele simula operações bancárias, incluindo gerenciamento de usuários, contas, agências, bancos e transações financeiras.

## Tecnologias Utilizadas

- **Kotlin**: Linguagem de programação principal.
- **Quarkus**: Framework para desenvolvimento de aplicações Java/Kotlin.
- **MySQL**: Banco de dados relacional utilizado.
- **JWT (JSON Web Tokens)**: Autenticação e autorização.
- **Jakarta RESTful Web Services**: Para criação das APIs REST.
- **Jakarta Validation**: Validação de dados de entrada.
- **Jakarta Security**: Controle de acesso baseado em funções.

## Configuração do Banco de Dados

Certifique-se de ter o MySQL instalado e em execução. Configure as credenciais de acesso ao banco de dados no arquivo `application.properties` ou `application.yml`:

```properties
quarkus.datasource.db-kind=mysql
quarkus.datasource.username=seu_usuario
quarkus.datasource.password=sua_senha
quarkus.datasource.jdbc.url=jdbc:mysql://localhost:3306/nome_do_banco
```

## Executando a Aplicação em Modo de Desenvolvimento

Para iniciar a aplicação em modo de desenvolvimento com suporte a live coding:

```bash
./gradlew quarkusDev
```

A interface de desenvolvimento estará disponível em: [http://localhost:8080/q/dev/](http://localhost:8080/q/dev/)

## Empacotando e Executando a Aplicação

Para empacotar a aplicação:

```bash
./gradlew build
```

O arquivo gerado estará em `build/quarkus-app/quarkus-run.jar`. Para executá-lo:

```bash
java -jar build/quarkus-app/quarkus-run.jar
```

## Endpoints da API

### Autenticação

- `POST /auth/register`: Registro de novo usuário.
- `POST /auth/login`: Autenticação de usuário e geração de token JWT.

### Usuários

- `GET /usuario/all`: Listar todos os usuários. *(Acesso: admin)*
- `GET /usuario/{id}`: Obter detalhes de um usuário específico. *(Acesso: admin)*
- `PUT /usuario/{id}`: Atualizar informações de um usuário. *(Acesso: admin)*
- `DELETE /usuario/{id}`: Remover um usuário. *(Acesso: admin)*

### Bancos

- `POST /banco/novo`: Criar um novo banco. *(Acesso: admin)*
- `GET /banco/all`: Listar todos os bancos. *(Acesso: admin)*
- `GET /banco/{id}`: Obter detalhes de um banco específico. *(Acesso: admin)*
- `PUT /banco/{id}`: Atualizar informações de um banco. *(Acesso: admin)*
- `DELETE /banco/{id}`: Remover um banco. *(Acesso: admin)*

### Agências

- `POST /agencia/novo`: Criar uma nova agência. *(Acesso: admin)*
- `GET /agencia/all`: Listar todas as agências. *(Acesso: admin)*
- `GET /agencia/{id}`: Obter detalhes de uma agência específica. *(Acesso: admin)*
- `PUT /agencia/{id}`: Atualizar informações de uma agência. *(Acesso: admin)*
- `DELETE /agencia/{id}`: Remover uma agência. *(Acesso: admin)*

### Contas

- `POST /conta/novo`: Criar uma nova conta. *(Acesso: admin)*
- `GET /conta/all`: Listar todas as contas. *(Acesso: admin)*
- `GET /conta/minhas`: Listar contas do usuário autenticado. *(Acesso: user)*
- `GET /conta/{id}`: Obter detalhes de uma conta específica. *(Acesso: admin)*
- `GET /conta/{number}`: Obter detalhes de uma conta pelo número. *(Acesso: user)*
- `PUT /conta/{id}`: Atualizar informações de uma conta. *(Acesso: admin)*
- `DELETE /conta/{id}`: Remover uma conta. *(Acesso: admin)*

### Transações

- `POST /transacao/transferencia`: Realizar uma transferência entre contas. *(Acesso: user)*
- `POST /transacao/saque`: Realizar um saque. *(Acesso: user)*
- `POST /transacao/deposito`: Realizar um depósito. *(Acesso: user)*
- `GET /transacao/all`: Listar todas as transações. *(Acesso: admin)*
- `DELETE /transacao/{id}`: Remover uma transação. *(Acesso: admin)*

## Autenticação e Autorização

A aplicação utiliza JWT para autenticação. Após o login, um token é fornecido e deve ser incluído no cabeçalho `Authorization` das requisições subsequentes:

```
Authorization: Bearer seu_token_jwt
```

As rotas são protegidas por roles:

- `admin`: Acesso total às funcionalidades.
- `user`: Acesso restrito às funcionalidades relacionadas às suas próprias contas e transações.

