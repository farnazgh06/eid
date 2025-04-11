import java.util.*;
import db.Database;
import todo.entity.Step;
import todo.entity.Task;
import todo.validator.StepValidator;
import todo.validator.TaskValidator;
import todo.service.TaskService;
import todo.service.StepService;
public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        TaskValidator taskValidator = new TaskValidator();
        StepValidator stepValidator = new StepValidator();

        while (true) {
            String order = scn.nextLine();

            if (order.equals("exit"))
                break;

            if (order.equals("add task")) {
                try {
                    System.out.print("Title: ");
                    String title = scn.nextLine();
                    System.out.print("Description: ");
                    String description = scn.nextLine();
                    System.out.print("Due date: ");
                    String dueDate = scn.nextLine();

                    Task task = new Task(title, description, dueDate);
                    task.setStatus(Task.Status.NotStarted);
                    taskValidator.validate(task);
                    Database.Task(task);

                    System.out.println("Task saved successfully.");
                    System.out.println("ID: " + task.getId());

                }
                catch (Exception e) {
                    System.out.println("Cannot save task.");
                    System.out.println("Error: " + e.getMessage());
                }

            }
            else if (order.equals("add step")) {
                try {
                    System.out.print("TaskID: ");
                    int taskId = Integer.parseInt(scn.nextLine());
                    System.out.print("Title: ");
                    String title = scn.nextLine();

                    Step step = new Step(title, taskId);
                    stepValidator.validate(step);
                    Database.saveStep(step);

                    System.out.println("Step saved successfully.");
                    System.out.println("ID: " + step.getId());
                    System.out.println("Creation Date: " + step.getCreatedAt());
                } catch (Exception e) {
                    System.out.println("Cannot save step.");
                    System.out.println("Error: " + e.getMessage());
                }
            }
            else if (order.equals("delete")) {
                try {
                    System.out.print("ID: ");
                    int id = Integer.parseInt(scn.nextLine());
                    boolean deleted = Database.deleteEntity(id);
                    if (deleted) {
                        System.out.println("Entity with ID=" + id + " successfully deleted.");
                    } else {
                        System.out.println("Cannot delete entity with ID=" + id + ".");
                    }
                } catch (Exception e) {
                    System.out.println("Cannot delete entity.");
                    System.out.println("Error: " + e.getMessage());
                }
            }
            else if (order.equals("update task")) {
                try {
                    System.out.print("ID: ");
                    int id = Integer.parseInt(scn.nextLine());
                    System.out.print("Field: ");
                    String field = scn.nextLine();
                    System.out.print("New Value: ");
                    String newValue = scn.nextLine();

                    Task task = Database.getTaskById(id);
                    if (task == null) throw new Exception("Cannot find task with id=" + id);

                    String oldValue = switch (field) {
                        case "title" -> task.getTitle();
                        case "description" -> task.getDescription();
                        case "dueDate" -> task.getDueDate().toString();
                        case "status" -> task.getStatus().name();
                        default -> throw new IllegalArgumentException("Invalid field.");
                    };

                    TaskService.updateTask(id, field, newValue);

                    System.out.println("Successfully updated the task.");
                    System.out.println("Field: " + field);
                    System.out.println("Old Value: " + oldValue);
                    System.out.println("New Value: " + newValue);
                    System.out.println("Modification Date: " + new Date());
                }
                catch (Exception e) {
                    System.out.println("Cannot update task.");
                    System.out.println("Error: " + e.getMessage());
                }
            }
            else if (order.equals("update step")) {
                try {
                    System.out.print("ID: ");
                    int id = Integer.parseInt(scn.nextLine());
                    System.out.print("Field: ");
                    String field = scn.nextLine();
                    System.out.print("New Value: ");
                    String newValue = scn.nextLine();

                    Step step = Database.getStepById(id);
                    if (step == null) throw new Exception("Cannot find step with ID=" + id);

                    String oldValue = switch (field) {
                        case "status" -> step.getStatus().name();
                        default -> throw new IllegalArgumentException("Invalid field");
                    };

                    StepService.updateStep(id, field, newValue);

                    System.out.println("Successfully updated the step.");
                    System.out.println("Field: " + field);
                    System.out.println("Old Value: " + oldValue);
                    System.out.println("New Value: " + newValue);
                    System.out.println("Modification Date: " + new Date());
                } catch (Exception e) {
                    System.out.println("Cannot update step.");
                    System.out.println("Error: " + e.getMessage());
                }
            }
            else if (order.equals("get task-by-id")) {
                try {
                    System.out.print("ID: ");
                    int id = Integer.parseInt(scn.nextLine());

                    Task task = Database.getTaskById(id);
                    if (task == null)
                        throw new Exception("Cannot find task with ID=" + id);

                    System.out.println("ID: " + task.getId());
                    System.out.println("Title: " + task.getTitle());
                    System.out.println("Due Date: " + task.getDueDate());
                    System.out.println("Status: " + task.getStatus());
                    System.out.println("Steps:");
                    for (Step step : Database.getStepsByTaskId(id)) {
                        System.out.println("    + " + step.getTitle() + ":");
                        System.out.println("        ID: " + step.getId());
                        System.out.println("        Status: " + step.getStatus());
                    }
                }
                catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
            else if (order.equals("get all-tasks")) {
                List<Task> tasks = Database.getAllTasksSorted();
                for (Task task : tasks) {
                    printTaskDetails(task);
                }
            }
            else if (order.equals("get incomplete-tasks")) {
                List<Task> tasks = Database.getIncompleteTasksSorted();
                for (Task task : tasks) {
                    printTaskDetails(task);
                }
            }
            else {
                System.out.println("Unknown order.");
            }
        }
    }

    private static void printTaskDetails(Task task) {
        System.out.println("ID: " + task.getId());
        System.out.println("Title: " + task.getTitle());
        System.out.println("Due Date: " + task.getDueDate());
        System.out.println("Status: " + task.getStatus());
        List<Step> steps = Database.getStepsByTaskId(task.getId());
        if (!steps.isEmpty()) {
            System.out.println("Steps:");
            for (Step step : steps) {
                System.out.println("    + " + step.getTitle() + ":");
                System.out.println("        ID: " + step.getId());
                System.out.println("        Status: " + step.getStatus());
            }
        }
        System.out.println();
    }
}
