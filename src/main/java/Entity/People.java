package Entity;

import jakarta.persistence.*;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
@Entity
@Table(name ="People")
public class People {
    @GeneratedValue
    private String id ;
    private String name ;
    @Id
    private String email ;

    private String favoriteProgrammingLanguage ;

    @Column(columnDefinition = "0")
    private int activeTaskCount ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getActiveTaskCount() {
        return activeTaskCount;
    }

    public void IncrementActiveTaskCount(){
        this.activeTaskCount ++;
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

    public String getFavoriteProgrammingLanguage() {
        return favoriteProgrammingLanguage;
    }

    public void setFavoriteProgrammingLanguage(String favoriteProgrammingLanguage) {
        this.favoriteProgrammingLanguage = favoriteProgrammingLanguage;
    }
}
