package todo.validator;

import db.Entity;
import db.Validator;
import todo.entity.Step;

public class StepValidator implements Validator {
    @Override
    public void validate(Entity entity) {
        if(!(entity instanceof Step)){
            throw new IllegalArgumentException("entity must be Step type");
        }
        Step step = (Step) entity;
        if(step.getTitle() == null){
            throw new IllegalArgumentException("title must not be null");
        }
    }
}
