import model.PessoaFisica;
import model.PessoaFisicaRepo;
import model.PessoaJuridica;
import model.PessoaJuridicaRepo;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final PessoaFisicaRepo repoFisica = new PessoaFisicaRepo();
    private static final PessoaJuridicaRepo repoJuridica = new PessoaJuridicaRepo();

    public static void main(String[] args) {
        int opcao;
        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            try {
                processarOpcao(opcao);
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Erro ao processar operação: " + e.getMessage());
            }
        } while (opcao != 0);
    }

    private static void exibirMenu() {
        System.out.println("\n=== Menu de Cadastro ===");
        System.out.println("1 - Incluir");
        System.out.println("2 - Alterar");
        System.out.println("3 - Excluir");
        System.out.println("4 - Exibir por ID");
        System.out.println("5 - Exibir todos");
        System.out.println("6 - Salvar dados");
        System.out.println("7 - Recuperar dados");
        System.out.println("0 - Finalizar");
        System.out.print("Escolha uma opção: ");
    }

    private static void processarOpcao(int opcao) throws IOException, ClassNotFoundException {
        switch (opcao) {
            case 1 -> incluir();
            case 2 -> alterar();
            case 3 -> excluir();
            case 4 -> exibirPorId();
            case 5 -> exibirTodos();
            case 6 -> salvarDados();
            case 7 -> recuperarDados();
            case 0 -> System.out.println("Programa finalizado.");
            default -> System.out.println("Opção inválida!");
        }
    }

    private static void incluir() {
        System.out.println("\nTipo de pessoa:");
        System.out.println("1 - Pessoa Física");
        System.out.println("2 - Pessoa Jurídica");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        if (tipo == 1) {
            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("CPF: ");
            String cpf = scanner.nextLine();
            System.out.print("Idade: ");
            int idade = scanner.nextInt();
            scanner.nextLine();

            PessoaFisica pf = new PessoaFisica(id, nome, cpf, idade);
            repoFisica.inserir(pf);
            System.out.println("Pessoa física cadastrada com sucesso!");
        } else if (tipo == 2) {
            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("CNPJ: ");
            String cnpj = scanner.nextLine();

            PessoaJuridica pj = new PessoaJuridica(id, nome, cnpj);
            repoJuridica.inserir(pj);
            System.out.println("Pessoa jurídica cadastrada com sucesso!");
        }
    }

    private static void alterar() {
        System.out.println("\nTipo de pessoa:");
        System.out.println("1 - Pessoa Física");
        System.out.println("2 - Pessoa Jurídica");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        System.out.print("ID da pessoa a ser alterada: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (tipo == 1) {
            PessoaFisica pf = repoFisica.obter(id);
            if (pf != null) {
                System.out.println("Dados atuais:");
                pf.exibir();
                System.out.print("Novo nome: ");
                String nome = scanner.nextLine();
                System.out.print("Novo CPF: ");
                String cpf = scanner.nextLine();
                System.out.print("Nova idade: ");
                int idade = scanner.nextInt();
                scanner.nextLine();

                pf.setNome(nome);
                pf.setCpf(cpf);
                pf.setIdade(idade);
                repoFisica.alterar(pf);
                System.out.println("Pessoa física alterada com sucesso!");
            } else {
                System.out.println("Pessoa física não encontrada!");
            }
        } else if (tipo == 2) {
            PessoaJuridica pj = repoJuridica.obter(id);
            if (pj != null) {
                System.out.println("Dados atuais:");
                pj.exibir();
                System.out.print("Novo nome: ");
                String nome = scanner.nextLine();
                System.out.print("Novo CNPJ: ");
                String cnpj = scanner.nextLine();

                pj.setNome(nome);
                pj.setCnpj(cnpj);
                repoJuridica.alterar(pj);
                System.out.println("Pessoa jurídica alterada com sucesso!");
            } else {
                System.out.println("Pessoa jurídica não encontrada!");
            }
        }
    }

    private static void excluir() {
        System.out.println("\nTipo de pessoa:");
        System.out.println("1 - Pessoa Física");
        System.out.println("2 - Pessoa Jurídica");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        System.out.print("ID da pessoa a ser excluída: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (tipo == 1) {
            repoFisica.excluir(id);
            System.out.println("Pessoa física excluída com sucesso!");
        } else if (tipo == 2) {
            repoJuridica.excluir(id);
            System.out.println("Pessoa jurídica excluída com sucesso!");
        }
    }

    private static void exibirPorId() {
        System.out.println("\nTipo de pessoa:");
        System.out.println("1 - Pessoa Física");
        System.out.println("2 - Pessoa Jurídica");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        System.out.print("ID da pessoa: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (tipo == 1) {
            PessoaFisica pf = repoFisica.obter(id);
            if (pf != null) {
                pf.exibir();
            } else {
                System.out.println("Pessoa física não encontrada!");
            }
        } else if (tipo == 2) {
            PessoaJuridica pj = repoJuridica.obter(id);
            if (pj != null) {
                pj.exibir();
            } else {
                System.out.println("Pessoa jurídica não encontrada!");
            }
        }
    }

    private static void exibirTodos() {
        System.out.println("\nTipo de pessoa:");
        System.out.println("1 - Pessoa Física");
        System.out.println("2 - Pessoa Jurídica");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        if (tipo == 1) {
            System.out.println("\nPessoas Físicas:");
            repoFisica.obterTodos().forEach(PessoaFisica::exibir);
        } else if (tipo == 2) {
            System.out.println("\nPessoas Jurídicas:");
            repoJuridica.obterTodos().forEach(PessoaJuridica::exibir);
        }
    }

    private static void salvarDados() throws IOException {
        System.out.print("Digite o prefixo para os arquivos: ");
        String prefixo = scanner.nextLine();

        repoFisica.persistir(prefixo + ".fisica.bin");
        repoJuridica.persistir(prefixo + ".juridica.bin");
        System.out.println("Dados salvos com sucesso!");
    }

    private static void recuperarDados() throws IOException, ClassNotFoundException {
        System.out.print("Digite o prefixo dos arquivos: ");
        String prefixo = scanner.nextLine();

        repoFisica.recuperar(prefixo + ".fisica.bin");
        repoJuridica.recuperar(prefixo + ".juridica.bin");
        System.out.println("Dados recuperados com sucesso!");
    }
} 