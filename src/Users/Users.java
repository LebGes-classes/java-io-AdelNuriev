package Users;

import java.io.Serial;
import java.io.Serializable;

abstract public class Users implements Serializable {
    @Serial
    private static final long serialVersionUID = 7235920555568433336L;
    protected String name;
    protected String lastname;

    public Users(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Users user = (Users) obj;
        return name.equalsIgnoreCase(user.name) && lastname.equalsIgnoreCase(user.lastname);
    }


}
