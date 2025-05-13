import model.PessoaFisica;
import model.PessoaFisicaRepo;
import model.PessoaJuridica;
import model.PessoaJuridicaRepo;

import java.io.IOException;

public class TesteRepositorios {
    public static void main(String[] args) {
        try {
            System.out.println("=== Teste de Pessoas Físicas ===");

            PessoaFisicaRepo repo1 = new PessoaFisicaRepo();
            repo1.inserir(new PessoaFisica(1, "João Silva", "123.456.789-00", 30));
            repo1.inserir(new PessoaFisica(2, "Maria Santos", "987.654.321-00", 25));

            repo1.persistir("pessoas_fisicas.bin");
            System.out.println("\nDados de Pessoas Físicas Armazenados:");

            PessoaFisicaRepo repo2 = new PessoaFisicaRepo();
            repo2.recuperar("pessoas_fisicas.bin");

            System.out.println("\nDados de Pessoas Físicas Recuperados:");
            repo2.obterTodos().forEach(PessoaFisica::exibir);

            System.out.println("\n=== Teste de Pessoas Jurídicas ===");

            PessoaJuridicaRepo repo3 = new PessoaJuridicaRepo();
            repo3.inserir(new PessoaJuridica(1, "Empresa A", "12.345.678/0001-90"));
            repo3.inserir(new PessoaJuridica(2, "Empresa B", "98.765.432/0001-10"));

            repo3.persistir("pessoas_juridicas.bin");
            System.out.println("\nDados de Pessoas Jurídicas Armazenados:");

            PessoaJuridicaRepo repo4 = new PessoaJuridicaRepo();
            repo4.recuperar("pessoas_juridicas.bin");

            System.out.println("\nDados de Pessoas Jurídicas Recuperados:");
            repo4.obterTodos().forEach(PessoaJuridica::exibir);

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao testar repositórios: " + e.getMessage());
        }
    }
}