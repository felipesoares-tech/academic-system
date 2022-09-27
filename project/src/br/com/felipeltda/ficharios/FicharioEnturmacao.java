package br.com.felipeltda.ficharios;
import br.com.felipeltda.modelos.*;

import java.util.ArrayList;
import java.util.Objects;
import  java.util.Scanner;

public class FicharioEnturmacao {
    private final ArrayList<Turma> turmas;
    private final ArrayList<Aluno> alunos;
    private final ArrayList<Enturmacao> enturmacoes;
    private final Scanner entrada;

    public FicharioEnturmacao(ArrayList<Turma> turmas, ArrayList<Aluno> alunos) {
        this.turmas = turmas;
        this.alunos = alunos;
        entrada = new Scanner(System.in);
        enturmacoes = new ArrayList<>();
    }

    private Turma buscaNomeTurma(String nome) {
        for (Turma turma : turmas) {
            if ((turma != null) && (Objects.equals(turma.getNome(), nome))) {
                return turma;
            }
        }
        return null;
    }

    private Turma buscaTurmaDoAluno(Aluno aluno) {
        for (Turma turma : turmas) {
            if (turma.getAlunos().contains(aluno))
                return turma;
        }
        return null;
    }

    protected Aluno buscaNomeAluno(String nome) {
        for (Aluno aluno : alunos) {
            if ((aluno != null) && (Objects.equals(aluno.getNome(), nome))) {
                return aluno;
            }
        }
        return null;
    }

    protected boolean alunoVinculado(Aluno aluno) {
        for (Turma turma : turmas) {
            if (turma.getAlunos().contains(aluno))
                return true;
        }
        return false;
    }

    private String buscaEnturmacao(Aluno aluno){
        Enturmacao enturmacao = new Enturmacao(aluno);
        if(enturmacoes.contains(enturmacao))
            return enturmacoes.get(enturmacoes.indexOf(enturmacao)).toString();
        else
            return "Enturmacao nao encontrada, O aluno informado nao foi enturmado";

    }

    private String buscaEnturmacao(int index){
        return enturmacoes.get(index).toString();
    }

    protected boolean alunoExiste(Aluno aluno) {
        return alunos.contains(aluno);
    }

    public void vincular() {
        System.out.println(" === Vincular Aluno Turma === ");

        System.out.println("Nome da turma: ");
        String nomeTurma = entrada.nextLine();

        System.out.println("Nome do aluno: ");
        String nomeAluno = entrada.nextLine();

        Turma turma = buscaNomeTurma(nomeTurma);
        Aluno aluno = buscaNomeAluno(nomeAluno);

        if (turma != null && aluno != null) {
            if (!alunoVinculado(aluno)){
                turma.getAlunos().add(aluno);
                Enturmacao enturmacao = new Enturmacao(turma,aluno);
                enturmacoes.add(enturmacao);
                aluno.setSituacaoAluno(SituacaoAluno.matriculado);
                System.out.println("Aluno vinculado com sucesso!");
            }
            else
                System.out.println("O Aluno informado ja esta vinculado a uma turma");
        } else
            System.out.println("Turma ou aluno inexistente!!");

    }

    public void desvincular() {
        System.out.println(" --==[Desvincular Aluno]==-- ");

        System.out.println("Digite o nome do aluno: ");
        String nomeAluno = entrada.nextLine();
        Aluno aluno = buscaNomeAluno(nomeAluno);

        if (alunoExiste(aluno)) {
            if (alunoVinculado(aluno)) {
                Turma turmaAluno = buscaTurmaDoAluno(aluno);
                System.out.println("Tem certeza que deseja desvincular o aluno? (1-sim) e (2-não) ");
                int resp = Integer.parseInt(entrada.nextLine());
                if (resp == 1) {
                    assert turmaAluno != null;
                    turmaAluno.getAlunos().remove(aluno);

                    Enturmacao enturmacao = new Enturmacao(aluno);
                    enturmacoes.remove(enturmacao);
                    aluno.setSituacaoAluno(SituacaoAluno.naoMatriculado);
                    System.out.println("Aluno desvinculado com sucesso!");
                } else
                    System.out.println("Operacao cancelada!");
            } else
                System.out.println("Aluno ja esta desvinculado!");

        } else
            System.out.println("Aluno inexistente!!");

    }

    public void consultar() {
        System.out.println(" --==[Consultar Enturmação]==-- ");
        System.out.println("1 - (Consulta por indice)");
        System.out.println("2 - (Consulta por aluno)");
        System.out.print("Opcao: ");
        int opcao = entrada.nextInt();
        entrada.skip("\n");

        if(opcao == 1){
            System.out.println("Qual a posição do vetor deseja consultar? ");
            int index = entrada.nextInt();
            entrada.skip("\n");
            try{
                System.out.println(buscaEnturmacao(index));
            }catch (IndexOutOfBoundsException e){
                System.out.println("Posição inválida!");
            }

        }else if(opcao == 2){
            System.out.println("Informe o nome do aluno o qual deseja consultar: ");
            String nomeAluno = entrada.nextLine();
            Aluno aluno = buscaNomeAluno(nomeAluno);
            System.out.println(buscaEnturmacao(aluno));
        }
    }


    public void relatorio() {

        System.out.println("[Relatório de ENTURMACOES]");

        for (Enturmacao enturmacao : enturmacoes) {
            System.out.println(enturmacao);
            System.out.println("---------------------");
        }
    }

}