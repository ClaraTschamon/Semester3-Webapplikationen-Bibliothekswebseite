package at.fhv.bibliothekweb.model;

import java.util.HashMap;

public class AuthenticationBean {
    HashMap<String, User> users; //key = userID

    public AuthenticationBean(){
        users = new HashMap<>();
        initialize();
    }

    public User getUser(String userId){
        return users.get(userId);
    }

    public User logInUser(){
        return null;
    }

    //addNewUser (nach Register aufgerufen) returns auch user. wenn man neuer user macht, soll der automatisch angemolden werden.
    public boolean addNewUser(User newUser){
        if(users.containsKey(newUser.getUserID())){ //check if userID already exists
            return false;
        } else{
            users.put(newUser.getUserID(), newUser);
            return true;
        }
    }

    public boolean checkUser(String userID, String password){
        if(users.containsKey(userID) && users.get(userID).getPassword().equals(password)){
            return true;
        }
        return false;
    }

    private void initialize(){
        User testUser = new User("Testuser", "Testuser", "user", "pass");
        User clara = new User("Clara", "Tsch", "cts1614", "Clara.t");
        User A = new User("A", "B", "Aaaaaaa", "A123456");
        users.put(testUser.getUserID(), testUser);
        users.put(clara.getUserID(), clara);
        users.put(A.getUserID(), A);
    }
}
