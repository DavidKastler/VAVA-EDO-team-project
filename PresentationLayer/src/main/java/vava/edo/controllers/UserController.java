package vava.edo.controllers;

import vava.edo.models.User;

import java.io.*;

public class UserController {

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


}
