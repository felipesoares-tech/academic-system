package br.com.felipeltda.servicos;

import br.com.felipeltda.modelos.Aluno;

public class MatriculaAlunoServico {
    public void avisar (Aluno aluno){
        NotificadorEmail notificadorEmail = new NotificadorEmail();
        notificadorEmail.notificar(aluno, "Matrícula foi realizada");
    }
}
