import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    private Agenda agenda;

    public MainWindow() {
        agenda = new Agenda();
        initUI();
        iniciarVerificadorLembretes(); // Inicia o verificador de lembretes
        setIconImage();
    }

    private void initUI() {
        setTitle("Agenda de Compromissos");
        setSize(450, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(12, 1, 10, 10));

        ImageIcon logoIcon = new ImageIcon("src/resources/logo.png");
        Image logoImage = logoIcon.getImage().getScaledInstance(120, 30, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(logoImage);
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(logoLabel);

        JButton addButton = new JButton("Adicionar Compromisso");
        JButton editButton = new JButton("Editar Compromisso");
        JButton deleteButton = new JButton("Excluir Compromisso");
        JButton viewButton = new JButton("Visualizar Compromissos");
        JButton markButton = new JButton("Marcar como Concluído");
        JButton buscarButton = new JButton("Buscar Compromissos por Palavra-Chave");
        JButton filtrarButton = new JButton("Filtrar Compromissos por Categoria");
        JButton relatorioButton = new JButton("Gerar Relatório de Compromissos");
        JButton exitButton = new JButton("Sair");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddCompromissoWindow(agenda).setVisible(true);
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditCompromissoWindow(agenda).setVisible(true);
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeleteCompromissoWindow(agenda).setVisible(true);
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewCompromissosWindow(agenda).setVisible(true);
            }
        });

        markButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MarkConcluidoWindow(agenda).setVisible(true);
            }
        });

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BuscarCompromissoWindow(agenda).setVisible(true);
            }
        });

        filtrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FiltrarCategoriaWindow(agenda).setVisible(true);
            }
        });

        relatorioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RelatorioWindow(agenda).setVisible(true);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agenda.salvarCompromissos();
                System.exit(0);
            }
        });

        panel.add(addButton);
        panel.add(editButton);
        panel.add(deleteButton);
        panel.add(viewButton);
        panel.add(markButton);
        panel.add(buscarButton);
        panel.add(filtrarButton);
        panel.add(relatorioButton);
        panel.add(exitButton);

        JLabel nomeLabel = new JLabel("Desenvolvido por: Luis Eduardo, Henrique Ângelo e Gabriel Soares.",
                SwingConstants.CENTER);
        nomeLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        nomeLabel.setForeground(Color.GRAY);
        panel.add(nomeLabel);

        JLabel matriculaLabel = new JLabel("23.1.8095 | 23.1.8028 | 22.1.8114",
                SwingConstants.CENTER);
        matriculaLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        matriculaLabel.setForeground(Color.GRAY);
        panel.add(matriculaLabel);

        add(panel);
    }

    // Método para iniciar o verificador de lembretes
    private void iniciarVerificadorLembretes() {
        VerificadorLembretes verificador = new VerificadorLembretes(agenda, 60); // Verifica a cada 60 segundos
        Thread threadVerificador = new Thread(verificador);
        threadVerificador.setDaemon(true);
        threadVerificador.start();
    }

    private void setIconImage() {
        // Carrega a imagem do ícone
        ImageIcon icon = new ImageIcon("src/resources/icon.png");
        // Define a imagem como ícone da janela
        setIconImage(icon.getImage());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }
}