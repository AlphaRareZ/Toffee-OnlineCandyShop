import java.util.*;

public class Manager extends Admin {

    Manager(String name, String id, String email, String telephone) {
        super(name, id, email, telephone);
    }

    /**
     * function that adds an admin to the system
     * @param admin the admin that is wanted to be added
     */
    public void addAdmin(Admin admin) {
        SystemData.admins.add(admin);
    }

    /**
     * function that removes an admin from the system
     * @param admin the admin that is wanted to be removed
     */
    public void removeAdmin(Admin admin) {
        SystemData.admins.remove(admin);
    }
}
