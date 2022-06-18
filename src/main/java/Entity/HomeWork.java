package Entity;


import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
public class HomeWork extends Task {

    @Enumerated(EnumType.STRING)
    final private Type type = Type.HomeWork ;
    private String course ;
    private String details ;
    @Basic
    private Date dueDate ;

    public Type getType() {
        return type;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
