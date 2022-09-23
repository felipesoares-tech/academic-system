package br.com.felipeltda.modelos;

import java.time.*;

public class Pessoa {
    protected String nome;
    protected String cpf;
    protected String telefone;
    protected String email;

    protected LocalDate dataNascimento;

    public Pessoa(){

    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {

        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}