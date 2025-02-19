import java.time.LocalDateTime;

public class Compromisso {
    private String titulo;
    private String descricao;
    private LocalDateTime dataHora;
    private String categoria;
    private boolean lembrete;

    public Compromisso(String titulo, String descricao, LocalDateTime dataHora, String categoria, boolean lembrete) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataHora = dataHora;
        this.categoria = categoria;
        this.lembrete = lembrete;
    }

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

    @Override
    public String toString() {
        return "Compromisso{" +
                "titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataHora=" + dataHora +
                ", categoria='" + categoria + '\'' +
                ", lembrete=" + lembrete +
                '}';
    }
}
