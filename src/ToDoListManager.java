import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ToDoListManager {
    private final List<Task> tasks;
    private final Scanner scanner;

    public ToDoListManager() {
        tasks = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void start() {
        int choice;
        do {
            System.out.println("\n---- To-Do List Manager ----");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Mark Task as Completed");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    viewTasks();
                    break;
                case 3:
                    markTaskAsCompleted();
                    break;
                case 4:
                    System.out.println("Exiting To-Do List Manager. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (choice != 4);
    }

    private void addTask() {
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();

        System.out.print("Enter due date (yyyy-MM-dd), or leave blank if not applicable: ");
        String dueDateString = scanner.nextLine();
        Date dueDate = parseDate(dueDateString);

        System.out.print("Enter priority (1-5, where 1 is the highest priority): ");
        int priority = scanner.nextInt();

        Task newTask = new Task(description, dueDate, priority);
        tasks.add(newTask);

        System.out.println("Task added successfully!");
    }

    private void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("\nTask " + (i + 1) + ":\n" + tasks.get(i));
            }
        }
    }

    private void markTaskAsCompleted() {
        viewTasks();
        if (tasks.isEmpty()) {
            return;
        }

        System.out.print("Enter the task number to mark as completed: ");
        int taskNumber = scanner.nextInt();
        if (taskNumber >= 1 && taskNumber <= tasks.size()) {
            Task selectedTask = tasks.get(taskNumber - 1);
            selectedTask.completed = true;
            System.out.println("Task marked as completed!");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    private Date parseDate(String dateString) {
        try {
            if (dateString.trim().isEmpty()) {
                return null;
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            System.out.println("Error parsing date. Task will be created without a due date.");
            return null;
        }
    }

    public static void main(String[] args) {
        ToDoListManager toDoListManager = new ToDoListManager();
        toDoListManager.start();
    }
}
