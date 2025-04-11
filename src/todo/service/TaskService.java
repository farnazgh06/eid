package todo.service;

import db.exception.InvalidEntityException;
import todo.entity.Task;

import static db.Database.add;

public class TaskService {
    public static void setAsCompleted(int taskId) {

    }
    public static void saveTask(Task task) throws InvalidEntityException {
        add(task);
    }
}