import java.io.Serializable;
import java.time.LocalDateTime;

public class Compromisso implements Serializable {
    private static final long serialVersionUID = 1L;

    private String titulo;
    private String descricao;
    private LocalDateTime dataHora;
    private String categoria;
    private boolean lembrete;
    private boolean concluido;

    public Compromisso(String titulo, String descricao, LocalDateTime dataHora, String categoria, boolean lembrete) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataHora = dataHora;
        this.categoria = categoria;
        this.lembrete = lembrete;
        this.concluido = false;
    }

    // Getters e Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean isLembrete() {
        return lembrete;
    }

    public void setLembrete(boolean lembrete) {
        this.lembrete = lembrete;
    }

    public boolean isConcluido() {
        return concluido;
    }

    public void marcarComoConcluido() {
        this.concluido = true;
    }

    @Override
    public String toString() {
        return "Compromisso{" +
                "titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataHora=" + dataHora +
                ", categoria='" + categoria + '\'' +
                ", lembrete=" + lembrete +
                ", concluido=" + concluido +
                '}';
    }
}