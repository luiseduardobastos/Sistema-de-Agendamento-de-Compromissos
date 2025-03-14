import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EditCompromissoWindow extends JFrame {
    private Agenda agenda;

    public EditCompromissoWindow(Agenda agenda) {
        this.agenda = agenda;
        initUI();
        setIconImage();
    }

    private void initUI() {
        setTitle("Editar Compromisso");
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2, 10, 10));

        JLabel indiceLabel = new JLabel("Índice do Compromisso:");
        JTextField indiceField = new JTextField();

        JLabel tituloLabel = new JLabel("Novo Título:");
        JTextField tituloField = new JTextField();

        JLabel descricaoLabel = new JLabel("Nova Descrição:");
        JTextField descricaoField = new JTextField();

        JLabel dataHoraLabel = new JLabel("Nova Data/Hora (yyyy-MM-ddTHH:mm):");
        JTextField dataHoraField = new JTextField();

        JLabel categoriaLabel = new JLabel("Nova Categoria:");
        JTextField categoriaField = new JTextField();

        JLabel lembreteLabel = new JLabel("Novo Lembrete (true/false):");
        JTextField lembreteField = new JTextField();

        JButton editButton = new JButton("Editar");

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int indice = Integer.parseInt(indiceField.getText());
                    String novoTitulo = tituloField.getText();
                    String novaDescricao = descricaoField.getText();
                    LocalDateTime novaDataHora = LocalDateTime.parse(dataHoraField.getText(),
                            DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                    String novaCategoria = categoriaField.getText();
                    boolean novoLembrete = Boolean.parseBoolean(lembreteField.getText());

                    agenda.editarCompromisso(indice, novoTitulo, novaDescricao, novaDataHora, novaCategoria,
                            novoLembrete);
                    JOptionPane.showMessageDialog(null, "Compromisso editado com sucesso!");
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao editar compromisso: " + ex.getMessage());
                }
            }
        });

        panel.add(indiceLabel);
        panel.add(indiceField);
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
        panel.add(editButton);

        add(panel);
    }

    private void setIconImage() {
        // Carrega a imagem do ícone
        ImageIcon icon = new ImageIcon("src/resources/icon.png");
        // Define a imagem como ícone da janela
        setIconImage(icon.getImage());
    }
}