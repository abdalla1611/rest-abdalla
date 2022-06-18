package Entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="People")
public class People implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String name ;
    @Column(name = "email" , unique = true)
    private String email ;

    @Column(name = "favoriteProgrammingLanguage")
    private String favoriteProgrammingLanguage ;

    @OneToMany(mappedBy = "person")
    private List<Task> tasks = new ArrayList<>();

    @Column(name = "activeTaskCount",columnDefinition = "0")
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
