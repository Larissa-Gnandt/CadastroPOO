# CadastroBD

Sistema de cadastro e movimentação de produtos, pessoas e usuários, com comunicação cliente-servidor em Java, persistência em MySQL e interface gráfica para mensagens.

## Descrição

Este projeto implementa um sistema de cadastro e movimentação de produtos, pessoas físicas e jurídicas, utilizando **Java**, **JPA (EclipseLink)** e **MySQL**. A comunicação entre cliente e servidor é feita via **sockets TCP**, com autenticação, listagem, entrada e saída de produtos, e interface gráfica Swing para exibição de mensagens.

## Funcionalidades

- Autenticação de usuários
- Listagem de produtos (apenas não deletados)
- Entrada e saída de produtos (movimentação)
- Atualização automática da quantidade de produtos
- Registro de movimentos (entradas/saídas)
- Interface gráfica Swing para exibição de mensagens e listas
- Cliente assíncrono (recebe mensagens do servidor em tempo real)

## Estrutura do Projeto

```
CadastroBD/
├── CadastroServer/      # Módulo principal (servidor e clientes)
│   ├── src/main/java/br/com/estacio/server/   # Servidor, threads e controladores
│   ├── src/main/java/br/com/estacio/client/   # Clientes (console e assíncrono)
│   ├── src/main/resources/META-INF/persistence.xml
│   └── sql/                                 # Scripts SQL para banco de dados
```

## Requisitos

- Java 17 ou superior
- Maven
- MySQL Server

## Como Executar

1. **Clone o repositório**
2. **Configure o banco de dados MySQL**  
   - Crie o banco e as tabelas usando o script `sql/schema.sql`
   - Insira dados de teste (usuário `op1`/`op1` e produtos)
3. **Atualize as credenciais no arquivo `persistence.xml`** se necessário
4. **Compile o projeto:**
   ```sh
   cd CadastroServer
   mvn clean package
   ```
5. **Execute o servidor:**
   ```sh
   java -jar target/CadastroServer-1.0-SNAPSHOT-jar-with-dependencies.jar
   ```
6. **Em outro terminal, execute o cliente assíncrono:**
   ```sh
   java -cp target/classes br.com.estacio.client.CadastroClientV2
   ```

## Uso do Sistema

- **Login:**  
  Use o usuário de teste:  
  - Login: `op1`  
  - Senha: `op1`

- **Menu de comandos (no console):**
  - `L` – Listar produtos
  - `E` – Entrada de produto
  - `S` – Saída de produto
  - `X` – Sair

- **As mensagens e listas do servidor aparecem na janela Swing "Saída do Servidor".**

## Banco de Dados

- Scripts para criação das tabelas: `sql/schema.sql`
- Estrutura das tabelas: `Usuario`, `Pessoa`, `PessoaFisica`, `PessoaJuridica`, `Produto`, `Movimento`
- Exemplo de inserção de usuário e produtos no banco:
  ```sql
  INSERT INTO Usuario (login, senha) VALUES ('op1', 'op1');
  INSERT INTO Produto (nome, quantidade, precoVenda, deletado) VALUES ('Laranja', 300, 2.00, 0);
  ```

## Tecnologias Utilizadas

- Java 17+
- Maven
- MySQL
- Jakarta Persistence (JPA) com EclipseLink
- Sockets TCP
- Swing (interface gráfica para mensagens)
