package java.pack.base.gerenciamentodetarefas;

import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Adicionar Tarefa");
            System.out.println("2. Listar Tarefas");
            System.out.println("3. Sair");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer de entrada

            switch (choice) {
                case 1:
                    System.out.println("Adicionar Tarefa:");
                    System.out.print("Título: ");
                    String title = scanner.nextLine();

                    System.out.print("Descrição: ");
                    String description = scanner.nextLine();

                    System.out.print("Data de Vencimento (dd/mm/yyyy): ");
                    String dueDateStr = scanner.nextLine();
                    Date dueDate;
                    try {
                        dueDate = dateFormat.parse(dueDateStr);
                    } catch (ParseException e) {
                        System.out.println("Formato de data inválido. Use dd/mm/yyyy.");
                        continue;
                    }

                    System.out.print("Categoria: ");
                    String category = scanner.nextLine();

                    System.out.print("Prioridade: ");
                    int priority = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer de entrada

                    Task newTask = new Task(title, description, dueDate, category, priority);
                    taskManager.addTask(newTask);

                    System.out.println("Tarefa adicionada com sucesso!");
                    break;
                case 2:
                    System.out.println("Lista de Tarefas:");
                    for (int i = 0; i < taskManager.getTasks().size(); i++) {
                        System.out.println("Tarefa " + (i + 1) + ":");
                        System.out.println(taskManager.getTasks().get(i));
                    }
                    break;
                case 3:
                    System.out.println("Encerrando o programa...");
                    return;
                default:
                    System.out.println("Opção inválida, tente novamente.");

                case 4:
                    // Código para atualizar tarefa
                    System.out.println("Atualizar Tarefa:");
                    System.out.print("Índice da Tarefa a ser atualizada: ");
                    int taskIndex = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer de entrada

                    if (taskIndex >= 1 && taskIndex <= taskManager.getTasks().size()) {
                        Task taskToUpdate = taskManager.getTasks().get(taskIndex - 1);

                        System.out.print("Novo Título: ");
                        taskToUpdate.setTitle(scanner.nextLine());

                        System.out.print("Nova Descrição: ");
                        taskToUpdate.setDescription(scanner.nextLine());

                        System.out.print("Nova Data de Vencimento (dd/mm/yyyy): ");
                        String newDueDateStr = scanner.nextLine();
                        try {
                            Date newDueDate = dateFormat.parse(newDueDateStr);
                            taskToUpdate.setDueDate(newDueDate);
                        } catch (ParseException e) {
                            System.out.println("Formato de data inválido. A data não foi atualizada.");
                        }

                        System.out.print("Nova Categoria: ");
                        taskToUpdate.setCategory(scanner.nextLine());

                        System.out.print("Nova Prioridade: ");
                        taskToUpdate.setPriority(scanner.nextInt());
                        scanner.nextLine(); // Limpar o buffer de entrada

                        System.out.println("Tarefa atualizada com sucesso!");
                    } else {
                        System.out.println("Índice inválido.");
                    }
                    break;

                case 5:
                    // Código para remover tarefa
                    System.out.println("Remover Tarefa:");
                    System.out.print("Índice da Tarefa a ser removida: ");
                    int removeIndex = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer de entrada

                    if (removeIndex >= 1 && removeIndex <= taskManager.getTasks().size()) {
                        taskManager.removeTask(removeIndex - 1);
                        System.out.println("Tarefa removida com sucesso!");
                    } else {
                        System.out.println("Índice inválido.");
                    }
                    break;

                case 6:
                    // Código para filtrar tarefas por categoria
                    System.out.print("Filtrar Tarefas por Categoria: ");
                    String filterCategory = scanner.nextLine();
                    List<Task> filteredByCategory = taskManager.filterTasksByCategory(filterCategory);
                    if (filteredByCategory.isEmpty()) {
                        System.out.println("Nenhuma tarefa encontrada para essa categoria.");
                    } else {
                        System.out.println("Tarefas na categoria '" + filterCategory + "':");
                        for (int i = 0; i < filteredByCategory.size(); i++) {
                            System.out.println("Tarefa " + (i + 1) + ":");
                            System.out.println(filteredByCategory.get(i));
                        }
                    }
                    break;

                case 7:
                    // Código para filtrar tarefas por prioridade
                    System.out.print("Filtrar Tarefas por Prioridade: ");
                    int filterPriority = scanner.nextInt();
                    List<Task> filteredByPriority = taskManager.filterTasksByPriority(filterPriority);
                    if (filteredByPriority.isEmpty()) {
                        System.out.println("Nenhuma tarefa encontrada para essa prioridade.");
                    } else {
                        System.out.println("Tarefas com prioridade '" + filterPriority + "':");
                        for (int i = 0; i < filteredByPriority.size(); i++) {
                            System.out.println("Tarefa " + (i + 1) + ":");
                            System.out.println(filteredByPriority.get(i));
                        }
                    }
                    break;

            }
        }
    }
}
