package cscc.edu;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class CensusData {
    public static void main(String[] args) {
        URL censusUrl = null;
        BufferedReader in = null;
        String inputLine;
        int count = 0;
        // Create a HashMap object called surnames
        HashMap<String, Surname> surNameHashMap = new HashMap<String, Surname>();

        try {
            censusUrl = new URL("https://www2.census.gov/topics/genealogy/1990surnames/dist.all.last");
            in = new BufferedReader(
                    new InputStreamReader(censusUrl.openStream()));
            while ((inputLine = in.readLine()) != null) {
                // System.out.println(inputLine);
                Surname anotherSurNameObject = new Surname("",0,0);
                // use object method to parse inputLine and populate object properties
                anotherSurNameObject.setFields(inputLine);
                // count++;
                surNameHashMap.put(anotherSurNameObject.getSurname(), anotherSurNameObject);
                // anotherSurNameObject.printSurname();
            }
            in.close();
            // System.out.println("lines = " + count);
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
            out.writeObject(surNameHashMap);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in ./surname.dat");
        } catch (IOException i) {
            i.printStackTrace();
        }
        // now read the file again
    }
}