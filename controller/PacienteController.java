package controller;

import model.TipoAtendimento;
import model.Paciente; // Adicionado para exibirPacientes
import service.PacienteService;

import java.util.List; // Adicionado para exibirPacientes
import java.util.Scanner;
import java.util.InputMismatchException; // Para tratar erro de tipo

public class PacienteController {
    private final PacienteService service;
    private final Scanner scanner; // Removido final para poder fechar e reabrir se necessário, ou inicializar no construtor

    public PacienteController(PacienteService service) {
        this.service = service;
        this.scanner = new Scanner(System.in); // Inicializar aqui
    }

    public void iniciar() {
        System.out.println("=== Sistema de Fichas Médicas (Console) ===");

        String continuar;
        do {
            System.out.print("Nome do paciente: ");
            String nome = scanner.nextLine();
            while (nome.trim().isEmpty()) {
                System.out.println("Nome não pode ser vazio. Tente novamente.");
                System.out.print("Nome do paciente: ");
                nome = scanner.nextLine();
            }


            System.out.print("Observações: ");
            String obs = scanner.nextLine();

            TipoAtendimento tipo = escolherTipoAtendimento();

            service.registrarPaciente(nome, obs, tipo);
            System.out.println("Paciente registrado com sucesso!");

            System.out.print("Deseja adicionar outro paciente? (s/n): ");
            continuar = scanner.nextLine();

        } while (continuar.equalsIgnoreCase("s"));

        exibirPacientes();
        scanner.close(); // Fechar o scanner ao final do uso
        System.out.println("Sistema encerrado.");
    }

    private TipoAtendimento escolherTipoAtendimento() {
        while (true) { // Loop até obter uma entrada válida
            System.out.println("Tipo de Atendimento:");
            System.out.println("1 - REGULAR");
            System.out.println("2 - PRIORIDADE");
            System.out.println("3 - PRIORIDADE DA PRIORIDADE");
            System.out.print("Escolha uma opção (1-3): ");
            String linha = scanner.nextLine();
            try {
                int opcao = Integer.parseInt(linha);
                return switch (opcao) {
                    case 1 -> TipoAtendimento.REGULAR;
                    case 2 -> TipoAtendimento.PRIORIDADE;
                    case 3 -> TipoAtendimento.PRIORIDADE_DA_PRIORIDADE;
                    default -> {
                        System.out.println("Opção inválida. Tente novamente.");
                        yield null;
                    }
                };
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número. Tente novamente.");
            }
        }
    }

    private void exibirPacientes() {
        List<Paciente> pacientes = service.listarPacientesOrdenados();
        if (pacientes.isEmpty()) {
            System.out.println("\nNenhum paciente registrado.");
            return;
        }
        System.out.println("\n=== Pacientes Ordenados por Prioridade e Chegada ===");
        for (Paciente p : pacientes) {
            System.out.println(p);
        }
    }
}

