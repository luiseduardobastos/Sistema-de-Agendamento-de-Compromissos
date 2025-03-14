import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BuscarCompromissoWindow extends JFrame {
    private Agenda agenda;

    public BuscarCompromissoWindow(Agenda agenda) {
        this.agenda = agenda;
        initUI();
        setIconImage();
    }

    private void initUI() {
        setTitle("Buscar Compromissos por Palavra-Chave");
        setSize(500, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));

        JLabel palavraChaveLabel = new JLabel("Palavra-chave:");
        JTextField palavraChaveField = new JTextField();
        JButton buscarButton = new JButton("Buscar");

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        textArea.append("\n\n\n\n\n\n\n\n\n");

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String palavraChave = palavraChaveField.getText();
                List<Compromisso> resultados = agenda.buscarCompromissosPorPalavraChave(palavraChave);

                textArea.setText(""); // Limpa a área de texto
                if (resultados.isEmpty()) {
                    textArea.append("Nenhum compromisso encontrado.\n");
                } else {
                    textArea.append("Compromissos encontrados:\n");
                    resultados.forEach(c -> textArea.append(c.toString() + "\n"));
                }
            }
        });

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(1, 2, 10, 10));
        inputPanel.add(palavraChaveLabel);
        inputPanel.add(palavraChaveField);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(buscarButton, BorderLayout.CENTER);
        panel.add(scrollPane, BorderLayout.SOUTH);

        add(panel);
    }

    private void setIconImage() {
        // Carrega a imagem do ícone
        ImageIcon icon = new ImageIcon("src/resources/icon.png");
        // Define a imagem como ícone da janela
        setIconImage(icon.getImage());
    }
}