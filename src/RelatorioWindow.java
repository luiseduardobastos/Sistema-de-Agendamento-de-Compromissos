import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.List;

public class RelatorioWindow extends JFrame {
    private Agenda agenda;

    public RelatorioWindow(Agenda agenda) {
        this.agenda = agenda;
        initUI();
        setIconImage();
    }

    private void initUI() {
        setTitle("Gerar Relatório de Compromissos");
        setSize(500, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));

        JButton relatorioPassadosButton = new JButton("Relatório de Compromissos Passados");
        JButton relatorioFuturosButton = new JButton("Relatório de Compromissos Futuros");

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        relatorioPassadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDateTime agora = LocalDateTime.now();
                List<Compromisso> compromissosPassados = agenda.getCompromissos().stream()
                        .filter(c -> c.getDataHora().isBefore(agora))
                        .toList();

                textArea.setText(""); // Limpa a área de texto
                if (compromissosPassados.isEmpty()) {
                    textArea.append("Nenhum compromisso passado encontrado.\n");
                } else {
                    textArea.append("Compromissos Passados:\n");
                    compromissosPassados.forEach(c -> textArea.append(c.toString() + "\n"));
                }
            }
        });

        relatorioFuturosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDateTime agora = LocalDateTime.now();
                List<Compromisso> compromissosFuturos = agenda.getCompromissos().stream()
                        .filter(c -> c.getDataHora().isAfter(agora))
                        .toList();

                textArea.setText(""); // Limpa a área de texto
                if (compromissosFuturos.isEmpty()) {
                    textArea.append("Nenhum compromisso futuro encontrado.\n");
                } else {
                    textArea.append("Compromissos Futuros:\n");
                    compromissosFuturos.forEach(c -> textArea.append(c.toString() + "\n"));
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 10, 10));
        buttonPanel.add(relatorioPassadosButton);
        buttonPanel.add(relatorioFuturosButton);

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