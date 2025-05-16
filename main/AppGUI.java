package main;

import view.PacienteForm;

import javax.swing.*;
import java.awt.*;

public class AppGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Sistema de Gerenciamento de Pacientes");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            PacienteForm pacienteForm = new PacienteForm();
            frame.setContentPane(pacienteForm.getPainelPrincipal());

            // frame.setSize(500, 400); // Pode ser substituído ou complementado por pack()
            frame.pack(); // Ajusta o tamanho da janela aos componentes
            frame.setLocationRelativeTo(null); // Centraliza na tela
            frame.setMinimumSize(new Dimension(450, 350)); // Define um tamanho mínimo
            frame.setVisible(true);
        });
    }
}
