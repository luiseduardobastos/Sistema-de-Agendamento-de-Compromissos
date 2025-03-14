import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Agenda {
    private List<Compromisso> compromissos;
    private static final String ARQUIVO_DADOS = "compromissos.dat";

    public Agenda() {
        this.compromissos = new ArrayList<>();
        carregarCompromissos(); // Carrega os compromissos ao inicializar
    }

    // Métodos para manipulação de compromissos
    public void adicionarCompromisso(String titulo, String descricao, LocalDateTime dataHora, String categoria,
            boolean lembrete) {
        Compromisso compromisso = new Compromisso(titulo, descricao, dataHora, categoria, lembrete);
        compromissos.add(compromisso);
        salvarCompromissos(); // Salva os compromissos após adicionar
    }

    public void editarCompromisso(int index, String novoTitulo, String novaDescricao, LocalDateTime novaDataHora,
            String novaCategoria, boolean novoLembrete) {
        if (index >= 0 && index < compromissos.size()) {
            Compromisso compromisso = compromissos.get(index);
            compromisso.setTitulo(novoTitulo);
            compromisso.setDescricao(novaDescricao);
            compromisso.setDataHora(novaDataHora);
            compromisso.setCategoria(novaCategoria);
            compromisso.setLembrete(novoLembrete);
            salvarCompromissos(); // Salva os compromissos após editar
        }
    }

    public void excluirCompromisso(int index) {
        if (index >= 0 && index < compromissos.size()) {
            compromissos.remove(index);
            salvarCompromissos(); // Salva os compromissos após excluir
        }
    }

    public void marcarCompromissoConcluido(int index) {
        if (index >= 0 && index < compromissos.size()) {
            Compromisso compromisso = compromissos.get(index);
            compromisso.marcarComoConcluido();
            compromissos.remove(index);
            salvarCompromissos();
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

    // Método para salvar os compromissos em um arquivo
    public void salvarCompromissos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_DADOS))) {
            oos.writeObject(compromissos);
            System.out.println("Compromissos salvos com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar compromissos: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void carregarCompromissos() {
        File arquivo = new File(ARQUIVO_DADOS);
        if (arquivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO_DADOS))) {
                compromissos = (List<Compromisso>) ois.readObject();
                System.out.println("Compromissos carregados com sucesso!");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Erro ao carregar compromissos: " + e.getMessage());
            }
        } else {
            System.out.println("Arquivo de compromissos não encontrado. Criando novo arquivo...");
        }
    }
}