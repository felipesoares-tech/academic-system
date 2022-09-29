package br.com.felipeltda.servicos;

import br.com.felipeltda.modelos.Aluno;

public class EnturmacaoAlunoServico {
    public void avisar(Aluno aluno){
        NotificadorEmail notificadorEmail = new NotificadorEmail();
        notificadorEmail.notificar(aluno, "Sua enturmacao na turma foi realizada!");
    }
}
