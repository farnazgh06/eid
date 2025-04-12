package todo.validator;

import db.Database;
import db.Entity;
import db.exception.EntityNotFoundException;
import db.Validator;
import db.exception.InvalidEntityException;
import todo.entity.Step;
import todo.entity.Task;

public class StepValidator implements Validator {

    @Override
    public void validate(Entity entity) throws InvalidEntityException {
        if(!(entity instanceof Step)){
            throw new InvalidEntityException("entity must be Step type");
        }
        Step step = (Step) entity;
        if(step.getTitle() == null){
            throw new InvalidEntityException("title must not be null");
        }
        try {
            int taskRef = step.gettaskRef();
            Entity task = Database.get(taskRef);
            if(!(task instanceof Task)){
                throw new InvalidEntityException("Entity isnt a Task");
            }

        }catch (EntityNotFoundException e){
            throw new InvalidEntityException("taskRef in step is chert!");
        }

    }
}
