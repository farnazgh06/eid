
import db.Database;
import todo.entity.Step;
import todo.entity.Task;

import java.util.*;

import todo.service.TaskService;
import todo.service.StepService;

public class Main {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static  ArrayList<Step> steps = new ArrayList<>();

    private static Task getTaskById(int taskId) {
        for (Task task : tasks) {
            if (task.getId() == taskId) {
                return task;
            }
        }
        return null;
    }

    private static Step getStepById(int taskRef) {
        for (Step step : steps) {
            if (step.gettaskRef() == taskRef) {
                return step;
            }
        }
        return null;
    }


    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        HashMap<String, Runnable> order = new HashMap<>();

        order.put("add task", () -> {
            try {
                System.out.print("Title: ");
                String title = scn.nextLine();
                System.out.print("Description: ");
                String description = scn.nextLine();
                System.out.print("Due date: ");
                String date = scn.next();

                Task task = new Task(title, description, date);
                tasks.add(task);

                System.out.println("Task saved successfully.");
                System.out.println("ID: " + task.getId());


            }
            catch (Exception e) {
                System.out.println("Cannot save task.");
                System.out.println("Error: Task title cannot be empty.");
            }
        });
        order.put("add step", () -> {
            try{
                System.out.println("TaskID: ");
                int taskID = scn.nextInt();
                System.out.print("Step Title: ");
                String title = scn.nextLine();

                Step step = new Step(title,taskID);
                steps.add(step);

                System.out.println("Step saved successfully.");
                System.out.println("ID:" + step.gettaskRef());
                System.out.println("Creation Date:" + step.getCreationDate());
            } catch (Exception e) {
                System.out.println("Cannot save step.");
                System.out.println("Error: Cannot find task with this ID");
            }
        });
        order.put("delete", () -> {
            try {
                System.out.println("ID: ");
                int idToDelete = scn.nextInt();

                if (getTaskById(idToDelete) != null) {
                    tasks.remove(getTaskById(idToDelete));
                    System.out.println("Entity with ID=" + idToDelete + " successfully deleted.");
                }

                if (getStepById(idToDelete) != null) {
                    steps.remove(getStepById(idToDelete));
                    System.out.println("Entity with ID=" + idToDelete + " successfully deleted.");

                }
            } catch (Exception e) {
                System.out.println("Cannot delete entity with this ID.");
                System.out.println("Error: Something happend");
            }
        });
        order.put("update task", () -> {
            try {
                System.out.print("ID: ");
                int id = Integer.parseInt(scn.nextLine());
                System.out.print("Field: ");
                String field = scn.nextLine();
                System.out.print("New Value: ");
                String newValue = scn.nextLine();

                TaskService.updateTask(id, field, newValue);
                Task task;
                Task.Status oldStatus = task.getStatus();

                System.out.println("Successfully updated the task.");
                System.out.println("Field: " + field);
                System.out.println("Old Value: " + oldStatus);
                System.out.println("New Value: " + newValue);
                System.out.println("Modification Date: " + new Date());

            } catch (Exception e) {
                System.out.println("Cannot update task with this ID");
                System.out.println("Error: Cannot find entity with this ID");
            }
        });

        order.put("update step", () -> {
            try {
                System.out.print("ID: ");
                int id = scn.nextInt();

                System.out.print("Field: ");
                String field = scn.nextLine();

                System.out.print("New Value: ");
                String newValue = scn.nextLine();

                StepService.updateStep(id, field, newValue);
                Step step;
                Step.Status oldStatus = step.getStatus();

                System.out.println("Successfully updated the step.");
                System.out.println("Field: " + field);
                System.out.println("Old Value: " + oldStatus);
                System.out.println("New Value: " + newValue);
                System.out.println("Modification Date: " + new Date());

            } catch (Exception e) {
                System.out.println("Cannot update task with this ID");
                System.out.println("Error: Cannot find entity with this ID");
            }
        });
        order.put("get task-by-id", () -> {
            try {
                System.out.print("ID: ");
                int id = scn.nextInt();

                Task task = getTaskById(id);

                System.out.println("ID: " + task.id);
                System.out.println("Title: " + task.getTitle());
                System.out.println("Due Date: " + task.getDueDate());
                System.out.println("Status: " + task.getStatus());


                if (!(steps.isEmpty())) {
                    for (Step step : steps) {
                        System.out.println("        + Complete the database:");
                        System.out.println("        ID: " + step.id);
                        System.out.println("        Status: " + step.getStatus());
                        System.out.println("        + Implement the to-do list:");
                        System.out.println("        ID: " + step.id);
                        System.out.println("        Status: " + step.getStatus());
                    }
                }
            } catch (Exception e) {
                System.out.println("Cannot update task with this ID");
            }
        });
        order.put("get all-tasks", () -> {
            for (Task task : tasks) {
                System.out.println("ID: " + task.id);
                System.out.println("Title: " + task.getTitle());
                System.out.println("Due Date: " + task.getDueDate());
                System.out.println("Status: " + task.getStatus());
                ArrayList<Step> steps = task.getSteps();
                for (Step step : steps) {
                    System.out.println("    + " + step.getTitle());
                    System.out.println("        ID: " + step.id);
                    System.out.println("        Status: " + step.getStatus());
                }
            }
        });
        order.put("get incomplete-tasks", () -> {
            for (Task task : tasks) {
                System.out.println("ID: " + task.id);
                System.out.println("Title: " + task.getTitle());
                System.out.println("Due Date: " + task.getDueDate());
                System.out.println("Status: " + task.getStatus());
                ArrayList<Step> steps = task.getSteps();
                for (Step step : steps) {
                    System.out.println("    + " + step.getTitle());
                    System.out.println("        ID: " + step.id);
                    System.out.println("        Status: " + step.getStatus());
                }
            }
        });
        order.put("exit", () -> {
            System.out.println("Exit.");
        });

    }

}



