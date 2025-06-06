package br.com.estacio.server.model;

import jakarta.persistence.*;

@Entity
@Table(name = "PessoaJuridica")
public class PessoaJuridica {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "cnpj")
    private String cnpj;

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}