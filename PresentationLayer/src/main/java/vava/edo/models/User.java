package vava.edo.models;

import java.io.*;
import java.time.LocalDateTime;

public class User implements Serializable {
    private Integer uid = null;
    private String username = null;
    private String password = null;
    private Role userRole = null;
    private boolean isLogged = false;
    private LocalDateTime lastActivity = null;

    public void setLogged(boolean logged) {
        isLogged = logged;
    }

    public void setLastActivity(LocalDateTime lastActivity) {
        this.lastActivity = lastActivity;
    }

    public Integer getUid() {
        return uid;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getUserRole() {
        return userRole;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public LocalDateTime getLastActivity() {
        return lastActivity;
    }

    public void  loadSerializeUser() {
        User user = null;
        try {
            FileInputStream fileIn = new FileInputStream("/tmp/employee.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            user = (User) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {  // chyba pri deserializacii
            i.printStackTrace();
        } catch (ClassNotFoundException c) {  // chyba nenajdeneho suboru so serializaciou
            System.out.println("User class not found");
            c.printStackTrace();
        }
    }

    public void serializeUser(User user) {
        try {
            // vytvorenie (alebo hladanie uz vytvoreneho) suboru, kde sa bude serializovat
            FileOutputStream fileOut = new FileOutputStream("user.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(user);  // pridavanie jednotlivych objektov
            out.close();
            fileOut.close();  // zatvorenie suboru
            System.out.println("Údaje boli uložené (employee.ser)");
        } catch (IOException i) {  // ak sa nahodou nepodari subor vytvorit alebo pre inu chybu
            i.printStackTrace();  // vypis vynimky (problemu)
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userRole=" + userRole +
                ", isLogged=" + isLogged +
                ", lastActivity=" + lastActivity +
                '}';
    }
}
