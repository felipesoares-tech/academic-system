package br.com.felipeltda.ficharios;
import br.com.felipeltda.modelos.Aluno;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class FicharioAluno {
    private final ArrayList<Aluno> alunos;
    private final Scanner entrada;
    private final OpAlunoTurmas opAlunoTurmas;

    public FicharioAluno(ArrayList<Aluno> alunos, OpAlunoTurmas opAlunoTurmas){
        this.alunos = alunos;
        entrada = new Scanner(System.in);
        this.opAlunoTurmas = opAlunoTurmas;
    }
    private Aluno buscaMatricula(String matricula){
        Aluno aluno = new Aluno(matricula);
        if(alunos.contains(aluno))
            return alunos.get(alunos.indexOf(aluno));

        return null;
    }
    private Aluno buscaNome(String nome){
        for (Aluno aluno : alunos) {
            if ((aluno != null) && (Objects.equals(aluno.getNome().toLowerCase(), nome.toLowerCase()))) {
                return aluno;
            }
        }
        return null;
    }
    private Aluno buscaCpf(String cpf){
        for (Aluno aluno : alunos) {
            if ((aluno != null) && (Objects.equals(aluno.getCpf(), cpf))) {
                return aluno;
            }
        }
        return null;
    }
    private Aluno busca(){
        System.out.println("===TIPO DE BUSCA===");
        System.out.println("[1] - Por Nome");
        System.out.println("[2] - Por Cpf");
        System.out.println("[3] - Por Matricula");
        short opcao = entrada.nextShort();
        entrada.skip("\n");

        Aluno aluno =  null;
        switch (opcao) {
            case 1 -> {
                System.out.print("Nome: ");
                String nomeAluno = entrada.nextLine();
                aluno = buscaNome(nomeAluno);
            }
            case 2 -> {
                System.out.print("Cpf: ");
                String cpf = entrada.nextLine();
                aluno = buscaCpf(cpf);
            }
            case 3 -> {
                System.out.print("Matricula: ");
                String numeroMatricula = entrada.nextLine();
                aluno = buscaMatricula(numeroMatricula);
            }
            default -> System.out.println("Opcao invalida!!");
        }
        return aluno;
    }

    public void consultar(){
        System.out.println(" === Consultar ALUNO ==== ");
        System.out.println("===Alunos Cadastrados===");
        System.out.println(alunos);
        Aluno aluno = busca();
        System.out.println(aluno != null ? aluno.exibirDados() : "Cadastro nao encontrado!!");
    }

    public void excluir(){
        System.out.println("===Alunos Cadastrados===");
        System.out.println(alunos);
        Aluno aluno = busca();

        try{
            if(opAlunoTurmas.alunoVinculado(aluno))
                System.out.println("Nao foi possivel excluir o aluno em questao, pois o mesmo esta vinculado a uma turma");
            else{
                System.out.println("Confirma a exclusão? (1-sim) e (2-não) ");
                int resposta = entrada.nextInt();
                entrada.skip("\n");
                if(resposta == 1){
                    alunos.remove(aluno);
                    System.out.println("Exclusão efetuada com sucesso!");
                    return;
                }
                System.out.println("Exclusão não efetuada.");
            }
        }catch (NullPointerException e){
            System.out.println("Aluno inexistente!!");
        }
    }

    public void cadastrar(){
        System.out.println(" === Cadastrar ALUNO ==== ");
        System.out.print("Nome: ");
        String nome = entrada.nextLine();
        System.out.print("Matrícula: ");
        String matricula = entrada.nextLine();
        System.out.print("Telefone: ");
        String telefone = entrada.nextLine();
        System.out.print("CPF: ");
        String cpf = entrada.nextLine();
        System.out.print("E-mail: ");
        String email = entrada.nextLine();

        System.out.print("Informe a data de nascimento (dd/mm/yy): ");
        String dataNascimento = entrada.nextLine();

        DateTimeFormatter formatador;
        LocalDate data=LocalDate.now();

        try{
            formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            data = LocalDate.parse(dataNascimento, formatador);
        }catch (DateTimeException e){
            System.out.println("Data no formato invalido");
        }

        Aluno aluno = new Aluno(nome,telefone,matricula,cpf,email,data);

        if(!alunos.contains(aluno)){
            alunos.add(aluno);
            return;
        }
        System.out.println("Aluno ja cadastrado!");
    }

    public void alterar(){
        System.out.println(" === Alterar ALUNO ==== ");
        System.out.println("===Alunos Cadastrados===");
        System.out.println(alunos);

        Aluno aluno = busca();
        if(aluno != null){
            System.out.println(aluno.exibirDados());
            System.out.println("Escolha o item a editar!");
            System.out.println("[1] - Matricula");
            System.out.println("[2] - Nome");
            System.out.println("[3] - Cpf");
            System.out.println("[4] - Telefone");
            System.out.println("[5] - E-mail");
            short opcao = entrada.nextShort();
            entrada.skip("\n");

            switch (opcao) {
                case 1 -> {
                    System.out.print("Matrícula: ");
                    String numeroMatricula = entrada.nextLine();
                    aluno.setMatricula(numeroMatricula);
                }
                case 2 -> {
                    System.out.print("Nome: ");
                    String nomeAluno = entrada.nextLine();
                    aluno.setNome(nomeAluno);
                }
                case 3 -> {
                    System.out.print("Cpf: ");
                    String numeroCpf = entrada.nextLine();
                    aluno.setCpf(numeroCpf);
                }
                case 4 -> {
                    System.out.print("Telefone: ");
                    String numeroTelefone = entrada.nextLine();
                    aluno.setTelefone(numeroTelefone);
                }
                case 5 -> {
                    System.out.print("E-mail: ");
                    String email = entrada.nextLine();
                    aluno.setEmail(email);
                }
            }
        }else
            System.out.println("Cadastro nao encontrado!!");
    }
    public void relatorio() {
        System.out.println("[Relatório de ALUNOS]");
        System.out.println(alunos);
    }
}