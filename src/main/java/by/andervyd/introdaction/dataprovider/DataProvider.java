package by.andervyd.introdaction.dataprovider;

import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.*;

import by.andervyd.introdaction.model.Customer;
import by.andervyd.introdaction.utilities.Stopwatch;
import org.json.simple.parser.JSONParser;

public class DataProvider {

    public static final String DATADIR = "./src/main/resources/data/";
    public static String size;
    public static final int SMALL = 10;
    public static final int MEDIUM = 1000;
    public static final int LARGE = 50000;

    //	This method is just for testing
    public static void main(String[] args) {
        Stopwatch watch = new Stopwatch().start("Getting data from JSON");
        List<Customer> data = getData(MEDIUM);
        System.out.println("Returned: " + data.size() + " [" + size + "]\n");
        for (int i = 0; i < Math.min(data.size(), 10); i++) {
            System.out.println((Customer)data.get(i));
        }
        watch.stop();
    }

    public static List<Customer> getData(int limit) {

//		Decide which file to read from.
//		Use 1000 record file for small or medium, 50,000 record file for large
        String filename = null;
        if (limit == LARGE) {
            size = "LARGE";
            filename = DATADIR + "customersLarge.json";
        }
        else {
            if(limit == MEDIUM) {
                size = "MEDIUM";
            } else {
                size = "SMALL";
            }
                        filename = DATADIR + "customers.json";
            }


//		Parse JSON file and get the data
        JSONArray inputData = null;
        JSONParser parser = new JSONParser();
        try {
            JSONObject obj = (JSONObject)parser.parse(new FileReader(filename));
            inputData = (JSONArray)obj.get("result");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }

//		Create a List to contain typed objects
        List<Customer> outputData = new ArrayList<>();

//		Loop through the array up to requested limit,
//		copy JSON objects to ArrayList of POJO's
        for (int i = 0; i < limit; i++) {
            JSONObject item = (JSONObject)inputData.get(i);
            Customer cust = new Customer();

            cust.setId((Number)item.get(Customer.ID));
            cust.setName((String)item.get(Customer.NAME));
            cust.setPhone((String)item.get(Customer.PHONE));
            cust.setAbout((String)item.get(Customer.ABOUT));
            cust.setAge((Number)item.get(Customer.AGE));
            cust.setBalance((String)item.get(Customer.BALANCE));
            cust.setActive((Boolean)item.get(Customer.ACTIVE));

            DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
            String joined = (String)item.get(Customer.JOINED);
            try {
                cust.setJoined(df.parse(joined));
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }

            outputData.add(cust);
        }

//		All done, return the data
        return outputData;

    }
}/* Output:
            Returned: 1000 [MEDIUM]

            1. Carly Gonzales joined March 5, 2005
            2. Taylor Moon joined May 25, 2000
            3. Lynda Bird joined September 28, 1990
            4. Deirdre Green joined November 7, 2003
            5. Lee Lindsey joined June 12, 2007
            6. Ilene Spears joined June 20, 1988
            7. Skinner Reyes joined April 9, 2013
            8. Jewell Sawyer joined December 25, 2001
            9. Leona Henderson joined October 4, 2010
            10. Schultz Sandoval joined May 28, 2012

            Getting data from JSON: 926 milliseconds
*///:~