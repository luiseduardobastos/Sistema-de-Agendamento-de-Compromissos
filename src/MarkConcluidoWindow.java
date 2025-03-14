import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MarkConcluidoWindow extends JFrame {
    private Agenda agenda;

    public MarkConcluidoWindow(Agenda agenda) {
        this.agenda = agenda;
        initUI();
        setIconImage();
    }

    private void initUI() {
        setTitle("Marcar Compromisso como Concluído");
        setSize(400, 150);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2, 10, 10));

        JLabel indiceLabel = new JLabel("Índice do Compromisso:");
        JTextField indiceField = new JTextField();

        JButton markButton = new JButton("Marcar como Concluído");

        markButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int indice = Integer.parseInt(indiceField.getText());
                    agenda.marcarCompromissoConcluido(indice);
                    JOptionPane.showMessageDialog(null, "Compromisso marcado como concluído e removido da lista!");
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                            "Erro ao marcar compromisso como concluído: " + ex.getMessage());
                }
            }
        });

        panel.add(indiceLabel);
        panel.add(indiceField);
        panel.add(markButton);

        add(panel);
    }

    private void setIconImage() {
        // Carrega a imagem do ícone
        ImageIcon icon = new ImageIcon("src/resources/icon.png");
        // Define a imagem como ícone da janela
        setIconImage(icon.getImage());
    }
}