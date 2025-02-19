import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Calendario {
    public void exibirDiario(List<Compromisso> compromissos, LocalDate data) {
        System.out.println("Compromissos para " + data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ":");
        compromissos.stream()
                .filter(c -> c.getDataHora().toLocalDate().equals(data))
                .forEach(System.out::println);
    }

    public void exibirSemanal(List<Compromisso> compromissos, LocalDate inicioSemana) {
        LocalDate fimSemana = inicioSemana.plusDays(6);
        System.out.println("Compromissos de " + inicioSemana.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
                " a " + fimSemana.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ":");
        compromissos.stream()
                .filter(c -> !c.getDataHora().toLocalDate().isBefore(inicioSemana) &&
                        !c.getDataHora().toLocalDate().isAfter(fimSemana))
                .forEach(System.out::println);
    }

    public void exibirMensal(List<Compromisso> compromissos, int mes, int ano) {
        System.out.println("Compromissos para " + mes + "/" + ano + ":");
        compromissos.stream()
                .filter(c -> c.getDataHora().getMonthValue() == mes && c.getDataHora().getYear() == ano)
                .forEach(System.out::println);
    }
}