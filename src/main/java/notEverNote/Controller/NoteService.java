package Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Model.User;
import Model.Note;
import Controller.UserRepository;
import java.util.List;
import java.util.Optional;

import Model.NoteCreateReq;

@Service
public class NoteService {
    
    @Autowired
    private NoteRepository dataBase;

    public NoteService(NoteRepository dataBase){
        this.dataBase = dataBase;
    }

    public Note create(Note note){
        dataBase.save(note);
        return note;
    }

    public Note delete(String username, String title){
        Note deleted = findNoteByUsernameTitle(username, title);
        dataBase.delete(deleted);
        return deleted;
    }

    public Note read(String username, String title){
        return dataBase.findByUsernameTitle(username, title);
    }

    public List<Note> readAll(String username){
        return dataBase.findByUsername(username);
    }

    public Note update(Note note){
        Note foundNote = findNoteByUsernameTitle(note.getUsername(), note.getTitle());
        foundNote.update(note);
        foundNote = dataBase.save(foundNote);
        return foundNote;

    }

    public void deleteByUsername(String username){
        for (Note note : dataBase.findByUsername(username)){
            dataBase.delete(note);
        }
    }

    public void deleteAll(){
        dataBase.deleteAll();
    }

    public Note findNoteByUsernameTitle(String username, String title){
        Note result = dataBase.findByUsernameTitle(username, title);
        return result;
    }
    
}
