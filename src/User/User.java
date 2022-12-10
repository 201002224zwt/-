package User;

import java.io.Serializable;

public  abstract  class  User implements Serializable{
        UserType type;
        String loadname;//id
        String passwd;

    public  abstract void menu() ;

    public User(UserType type, String loadname, String passwd) {
            this.type = type;
            this.loadname = loadname;
            this.passwd = passwd;
        }
    }
