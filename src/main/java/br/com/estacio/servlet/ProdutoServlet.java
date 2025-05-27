package br.com.estacio.servlet;

import br.com.estacio.dao.ProdutoDAO;
import br.com.estacio.model.Produto;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/produtos/*")
public class ProdutoServlet extends HttpServlet {

    private ProdutoDAO produtoDAO;

    @Override
    public void init() throws ServletException {
        produtoDAO = new ProdutoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();

        if (action == null || action.equals("/")) {
            listarProdutos(request, response);
        } else if (action.equals("/novo")) {
            mostrarFormularioNovo(request, response);
        } else if (action.equals("/editar")) {
            mostrarFormularioEditar(request, response);
        } else if (action.equals("/excluir")) {
            excluirProduto(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();

        if (action == null || action.equals("/")) {
            salvarProduto(request, response);
        } else if (action.equals("/atualizar")) {
            atualizarProduto(request, response);
        }
    }

    private void listarProdutos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Produto> produtos = produtoDAO.listarTodos();
        request.setAttribute("produtos", produtos);
        request.getRequestDispatcher("/WEB-INF/views/produto/lista.jsp")
                .forward(request, response);
    }

    private void mostrarFormularioNovo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/produto/form.jsp")
                .forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Produto produto = produtoDAO.buscarPorId(id);
        request.setAttribute("produto", produto);
        request.getRequestDispatcher("/WEB-INF/views/produto/form.jsp")
                .forward(request, response);
    }

    private void salvarProduto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Produto produto = new Produto();
        produto.setNome(request.getParameter("nome"));
        produto.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
        produto.setPrecoVenda(Float.parseFloat(request.getParameter("precoVenda")));

        produtoDAO.salvar(produto);
        response.sendRedirect(request.getContextPath() + "/produtos");
    }

    private void atualizarProduto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Produto produto = produtoDAO.buscarPorId(id);

        produto.setNome(request.getParameter("nome"));
        produto.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
        produto.setPrecoVenda(Float.parseFloat(request.getParameter("precoVenda")));

        produtoDAO.atualizar(produto);
        response.sendRedirect(request.getContextPath() + "/produtos");
    }

    private void excluirProduto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        produtoDAO.excluir(id);
        response.sendRedirect(request.getContextPath() + "/produtos");
    }

    @Override
    public void destroy() {
        produtoDAO.fechar();
    }
}