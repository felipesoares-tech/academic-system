package br.com.felipeltda.ficharios;
import br.com.felipeltda.modelos.*;

import java.util.ArrayList;
import java.util.Objects;
import  java.util.Scanner;

public class OpAlunoTurmas {
    private final ArrayList<Turma> turmas;
    private final ArrayList<Aluno> alunos;
    private final Scanner entrada;

    public OpAlunoTurmas(ArrayList<Turma> turmas, ArrayList<Aluno> alunos) {
        this.turmas = turmas;
        this.alunos = alunos;
        entrada = new Scanner(System.in);
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

    private boolean alunoVinculado(Turma turma, Aluno aluno) {
        return turma.getAlunos().contains(aluno);
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
                    System.out.println("Aluno desvinculado com sucesso!");
                } else
                    System.out.println("Operacao cancelada!");
            } else
                System.out.println("Aluno ja esta desvinculado!");

        } else
            System.out.println("Aluno inexistente!!");

    }

    public void alterar() {
        System.out.println(" === Alterar ENTURMACAO ==== ");
        System.out.println("Informe o nome da turma: ");
        String nomeTurma = entrada.nextLine();
        Turma turma = buscaNomeTurma(nomeTurma);

        if (turma != null) {
            System.out.println("Informe o nome do aluno que deseja alterar: ");
            String nomeAluno = entrada.nextLine();

            Aluno aluno = buscaNomeAluno(nomeAluno);

            if (aluno != null) {
                if (alunoVinculado(turma, aluno)) {
                    System.out.println("Informe o nome do novo aluno: ");
                    String nomeNovoAluno = entrada.nextLine();
                    Aluno novoAluno = buscaNomeAluno(nomeNovoAluno);
                    if (novoAluno != null) {
                        if (!alunoVinculado(novoAluno)) {
                            turma.getAlunos().remove(aluno);
                            turma.getAlunos().add(novoAluno);
                            System.out.println("Alteração realizada com sucesso!");
                        } else
                            System.out.println("Aluno informado já vinculado a uma turma, necessário desvincula-lo antes!");

                    } else
                        System.out.println("Aluno inexistente!");
                } else
                    System.out.println("Aluno nao pertence a esta turma");
            } else
                System.out.println("Aluno inexistente!");
        }else
            System.out.println("Turma inexistente");
    }
}