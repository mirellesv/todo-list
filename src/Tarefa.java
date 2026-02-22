import java.time.LocalDate;

public class Tarefa {
    private String nome;
    private String descricao;
    private LocalDate dataTermino;
    private NivelPrioridade nivelPrioridade;
    private String categoria;
    private Status status;

    public Tarefa(String nome, String descricao, LocalDate dataTermino, int nivelPrioridade, String categoria, int indicadorStatus) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataTermino = dataTermino;
        this.nivelPrioridade = new NivelPrioridade(nivelPrioridade);
        this.categoria = categoria;
        this.status = new Status(indicadorStatus);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public NivelPrioridade getNivelPrioridade() {
        return nivelPrioridade;
    }

    public void setNivelPrioridade(int nivelPrioridade) {
        this.nivelPrioridade = new NivelPrioridade(nivelPrioridade);
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(LocalDate dataTermino) {
        this.dataTermino = dataTermino;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Status getStatus() { return status; }

    public void setStatus(int indicadorStatus) {
        this.status = new Status(indicadorStatus);
    }

    @Override
    public String toString() {
        return "Tarefa{" +
                "nome=" + nome +
                ", descricao=" + descricao +
                ", dataTermino=" + dataTermino +
                ", nivelPrioridade=" + nivelPrioridade +
                ", categoria=" + categoria +
                ", status=" + status +
                '}';
    }
}
