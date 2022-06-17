package data;


import Entity.Status;

import java.util.Date;

public class HomeWorkData extends TaskData{
    private String course ;
    private String details ;
    private Date dueDate ;

    private Status status ;

    public HomeWorkData(String course, String details, Date dueDate, Status status) {
        super();
        this.course = course;
        this.details = details;
        this.dueDate = dueDate;
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
