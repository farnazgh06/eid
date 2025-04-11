package todo.validator;

import db.Entity;
import db.Validator;
import todo.entity.Task;

public class TaskValidator implements Validator {

    @Override
    public void validate(Entity entity) {
        if(!(entity instanceof Task)){
            throw new IllegalArgumentException("entity must be Task type");
        }
        Task task = (Task) entity;
        if(task.getTitle() == null){
            throw new IllegalArgumentException("title must not be null");
        }
    }
}
