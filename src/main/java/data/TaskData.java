package data;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.hibernate.annotations.Type;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ChoreData.class, name = "Chore"),
        @JsonSubTypes.Type(value = HomeWorkData.class, name = "HomeWork")
})
public abstract class TaskData{
    public TaskData() {
    }
}
