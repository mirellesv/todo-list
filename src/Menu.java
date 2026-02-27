import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Menu {
    private final ServicoTarefa servicoTarefa;
    private final Scanner leitura;

    public Menu(){
        this.servicoTarefa = new ServicoTarefa();
        this.leitura = new Scanner(System.in);
    }

    public void iniciar(){
        int resposta;

        do{
            System.out.println("GERENCIADOR DE TAREFAS");
            System.out.println("1 - Criar tarefa");
            System.out.println("2 - Acessar tarefas");
            System.out.println("3 - Atualizar tarefas");
            System.out.println("4 - Apagar tarefas");
            System.out.println("5 - Sair");
            resposta = lerInteiro("Digite a opção desejada: ", 5);

            switch (resposta) {
                case 1 -> criarTarefa();
                case 2 -> acessarTarefa();
                case 3 -> atualizarTarefa();
                case 4 -> removerTarefa();
                case 5 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida!");
            }
        }while(resposta != 5);
    }

    private void criarTarefa(){
        String nomeTarefa, descricaoTarefa, categoriaTarefa;
        LocalDate dataTerminoTarefa;
        int nivelPrioridadeTarefa, codigoStatus;

        System.out.println("--------- Criação de Tarefa ---------");

        System.out.println("Nome da tarefa: ");
        nomeTarefa = leitura.nextLine();

        System.out.println("Breve descrição: ");
        descricaoTarefa = leitura.nextLine();

        dataTerminoTarefa = lerData("Data de término (ex: 27/02/2026): ");

        System.out.println("Nível de prioridade: ");
        System.out.println("1 - Baixo");
        System.out.println("2 - Moderada");
        System.out.println("3 - Média");
        System.out.println("4 - Alta");
        System.out.println("5 - Crítica");
        nivelPrioridadeTarefa = lerInteiro("Nível de prioridade (1 - 5): ", 5);

        System.out.println("Categoria: ");
        categoriaTarefa = leitura.nextLine();

        System.out.println("Status");
        System.out.println(("1 - TODO"));
        System.out.println("2 - Doing");
        System.out.println("3 - Done");
        codigoStatus = lerInteiro("Status (1 - 3): ", 3);
        Status status = Status.fromCodigo(codigoStatus);

        Tarefa tarefa = new Tarefa(nomeTarefa, descricaoTarefa, dataTerminoTarefa, nivelPrioridadeTarefa, categoriaTarefa, status);
        servicoTarefa.adicionar(tarefa);
        servicoTarefa.ordenarPorPrioridade();

        for (Tarefa t : servicoTarefa.listar()) {
            System.out.println(t);
        }
    }

    private void acessarTarefa(){
        int opcaoListagem;

        System.out.println("Você deseja listar as tarefas por: ");
        System.out.println("1 - Categoria");
        System.out.println("2 - Prioridade");
        System.out.println("3 - Status");

        opcaoListagem = lerInteiro("Digite a opção desejada (1 - 3): ", 3);

        if (opcaoListagem == 1) {
            servicoTarefa.ordenarPorCategoria();
        }else if(opcaoListagem == 2){
            servicoTarefa.ordenarPorPrioridade();
        }else if(opcaoListagem == 3){
            servicoTarefa.ordenarPorStatus();
        }else{
            System.out.println("Opção inexistente!");
        }

        if(opcaoListagem >= 1 && opcaoListagem <= 3) {
            for (Tarefa tarefa : servicoTarefa.listar()) {
                System.out.println(tarefa);
            }
        }
    }

    private void atualizarTarefa() {
        if (servicoTarefa.estaVazia()) {
            System.out.println("Não existem tarefas registradas!");
            return;
        }

        imprimirTarefas();

        int tarefaEscolhida = lerInteiro(
                "Digite a tarefa desejada (1 - " + servicoTarefa.tamanho() + "): ",
                servicoTarefa.tamanho()
        ) - 1;

        if (tarefaEscolhida < 0 || tarefaEscolhida >= servicoTarefa.tamanho()) {
            System.out.println("Selecione uma tarefa válida!");
            return;
        }

        Tarefa tarefa = servicoTarefa.obter(tarefaEscolhida);

        System.out.println("Tarefa escolhida: ");
        System.out.println(tarefa);

        int propriedadeEscolhida;
        do {
            propriedadeEscolhida = lerMenuAtualizacao();

            switch (propriedadeEscolhida) {
                case 1 -> alterarNome(tarefa);
                case 2 -> alterarDescricao(tarefa);
                case 3 -> alterarDataTermino(tarefa);
                case 4 -> alterarNivelPrioridade(tarefa);
                case 5 -> alterarCategoria(tarefa);
                case 6 -> alterarStatus(tarefa);
                case 7 -> System.out.println("Voltando ao menu...");
                default -> System.out.println("Escolha uma opção válida");
            }
        } while (propriedadeEscolhida != 7);
    }

    private int lerMenuAtualizacao() {
        System.out.println("O que você deseja alterar? ");
        System.out.println("1 - Nome");
        System.out.println("2 - Descrição");
        System.out.println("3 - Data de término");
        System.out.println("4 - Nível de prioridade");
        System.out.println("5 - Categoria");
        System.out.println("6 - Status");
        System.out.println("7 - Sair");
        return lerInteiro("Digite a opção desejada (1 - 7): ", 7);
    }

    private void alterarNome(Tarefa tarefa) {
        System.out.println("Nome atual: " + tarefa.getNome());
        System.out.print("Novo nome: ");
        tarefa.setNome(leitura.nextLine());
        System.out.println("Nome alterado com sucesso!");
        System.out.println(tarefa);
    }

    private void alterarDescricao(Tarefa tarefa) {
        System.out.println("Descrição atual: " + tarefa.getDescricao());
        System.out.print("Nova descrição: ");
        tarefa.setDescricao(leitura.nextLine());
        System.out.println("Descrição alterada com sucesso!");
        System.out.println(tarefa);
    }

    private void alterarDataTermino(Tarefa tarefa) {
        System.out.println("Data atual: " + tarefa.getDataTermino());
        LocalDate novaData = lerData("Nova data de término (ex: 27/02/2026): ");
        tarefa.setDataTermino(novaData);
        System.out.println("Data de término alterada com sucesso!");
        System.out.println(tarefa);
    }

    private void alterarNivelPrioridade(Tarefa tarefa) {
        System.out.println("Nível atual: " + tarefa.getNivelPrioridade());
        System.out.println("1 - Baixo");
        System.out.println("2 - Moderada");
        System.out.println("3 - Média");
        System.out.println("4 - Alta");
        System.out.println("5 - Crítica");
        int novaPrioridade = lerInteiro("Novo nível de prioridade (1 - 5): ", 5);
        tarefa.setNivelPrioridade(novaPrioridade);
        System.out.println("Nível de prioridade alterado com sucesso!");
        System.out.println(tarefa);
    }

    private void alterarCategoria(Tarefa tarefa) {
        System.out.println("Categoria atual: " + tarefa.getCategoria());
        System.out.print("Nova categoria: ");
        tarefa.setCategoria(leitura.nextLine());
        System.out.println("Categoria alterada com sucesso!");
        System.out.println(tarefa);
    }

    private void alterarStatus(Tarefa tarefa) {
        System.out.println("Status atual: " + tarefa.getStatus());
        System.out.println("1 - TODO");
        System.out.println("2 - Doing");
        System.out.println("3 - Done");
        int codigo = lerInteiro("Novo status (1 - 3): ", 3);
        tarefa.setStatus(Status.fromCodigo(codigo));
        System.out.println("Status alterado com sucesso!");
        System.out.println(tarefa);
    }

    private void imprimirTarefas() {
        for (int i = 0; i < servicoTarefa.tamanho(); i++) {
            System.out.println("Tarefa " + (i + 1) + ": " + servicoTarefa.obter(i));
        }
    }

    private void removerTarefa(){
        if(!servicoTarefa.estaVazia()){
            int tarefaEscolhida;

            for(int i = 0; i < servicoTarefa.tamanho(); i++){
                System.out.println("Tarefa " + (i + 1) + ": " + servicoTarefa.obter(i));
            }

            do{
                System.out.println("Qual tarefa deseja apagar? (1 - " + servicoTarefa.tamanho() + ") ");
                tarefaEscolhida = lerInteiro("Digite a tarefa desejada (1 - " + servicoTarefa.tamanho() + "): ", servicoTarefa.tamanho());
                tarefaEscolhida--;

                if(tarefaEscolhida >= 0 && tarefaEscolhida < servicoTarefa.tamanho()){
                    servicoTarefa.remover(tarefaEscolhida);

                    for(int i = 0; i < servicoTarefa.tamanho(); i++){
                        System.out.println("Tarefa " + (i + 1) + ": " + servicoTarefa.obter(i));
                    }

                    System.out.println("Tarefa removida com sucesso!");

                    if(servicoTarefa.tamanho() == 0){
                        break;
                    }
                }else{
                    System.out.println("Selecione uma tarefa válida!");
                }
            }while(tarefaEscolhida < 0 || tarefaEscolhida >= servicoTarefa.tamanho());
        }else{
            System.out.println("Não existem tarefas registradas!");
        }
    }

    private int lerInteiro(String mensagem, int max){
        while (true) {
            System.out.print(mensagem);
            try {
                int valor = Integer.parseInt(leitura.nextLine().trim());
                if (valor < 1 || valor > max) {
                    System.out.println("Digite um número entre " + 1 + " e " + max + ".");
                    continue;
                }
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido!");
            }
        }
    }

    private LocalDate lerData(String mensagem) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true) {
            System.out.print(mensagem);
            try {
                return LocalDate.parse(leitura.nextLine().trim(), formato);
            } catch (Exception e) {
                System.out.println("Formato inválido! Use dd/MM/yyyy (ex: 27/02/2026).");
            }
        }
    }
}
