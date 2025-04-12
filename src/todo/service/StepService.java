package todo.service;

import db.Database;
import db.exception.EntityNotFoundException;
import db.exception.InvalidEntityException;
import todo.entity.Step;
import todo.entity.Task;

public class StepService {
    public static void saveStep(int taskRef, String title) throws EntityNotFoundException, InvalidEntityException {

        Task task = (Task) Database.get(taskRef);
        Step step = new Step(title, taskRef);

        step.setStatus(Step.Status.NotStarted);
        Database.add(step);
    }

    public static void updateStep(int id, String field, String newValue) throws EntityNotFoundException {
        Step step = (Step) Database.get(id);
        if (step == null) {
            throw new EntityNotFoundException("Cannot find step with ID=" + id);
        }
    }
}
