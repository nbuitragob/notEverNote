package Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
 
import javax.validation.Valid;
import java.util.List;

import Controller.UserService;
import Controller.NoteService;
import Model.User;
import Model.UserReq;

@RestController
final class UserRestController {

    @Autowired
    private final UserService service;
    
    @Autowired
    private final NoteService noteService;    

    @Autowired
    UserRestController(UserService service, NoteService noteService) {
        this.service = service;
        this.noteService = noteService;
    }
 
    @RequestMapping(value="/user/createUser", method = RequestMethod.POST)
    String create(@RequestBody @Valid User UserEntry) {
        if(service.findUserByUsername(UserEntry.getUsername()) != null){
            return "The user already exist";
        }
        return service.create(UserEntry).toString();
    }
 
    @RequestMapping(value = "/user/{username}", method = RequestMethod.DELETE)
    String delete(@PathVariable("username") String username) throws IllegalArgumentException{
        try{
            noteService.deleteByUsername(username);
            return service.delete(username).toString();
        }catch(IllegalArgumentException ex){
            return "The user does not exist";
        }
    }
 
    @RequestMapping(value="/user", method = RequestMethod.GET)
    List<User> findAll() {
        return service.readAll();
    }
 
    @RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
    String findUserByUsername(@PathVariable("username") String username) {
        return service.findUserByUsername(username).toString();
    }
 
    @RequestMapping(value="/user", method = RequestMethod.PUT)
    String updatePassword(@RequestBody @Valid UserReq user) throws NullPointerException {
        try{
            return service.updatePassword(user.username, user.password).toString();
        }catch(NullPointerException ex){
            return "The user does not exist";
        }
    }
 
 }
