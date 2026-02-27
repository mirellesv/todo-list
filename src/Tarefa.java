import java.time.LocalDate;

public class Tarefa {
    private String nome;
    private String descricao;
    private LocalDate dataTermino;
    private NivelPrioridade nivelPrioridade;
    private String categoria;
    private Status status;

    public Tarefa(String nome, String descricao, LocalDate dataTermino, int nivelPrioridade, String categoria, Status status) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataTermino = dataTermino;
        this.nivelPrioridade = new NivelPrioridade(nivelPrioridade);
        this.categoria = categoria;
        this.status = status;
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

    public void setStatus(Status status) { this.status = status; }

    @Override
    public String toString() {
        return "------------------------------\n" +
                "Nome: " + nome + "\n" +
                "Descrição: " + descricao + "\n" +
                "Término: " + dataTermino + "\n" +
                "Prioridade:\n" +
                nivelPrioridade + "\n" +
                "Categoria: " + categoria + "\n" +
                "Status: " + status + "\n" +
                "------------------------------";
    }
}
