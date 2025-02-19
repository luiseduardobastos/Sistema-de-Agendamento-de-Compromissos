import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class VerificadorLembretes implements Runnable {
    private Agenda agenda;
    private int intervaloVerificacao; // Intervalo de verificação em segundos

    public VerificadorLembretes(Agenda agenda, int intervaloVerificacao) {
        this.agenda = agenda;
        this.intervaloVerificacao = intervaloVerificacao;
    }

    @Override
    public void run() {
        while (true) {
            LocalDateTime agora = LocalDateTime.now();
            // Filtra compromissos com lembretes ativos que estão próximos (dentro de 30
            // minutos)
            List<Compromisso> lembretes = agenda.getCompromissos().stream()
                    .filter(c -> c.isLembrete() &&
                            c.getDataHora().isAfter(agora) &&
                            c.getDataHora().isBefore(agora.plusMinutes(30)))
                    .collect(Collectors.toList());

            if (!lembretes.isEmpty()) {
                System.out.println("\n--- LEMBRETES ATIVOS ---");
                lembretes.forEach(c -> System.out.println(c.getTitulo() + " - " + c.getDataHora()));
            }

            try {
                Thread.sleep(intervaloVerificacao * 1000); // Converte segundos para milissegundos
            } catch (InterruptedException e) {
                System.out.println("Verificador de lembretes interrompido.");
                e.printStackTrace();
            }
        }
    }
}