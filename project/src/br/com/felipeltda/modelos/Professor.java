package br.com.felipeltda.modelos;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Professor extends Pessoa{

    private final int registro;
    private static int geradorCodigo;
    public Professor(String nome, String telefone, String cpf, String email, LocalDate dataNascimento){
        this.nome = nome;
        this.telefone = telefone;
        this.registro = ++geradorCodigo;
        this.cpf = cpf;
        this.email = email;
        this.dataNascimento = dataNascimento;

    }

    public Professor(int registro){
        this.registro = registro;
    }
    public int getRegistro() {
        return registro;
    }

    public String exibirDados(){
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "nome="+nome+" cpf="+cpf+" telefone="+telefone+" email="+email+" registro="+registro+" data de nascimento="+dataNascimento.format(formatador);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Professor professor)) return false;
        return getRegistro() == professor.getRegistro();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRegistro());
    }

    @Override
    public String toString() {
        return nome;
    }
}