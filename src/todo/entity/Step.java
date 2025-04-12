package todo.entity;

import db.Entity;

import java.util.Date;

public class Step extends Entity {
    public static final int STEP_ENTITY_CODE = 17;

    private String title;
    private Status status;
    private int taskRef;
    private Date creationDate;
    public enum Status {
        NotStarted , Completed;
    }

    public Step(String title, int taskRef){
        this.title = title;
        this.taskRef = taskRef;
        this.status = Status.NotStarted;
        this.creationDate = new Date();
    }

    @Override
    public Entity copy() {
        Step copy = new Step(this.title, this.taskRef);
        copy.id = this.id;
        copy.status = this.status;
        return copy;
    }

    @Override
    public int getEntityCode() {
        return STEP_ENTITY_CODE;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int gettaskRef() {
        return taskRef;
    }

    public void settaskRef(int taskRef) {
        this.taskRef = taskRef;
    }
    public Date getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(Date creationDate){
        this.creationDate = creationDate;
    }

}
