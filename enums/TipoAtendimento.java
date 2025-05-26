package enums;

public enum TipoAtendimento {
    REGULAR(1),
    PRIORIDADE(2),
    PRIORIDADE_DA_PRIORIDADE(3);

    private final int nivel;

    TipoAtendimento(int nivel) {
        this.nivel = nivel;
    }

    public int getNivel() {
        return nivel;
    }
}
