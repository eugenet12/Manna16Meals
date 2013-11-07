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
         boolean remain1 = false, remain2 = false;

         if (n % 3 == 1) remain1 = true;
         if (n % 3 == 2) {
            remain1 = true;
            remain2 = true;
         }

         for(int index = 0; index < n - 2; index += 3) {
            Groups groups = new Groups();
            groups.addStudent(listOfStudents.get(index));
            groups.addStudent(listOfStudents.get(index + 1));
            groups.addStudent(listOfStudents.get(index + 2));
            listOfGroups.add(groups);
         }
         if (remain2) listOfGroups.get(1).addStudent(listOfStudents.get(n - 2));
         if (remain1) listOfGroups.get(0).addStudent(listOfStudents.get(n - 1));
      }
}