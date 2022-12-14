package br.com.felipeltda.ficharios;
import br.com.felipeltda.modelos.Aluno;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class FicharioAluno implements Fichario{
    private final ArrayList<Aluno> alunos;
    private final Scanner entrada;
    private final FicharioEnturmacao ficharioEnturmacao;

    public FicharioAluno(ArrayList<Aluno> alunos, FicharioEnturmacao ficharioEnturmacao){
        this.alunos = alunos;
        entrada = new Scanner(System.in);
        this.ficharioEnturmacao = ficharioEnturmacao;
    }

    private Aluno buscaMatricula(int matricula){
        Aluno aluno = new Aluno(matricula);
        if(alunos.contains(aluno))
            return alunos.get(alunos.indexOf(aluno));
        return null;
    }

    private Aluno buscaCpf(String cpf){
        for (Aluno aluno : alunos) {
            if (Objects.equals(aluno.getCpf(), cpf)) {
                return aluno;
            }
        }
        return null;
    }
    private Aluno buscaAluno(int matricula){
        return buscaMatricula(matricula);
    }
    private Aluno buscaAluno(String cpf){
        return buscaCpf(cpf);
    }
    private Aluno busca(){
        System.out.println("===TIPO DE BUSCA===");
        System.out.println("[1] - Por Matricula");
        System.out.println("[2] - Por Cpf");
        short opcao = entrada.nextShort();
        entrada.skip("\n");

        Aluno aluno =  null;
        switch (opcao) {
            case 1 -> {
                System.out.print("Matricula: ");
                int matricula = entrada.nextInt();
                aluno = buscaAluno(matricula);
            }
            case 2 -> {
                System.out.print("Cpf: ");
                String cpf = entrada.nextLine();
                aluno = buscaAluno(cpf);
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
            if(ficharioEnturmacao.alunoVinculado(aluno))
                System.out.println("Nao foi possivel excluir o aluno em questao, pois o mesmo esta vinculado a uma turma");
            else{
                System.out.println("Confirma a exclus??o? (1-sim) e (2-n??o) ");
                int resposta = entrada.nextInt();
                entrada.skip("\n");
                if(resposta == 1){
                    alunos.remove(aluno);
                    System.out.println("Exclus??o efetuada com sucesso!");
                    return;
                }
                System.out.println("Exclus??o n??o efetuada.");
            }
        }catch (NullPointerException e){
            System.out.println("Aluno inexistente!!");
        }
    }

    public void cadastrar(){
        System.out.println(" === Cadastrar ALUNO ==== ");
        System.out.print("Nome: ");
        String nome = entrada.nextLine();
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

        Aluno aluno = new Aluno(nome,telefone,cpf,email,data);

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
            System.out.println("[1] - Nome");
            System.out.println("[2] - Cpf");
            System.out.println("[3] - Telefone");
            System.out.println("[4] - E-mail");
            short opcao = entrada.nextShort();
            entrada.skip("\n");

            switch (opcao) {
                case 1 -> {
                    System.out.print("Nome: ");
                    String nomeAluno = entrada.nextLine();
                    aluno.setNome(nomeAluno);
                }
                case 2 -> {
                    System.out.print("Cpf: ");
                    String numeroCpf = entrada.nextLine();
                    aluno.setCpf(numeroCpf);
                }
                case 3 -> {
                    System.out.print("Telefone: ");
                    String numeroTelefone = entrada.nextLine();
                    aluno.setTelefone(numeroTelefone);
                }
                case 4 -> {
                    System.out.print("E-mail: ");
                    String email = entrada.nextLine();
                    aluno.setEmail(email);
                }
            }
        }else
            System.out.println("Cadastro nao encontrado!!");
    }
    public void relatorio() {
        System.out.println("[Relat??rio de ALUNOS]");
        System.out.println(alunos);
    }
}