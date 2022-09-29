import br.com.felipeltda.modelos.Aluno;
import br.com.felipeltda.servicos.MatriculaAlunoServico;

import java.time.LocalDate;

public class MainServicos {
    public static void main(String[] args) {
        Aluno maria = new Aluno("Maria","38999110286","01304446697","maria@gmail.com", LocalDate.now());
        Aluno felipe = new Aluno("Felipe Soares","38998605529","02187354644","soares.felipe200@gmail.com", LocalDate.now());

        MatriculaAlunoServico matriculaAlunoServico = new MatriculaAlunoServico();
        matriculaAlunoServico.avisar(maria);
        matriculaAlunoServico.avisar(felipe);
    }
}
