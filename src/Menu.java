import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Menu {
    public static void menu(){
        int resposta;
        Scanner leitura = new Scanner(System.in);
        ServicoTarefa servicoTarefa = new ServicoTarefa();

        do{
            System.out.println("GERENCIADOR DE TAREFAS");
            System.out.println("1 - Criar tarefa");
            System.out.println("2 - Acessar tarefas");
            System.out.println("3 - Atualizar tarefas");
            System.out.println("4 - Apagar tarefas");
            System.out.println("5 - Sair");
            System.out.println("Digite a opção desejada: ");
            resposta = Integer.parseInt(leitura.nextLine());

            switch (resposta) {
                case 1 -> criarTarefa(leitura, servicoTarefa);
                case 2 -> acessarTarefa(leitura, servicoTarefa);
                case 3 -> atualizarTarefa(leitura, servicoTarefa);
                case 4 -> removerTarefa(leitura, servicoTarefa);
                case 5 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida!");
            }
        }while(resposta != 5);
    }

    private static void criarTarefa(Scanner leitura, ServicoTarefa servicoTarefa){
        String nomeTarefa, descricaoTarefa, categoriaTarefa;
        LocalDate dataTerminoTarefa;
        int nivelPrioridadeTarefa, statusTarefa;

        System.out.println("--------- Criação de Tarefa ---------");

        System.out.println("Nome da tarefa: ");
        nomeTarefa = leitura.nextLine();

        System.out.println("Breve descrição: ");
        descricaoTarefa = leitura.nextLine();

        System.out.println("Data de término: ");
        dataTerminoTarefa = formataData(leitura.nextLine());

        System.out.println("Nível de prioridade");
        System.out.println("1 - Baixo");
        System.out.println("2 - Moderada");
        System.out.println("3 - Média");
        System.out.println("4 - Alta");
        System.out.println("5 - Crítica");
        nivelPrioridadeTarefa = Integer.parseInt(leitura.nextLine());

        System.out.println("Categoria: ");
        categoriaTarefa = leitura.nextLine();

        System.out.println("Status");
        System.out.println(("1 - TODO"));
        System.out.println("2 - Doing");
        System.out.println("3 - Done");
        int codigoStatus = Integer.parseInt(leitura.nextLine());
        Status status = Status.fromCodigo(codigoStatus);

        Tarefa tarefa = new Tarefa(nomeTarefa, descricaoTarefa, dataTerminoTarefa, nivelPrioridadeTarefa, categoriaTarefa, status);
        servicoTarefa.adicionar(tarefa);
        servicoTarefa.ordenarPorPrioridade();

        for (Tarefa t : servicoTarefa.listar()) {
            System.out.println(t);
        }
    }

    private static void acessarTarefa(Scanner leitura, ServicoTarefa servicoTarefa){
        int opcaoListagem;

        System.out.println("Você deseja listar as tarefas por: ");
        System.out.println("1 - Categoria");
        System.out.println("2 - Prioridade");
        System.out.println("3 - Status");

        opcaoListagem = Integer.parseInt(leitura.nextLine());

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

    private static void atualizarTarefa(Scanner leitura, ServicoTarefa servicoTarefa){
        if(!servicoTarefa.estaVazia()){
            int tarefaEscolhida;

            for(int i = 0; i < servicoTarefa.tamanho(); i++){
                System.out.println("Tarefa " + (i + 1) + ": " + servicoTarefa.obter(i));
            }

            System.out.println("Qual tarefa desejar editar? (1 - " + servicoTarefa.tamanho() + ") ");
            tarefaEscolhida = Integer.parseInt(leitura.nextLine()) - 1;

            System.out.println("Tarefa escolhida: ");
            System.out.println(servicoTarefa.obter(tarefaEscolhida));

            if(tarefaEscolhida >= 0 && tarefaEscolhida < servicoTarefa.tamanho()){
                int propriedadeEscolhida;

                do{
                    System.out.println("O que você deseja alterar? ");
                    System.out.println("1 - Nome");
                    System.out.println("2 - Descrição");
                    System.out.println("3 - Data de término");
                    System.out.println("4 - Nível de prioridade");
                    System.out.println("5 - Categoria");
                    System.out.println("6 - Status");
                    System.out.println("7 - Sair");
                    propriedadeEscolhida = Integer.parseInt(leitura.nextLine());

                    switch(propriedadeEscolhida){
                        case 1:
                            String novoNomeTarefa;

                            System.out.println("Nome: ");
                            System.out.println(servicoTarefa.obter(tarefaEscolhida).getNome());
                            System.out.println("Novo nome: ");
                            novoNomeTarefa = leitura.nextLine();

                            servicoTarefa.obter(tarefaEscolhida).setNome(novoNomeTarefa);

                            System.out.println(servicoTarefa.obter(tarefaEscolhida));

                            System.out.println("Nome alterado com sucesso!");

                            break;
                        case 2:
                            String novaDescTarefa;

                            System.out.println("Descrição: ");
                            System.out.println(servicoTarefa.obter(tarefaEscolhida).getDescricao());
                            System.out.println("Nova descrição: ");
                            novaDescTarefa = leitura.nextLine();

                            servicoTarefa.obter(tarefaEscolhida).setDescricao(novaDescTarefa);

                            System.out.println(servicoTarefa.obter(tarefaEscolhida));

                            System.out.println("Descrição alterada com sucesso!");

                            break;
                        case 3:
                            LocalDate novaDataTarefa;

                            System.out.println("Data de término: ");
                            System.out.println(servicoTarefa.obter(tarefaEscolhida).getDataTermino());
                            System.out.println("Nova data de término (dia/mês/ano): ");
                            novaDataTarefa = formataData(leitura.nextLine());

                            servicoTarefa.obter(tarefaEscolhida).setDataTermino(novaDataTarefa);

                            System.out.println(servicoTarefa.obter(tarefaEscolhida));

                            System.out.println("Data de término alterada com sucesso");

                            break;
                        case 4:
                            int novaPrioridadeTarefa;

                            System.out.println("Nível de prioridade: ");
                            System.out.println(servicoTarefa.obter(tarefaEscolhida).getNivelPrioridade());
                            System.out.println("1 - Baixo");
                            System.out.println("2 - Moderada");
                            System.out.println("3 - Média");
                            System.out.println("4 - Alta");
                            System.out.println("5 - Crítica");
                            System.out.println(("Novo nível de prioridade (1 - 5): "));
                            novaPrioridadeTarefa = Integer.parseInt(leitura.nextLine());

                            servicoTarefa.obter(tarefaEscolhida).setNivelPrioridade(novaPrioridadeTarefa);

                            System.out.println(servicoTarefa.obter(tarefaEscolhida));

                            System.out.println("Nível de prioridade alterado com sucesso!");

                            break;
                        case 5:
                            String novaCategoriaTarefa;

                            System.out.println("Categoria: ");
                            System.out.println(servicoTarefa.obter(tarefaEscolhida).getCategoria());
                            System.out.println("Nova categoria: ");
                            novaCategoriaTarefa = leitura.nextLine();

                            servicoTarefa.obter(tarefaEscolhida).setCategoria(novaCategoriaTarefa);

                            System.out.println(servicoTarefa.obter(tarefaEscolhida));

                            System.out.println(("Categoria alterada com sucesso!"));

                            break;
                        case 6:
                            int codigoNovoStatusTarefa;

                            System.out.println("Status: ");
                            System.out.println(servicoTarefa.obter(tarefaEscolhida).getStatus());
                            System.out.println("1 - TODO");
                            System.out.println("2 - Doing");
                            System.out.println("3 - Done");
                            System.out.println(("Novo status (1 - 3): "));
                            codigoNovoStatusTarefa = Integer.parseInt(leitura.nextLine());

                            Status status = Status.fromCodigo(codigoNovoStatusTarefa);
                            servicoTarefa.obter(tarefaEscolhida).setStatus(status);

                            System.out.println(servicoTarefa.obter(tarefaEscolhida));

                            System.out.println("Status alterado com sucesso!");

                            break;
                        case 7:
                            System.out.println("Voltando ao menu...");
                            break;
                        default:
                            System.out.println("Escolha uma opção válida");
                    }
                }while(propriedadeEscolhida != 7);
            }
        }else{
            System.out.println("Não existem tarefas registradas!");
        }
    }

    private static void removerTarefa(Scanner leitura, ServicoTarefa servicoTarefa){
        if(!servicoTarefa.estaVazia()){
            int tarefaEscolhida;

            for(int i = 0; i < servicoTarefa.tamanho(); i++){
                System.out.println("Tarefa " + (i + 1) + ": " + servicoTarefa.obter(i));
            }

            do{
                System.out.println("Qual tarefa deseja apagar? (1 - " + servicoTarefa.tamanho() + ") ");
                tarefaEscolhida = Integer.parseInt(leitura.nextLine()) - 1;

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

    public static LocalDate formataData(String entradaData){
        try {
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            return LocalDate.parse(entradaData, formato);
        }catch(Exception e){
            System.out.println("Erro ao formatar data: " + e.getMessage());
            throw new IllegalArgumentException(("Formato de data inválido" + entradaData));
        }
    }
}
