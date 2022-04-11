package vava.edo.models;

import java.io.*;

public final class UserHolder {
    private User user;
    private final static UserHolder INSTANCE = new UserHolder();

    private UserHolder() {}

    public static UserHolder getInstance() {
        return INSTANCE;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    public void  loadSerializeUSer() {
        this.user = null;
        try {
            // pripravenie suboru kde su serializovane denne smeny
            FileInputStream fileIn = new FileInputStream("user.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);

            // naplnenie zoznamu pre controller
            this.user = (User) in.readObject();

            in.close();
            fileIn.close();
            System.out.println("Smeny sú načítané");
        } catch (IOException i) {  // chyba pri deserializacii
            i.printStackTrace();
        } catch (ClassNotFoundException c) {  // chyba nenajdeneho suboru so serializaciou
            System.out.println("Shifts class not found");
            c.printStackTrace();
        }
    }

    public void serializeUser() {
        try {
            // vytvorenie (alebo hladanie uz vytvoreneho) suboru, kde sa bude serializovat
            FileOutputStream fileOut = new FileOutputStream("user.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this.user);  // pridavanie jednotlivych objektov
            out.close();
            fileOut.close();  // zatvorenie suboru
            System.out.println("Údaje boli uložené (user.ser)");
        } catch (IOException i) {  // ak sa nahodou nepodari subor vytvorit alebo pre inu chybu
            i.printStackTrace();  // vypis vynimky (problemu)
        }
    }
}
