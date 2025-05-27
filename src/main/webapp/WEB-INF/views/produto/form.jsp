<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>${produto == null ? 'Novo Produto' : 'Editar Produto'}</title>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        </head>

        <body>
            <div class="container mt-4">
                <h2>${produto == null ? 'Novo Produto' : 'Editar Produto'}</h2>

                <form action="${pageContext.request.contextPath}/produtos${produto == null ? '' : '/atualizar'}"
                    method="post" class="needs-validation" novalidate>

                    <c:if test="${produto != null}">
                        <input type="hidden" name="id" value="${produto.id}">
                    </c:if>

                    <div class="mb-3">
                        <label for="nome" class="form-label">Nome:</label>
                        <input type="text" class="form-control" id="nome" name="nome" value="${produto.nome}" required>
                        <div class="invalid-feedback">
                            Por favor, informe o nome do produto.
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="quantidade" class="form-label">Quantidade:</label>
                        <input type="number" class="form-control" id="quantidade" name="quantidade"
                            value="${produto.quantidade}" required min="0">
                        <div class="invalid-feedback">
                            Por favor, informe a quantidade do produto.
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="precoVenda" class="form-label">Preço de Venda:</label>
                        <input type="number" class="form-control" id="precoVenda" name="precoVenda"
                            value="${produto.precoVenda}" required min="0" step="0.01">
                        <div class="invalid-feedback">
                            Por favor, informe o preço de venda do produto.
                        </div>
                    </div>

                    <button type="submit" class="btn btn-primary">
                        ${produto == null ? 'Salvar' : 'Atualizar'}
                    </button>

                    <a href="${pageContext.request.contextPath}/produtos" class="btn btn-secondary">
                        Cancelar
                    </a>
                </form>
            </div>

            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
            <script>
                // Validação do formulário
                (function () {
                    'use strict'
                    var forms = document.querySelectorAll('.needs-validation')
                    Array.prototype.slice.call(forms).forEach(function (form) {
                        form.addEventListener('submit', function (event) {
                            if (!form.checkValidity()) {
                                event.preventDefault()
                                event.stopPropagation()
                            }
                            form.classList.add('was-validated')
                        }, false)
                    })
                })()
            </script>
        </body>

        </html>