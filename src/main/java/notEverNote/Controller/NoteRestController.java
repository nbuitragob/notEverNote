package Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
 
import javax.validation.Valid;
import java.util.List;

import Controller.NoteService;
import Model.Note;
import Model.NoteReq;

import Model.NoteCreateReq;

@RestController
final class NoteRestController {
 
    private final NoteService service;
    private final UserService uService;

    @Autowired
    NoteRestController(NoteService service, UserService uService) {
        this.uService = uService;
        this.service = service;
    }
 
    @RequestMapping(value="/note", method = RequestMethod.POST)
    String create(@RequestBody Item recived_note) {
        if(service.findNoteByUsernameTitle(recived_note.username, recived_note.title) != null){
            return "Note exists";
        }
        if(uService.findUserByUsername(recived_note.username) == null){
            return "The user does not exist";
        }
        Note note = new Note(recived_note.title, recived_note.description, recived_note.note_date, recived_note.username);
        return service.create(note).toString();
    }

    public static class Item{
        public String username;
        public String description;
        public String title;
        public String note_date;

        public Item(){}
        public Item(String username, String description, String title, String note_date){
            this.username = username;
            this.description = description;
            this.title = title;
            this.note_date = note_date;
        }
    }

 
    @RequestMapping(value="/note/{username}/{title}", method=RequestMethod.DELETE)    
    String delete(@PathVariable("username") String username, @PathVariable("title") String title) {
        return service.delete(username, title).toString();
    }
 
    @RequestMapping(value="/note/{username}", method = RequestMethod.GET)
    List<Note> findAll(@PathVariable("username") String username) {
        return service.readAll(username);
    }
 
    @RequestMapping(value="/note/{username}/{title}", method=RequestMethod.GET) 
    String findNoteByUsernameTitle(@PathVariable("username") String username, @PathVariable("title") String title) {
        return service.findNoteByUsernameTitle(username, title).toString();
    }
 
    @RequestMapping(value="/note", method=RequestMethod.PUT)
    String update(@RequestBody @Valid Item recived_note) throws NullPointerException {
        try{
            if(uService.findUserByUsername(recived_note.username) == null){
                return "The user does not exist";
            }
            Note note = new Note(recived_note.title, recived_note.description, recived_note.note_date, recived_note.username);
            return service.update(note).toString();
        }catch (NullPointerException ex){
            return "The Note does not exist";
        }
    }
 
 
}
