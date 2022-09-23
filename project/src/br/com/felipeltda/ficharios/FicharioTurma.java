package br.com.felipeltda.ficharios;
import br.com.felipeltda.modelos.Turma;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class FicharioTurma {
    private final ArrayList<Turma> turmas;
    private final Scanner entrada;

    public FicharioTurma(ArrayList<Turma> turmas){
        this.turmas = turmas;
        entrada = new Scanner(System.in);
    }

    private Turma busca(){
        Turma turma = null;

        System.out.println("[1] - Por Nome");
        System.out.println("[2] - Por Codigo");
        short opcao = entrada.nextShort();
        entrada.skip("\n");

        switch (opcao) {
            case 1 -> {
                System.out.println("Nome: ");
                String nome = entrada.nextLine();
                turma = buscaNome(nome);
            }
            case 2 -> {
                System.out.println("Codigo: ");
                int codigo = entrada.nextShort();
                turma = buscaCodigo(codigo);
                entrada.skip("\n");
            }
            default -> System.out.println("Opcao invalida!!");
        }

        return turma;
    }

    private Turma buscaNome(String nome){
        for (Turma turma : turmas) {
            if ((turma != null) && (Objects.equals(turma.getNome().toLowerCase(), nome.toLowerCase()))) {
                return turma;
            }
        }
        return null;
    }
    private Turma buscaCodigo(int codigo){
        for (Turma turma : turmas) {
            if ((turma != null) && (turma.getCodigo() == codigo)) {
                return turma;
            }
        }
        return null;
    }
    public void consultar(){
        System.out.println(" === Consultar TURMAS ==== ");
        System.out.println("===Turmas Cadastradas===");
        System.out.println(turmas);
        Turma turma = busca();
        if(turma != null){
            System.out.println(turma);
            System.out.println(turma.getAlunos());
        }
    }

    public void relatorio() {
        System.out.println("[Relatório de TURMAS]");
        for (Turma turma : turmas) {
            if (turma != null) {
                System.out.println(turma);
            }
        }
    }

    private boolean nomeUtilizado(String nomeTurma){
        for (Turma turma : turmas) {
            if (Objects.equals(turma.getNome(), nomeTurma))
                return true;
        }
        return false;
    }

    public void cadastrar(){
        System.out.println(" === Cadastrar TURMA ==== ");
        System.out.print("Nome: ");
        String nomeTurma = entrada.nextLine();

        Turma turma = new Turma(nomeTurma);
        if(!turmas.contains(turma)){
            turmas.add(turma);
        }else{
            turma.decrementoGeradorCodigo();
            System.out.println("Turma ja cadastrado!");
        }
    }

    private boolean possuiAluno(Turma turma){
        return turma.getAlunos().size() > 0;
    }

    public void excluir(){
        System.out.println(" === Excluir TURMAS ==== ");
        System.out.println("===Turmas Cadastradas===");
        System.out.println(turmas);
        Turma turma = busca();

        try{
            if(!possuiAluno(turma)){
                System.out.println("Confirma a exclusão? (1-sim) e (2-não) ");
                int resposta = entrada.nextInt();
                if(resposta == 1){
                    turmas.remove(turma);
                    System.out.println("Exclusão efetuada com sucesso!");
                    entrada.skip("\n");
                    return;
                }
                System.out.println("Exclusão não efetuada.");
            }else
                System.out.println("A Turma informada possui alunos, nao foi possivel excluir!");
        }catch (NullPointerException e){
            System.out.println("Turma inexistente!!");
        }

    }

    public void alterar(){
        System.out.println(" === Alterar TURMAS ==== ");
        System.out.println("===Turmas Cadastradas===");
        System.out.println(turmas);

        Turma turma = busca();
        if(turma != null){
            System.out.println(turma);
            System.out.println("Informe um novo nome para a turma: ");
            String novoNomeTurma = entrada.nextLine();

            if(!nomeUtilizado(novoNomeTurma)){
                turma.setNome(novoNomeTurma);
                System.out.println("Nome alterado com sucesso!!");
                return;
            }
            System.out.println("O Nome informado já está sendo utilizado!");
        }else
            System.out.println("Turma nao encontrada!!");
    }
}