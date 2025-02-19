import java.time.LocalDateTime;
import java.util.List;

public class Relatorio {
    public void gerarRelatorioPassados(List<Compromisso> compromissos) {
        LocalDateTime agora = LocalDateTime.now();
        System.out.println("Compromissos passados:");
        compromissos.stream()
                .filter(c -> c.getDataHora().isBefore(agora))
                .forEach(System.out::println);
    }

    public void gerarRelatorioFuturos(List<Compromisso> compromissos) {
        LocalDateTime agora = LocalDateTime.now();
        System.out.println("Compromissos futuros:");
        compromissos.stream()
                .filter(c -> c.getDataHora().isAfter(agora))
                .forEach(System.out::println);
    }
}