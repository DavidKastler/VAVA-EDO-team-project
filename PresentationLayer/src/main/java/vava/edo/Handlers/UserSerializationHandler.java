package vava.edo.Handlers;

import vava.edo.models.User;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Serialization class for user
 * It is for remembering user
 */
public class UserSerializationHandler {

    private static final String DIR_PATH = "PresentationLayer/src/main/resources/serialization/";
    private static final String PATH = DIR_PATH + "user.ser";

    public static String getPATH() {
        return PATH;
    }

    /**
     * Method deserializes user and returns it if it exists
     * @return  user
     */
    public static User loadSerializedUser() {
        try {
            FileInputStream fileIn = new FileInputStream(PATH);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            User user = (User) in.readObject();
            in.close();
            fileIn.close();
            return user;
        } catch (IOException e) {
            System.err.println("IOException pre serializaciu.");
            return null;
        } catch (ClassNotFoundException e) {
            System.err.println("Subor s ulozenym pouzivatelom sa nenasiel!");
            return null;
        }
    }

    /**
     * Method that serializes user to database
     * @param user
     * @throws IOException
     */
    public static void serializeUser(User user) throws IOException {
        Files.createDirectories(Paths.get(DIR_PATH));
        // vytvorenie (alebo hladanie uz vytvoreneho) suboru, kde sa bude serializovat
        FileOutputStream fileOut = new FileOutputStream(PATH);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(user);  // pridavanie jednotlivych objektov
        out.close();
        fileOut.close();  // zatvorenie suboru
        System.out.println("Údaje boli uložené " + PATH);

    }
}
