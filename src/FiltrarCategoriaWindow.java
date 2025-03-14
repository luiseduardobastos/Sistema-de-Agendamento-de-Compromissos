import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FiltrarCategoriaWindow extends JFrame {
    private Agenda agenda;

    public FiltrarCategoriaWindow(Agenda agenda) {
        this.agenda = agenda;
        initUI();
        setIconImage();
    }

    private void initUI() {
        setTitle("Filtrar Compromissos por Categoria");
        setSize(500, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));

        JLabel categoriaLabel = new JLabel("Categoria:");
        JTextField categoriaField = new JTextField();
        JButton filtrarButton = new JButton("Filtrar");

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        textArea.append("\n\n\n\n\n\n\n\n\n");

        filtrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String categoria = categoriaField.getText();
                List<Compromisso> resultados = agenda.filtrarPorCategoria(categoria);

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
        inputPanel.add(categoriaLabel);
        inputPanel.add(categoriaField);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(filtrarButton, BorderLayout.CENTER);
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