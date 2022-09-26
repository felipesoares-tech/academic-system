package br.com.felipeltda.modelos;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Aluno extends Pessoa{
    private final int matricula;
    protected String cpf;
    private static int geradorCodigo;
    public Aluno(String nome, String telefone, String cpf, String email, LocalDate dataNascimento){
        this.telefone = telefone;
        this.nome = nome;
        this.matricula = ++geradorCodigo;
        this.cpf = cpf;
        this.email = email;
        this.dataNascimento = dataNascimento;

    }
    public Aluno(int matricula){
        this.matricula = matricula;
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
        if (!(o instanceof Aluno aluno)) return false;
        return Objects.equals(getCpf(), aluno.getCpf());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getCpf());
    }
}