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
