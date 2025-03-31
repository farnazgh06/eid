package db;

import java.util.ArrayList;

public class Database {
    private static ArrayList<Entity> entities = new ArrayList<>();
    private static int IdCounter = 1;

    public static void add (Entity e){
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
    public static void update(Entity e) throws EntityNotFoundException {
        for(Entity entity : entities) {
            if (entity.id == e.id) {
                entities.remove(entity);
                entities.add(e.copy());
                return;
            }
        }
        throw new EntityNotFoundException();
    }
}