package br.com.felipeltda.modelos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Aluno extends Pessoa{
    private String matricula;
    public Aluno(String nome, String telefone, String matricula, String cpf, String email, LocalDate dataNascimento){
        this.telefone = telefone;
        this.nome = nome;
        this.matricula = matricula;
        this.cpf = cpf;
        this.email = email;
        this.dataNascimento = dataNascimento;

    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    public String getMatricula() {
        return matricula;
    }
    @Override
    public String toString() {
        return nome;
    }
    public String exibirDados(){
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "nome="+nome+" cpf="+cpf+" telefone="+telefone+" email="+email+" matricula="+matricula + " data de nascimento="+dataNascimento.format(formatador);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aluno aluno = (Aluno) o;
        return Objects.equals(matricula, aluno.matricula);
    }
    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }

}