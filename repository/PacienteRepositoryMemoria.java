package repository;

import model.Paciente;
import java.util.ArrayList;
import java.util.List;

public class PacienteRepositoryMemoria implements IPacienteRepository {
    private final List<Paciente> pacientes = new ArrayList<>();

    @Override
    public void salvar(Paciente paciente) {
        pacientes.add(paciente);
    }

    @Override
    public List<Paciente> listarTodos() {
        return new ArrayList<>(pacientes);
    }
}
