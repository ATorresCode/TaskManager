import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;

public class TaskManager {
    public static void main(String[] args) {
        int opcion;
        Task[] tasksToDo = new Task[10];
        Task[] tasksDone = new Task[10];

        Scanner sc = new Scanner(System.in);
        do {
            showMenu();
            opcion = sc.nextInt();

            switch (opcion) {
                case 0:
                    System.out.println("Saliendo de la aplicación");
                    break;
                case 1:
                    System.out.println("Nombre de la tarea:");
                    sc.nextLine();
                    String name = sc.nextLine();
                    System.out.println("Fecha de finalización (dd/mm/aaaa):");
                    String dueDate = sc.nextLine();

                    Task newTask = new Task (name, LocalDate.parse(dueDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")));

                    addTask(tasksToDo, newTask);
                    System.out.println("La tarea se ha añadido correctamente");
                    break;
                case 2:
                    System.out.println("Qué tarea deseas realizar?");
                    showTasks(tasksToDo);
                    int taskNumber = sc.nextInt() - 1;
                    completeTask(tasksToDo, taskNumber, tasksDone);
                    break;
                case 3:
                    clearTasks(tasksToDo);
                    break;
                case 4:
                    showTasks(tasksToDo);
                    System.out.println("Qué tarea deseas modificar?");
                    int taskToModify = sc.nextInt() - 1;

                    System.out.println("Nuevo nombre");
                    sc.nextLine();
                    String newName = sc.nextLine();

                    System.out.println("Nueva fecha de finalización (dd/mm/aaaa):");
                    String newDueDate = sc.nextLine();

                    modifyTask(tasksToDo, taskToModify, newName, LocalDate.parse(newDueDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    System.out.println("La tarea se ha modificado correctamente");
                    break;
                case 5:
                    orderTasks(tasksToDo);
                    System.out.println("Tareas a realizar:");
                    showTasks(tasksToDo);
                    System.out.println("Tareas realizadas:");
                    showTasks(tasksDone);
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;

            }

        } while (opcion != 0);
    }

    public static void showMenu(){
        System.out.println("Gestión tareas. Elige una opción");
        System.out.println("0. Salir de la aplicación");
        System.out.println("1. Añadir una tarea");
        System.out.println("2. Realizar una tarea");
        System.out.println("3. Borrar todas las tareas");
        System.out.println("4. Modificar una tarea");
        System.out.println("5. Mostrar todas las tareas");

    }

    public static int numTasks (Task[] tasks) {
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] == null) {
                return i;
            }
        }
        return tasks.length;
    }

    public static void addTask(Task[] tasks, Task newTask) {
        tasks[numTasks(tasks)] = newTask;
    }

    public static void completeTask(Task[] tasksToDo, int taskNumber, Task[] tasksDone) {
        tasksToDo[taskNumber].completeTask();
        addTask(tasksDone, tasksToDo[taskNumber]);
        deleteTask(tasksToDo, taskNumber);
    }

    public static void deleteTask (Task[] tasks, int taskNumber) {
        for (int i = taskNumber; i < tasks.length - 1; i++) {
            tasks[i] = tasks[i + 1];
        }
        tasks[tasks.length - 1] = null;
    }

    public static void clearTasks(Task[] tasks) {
        Arrays.fill(tasks, null);
    }

    public static void modifyTask(Task[] tasks, int taskNumber, String newName, LocalDate newDueDate) {
        tasks[taskNumber].setName(newName);
        tasks[taskNumber].setDueDate(newDueDate);
    }

    public static void showTasks(Task[] tasks) {
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] != null) {
                System.out.println((i + 1) + " - " + tasks[i]);
            }
        }
    }

    public static void orderTasks(Task[] tasks) {
        for (int i = 0; i < tasks.length && tasks[i] != null; i++) {
            for (int j = i; j < tasks.length && tasks[j] != null; j++) {
                if (tasks[i].getDueDate().isAfter(tasks[j].getDueDate())) {
                    Task temp = tasks[i];
                    tasks[i] = tasks[j];
                    tasks[j] = temp;
                }
            }
        }
    }
}
