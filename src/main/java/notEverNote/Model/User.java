package Model;

import java.util.List;
import org.springframework.data.annotation.Id;

public class User {
    @Id
    public String id;

    private String username;
    private String password;

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format(
                "User[id=%s, username='%s']",
                id, username, password);
    }

    public String getUsername(){
        return username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getId(){
        return id;
    }


}

