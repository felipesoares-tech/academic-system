package br.com.felipeltda.modelos;

import java.util.ArrayList;
import java.util.Objects;

public class Turma {
    private static int geradorCodigo;
    private final int codigo;
    private String nome;
    private final ArrayList<Aluno> alunos;

    public void decrementoGeradorCodigo() {
        geradorCodigo -= 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Turma turma)) return false;
        return getNome().equals(turma.getNome());
    }


    @Override
    public int hashCode() {
        return Objects.hash(getNome());
    }

    public ArrayList<Aluno> getAlunos() {
        return alunos;
    }

    public Turma(String nome){
        this.nome = nome;
        this.codigo = ++geradorCodigo;
        alunos = new ArrayList<>();
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "nome="+nome+" codigo="+codigo;
    }
}