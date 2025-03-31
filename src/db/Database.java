package db;

import db.exception.InvalidEntityException;

import java.util.ArrayList;
import java.util.HashMap;

public class Database {
    private static ArrayList<Entity> entities = new ArrayList<>();
    private static int IdCounter = 1;
    private static HashMap<Integer, Validator> validators = new HashMap<>();

    public static void add (Entity e) throws InvalidEntityException {
        Validator validator = validators.get(e.getEntityCode());
        if (validator != null){
            validator.validate(e);
        }
        e.id = IdCounter++;
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
        throw new EntityNotFoundException();

    }
    public static void update(Entity e) throws EntityNotFoundException, InvalidEntityException {
        Validator validator = validators.get(e.getEntityCode());
        if (validator != null){
            validator.validate(e);
        }
        for(Entity entity : entities) {
            if (entity.id == e.id) {
                entities.remove(entity);
                entities.add(e.copy());
                return;
            }
        }
        throw new EntityNotFoundException();
    }
    public static void registerValidator(int entityCode, Validator validator) {
        if (validators.get(entityCode) != null){
            throw new IllegalArgumentException("there is a Validator!");
        }
        validators.put(entityCode,validator);
    }
}