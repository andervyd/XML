package by.andervyd.introdaction.xmlstring;

import by.andervyd.introdaction.dataprovider.DataProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        String filename = DataProvider.DATADIR + "customers.xml";

        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader fileReader = new FileReader(new File(filename))) {


            int content;
            while((content = fileReader.read()) != -1) {
                stringBuilder.append((char) content);
            }

            System.out.println(stringBuilder.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
