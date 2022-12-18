//Clara Tschamon
package at.fhv.bibliothekweb.model;

public class GuestBookEntry {

    static int idSeed = 1;
    private int id;
    private String name;
    private String email;
    private String message;


    public GuestBookEntry(String name, String email, String message){
        this.id = idSeed++;
        this.name = name;
        this.email = email;
        this.message = message;
    }

    public String getName(){
        return  name;
    }

    public String getEmail() {
        return email;
    }

    public String getMessage(){
        return this.message;
    }

}
