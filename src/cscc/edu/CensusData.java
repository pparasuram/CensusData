package cscc.edu;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class CensusData {
    public static void main(String[] args) {
        URL censusUrl = null;
        BufferedReader in = null;
        String inputLine;
        Surname sName = new Surname("",0,0);
        int count = 0;
        // Create a HashMap object called surnames
        HashMap<String, Surname> surnames = new HashMap<String, Surname>();

        try {
            censusUrl = new URL("https://www2.census.gov/topics/genealogy/1990surnames/dist.all.last");
            in = new BufferedReader(
                    new InputStreamReader(censusUrl.openStream()));
            while ((inputLine = in.readLine()) != null) {
                // System.out.println(inputLine);
                sName.setFields(inputLine);
                System.out.println(sName.getSurname() + " " + sName.getFrequency() + " " + sName.getRank());
                count++;
                // now write hash
                surnames.put(sName.getSurname(), sName);
                // if (count == 5)
                //    break;
            }
            in.close();
            System.out.println("lines = " + count);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // here surnames hashmap has all the surnames
        // now serialize into a local file "./surname.dat"
        try {
            FileOutputStream fileOut =
                    new FileOutputStream("./surname.dat");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(surnames);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in ./surname.dat");
        } catch (IOException i) {
            i.printStackTrace();
        }
        // now read the file again

        try {
            FileInputStream fileIn = new FileInputStream("./surname.dat");
            ObjectInputStream inFile = new ObjectInputStream(fileIn);
            surnames = (HashMap) inFile.readObject();
            inFile.close();
            fileIn.close();
            // pull out names from surnames
            Surname newsName = (Surname) surnames.get("SMITH");
            System.out.println("deserialized size is " + surnames.size());
            System.out.println("data: "+ newsName.getSurname() + " " + newsName.getFrequency() + " " + newsName.getRank());
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
            return;
        }

    }
}