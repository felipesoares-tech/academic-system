package br.com.felipeltda.servicos;

import br.com.felipeltda.modelos.Aluno;

public interface Notificador {
    void notificar (Aluno aluno, String mensagem);
}
