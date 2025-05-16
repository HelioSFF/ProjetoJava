package view;

import model.Paciente;
import model.TipoAtendimento;
import service.PacienteService;
import repository.PacienteRepositoryMemoria;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class PacienteForm {
    private JPanel painelPrincipal;
    private JTextField campoNome;
    private JTextArea campoObservacoes;
    private JComboBox<TipoAtendimento> comboPrioridade;
    private JButton botaoSalvar;
    private JTextArea areaLista;

    private final PacienteService service;

    public PacienteForm() {
        this.service = new PacienteService(new PacienteRepositoryMemoria());

        // 1. Inicializar todos os componentes da UI
        painelPrincipal = new JPanel(new BorderLayout(10, 10)); // Adiciona espaçamento
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margem

        JPanel formularioPanel = new JPanel(new GridBagLayout()); // Usar GridBagLayout para mais flexibilidade
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaçamento entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        campoNome = new JTextField(20);
        campoObservacoes = new JTextArea(5, 20);
        comboPrioridade = new JComboBox<>();
        botaoSalvar = new JButton("Salvar Paciente");
        areaLista = new JTextArea(10, 30);
        areaLista.setEditable(false);

        // Popular JComboBox
        for (TipoAtendimento tipo : TipoAtendimento.values()) {
            comboPrioridade.addItem(tipo);
        }

        // Layout do formulário
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        formularioPanel.add(new JLabel("Nome:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1.0; // Permitir que o campo de texto expanda
        formularioPanel.add(campoNome, gbc);
        gbc.weightx = 0; // Resetar

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.NORTHEAST; // Alinhar label ao topo
        formularioPanel.add(new JLabel("Observações:"), gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.BOTH; // Permitir que a área de texto expanda
        gbc.weighty = 1.0;
        formularioPanel.add(new JScrollPane(campoObservacoes), gbc);
        gbc.weighty = 0; // Resetar
        gbc.fill = GridBagConstraints.HORIZONTAL; // Voltar ao padrão

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        formularioPanel.add(new JLabel("Prioridade:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        formularioPanel.add(comboPrioridade, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        formularioPanel.add(botaoSalvar, gbc);

        // Adicionar painéis ao painel principal
        painelPrincipal.add(formularioPanel, BorderLayout.NORTH);
        painelPrincipal.add(new JScrollPane(areaLista), BorderLayout.CENTER);

        // Adicionar ActionListener ao botão
        botaoSalvar.addActionListener(e -> {
            String nome = campoNome.getText();
            String obs = campoObservacoes.getText();
            TipoAtendimento tipo = (TipoAtendimento) comboPrioridade.getSelectedItem();

            if (nome == null || nome.trim().isEmpty()) {
                JOptionPane.showMessageDialog(painelPrincipal,
                        "O nome do paciente não pode estar vazio.",
                        "Erro de Validação", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (tipo == null) {
                JOptionPane.showMessageDialog(painelPrincipal,
                        "Selecione um tipo de atendimento.",
                        "Erro de Validação", JOptionPane.ERROR_MESSAGE);
                return;
            }


            Paciente paciente = new Paciente(nome, obs, tipo, LocalDate.now(), service.getNovaOrdem());
            service.registrarPaciente(paciente);

            atualizarLista();
            limparCampos();
        });

        // Atualizar a lista inicialmente (se houver dados pré-existentes, embora aqui não haja)
        atualizarLista();
    }

    private void atualizarLista() {
        StringBuilder sb = new StringBuilder();
        for (Paciente p : service.listarPacientesOrdenados()) {
            sb.append(p.toString()).append("\n");
        }
        areaLista.setText(sb.toString());
    }

    private void limparCampos() {
        campoNome.setText("");
        campoObservacoes.setText("");
        comboPrioridade.setSelectedIndex(0); // Assume que o primeiro item é um padrão aceitável
        campoNome.requestFocusInWindow(); // Focar no campo nome após limpar
    }

    public JPanel getPainelPrincipal() {
        return painelPrincipal; // Agora apenas retorna o painel já construído
    }
}


