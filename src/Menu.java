import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Menu {
    public static void menu(){
        int resposta;
        Scanner leitura = new Scanner(System.in);

        do{
            System.out.println("GERENCIADOR DE TAREFAS");
            System.out.println("1 - Criar tarefa");
            System.out.println("2 - Acessar tarefas");
            System.out.println("3 - Atualizar tarefas");
            System.out.println("4 - Apagar tarefas");
            System.out.println("5 - Sair");
            System.out.println("Digite a opção desejada: ");
            resposta = Integer.parseInt(leitura.nextLine());
        }while(resposta < 0);
    }
}
