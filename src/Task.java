import java.util.Scanner;

public class Task {
    public static void main(String[] args) {
        int opcion;
        String[] tasks = new String[10];

        Scanner sc = new Scanner(System.in);
        do {
            showMenu();
            opcion = sc.nextInt();

            switch (opcion) {
                case 0:
                    System.out.println("Saliendo de la aplicación");
                    break;
                case 1:
                    System.out.println("Qué tarea deseas añadir?");
                    sc.nextLine();
                    addTask(tasks, sc.nextLine());
                    break;
                case 2:
                    System.out.println("Qué tarea deseas realizar?");
                    showTasks(tasks);
                    deleteTask(tasks, sc.nextInt() - 1);
                    break;
                case 3:
                    setTasks(tasks, 0);
                    break;
                case 4:
                    System.out.println("Introduce el número de tareas que deseas establecer");
                    setTasks(tasks, sc.nextInt());
                    break;
                case 5:
                    showTasks(tasks);
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
        System.out.println("4. Establecer el número de tareas");
        System.out.println("5. Mostrar tareas actuales");

    }

    public static int numTasks (String[] tasks) {
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] == null) {
                return i;
            }
        }
        return tasks.length;
    }

    public static void addTask(String[] tasks, String newTask) {
        tasks[numTasks(tasks)] = newTask;
        System.out.println("La tarea se ha añadido correctamente");
    }

    public static void deleteTask(String[] tasks, int taskNumber) {
        for (int i = taskNumber; i < tasks.length - 1; i++) {
            tasks[i] = tasks[i + 1];
        }
        tasks[tasks.length - 1] = null;
        System.out.println("La tarea se ha realizado correctamente");
    }

    public static void setTasks(String[] tasks, int newTasks) {
        for (int i = 0; i < tasks.length; i++) {
            if (i < newTasks && tasks[i] == null) {
                tasks[i] = "Nueva tarea";
            }
            if (i >= newTasks) {
                tasks[i] = null;
            }
        }
        System.out.println("Las tareas se han establecido correctamente a " + newTasks);
    }

    public static void showTasks(String[] tasks) {
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] != null) {
                System.out.println((i + 1) + " - " + tasks[i]);
            }
        }
    }
}
