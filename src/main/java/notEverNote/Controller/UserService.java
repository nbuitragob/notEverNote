package Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Model.User;
import Controller.UserRepository;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    
    @Autowired
    private UserRepository dataBase;

    public UserService(UserRepository dataBase){
        this.dataBase = dataBase;
    }

    public User create(User user){
        dataBase.save(user);
        return user;
    }

    public User delete(String username){
        User deleted = findUserByUsername(username);
        dataBase.delete(deleted);
        return deleted;
    }

    public User read(String username){
        return dataBase.findByUsername(username);
    }

    public List<User> readAll(){
        return dataBase.findAll();
    }

    public User updatePassword(String username, String password){
        User foundUser = findUserByUsername(username);
        foundUser.setPassword(password);
        foundUser = dataBase.save(foundUser);
        return foundUser;

    }

    public void deleteAll(){
        dataBase.deleteAll();
    }

    public User findUserByUsername(String username){
        User result = dataBase.findByUsername(username);
        return result;
    }
    
}
