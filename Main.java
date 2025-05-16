import controller.PacienteController;
import repository.PacienteRepositoryMemoria;
import service.PacienteService;

public class Main {
    public static void main(String[] args) {
        var repositorio = new PacienteRepositoryMemoria();
        var service = new PacienteService(repositorio);
        var controller = new PacienteController(service);

        controller.iniciar();
    }
}
