package repository;

import model.Paciente;
import java.util.List;

public interface IPacienteRepository {
    void salvar(Paciente paciente);
    List<Paciente> listarTodos();
}
