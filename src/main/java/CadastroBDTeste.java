import cadastrobd.model.*;
import java.util.List;
import java.util.Scanner;

public class CadastroBDTeste {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PessoaFisicaDAO pfDAO = new PessoaFisicaDAO();
        PessoaJuridicaDAO pjDAO = new PessoaJuridicaDAO();
        int opcao = -1;
        while (opcao != 0) {
            try {
                System.out.println("\nMenu Cadastro:");
                System.out.println("1 - Incluir");
                System.out.println("2 - Alterar");
                System.out.println("3 - Excluir");
                System.out.println("4 - Exibir pelo id");
                System.out.println("5 - Exibir todos");
                System.out.println("0 - Sair");
                System.out.print("Escolha uma opção: ");
                opcao = Integer.parseInt(sc.nextLine());
                switch (opcao) {
                    case 1:
                        System.out.print("Tipo (F - Física, J - Jurídica): ");
                        String tipoInc = sc.nextLine().trim().toUpperCase();
                        if (tipoInc.equals("F")) {
                            PessoaFisica pf = new PessoaFisica();
                            System.out.print("Nome: ");
                            pf.setNome(sc.nextLine());
                            System.out.print("Logradouro: ");
                            pf.setLogradouro(sc.nextLine());
                            System.out.print("Cidade: ");
                            pf.setCidade(sc.nextLine());
                            System.out.print("Estado: ");
                            pf.setEstado(sc.nextLine());
                            System.out.print("Telefone: ");
                            pf.setTelefone(sc.nextLine());
                            System.out.print("Email: ");
                            pf.setEmail(sc.nextLine());
                            System.out.print("CPF: ");
                            pf.setCpf(sc.nextLine());
                            pfDAO.incluir(pf);
                            System.out.println("Pessoa física incluída!");
                        } else if (tipoInc.equals("J")) {
                            PessoaJuridica pj = new PessoaJuridica();
                            System.out.print("Nome: ");
                            pj.setNome(sc.nextLine());
                            System.out.print("Logradouro: ");
                            pj.setLogradouro(sc.nextLine());
                            System.out.print("Cidade: ");
                            pj.setCidade(sc.nextLine());
                            System.out.print("Estado: ");
                            pj.setEstado(sc.nextLine());
                            System.out.print("Telefone: ");
                            pj.setTelefone(sc.nextLine());
                            System.out.print("Email: ");
                            pj.setEmail(sc.nextLine());
                            System.out.print("CNPJ: ");
                            pj.setCnpj(sc.nextLine());
                            pjDAO.incluir(pj);
                            System.out.println("Pessoa jurídica incluída!");
                        } else {
                            System.out.println("Tipo inválido!");
                        }
                        break;
                    case 2:
                        System.out.print("Tipo (F - Física, J - Jurídica): ");
                        String tipoAlt = sc.nextLine().trim().toUpperCase();
                        System.out.print("ID: ");
                        int idAlt = Integer.parseInt(sc.nextLine());
                        if (tipoAlt.equals("F")) {
                            PessoaFisica pf = pfDAO.getPessoa(idAlt);
                            if (pf == null) {
                                System.out.println("Pessoa não encontrada!");
                                break;
                            }
                            System.out.println("Dados atuais:");
                            pf.exibir();
                            System.out.print("Novo nome: ");
                            pf.setNome(sc.nextLine());
                            System.out.print("Novo logradouro: ");
                            pf.setLogradouro(sc.nextLine());
                            System.out.print("Nova cidade: ");
                            pf.setCidade(sc.nextLine());
                            System.out.print("Novo estado: ");
                            pf.setEstado(sc.nextLine());
                            System.out.print("Novo telefone: ");
                            pf.setTelefone(sc.nextLine());
                            System.out.print("Novo email: ");
                            pf.setEmail(sc.nextLine());
                            System.out.print("Novo CPF: ");
                            pf.setCpf(sc.nextLine());
                            pfDAO.alterar(pf);
                            System.out.println("Pessoa física alterada!");
                        } else if (tipoAlt.equals("J")) {
                            PessoaJuridica pj = pjDAO.getPessoa(idAlt);
                            if (pj == null) {
                                System.out.println("Pessoa não encontrada!");
                                break;
                            }
                            System.out.println("Dados atuais:");
                            pj.exibir();
                            System.out.print("Novo nome: ");
                            pj.setNome(sc.nextLine());
                            System.out.print("Novo logradouro: ");
                            pj.setLogradouro(sc.nextLine());
                            System.out.print("Nova cidade: ");
                            pj.setCidade(sc.nextLine());
                            System.out.print("Novo estado: ");
                            pj.setEstado(sc.nextLine());
                            System.out.print("Novo telefone: ");
                            pj.setTelefone(sc.nextLine());
                            System.out.print("Novo email: ");
                            pj.setEmail(sc.nextLine());
                            System.out.print("Novo CNPJ: ");
                            pj.setCnpj(sc.nextLine());
                            pjDAO.alterar(pj);
                            System.out.println("Pessoa jurídica alterada!");
                        } else {
                            System.out.println("Tipo inválido!");
                        }
                        break;
                    case 3:
                        System.out.print("Tipo (F - Física, J - Jurídica): ");
                        String tipoExc = sc.nextLine().trim().toUpperCase();
                        System.out.print("ID: ");
                        int idExc = Integer.parseInt(sc.nextLine());
                        if (tipoExc.equals("F")) {
                            pfDAO.excluir(idExc);
                            System.out.println("Pessoa física excluída!");
                        } else if (tipoExc.equals("J")) {
                            pjDAO.excluir(idExc);
                            System.out.println("Pessoa jurídica excluída!");
                        } else {
                            System.out.println("Tipo inválido!");
                        }
                        break;
                    case 4:
                        System.out.print("Tipo (F - Física, J - Jurídica): ");
                        String tipoExb = sc.nextLine().trim().toUpperCase();
                        System.out.print("ID: ");
                        int idExb = Integer.parseInt(sc.nextLine());
                        if (tipoExb.equals("F")) {
                            PessoaFisica pf = pfDAO.getPessoa(idExb);
                            if (pf == null)
                                System.out.println("Pessoa não encontrada!");
                            else
                                pf.exibir();
                        } else if (tipoExb.equals("J")) {
                            PessoaJuridica pj = pjDAO.getPessoa(idExb);
                            if (pj == null)
                                System.out.println("Pessoa não encontrada!");
                            else
                                pj.exibir();
                        } else {
                            System.out.println("Tipo inválido!");
                        }
                        break;
                    case 5:
                        System.out.print("Tipo (F - Física, J - Jurídica): ");
                        String tipoTodos = sc.nextLine().trim().toUpperCase();
                        if (tipoTodos.equals("F")) {
                            List<PessoaFisica> lista = pfDAO.getPessoas();
                            for (PessoaFisica pf : lista) {
                                pf.exibir();
                                System.out.println("-------------------");
                            }
                        } else if (tipoTodos.equals("J")) {
                            List<PessoaJuridica> lista = pjDAO.getPessoas();
                            for (PessoaJuridica pj : lista) {
                                pj.exibir();
                                System.out.println("-------------------");
                            }
                        } else {
                            System.out.println("Tipo inválido!");
                        }
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        sc.close();
    }
}