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

        JLabel lembreteLabel = new JLabel("Lembrete:");
        JComboBox<String> lembreteComboBox = new JComboBox<>(new String[] { "Sim", "Não" });
        JButton addButton = new JButton("Adicionar");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String titulo = tituloField.getText();
                    String descricao = descricaoField.getText();
                    String dataHoraTexto = dataHoraField.getText();
                    String categoria = categoriaField.getText();
                    boolean lembrete = lembreteComboBox.getSelectedItem().equals("Sim");

                    // Valida se os campos obrigatórios estão preenchidos
                    if (titulo.isEmpty() || descricao.isEmpty() || dataHoraTexto.isEmpty() || categoria.isEmpty()) {
                        throw new IllegalArgumentException("Todos os campos devem ser preenchidos!");
                    }

                    // Converte a data/hora para LocalDateTime
                    LocalDateTime dataHora = LocalDateTime.parse(dataHoraTexto, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

                    // Adiciona o compromisso à agenda
                    agenda.adicionarCompromisso(titulo, descricao, dataHora, categoria, lembrete);

                    // Exibe mensagem de sucesso
                    JOptionPane.showMessageDialog(null, "Compromisso adicionado com sucesso!");
                    dispose(); // Fecha a janela após adicionar o compromisso
                } catch (IllegalArgumentException ex) {
                    // Exibe mensagem de erro para campos vazios ou dados inválidos
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    // Exibe mensagem de erro genérico para outras exceções
                    JOptionPane.showMessageDialog(null,
                            "Ocorreu um erro ao adicionar o compromisso: " + ex.getMessage(), "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }
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
        panel.add(lembreteComboBox);
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