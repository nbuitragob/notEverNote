package Model;

import org.springframework.data.annotation.Id;
import Controller.UserService;
import Controller.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class Note {

    @Id
    public String id;

    private UserService uService;
    @Autowired
    private UserRepository uRepository;

    private String title;
    private String description;
    private String note_date;
    private String username;

    public Note() {}

    public Note(String title, String text, String date, String username) {
        this.description = new String(text);
        this.note_date = new String(date);
        this.title = title;
        this.username = username;
    }

    @Override
    public String toString() {
        return String.format(
                "Note[id=%s, title='%s', note_date='%s', description='%s', username='%s']",
                id, title, note_date, description, username);
    }

    public String getTitle(){
        return this.title;
    }

    public String getUsername(){
        return this.username;
    }

    public void update(Note note){
        this.description = note.description;
        this.title = note.title;
    }
}

