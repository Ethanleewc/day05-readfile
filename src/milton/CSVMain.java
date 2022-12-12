package milton;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sound.sampled.Line;

public class CSVMain {

    public static void main(String[] args) {

        String path = "books.csv";
        //String line = "";
        Map<String, List<String>> wordMap = new HashMap<String, List<String>>();
        ArrayList<Books> info = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            
            for (Integer i = 0; i <= 1000; i++) {
                String line = br.readLine();
                if (null == line)
                    break;
                String[] index = line.split(",");
                
                String publisher = index[11];
                String title = index[1];
                info.add(new Books(publisher, title));  
            }
            br.close();
            Collections.sort(info);
            System.out.println(info); 
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
}
