import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;

@Entity
public class Person {
    @Id
    @GeneratedValue
    private String id ;
    private String name ;
    @Id
    private String email ;
    private String favoritePL ;

    public Person() {
    }

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFavoritePL() {
        return favoritePL;
    }

    public void setFavoritePL(String favoritePL) {
        this.favoritePL = favoritePL;
    }



}
