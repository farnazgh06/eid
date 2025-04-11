package db;

import db.exception.InvalidEntityException;
import db.EntityNotFoundException;
import todo.entity.Step;
import todo.entity.Task;

import java.util.*;

public class Database {
    private static ArrayList<Entity> entities = new ArrayList<>();
    private static int IdCounter = 1;
    private static HashMap<Integer, Validator> validators = new HashMap<>();
    private static ArrayList<Step> steps = new ArrayList<>();
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void add (Entity e) throws InvalidEntityException {
        e.id = IdCounter++;

        Validator validator = validators.get(e.getEntityCode());
        if (validator != null){
            validator.validate(e);
        }
        if (e instanceof Trackable){
            Date date = new Date();
            Trackable trackableE = (Trackable) e;
            trackableE.setLastModificationDate(date);
            trackableE.setCreationDate(date);
        }

        entities.add(e.copy());
    }

    public static Entity get(int id) throws EntityNotFoundException {
        for (Entity entity : entities) {
            if (entity.id == id) {
                return entity.copy();
            }
        }
        throw new EntityNotFoundException(id);
    }

    public static void delete(int id) throws EntityNotFoundException {
        for(Entity entity : entities) {
            if (entity.id == id) {
                entities.remove(entity);
                return;
            }
        }
        throw new EntityNotFoundException(id);

    }
    public static void update(Entity e) throws EntityNotFoundException, InvalidEntityException {
        for (Entity entity : entities) {
            if (entity.id == e.id) {

                Validator validator = validators.get(e.getEntityCode());
                if (validator != null) {
                    validator.validate(e);
                }

                if (e instanceof Trackable){
                    Date date = new Date();
                    Trackable trackableE = (Trackable) e;
                    trackableE.setLastModificationDate(new Date());
                }

                entities.remove(entity);
                entities.add(e.copy());

                return;
            }
        }
        throw new EntityNotFoundException(e.id);
    }

    public static void registerValidator(int entityCode, Validator validator) {
        if (validators.get(entityCode) != null){
            throw new IllegalArgumentException("there is a Validator!");
        }
        validators.put(entityCode,validator);
    }

    public static ArrayList<Entity> getAll(int entityCode) {
        ArrayList<Entity> result = new ArrayList<>();
        for (Entity entity : entities) {
            if (entity.getEntityCode() == entityCode) {
                result.add(entity);
            }
        }
        return result;
    }

    public static ArrayList<Entity> getAllEntities() {
        ArrayList<Entity> all = new ArrayList<>();
        for (Entity e : entities) {
            all.add(e.copy());
        }
        return all;
    }
    public static boolean deleteEntity(int id) {
        try {
            delete(id);
            return true;
        } catch (EntityNotFoundException e) {
            return false;
        }
    }

    public static Task getTaskById(int id) throws EntityNotFoundException {
        return (Task) get(id);
    }

    public static void updateTask(Task task) throws EntityNotFoundException, InvalidEntityException {
        update(task);
    }

    public static Step getStepById(int id) throws EntityNotFoundException {
        return (Step) get(id);
    }

    public static void updateStep(Step step) throws EntityNotFoundException, InvalidEntityException {
        update(step);
    }

    public static ArrayList<Step> getStepsByTaskId(int taskId) {
        ArrayList<Step> taskSteps = new ArrayList<>();
        for (Step step : steps) {
            if (step.getTaskId() == taskId) {
                taskSteps.add(step);
            }
        }
        return taskSteps;
    }
    public static ArrayList<Task> getAllTasksSorted() {
        tasks.sort(Comparator.comparing(Task::getCreationDate));
        return tasks;
    }
    public static ArrayList<Task> getIncompleteTasksSorted() {
        ArrayList<Task> incompleteTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getStatus() == Task.Status.NotStarted || task.getStatus() == Task.Status.InProgress) {
                incompleteTasks.add(task);
            }
        }
        incompleteTasks.sort(Comparator.comparing(Task::getCreationDate));
        return incompleteTasks;
    }

    public static void Task(Task task) {
    }
}
