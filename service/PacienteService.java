package service;

import model.Paciente;
import enums.TipoAtendimento;
import repository.IPacienteRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class PacienteService {
    private final IPacienteRepository repositorio;
    private int ordem = 1;

    public PacienteService(IPacienteRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void registrarPaciente(String nome, String observacoes, TipoAtendimento tipo) {
        Paciente paciente = new Paciente(nome, observacoes, tipo, LocalDate.now(), ordem++);
        repositorio.salvar(paciente);
    }

    public void registrarPaciente(Paciente paciente) {
        repositorio.salvar(paciente);
    }

    public List<Paciente> listarPacientesOrdenados() {
        List<Paciente> lista = repositorio.listarTodos();
        Collections.sort(lista);
        return lista;
    }

    public int getNovaOrdem() {
        return ordem++;
    }
}
