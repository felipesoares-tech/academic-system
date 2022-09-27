package br.com.felipeltda.modelos;

public enum SituacaoAluno {
    matriculado {
        public String toString() {
            return "enturmado";
        }
    },
    naoMatriculado {
        public String toString() {
            return "nao enturmado";
        }
    }
}
