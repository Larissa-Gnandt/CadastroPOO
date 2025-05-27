package br.com.estacio.cadastroee.servlets;

import br.com.estacio.cadastroee.controller.ProdutoFacadeLocal;
import br.com.estacio.cadastroee.model.Produto;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ServletProdutoFC", urlPatterns = { "/ServletProdutoFC" })
public class ServletProdutoFC extends HttpServlet {

    @EJB
    private ProdutoFacadeLocal facade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getParameter("acao");
        String destino = "ProdutoLista.jsp";

        if (acao != null) {
            switch (acao) {
                case "listar":
                    List<Produto> produtos = facade.findAll();
                    request.setAttribute("produtos", produtos);
                    break;

                case "formIncluir":
                    destino = "ProdutoDados.jsp";
                    break;

                case "incluir":
                    Produto novoProduto = new Produto();
                    novoProduto.setNome(request.getParameter("nome"));
                    novoProduto.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
                    novoProduto.setPrecoVenda(Float.parseFloat(request.getParameter("precoVenda")));
                    facade.create(novoProduto);
                    request.setAttribute("produtos", facade.findAll());
                    break;

                case "formAlterar":
                    Integer id = Integer.parseInt(request.getParameter("id"));
                    Produto produto = facade.find(id);
                    request.setAttribute("produto", produto);
                    destino = "ProdutoDados.jsp";
                    break;

                case "alterar":
                    Produto produtoAlterado = new Produto();
                    produtoAlterado.setId(Integer.parseInt(request.getParameter("id")));
                    produtoAlterado.setNome(request.getParameter("nome"));
                    produtoAlterado.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
                    produtoAlterado.setPrecoVenda(Float.parseFloat(request.getParameter("precoVenda")));
                    facade.edit(produtoAlterado);
                    request.setAttribute("produtos", facade.findAll());
                    break;

                case "excluir":
                    Integer idExcluir = Integer.parseInt(request.getParameter("id"));
                    Produto produtoExcluir = facade.find(idExcluir);
                    facade.remove(produtoExcluir);
                    request.setAttribute("produtos", facade.findAll());
                    break;
            }
        }

        request.getRequestDispatcher(destino).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Front Controller para Produto";
    }
}