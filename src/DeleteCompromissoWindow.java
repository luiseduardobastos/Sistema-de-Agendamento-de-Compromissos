import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteCompromissoWindow extends JFrame {
    private Agenda agenda;

    public DeleteCompromissoWindow(Agenda agenda) {
        this.agenda = agenda;
        initUI();
        setIconImage();
    }

    private void initUI() {
        setTitle("Excluir Compromisso");
        setSize(400, 150);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2, 10, 10));

        JLabel indiceLabel = new JLabel("Índice do Compromisso:");
        JTextField indiceField = new JTextField();

        JButton deleteButton = new JButton("Excluir");

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int indice = Integer.parseInt(indiceField.getText());
                    agenda.excluirCompromisso(indice);
                    JOptionPane.showMessageDialog(null, "Compromisso excluído com sucesso!");
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao excluir compromisso: " + ex.getMessage());
                }
            }
        });

        panel.add(indiceLabel);
        panel.add(indiceField);
        panel.add(deleteButton);

        add(panel);
    }

    private void setIconImage() {
        // Carrega a imagem do ícone
        ImageIcon icon = new ImageIcon("src/resources/icon.png");
        // Define a imagem como ícone da janela
        setIconImage(icon.getImage());
    }
}