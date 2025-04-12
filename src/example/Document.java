package example;
import db.Trackable;
import db.Entity;

import java.util.Date;

public class Document extends Entity implements Trackable {
    public String content;
    private Date creationDate;
    private Date lastModificationDate;
    public static final int DOCUMENT_ENTITY_CODE = 15;

    public Document(String content){
        this.content = content;
    }

    @Override
    public Entity copy() {
        Document copyDocument = new Document(content);
        copyDocument.id = this.id;
        copyDocument.content = this.content;
        copyDocument.creationDate = this.creationDate;
        copyDocument.lastModificationDate = this.lastModificationDate;

        return copyDocument;
    }

    @Override
    public int getEntityCode() {
        return DOCUMENT_ENTITY_CODE;
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
