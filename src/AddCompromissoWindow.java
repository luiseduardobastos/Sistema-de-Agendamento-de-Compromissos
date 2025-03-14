import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddCompromissoWindow extends JFrame {
    private Agenda agenda;

    public AddCompromissoWindow(Agenda agenda) {
        this.agenda = agenda;
        initUI();
        setIconImage();
    }

    private void initUI() {
        setTitle("Adicionar Compromisso");
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));

        JLabel tituloLabel = new JLabel("Título:");
        JTextField tituloField = new JTextField();

        JLabel descricaoLabel = new JLabel("Descrição:");
        JTextField descricaoField = new JTextField();

        JLabel dataHoraLabel = new JLabel("Data/Hora (yyyy-MM-ddTHH:mm):");
        JTextField dataHoraField = new JTextField();

        JLabel categoriaLabel = new JLabel("Categoria:");
        JTextField categoriaField = new JTextField();

        JLabel lembreteLabel = new JLabel("Lembrete (true/false):");
        JTextField lembreteField = new JTextField();

        JButton addButton = new JButton("Adicionar");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titulo = tituloField.getText();
                String descricao = descricaoField.getText();
                LocalDateTime dataHora = LocalDateTime.parse(dataHoraField.getText(),
                        DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                String categoria = categoriaField.getText();
                boolean lembrete = Boolean.parseBoolean(lembreteField.getText());

                agenda.adicionarCompromisso(titulo, descricao, dataHora, categoria, lembrete);
                JOptionPane.showMessageDialog(null, "Compromisso adicionado com sucesso!");
                dispose();
            }
        });

        panel.add(tituloLabel);
        panel.add(tituloField);
        panel.add(descricaoLabel);
        panel.add(descricaoField);
        panel.add(dataHoraLabel);
        panel.add(dataHoraField);
        panel.add(categoriaLabel);
        panel.add(categoriaField);
        panel.add(lembreteLabel);
        panel.add(lembreteField);
        panel.add(addButton);

        add(panel);
    }

    private void setIconImage() {
        // Carrega a imagem do ícone
        ImageIcon icon = new ImageIcon("src/resources/icon.png");
        // Define a imagem como ícone da janela
        setIconImage(icon.getImage());
    }
}