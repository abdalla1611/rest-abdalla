package Entity;

import jakarta.persistence.*;

import java.io.Serializable;

import static jakarta.persistence.InheritanceType.JOINED;

@Entity
@Inheritance(strategy=JOINED)
@Table(name = "Tasks")
public class Task implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String id ;

    @ManyToOne
    @JoinColumn(name = "ownerId" ,referencedColumnName = "id")
    private People person ;

    @Enumerated(EnumType.STRING)
    private Status status ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public People getPerson() {
        return person;
    }

    public void setPerson(People person) {
        this.person = person;
    }


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}


