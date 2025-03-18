# CadastroPOO

Sistema de cadastro de clientes em modo texto, com persistência em arquivos, desenvolvido em Java.

## Descrição

Este projeto implementa um sistema de cadastro de clientes que permite gerenciar pessoas físicas e jurídicas. O sistema utiliza conceitos de **Programação Orientada a Objetos** como **herança**, **polimorfismo** e **persistência de dados em arquivos binários**.

## Funcionalidades

- Cadastro de pessoas físicas e jurídicas
- Alteração de cadastros existentes
- Exclusão de cadastros
- Consulta por ID
- Listagem de todos os cadastros
- Persistência dos dados em arquivos binários
- Recuperação de dados de arquivos binários

## Estrutura do Projeto

```
src/
├── main/
│   └── java/
│       ├── Main.java
│       ├── TesteRepositorios.java
│       └── model/
│           ├── Pessoa.java
│           ├── PessoaFisica.java
│           ├── PessoaJuridica.java
│           ├── PessoaFisicaRepo.java
│           └── PessoaJuridicaRepo.java
```

## Requisitos

- Java JDK 17 ou superior
- Maven

## Como Executar

1. Clone o repositório
2. Navegue até o diretório do projeto
3. Execute o comando:
   ```bash
   mvn clean package
   ```
4. Execute o programa:
   ```bash
   java -jar target/CadastroPOO-1.0-SNAPSHOT.jar
   ```

## Uso do Sistema

O sistema apresenta um menu interativo com as seguintes opções:

1. **Incluir** - Adiciona uma nova pessoa física ou jurídica
2. **Alterar** - Modifica os dados de uma pessoa existente
3. **Excluir** - Remove uma pessoa do cadastro
4. **Exibir por ID** - Consulta uma pessoa específica
5. **Exibir todos** - Lista todas as pessoas cadastradas
6. **Salvar dados** - Persiste os dados em arquivos binários
7. **Recuperar dados** - Carrega os dados dos arquivos binários
8. **Finalizar** - Encerra o programa

## Persistência de Dados

Os dados são salvos em arquivos binários com os seguintes formatos:

- `[prefixo].fisica.bin` - Arquivo para pessoas físicas
- `[prefixo].juridica.bin` - Arquivo para pessoas jurídicas

## Tecnologias Utilizadas

- Java 17
- Maven
- Programação Orientada a Objetos
- Serialização de objetos
- Manipulação de arquivos binários
