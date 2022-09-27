package br.com.felipeltda.modelos;

import java.time.*;
import java.util.Objects;

public class Pessoa{
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

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pessoa pessoa)) return false;
        return Objects.equals(cpf, pessoa.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }

}