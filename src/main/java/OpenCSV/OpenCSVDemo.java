package OpenCSV;

import com.opencsv.CSVWriter;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class OpenCSVDemo {
    @Test
    public void test() {
        CSVWriter csvWriter = null;
        try {
            File file = new File("e:\\write.csv");
            Writer writer = new FileWriter(file);
            csvWriter = new CSVWriter(writer);
            String[] strs = {"abc", "abc", "abc"};
            List<String[]> list = new ArrayList<String[]>();
            list.add(strs);
            list.add(strs);
            list.add(strs);
            list.add(strs);
            csvWriter.writeAll(list);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                csvWriter.close();
            } catch (Exception e) {
                System.out.println("e = " + e);
            }
        }

    }

}
