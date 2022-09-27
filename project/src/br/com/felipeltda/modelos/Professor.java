package br.com.felipeltda.modelos;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Professor extends Pessoa{

    private int registro;
    private static int geradorCodigo;
    public Professor(String nome, String telefone, String cpf, String email, LocalDate dataNascimento){
        this.nome = nome;
        this.telefone = telefone;
        this.registro = ++geradorCodigo;
        this.cpf = cpf;
        this.email = email;
        this.dataNascimento = dataNascimento;

    }

    public Professor(String cpf){
        this.cpf = cpf;
    }
    public int getRegistro() {
        return registro;
    }

    public String exibirDados(){
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "nome="+nome+" cpf="+cpf+" telefone="+telefone+" email="+email+" registro="+registro+" data de nascimento="+dataNascimento.format(formatador);
    }

    @Override
    public String toString() {
        return nome;
    }
}