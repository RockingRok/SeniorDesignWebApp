package webapp;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class UserInstance {
    @Id long id;
    String name;

    public UserInstance(String name) {
        this.name = name;
    }
}