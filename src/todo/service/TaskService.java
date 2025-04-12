package todo.service;

import db.exception.EntityNotFoundException;
import db.exception.InvalidEntityException;
import todo.entity.Task;
import db.Database;

public class TaskService {
    public static void setAsCompleted(int taskId) throws EntityNotFoundException, InvalidEntityException {
        Task task = (Task) Database.get(taskId);
        task.setStatus(Task.Status.completed);
        Database.update(task);
    }

    public static void setAsNotStarted(int taskId) throws EntityNotFoundException, InvalidEntityException {
        Task task = (Task) Database.get(taskId);
        task.setStatus(Task.Status.NotStarted);
        Database.update(task);
    }

    public static void updateTask(int id, String field, String newValue) throws EntityNotFoundException {
        Task task = (Task) Database.get(id);
        if (task == null) {
            throw new EntityNotFoundException("Cannot find step with ID=" + id);
        }
    }
}