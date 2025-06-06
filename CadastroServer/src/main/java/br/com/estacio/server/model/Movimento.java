package br.com.estacio.server.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Movimento")
public class Movimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMovimento")
    private Integer idMovimento;

    @Column(name = "idUsuario")
    private Integer idUsuario;

    @Column(name = "idPessoa")
    private Integer idPessoa;

    @Column(name = "idProduto")
    private Integer idProduto;

    @Column(name = "quantidade")
    private Integer quantidade;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "valorUnitario")
    private Double valorUnitario;

    // Getters e Setters
    public Integer getIdMovimento() {
        return idMovimento;
    }

    public void setIdMovimento(Integer idMovimento) {
        this.idMovimento = idMovimento;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
}