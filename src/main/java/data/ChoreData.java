package data;


import Entity.Size;
import Entity.Status;

public class ChoreData extends TaskData{


    private Status status ;
    private String description ;
    private Size size ;

    public ChoreData(Status status, String description, Size size) {
        super();
        this.status = status;
        this.description = description;
        this.size = size;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }
}
