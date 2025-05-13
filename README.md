# CadastroPOO

Sistema de cadastro de pessoas físicas e jurídicas em modo texto, com persistência em banco de dados MySQL, desenvolvido em Java.

## Descrição

Este projeto implementa um sistema de cadastro de pessoas físicas e jurídicas, utilizando conceitos de **Programação Orientada a Objetos** como **herança** e **polimorfismo**. O sistema realiza a persistência dos dados diretamente em um banco de dados MySQL, utilizando o padrão DAO (Data Access Object).

## Funcionalidades

- Cadastro de pessoas físicas e jurídicas
- Alteração de cadastros existentes
- Exclusão de cadastros
- Consulta por ID
- Listagem de todos os cadastros

## Estrutura do Projeto

```
src/
├── main/
│   └── java/
│       ├── CadastroBDTeste.java
│       └── cadastrobd/
│           └── model/
│               ├── Pessoa.java
│               ├── PessoaFisica.java
│               ├── PessoaJuridica.java
│               ├── PessoaFisicaDAO.java
│               ├── PessoaJuridicaDAO.java
│               └── util/
│                   └── ConectorBD.java
```

## Requisitos

- Java JDK 17 ou superior
- Maven
- MySQL Server

## Como Executar

1. Clone o repositório
2. Navegue até o diretório do projeto
3. Certifique-se de que o banco de dados MySQL está rodando e configurado conforme o arquivo `schema.sql`
4. Atualize as credenciais de acesso ao banco no arquivo `ConectorBD.java` se necessário
5. Execute o comando abaixo para compilar e rodar o sistema com o menu interativo:
   ```bash
   mvn compile exec:java -Dexec.mainClass="CadastroBDTeste"
   ```

## Uso do Sistema

O sistema apresenta um menu interativo com as seguintes opções:

1. **Incluir** - Adiciona uma nova pessoa física ou jurídica
2. **Alterar** - Modifica os dados de uma pessoa existente
3. **Excluir** - Remove uma pessoa do cadastro
4. **Exibir por id** - Consulta uma pessoa específica
5. **Exibir todos** - Lista todas as pessoas cadastradas
0. **Sair** - Encerra o programa

## Persistência de Dados

A persistência dos dados é realizada diretamente em um banco de dados MySQL, utilizando o padrão DAO (Data Access Object) para todas as operações de cadastro, alteração, exclusão e consulta.

## Banco de Dados

O sistema utiliza MySQL para persistência dos dados. O script SQL com a estrutura do banco de dados está disponível no arquivo `schema.sql` na raiz do projeto (ou na pasta `sql/`, se preferir organizar assim).

### Configuração do Banco de Dados

1. Certifique-se de ter o MySQL instalado e rodando.
2. No terminal, navegue até a pasta onde está o arquivo `schema.sql`.
3. Execute o script SQL para criar as tabelas necessárias:
   ```bash
   mysql -u seu_usuario -p < schema.sql
   ```
4. Atualize as credenciais de acesso ao banco no arquivo `ConectorBD.java` do projeto, se necessário.

### Estrutura do Banco de Dados

O banco de dados contém as seguintes tabelas:
- `Usuario` - Armazena dados de usuários do sistema (login, senha, perfil)
- `Pessoa` - Tabela base com informações comuns a todos os tipos de pessoas
- `PessoaFisica` - Armazena dados específicos de pessoas físicas (CPF)
- `PessoaJuridica` - Armazena dados específicos de pessoas jurídicas (CNPJ)
- `Produto` - Cadastro de produtos disponíveis no sistema
- `Movimento` - Registra as operações realizadas no sistema

## Tecnologias Utilizadas

- Java 17
- Maven
- MySQL
- Programação Orientada a Objetos
- Padrão DAO
