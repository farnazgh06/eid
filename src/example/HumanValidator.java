package example;
import db.Entity;
import db.Validator;
import db.exception.InvalidEntityException;

public class HumanValidator implements Validator {
    @Override
    public void validate(Entity entity) throws InvalidEntityException {
        if(!(entity instanceof Human)){
            throw new InvalidEntityException("the Entity isnt Human!");
        }
        if(((Human) entity).age < 0){
            throw new InvalidEntityException("age must not be less than 0!");
        }
        if(((Human) entity).name == null){
            throw new InvalidEntityException("Name must not be null!");
        }
    }
}