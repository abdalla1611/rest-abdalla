package Entity;

import com.fasterxml.jackson.annotation.JsonView;
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

}


