import javax.swing.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class VerificadorLembretes implements Runnable {
    private Agenda agenda;
    private int intervaloVerificacao;

    public VerificadorLembretes(Agenda agenda, int intervaloVerificacao) {
        this.agenda = agenda;
        this.intervaloVerificacao = intervaloVerificacao;
    }

    @Override
    public void run() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                LocalDateTime agora = LocalDateTime.now();
                List<Compromisso> compromissos = agenda.getCompromissos();

                // Verifica e remove compromissos com data passada
                compromissos.removeIf(c -> c.getDataHora().isBefore(agora));

                // Verifica lembretes ativos
                for (Compromisso compromisso : compromissos) {
                    if (compromisso.isLembrete() &&
                            compromisso.getDataHora().isAfter(agora) &&
                            compromisso.getDataHora().isBefore(agora.plusMinutes(30))) {
                        exibirNotificacao(compromisso);
                    }
                }
            }
        }, 0, intervaloVerificacao * 1000); // Converte segundos para milissegundos
    }

    private void exibirNotificacao(Compromisso compromisso) {
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(
                    null,
                    "Lembrete: " + compromisso.getTitulo() + "\n" +
                            "Descrição: " + compromisso.getDescricao() + "\n" +
                            "Data/Hora: " + compromisso.getDataHora(),
                    "Lembrete de Compromisso",
                    JOptionPane.INFORMATION_MESSAGE);
        });
    }
}