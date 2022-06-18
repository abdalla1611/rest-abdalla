package Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "chore_table")
public class Chore extends Task {

    @Enumerated(EnumType.STRING)
    final private Type type = Type.Chore ;

    @Column(name = "description")
    private String description ;

    @Enumerated(EnumType.STRING)
    private Size size ;

    public Chore(Status status, String description, Size size) {
        super(status);
        this.description = description;
        this.size = size;
    }

    public Chore() {

    }

    public Type getType() {
        return type;
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
