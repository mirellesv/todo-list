import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Menu {
    public static void menu(){
        int resposta;
        Scanner leitura = new Scanner(System.in);
        ArrayList<Tarefa> listaTarefas = new ArrayList<Tarefa>();

        do{
            System.out.println("GERENCIADOR DE TAREFAS");
            System.out.println("1 - Criar tarefa");
            System.out.println("2 - Acessar tarefas");
            System.out.println("3 - Atualizar tarefas");
            System.out.println("4 - Apagar tarefas");
            System.out.println("5 - Sair");
            System.out.println("Digite a opção desejada: ");
            resposta = Integer.parseInt(leitura.nextLine());

            if(resposta == 1){
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
                statusTarefa = Integer.parseInt(leitura.nextLine());

                Tarefa tarefa = new Tarefa(nomeTarefa, descricaoTarefa, dataTerminoTarefa, nivelPrioridadeTarefa, categoriaTarefa, statusTarefa);
                listaTarefas.add(tarefa);

                System.out.println(tarefa);
                System.out.println(listaTarefas);
            }

            if(resposta == 2){
                int opcaoListagem;

                System.out.println("Você deseja listar as tarefas por: ");
                System.out.println("1 - Categoria");
                System.out.println("2 - Prioridade");
                System.out.println("3 - Status");

                opcaoListagem = Integer.parseInt(leitura.nextLine());

                if (opcaoListagem == 1) {
                    listaTarefas.sort((Tarefa t1, Tarefa t2) -> t1.getCategoria().compareToIgnoreCase(t2.getCategoria()));
                }else if(opcaoListagem == 2){
                    listaTarefas.sort(Comparator.comparing(Tarefa::getNivelPrioridade));
                }else if(opcaoListagem == 3){
                    listaTarefas.sort(Comparator.comparing(Tarefa::getStatus));
                }else{
                    System.out.println("Opção inexistente!");
                }

                if(opcaoListagem >= 1 && opcaoListagem <= 3) {
                    for (Tarefa tarefa : listaTarefas) {
                        System.out.println(tarefa);
                    }
                }
            }

            if(resposta == 3){
                if(!listaTarefas.isEmpty()){
                    int tarefaEscolhida;

                    for(int i = 0; i < listaTarefas.size(); i++){
                        System.out.println("Tarefa " + (i + 1) + ": " + listaTarefas.get(i));
                    }

                    System.out.println("Qual tarefa desejar editar? (1 - " + listaTarefas.size() + ") ");
                    tarefaEscolhida = Integer.parseInt(leitura.nextLine()) - 1;

                    System.out.println("Tarefa escolhida: ");
                    System.out.println(listaTarefas.get(tarefaEscolhida));

                    if(tarefaEscolhida >= 0 && tarefaEscolhida < listaTarefas.size()){
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
                                    System.out.println(listaTarefas.get(tarefaEscolhida).getNome());
                                    System.out.println("Novo nome: ");
                                    novoNomeTarefa = leitura.nextLine();

                                    listaTarefas.get(tarefaEscolhida).setNome(novoNomeTarefa);

                                    System.out.println(listaTarefas.get(tarefaEscolhida));

                                    System.out.println("Nome alterado com sucesso!");

                                    break;
                                case 2:
                                    String novaDescTarefa;

                                    System.out.println("Descrição: ");
                                    System.out.println(listaTarefas.get(tarefaEscolhida).getDescricao());
                                    System.out.println("Nova descrição: ");
                                    novaDescTarefa = leitura.nextLine();

                                    listaTarefas.get(tarefaEscolhida).setDescricao(novaDescTarefa);

                                    System.out.println(listaTarefas.get(tarefaEscolhida));

                                    System.out.println("Descrição alterada com sucesso!");

                                    break;
                                case 3:
                                    LocalDate novaDataTarefa;

                                    System.out.println("Data de término: ");
                                    System.out.println(listaTarefas.get(tarefaEscolhida).getDataTermino());
                                    System.out.println("Nova data de término (dia/mês/ano): ");
                                    novaDataTarefa = formataData(leitura.nextLine());

                                    listaTarefas.get(tarefaEscolhida).setDataTermino(novaDataTarefa);

                                    System.out.println(listaTarefas.get(tarefaEscolhida));

                                    System.out.println("Data de término alterada com sucesso");

                                    break;
                                case 4:
                                    int novaPrioridadeTarefa;

                                    System.out.println("Nível de prioridade: ");
                                    System.out.println(listaTarefas.get(tarefaEscolhida).getNivelPrioridade());
                                    System.out.println("1 - Baixo");
                                    System.out.println("2 - Moderada");
                                    System.out.println("3 - Média");
                                    System.out.println("4 - Alta");
                                    System.out.println("5 - Crítica");
                                    System.out.println(("Novo nível de prioridade (1 - 5): "));
                                    novaPrioridadeTarefa = Integer.parseInt(leitura.nextLine());

                                    listaTarefas.get(tarefaEscolhida).setNivelPrioridade(novaPrioridadeTarefa);

                                    System.out.println(listaTarefas.get(tarefaEscolhida));

                                    System.out.println("Nível de prioridade alterado com sucesso!");

                                    break;
                                case 5:
                                    String novaCategoriaTarefa;

                                    System.out.println("Categoria: ");
                                    System.out.println(listaTarefas.get(tarefaEscolhida).getCategoria());
                                    System.out.println("Nova categoria: ");
                                    novaCategoriaTarefa = leitura.nextLine();

                                    listaTarefas.get(tarefaEscolhida).setCategoria(novaCategoriaTarefa);

                                    System.out.println(listaTarefas.get(tarefaEscolhida));

                                    System.out.println(("Categoria alterada com sucesso!"));

                                    break;
                                case 6:
                                    int novoStatusTarefa;

                                    System.out.println("Status: ");
                                    System.out.println(listaTarefas.get(tarefaEscolhida).getStatus());
                                    System.out.println("1 - TODO");
                                    System.out.println("2 - Doing");
                                    System.out.println("3 - Done");
                                    System.out.println(("Novo status (1 - 3): "));
                                    novoStatusTarefa = Integer.parseInt(leitura.nextLine());

                                    listaTarefas.get(tarefaEscolhida).setStatus(novoStatusTarefa);

                                    System.out.println(listaTarefas.get(tarefaEscolhida));

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
        }while(resposta != 5);
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
