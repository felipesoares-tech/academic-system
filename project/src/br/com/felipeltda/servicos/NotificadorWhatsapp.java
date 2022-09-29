package br.com.felipeltda.servicos;

import br.com.felipeltda.modelos.Aluno;

public class NotificadorWhatsapp implements Notificador{
    public void notificar(Aluno aluno, String mensagem){
        System.out.printf("Notificando %s no Whatsapp %s: %s\n",aluno.getNome(), aluno.getTelefone(), mensagem);
    }
}
