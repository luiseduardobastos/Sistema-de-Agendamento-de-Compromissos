import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

public class ViewCompromissosWindow extends JFrame {
    private Agenda agenda;
    private Calendario calendario;

    public ViewCompromissosWindow(Agenda agenda) {
        this.agenda = agenda;
        this.calendario = new Calendario();
        initUI();
        setIconImage();
    }

    private void initUI() {
        setTitle("Visualizar Compromissos");
        setSize(500, 600);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));

        JButton diarioButton = new JButton("Visualizar Diário");
        JButton semanalButton = new JButton("Visualizar Semanal");
        JButton mensalButton = new JButton("Visualizar Mensal");

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(textArea);

        diarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDate data = LocalDate.now(); // Data atual
                textArea.setText(""); // Limpa a área de texto
                List<Compromisso> compromissosDiarios = agenda.getCompromissos().stream()
                        .filter(c -> c.getDataHora().toLocalDate().equals(data))
                        .toList();
                if (compromissosDiarios.isEmpty()) {
                    textArea.append("Nenhum compromisso para hoje.\n");
                } else {
                    textArea.append("Compromissos para " + data + ":\n");
                    compromissosDiarios.forEach(c -> textArea.append("→ " + c.toString() + "\n"));
                }
            }
        });

        semanalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDate inicioSemana = LocalDate.now(); // Início da semana (hoje)
                LocalDate fimSemana = inicioSemana.plusDays(6); // Fim da semana (6 dias após hoje)
                textArea.setText(""); // Limpa a área de texto
                List<Compromisso> compromissosSemanais = agenda.getCompromissos().stream()
                        .filter(c -> !c.getDataHora().toLocalDate().isBefore(inicioSemana) &&
                                !c.getDataHora().toLocalDate().isAfter(fimSemana))
                        .toList();
                if (compromissosSemanais.isEmpty()) {
                    textArea.append("Nenhum compromisso para esta semana.\n");
                } else {
                    textArea.append("Compromissos de " + inicioSemana + " a " + fimSemana + ":\n");
                    compromissosSemanais.forEach(c -> textArea.append("→ " + c.toString() + "\n"));
                }
            }
        });

        mensalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDate hoje = LocalDate.now();
                int mes = hoje.getMonthValue();
                int ano = hoje.getYear();
                textArea.setText(""); // Limpa a área de texto
                List<Compromisso> compromissosMensais = agenda.getCompromissos().stream()
                        .filter(c -> c.getDataHora().getMonthValue() == mes && c.getDataHora().getYear() == ano)
                        .toList();
                if (compromissosMensais.isEmpty()) {
                    textArea.append("Nenhum compromisso para este mês.\n");
                } else {
                    textArea.append("Compromissos para " + mes + "/" + ano + ":\n");
                    compromissosMensais.forEach(c -> textArea.append("→ " + c.toString() + "\n"));
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));
        buttonPanel.add(diarioButton);
        buttonPanel.add(semanalButton);
        buttonPanel.add(mensalButton);

        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);
    }

    private void setIconImage() {
        // Carrega a imagem do ícone
        ImageIcon icon = new ImageIcon("src/resources/icon.png");
        // Define a imagem como ícone da janela
        setIconImage(icon.getImage());
    }
}