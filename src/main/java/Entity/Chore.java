package Entity;
import jakarta.persistence.*;

@Entity
@Table
public class Chore extends Task {

    @Enumerated(EnumType.STRING)
    final private Type type = Type.Chore ;

    private String description ;

    @Enumerated(EnumType.STRING)
    private Size size ;


}
