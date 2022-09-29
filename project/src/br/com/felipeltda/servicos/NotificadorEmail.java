package br.com.felipeltda.servicos;

import br.com.felipeltda.modelos.Aluno;

public class NotificadorEmail implements Notificador{
    public void notificar(Aluno aluno, String mensagem){
        System.out.printf("Notificando %s no e-mail %s: %s\n",aluno.getNome(), aluno.getEmail(), mensagem);
    }
}
