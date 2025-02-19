import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Agenda {
    private List<Compromisso> compromissos;

    public Agenda() {
        this.compromissos = new ArrayList<>();
    }

    public void adicionarCompromisso(Compromisso compromisso) {
        compromissos.add(compromisso);
    }

    public void editarCompromisso(int index, Compromisso compromisso) {
        if (index >= 0 && index < compromissos.size()) {
            compromissos.set(index, compromisso);
        }
    }

    public void excluirCompromisso(int index) {
        if (index >= 0 && index < compromissos.size()) {
            compromissos.remove(index);
        }
    }

    public List<Compromisso> buscarCompromissosPorPalavraChave(String palavraChave) {
        return compromissos.stream()
                .filter(c -> c.getTitulo().contains(palavraChave) || c.getDescricao().contains(palavraChave))
                .collect(Collectors.toList());
    }

    public List<Compromisso> filtrarPorCategoria(String categoria) {
        return compromissos.stream()
                .filter(c -> c.getCategoria().equalsIgnoreCase(categoria))
                .collect(Collectors.toList());
    }

    public List<Compromisso> getCompromissos() {
        return compromissos;
    }

    public List<Compromisso> getProximosCompromissos() {
        LocalDateTime agora = LocalDateTime.now();
        return compromissos.stream()
                .filter(c -> c.getDataHora().isAfter(agora))
                .collect(Collectors.toList());
    }
}