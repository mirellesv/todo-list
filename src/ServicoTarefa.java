import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ServicoTarefa {

    private final List<Tarefa> tarefas = new ArrayList<>();

    public void adicionar(Tarefa tarefa) {
        tarefas.add(tarefa);
    }

    public boolean estaVazia() {
        return tarefas.isEmpty();
    }

    public int tamanho() {
        return tarefas.size();
    }

    public Tarefa obter(int indice) {
        return tarefas.get(indice);
    }

    public void remover(int indice) {
        tarefas.remove(indice);
    }

    public List<Tarefa> listar() {
        return List.copyOf(tarefas);
    }

    public void ordenarPorPrioridade() {
        tarefas.sort(Comparator.comparing(Tarefa::getNivelPrioridade));
    }

    public void ordenarPorCategoria() {
        tarefas.sort(Comparator.comparing(
                Tarefa::getCategoria,
                String.CASE_INSENSITIVE_ORDER
        ));
    }

    public void ordenarPorStatus() {
        tarefas.sort(Comparator.comparing(Tarefa::getStatus));
    }
}