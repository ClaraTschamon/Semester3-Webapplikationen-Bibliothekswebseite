package at.fhv.bibliothekweb.model;

import java.util.HashMap;

public class AuthenticationBean {
    HashMap<String, User> users;

    public AuthenticationBean(){
        users = new HashMap<>();
        initialize();
    }

    //getUser(long userId)

    public User logInUser(){
        return null;
    }

    //addNewUser (nach Register aufgerufen) returns auch user. wenn man neuer user macht, soll der automatisch angemolden werden.
    public User addNewUser(){
        return null;
    }

    public boolean checkUser(String userID, String password){
        if(users.containsKey(userID) && users.get(userID).getPassword().equals(password)){
            return true;
        }
        return false;
    }

    private void initialize(){
        User clara = new User("Clara", "Tsch", "cts1614", "Clara.t");
        users.put(clara.getUserID(), clara);
    }
}
