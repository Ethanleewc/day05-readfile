package milton;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Flow.Publisher;

import javax.security.auth.kerberos.KerberosCredMessage;

//This program is use to group Book titles into one publisher in CSV file, hence removing the duplicated publishers.
public class Main {

    //Declare the variable for file header in CSV so to print out at first row.
    public static final String HEADER = "Publisher,Titles\n";

    public static void main(String[] args) {
        
        //Declare variable of the file name
        String path = "books.csv";
        //Create a HashMap to store read word
        Map<String, List<String>> wordMap = new HashMap<String, List<String>>();

        //Read file
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            
            //The for loop is use to limit the amount of line read
            for (Integer i = 0; i <= 20000; i++) {
                String line = br.readLine();
                if (null == line)
                    break;
                //This command is use to turn read line into array of individual words
                String[] index = line.split(",");
                // If file contains publisher already, add the title to the values list
                if (wordMap.containsKey(index[11])) {
                    wordMap.get(index[11]).add(index[1]);
                }
                // if file doesn't have this publishser as key, insert a key and value
                else {
                    List<String> title = new ArrayList<>();
                    title.add(index[1]);
                    wordMap.put(index[11], title);
                }
                
            }
            br.close();

            //Print out items in terminal and formatting the output to e.g "Publisher = Titles,Titles,Titles...."
            /*
            for (Entry<String, List<String>> entry : wordMap.entrySet()) {
                System.out.println( entry.getKey() + " =  " + entry.getValue());
            }
            */

            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    //Print to CSV file
    try (FileOutputStream fos = new FileOutputStream("outputBooks.csv")) {
        OutputStreamWriter osw = new OutputStreamWriter(fos);

        osw.write(HEADER);
        for (Entry<String, List<String>> entry : wordMap.entrySet()) {
            String line = String.format("%s,%s\n", entry.getKey(), entry.getValue());
            osw.write(line);

        }

        osw.flush();
        osw.close();
        fos.close();
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    }
    
}
