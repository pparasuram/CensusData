package cscc.edu;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.*;

public class Lookup {
    static HashMap<String, Surname> surNameHashMap = new HashMap<String, Surname>();

    public static void main(String[] args) {
        String inString = "";
        Scanner consoleInput = new Scanner(System.in);
        try {
            FileInputStream fileIn = new FileInputStream("./surname.dat");
            ObjectInputStream inFile = new ObjectInputStream(fileIn);
            surNameHashMap = (HashMap) inFile.readObject();
            inFile.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("class not found");
            c.printStackTrace();
            return;
        }
        while (true) {
            System.out.println("Enter a surname (or quit to end):");
            if (consoleInput.hasNext()) {
                inString = consoleInput.next();
                if (inString.toLowerCase().equals("quit"))
                    break;
                else
                    lookUpSurName(inString);
                consoleInput.nextLine();
            }
        }
    }

    public static void lookUpSurName(String surName) {
        // pull out names from surnames
        Surname newSurName = (Surname) surNameHashMap.get(surName.toUpperCase());
        if (newSurName == null)
            System.out.println("Surname: " + surName + " not found");
        else {
            System.out.print("Surname: ");
            newSurName.printSurname();
        }
    }
}
