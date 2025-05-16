package model;

import java.time.LocalDate;

public class Paciente implements Comparable<Paciente> {
    private String nome;
    private String observacoes;
    private TipoAtendimento tipoAtendimento;
    private LocalDate dataAtendimento;
    private int ordemChegada;

    public Paciente(String nome, String observacoes, TipoAtendimento tipoAtendimento, LocalDate dataAtendimento, int ordemChegada) {
        this.nome = nome;
        this.observacoes = observacoes;
        this.tipoAtendimento = tipoAtendimento;
        this.dataAtendimento = dataAtendimento;
        this.ordemChegada = ordemChegada;
    }

    public TipoAtendimento getTipoAtendimento() {
        return tipoAtendimento;
    }

    public int getOrdemChegada() {
        return ordemChegada;
    }

    @Override
    public int compareTo(Paciente outro) {
        int comp = outro.tipoAtendimento.ordinal() - this.tipoAtendimento.ordinal();
        return comp != 0 ? comp : Integer.compare(this.ordemChegada, outro.ordemChegada);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (%s) - Ordem: %d", tipoAtendimento, nome, dataAtendimento, ordemChegada);
    }
}
