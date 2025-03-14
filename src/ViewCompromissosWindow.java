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

        JTextPane textPane = new JTextPane();
        textPane.setContentType("text/html"); // Habilita HTML no JTextPane
        textPane.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textPane);

        diarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDate data = LocalDate.now(); // Data atual
                List<Compromisso> compromissosDiarios = agenda.getCompromissos().stream()
                        .filter(c -> c.getDataHora().toLocalDate().equals(data))
                        .toList();
                StringBuilder htmlContent = new StringBuilder("<html><body><h2>Compromissos para " + data + "</h2>");
                if (compromissosDiarios.isEmpty()) {
                    htmlContent.append("<p>Nenhum compromisso para hoje.</p>");
                } else {
                    htmlContent.append("<ul>");
                    compromissosDiarios.forEach(c -> htmlContent.append("<li>").append(c.toString()).append("</li>"));
                    htmlContent.append("</ul>");
                }
                htmlContent.append("</body></html>");
                textPane.setText(htmlContent.toString());
            }
        });

        semanalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDate inicioSemana = LocalDate.now(); // Início da semana (hoje)
                LocalDate fimSemana = inicioSemana.plusDays(6); // Fim da semana (6 dias após hoje)
                List<Compromisso> compromissosSemanais = agenda.getCompromissos().stream()
                        .filter(c -> !c.getDataHora().toLocalDate().isBefore(inicioSemana) &&
                                !c.getDataHora().toLocalDate().isAfter(fimSemana))
                        .toList();
                StringBuilder htmlContent = new StringBuilder(
                        "<html><body><h2>Compromissos de " + inicioSemana + " a " + fimSemana + "</h2>");
                if (compromissosSemanais.isEmpty()) {
                    htmlContent.append("<p>Nenhum compromisso para esta semana.</p>");
                } else {
                    htmlContent.append("<ul>");
                    compromissosSemanais.forEach(c -> htmlContent.append("<li>").append(c.toString()).append("</li>"));
                    htmlContent.append("</ul>");
                }
                htmlContent.append("</body></html>");
                textPane.setText(htmlContent.toString());
            }
        });

        mensalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDate hoje = LocalDate.now();
                int mes = hoje.getMonthValue();
                int ano = hoje.getYear();
                List<Compromisso> compromissosMensais = agenda.getCompromissos().stream()
                        .filter(c -> c.getDataHora().getMonthValue() == mes && c.getDataHora().getYear() == ano)
                        .toList();
                StringBuilder htmlContent = new StringBuilder(
                        "<html><body><h2>Compromissos para " + mes + "/" + ano + "</h2>");
                if (compromissosMensais.isEmpty()) {
                    htmlContent.append("<p>Nenhum compromisso para este mês.</p>");
                } else {
                    htmlContent.append("<ul>");
                    compromissosMensais.forEach(c -> htmlContent.append("<li>").append(c.toString()).append("</li>"));
                    htmlContent.append("</ul>");
                }
                htmlContent.append("</body></html>");
                textPane.setText(htmlContent.toString());
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