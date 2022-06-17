package Entity;


import jakarta.persistence.*;

import java.util.Date;

@Entity
public class HomeWork extends Task {

    @Enumerated(EnumType.STRING)
    final private Type type = Type.HomeWork ;
    private String course ;
    private String details ;
    @Basic
    private Date dueDate ;

}
