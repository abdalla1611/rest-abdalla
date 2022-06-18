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
