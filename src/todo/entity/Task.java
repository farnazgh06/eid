package todo.entity;

import db.Entity;
import db.Trackable;

import java.util.Date;

public class Task extends Entity implements Trackable {
    public static final int TASK_ENTITY_CODE = 16;
    private Date creationDate;
    private Date lastModificationDate;
    private String title;
    private String description;
    private Date dueDate;
    private Status status;


    public enum Status {
        Completed, InProgress, NotStarted
    }
    public Task(String title, String description, Date dueDate) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = Status.NotStarted;  // Default status
        this.creationDate = new Date();
        this.lastModificationDate = new Date();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Status getStatus() {
        return status;  // Corrected method signature
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    public int getId() {
        return this.id;
    }
    public Date getCreatedAt() {
        return this.creationDate;
    }


    @Override
    public Entity copy() {
        Task copiedTask = new Task(this.title, this.description, this.dueDate);
        copiedTask.setStatus(this.status);
        copiedTask.setCreationDate(this.creationDate);
        copiedTask.setLastModificationDate(this.lastModificationDate);
        return copiedTask;
    }

    @Override
    public int getEntityCode() {
        return TASK_ENTITY_CODE;
    }

    @Override
    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    @Override
    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public void setLastModificationDate(Date date) {
        this.lastModificationDate = date;
    }

    @Override
    public Date getLastModificationDate() {
        return lastModificationDate;
    }
}
