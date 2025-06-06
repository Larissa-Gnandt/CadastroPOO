package br.com.estacio.server.model;

import jakarta.persistence.*;

@Entity
@Table(name = "PessoaFisica")
public class PessoaFisica {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "cpf")
    private String cpf;

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}