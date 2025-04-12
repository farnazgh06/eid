package db;

import db.exception.InvalidEntityException;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Database {
    private static ArrayList<Entity> entities = new ArrayList<>();
    private static int IdCounter = 1;
    private static HashMap<Integer, Validator> validators = new HashMap<>();

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
}