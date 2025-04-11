package todo.service;

import db.exception.InvalidEntityException;
import todo.entity.Step;

import static db.Database.add;

public class StepService {
    public static void saveStep(Step step)throws InvalidEntityException {
        add(step);
    }


}
