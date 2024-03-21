import java.util.*;

public class Human {
    protected String name;
    protected String id;
    protected String email;
    protected String telephone;

    Human(String name, String id, String email, String telephone) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.telephone = telephone;
    }

    public String getName() {
        return this.name;
    }

    public String getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
