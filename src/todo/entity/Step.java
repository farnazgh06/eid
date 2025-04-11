package todo.entity;

import db.Entity;

import java.util.Date;

public class Step extends Entity {
    public static final int TASK_ENTITY_CODE = 17;
    private String title;
    private Status status;
    private int taskRef;
    private int taskId;
    public int getTaskId() {
        return taskId;
    }

    enum Status {
        NotStarted , Completed;
    }
    public Step(String title, int taskRef){
        this.title = title;
        this.taskRef = taskRef;
        this.status = Status.NotStarted;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }

    public int getTaskRef() {
        return taskRef;
    }
    public int getId() {
        return this.id;
    }
    public Date getCreatedAt() {
        return this.creationDate;
    }

    @Override
    public Entity copy() {
        return null;
    }

    @Override
    public int getEntityCode() {
        return TASK_ENTITY_CODE;
    }


}
