package Entity;

import jakarta.persistence.*;

import static jakarta.persistence.InheritanceType.JOINED;

@Entity
@Inheritance(strategy=JOINED)
@Table(name = "Tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String id ;

    @ManyToOne
    @JoinColumn(name = "ownerId")
    private People person ;

    @Enumerated(EnumType.STRING)
    private Status status ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}


