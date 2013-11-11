import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class GroupMaker {

      final static private int GROUP_SIZE = 3;
      final static private String FILEPATH = "csv/";
      
      static private ArrayList<Student> listOfStudents = new ArrayList<Student>();
      static private ArrayList<Groups> listOfGroups = new ArrayList<Groups>();
      static private String csvSplitBy = ",";

      public static void main(String[] args) {
         try {
            Calendar cal = Calendar.getInstance();
            String fileName = FILEPATH + "groupings" + cal.get(Calendar.MONTH) +
            		cal.get(Calendar.DAY_OF_MONTH) + ".csv";
            FileWriter writer = new FileWriter(fileName);

            FileReader info = new FileReader(new File(args[0]));
            // FileReader set1 = new FileReader(new File(args[1]));
            // FileReader set2 = new FileReader(new File(args[2]));
            // FileReader set3 = new FileReader(new File(args[3]));
            
            writer.append("Name");
            writer.append(",");
            writer.append("Email");
            writer.append("\n");
            
            readPeople(info);
            assert(listOfStudents.size() >= GROUP_SIZE);
            
            createGroups();
            // createGroups(writer, info, set1, set2, set3);

            for(Groups g : listOfGroups) {
               g.writeCSV(writer);
            }

            writer.flush();
            writer.close();
            info.close();
         } catch (FileNotFoundException e) {
            e.printStackTrace();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }

      private static void readPeople(FileReader info) {
         try {
            BufferedReader br = new BufferedReader(info);

            String line = br.readLine(); // read header line

            while ((line = br.readLine()) != null) {

               String[] personInfo = line.split(csvSplitBy);

               Student s = new Student(personInfo[1], personInfo[2]);
               listOfStudents.add(s);
            }
         } catch(IOException e) {
            e.printStackTrace();
         }
      }

      private static void createGroups() {
         Collections.shuffle(listOfStudents);

         int n = listOfStudents.size();
         int remainder = n % GROUP_SIZE;

         for(int index = 0; index < n;) {
            Groups groups = new Groups();
            int groupSize = GROUP_SIZE;
            if (remainder > 0) {
            	groupSize += 1;
            	remainder--;
            }
            for (int j = 0; j < groupSize; j++) {
            	groups.addStudent(listOfStudents.get(index + j));
            }
            listOfGroups.add(groups);
            index += groupSize;
         }
      }
}