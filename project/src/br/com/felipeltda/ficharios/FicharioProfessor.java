package br.com.felipeltda.ficharios;
import br.com.felipeltda.modelos.Professor;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class FicharioProfessor {
    private final ArrayList<Professor> professores;
    private final Scanner entrada;

    public FicharioProfessor(ArrayList<Professor> professores){
        this.professores = professores;
        entrada = new Scanner(System.in);
    }
    private Professor buscaRegistro(int registro){
        Professor professor = new Professor(registro);
        if(professores.contains(professor))
            return professores.get(professores.indexOf(professor));

        return null;
    }
    private Professor buscaNome(String nome){
        for (Professor professor : professores) {
            if ((professor != null) && (Objects.equals(professor.getNome().toLowerCase(), nome.toLowerCase()))) {
                return professor;
            }
        }
        return null;
    }
    private Professor buscaProfessor(String nome){
        return buscaNome(nome);
    }
    private Professor buscaProfessor(int valor){
        return buscaRegistro(valor);
    }

    private Professor busca(){
        System.out.println("===TIPO DE BUSCA===");
        System.out.println("[1] - Por Nome");
        System.out.println("[2] - Por Registro");
        short opcao = entrada.nextShort();
        entrada.skip("\n");

        Professor professor =  null;
        switch (opcao) {
            case 1 -> {
                System.out.println("Nome: ");
                String nomeProfessor = entrada.nextLine();
                professor = buscaProfessor(nomeProfessor);
            }
            case 2 -> {
                System.out.println("Registro: ");
                int registro = entrada.nextInt();
                professor = buscaProfessor(registro);
            }
            default -> System.out.println("Opçao invalida!");
        }

        return professor;
    }

    public void cadastrar(){
        int registro=0;
        System.out.println(" === Cadastrar PROFESSOR ==== ");
        System.out.print("Nome: ");
        String nome = entrada.nextLine();
        try {
            System.out.print("Registro: ");
            registro = Integer.parseInt(entrada.nextLine());
        }catch (NumberFormatException e){
            System.out.println("O Valor informado precisa ser inteiro!");
        }
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

        Professor professor = new Professor(nome,telefone,registro,cpf,email,data);
        if(!professores.contains(professor)){
            professores.add(professor);
            return;
        }
        System.out.println("Professor ja cadastrado!");
    }

    public void consultar(){
        System.out.println(" === Consultar PROFESSOR ==== ");
        System.out.println("===Professores Cadastrados===");
        System.out.println(professores);
        Professor professor = busca();
        System.out.println(professor != null ? professor.exibirDados() : "Cadastro nao encontrado!!");
    }

    public void relatorio() {
        System.out.println("[Relatório de PROFESSORES]");
        System.out.println(professores);
    }
    public void excluir(){
        System.out.println("===Professores Cadastrados===");
        System.out.println(professores);
        Professor professor = busca();

        try{
            System.out.println("Confirma a exclusão? (1-sim) e (2-não) ");
            int resposta = entrada.nextInt();
            entrada.skip("\n");
            if(resposta == 1){
                professores.remove(professor);
                System.out.println("Exclusão efetuada com sucesso!");
                return;
            }
            System.out.println("Exclusão não efetuada.");
        }catch (NullPointerException e){
            System.out.println("Professor inexistente");
        }

    }
    public void alterar(){
        System.out.println(" === Alterar PROFESSOR ==== ");
        System.out.println("===Professores Cadastrados===");
        System.out.println(professores);

        Professor professor = busca();
        if(professor != null){
            System.out.println(professor);
            System.out.println("Escolha o item a editar!");
            System.out.println("[1] - Registro");
            System.out.println("[2] - Nome");
            System.out.println("[3] - Cpf");
            System.out.println("[4] - Telefone");
            System.out.println("[5] - E-mail");
            short opcao = entrada.nextShort();
            entrada.skip("\n");

            switch (opcao) {
                case 1 -> {
                    System.out.print("Registro: ");
                    int registro = entrada.nextInt();
                    professor.setRegistro(registro);
                }
                case 2 -> {
                    System.out.print("Nome: ");
                    String nomeProfessor = entrada.nextLine();
                    professor.setNome(nomeProfessor);
                }
                case 3 -> {
                    System.out.print("Cpf: ");
                    String cpf = entrada.nextLine();
                    professor.setCpf(cpf);
                }
                case 4 -> {
                    System.out.print("Telefone: ");
                    String telefone = entrada.nextLine();
                    professor.setTelefone(telefone);
                }
                case 5 -> {
                    System.out.print("E-mail: ");
                    String email = entrada.nextLine();
                    professor.setEmail(email);
                }
            }
        }else
            System.out.println("Cadastro nao encontrado!!");
    }
}