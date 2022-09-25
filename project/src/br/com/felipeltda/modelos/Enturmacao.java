package br.com.felipeltda.modelos;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Enturmacao {
    private static int geradorCodigo;
    private int codigo;
    private final Aluno aluno;
    private LocalDate dataEnturmacao;
    private Turma turma;
    public int getCodigo() {
        return codigo;
    }

    @Override
    public String toString(){
        return "Codigo: "+this.getCodigo() + " Turma: "+ this.getTurma().getNome() + " Aluno: "+ this.getAluno().getNome()+ " Data Enturmacao: "+this.getDataEnturmacao();
    }


    public String getDataEnturmacao() {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dataEnturmacao.format(formatador);
    }

    public Enturmacao(Turma turma, Aluno aluno) {
        codigo = ++geradorCodigo;
        this.turma = turma;
        this.aluno = aluno;
        dataEnturmacao = LocalDate.now();
    }
    public Enturmacao(Aluno aluno) {
        this.aluno = aluno;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Turma getTurma() {
        return turma;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Enturmacao that)) return false;
        return Objects.equals(getAluno(), that.getAluno());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAluno());
    }

}
