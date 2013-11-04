import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class GroupChecker {
	
	private static String csvSplitBy = ",";
	
	public static void main(String args[]) {
		FileReader set1 = null;
		FileReader set2 = null;
		try {
			set1 = new FileReader(new File(args[0]));
			set2 = new FileReader(new File(args[1]));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		ArrayList<Groups> groups1 = readGroup(set1);
		ArrayList<Groups> groups2 = readGroup(set2);
		
		System.out.println("Group repeats:");
		for (Groups g : groups1) {
			if (groups2.contains(g)) {
				System.out.println(g);
			}
		}
	}
	
	private static ArrayList<Groups> readGroup(FileReader reader) {
		ArrayList<Groups> groups = new ArrayList<Groups>();
		
		try {
            BufferedReader br = new BufferedReader(reader);

            String line = br.readLine(); // read header line
            
            Groups g = new Groups();
            while ((line = br.readLine()) != null) {        
            	String[] personInfo = line.split(csvSplitBy);           	
            	if (personInfo.length > 1) {
            		Student s = new Student(personInfo[0], personInfo[1]);
            		g.addStudent(s);
            	} else {
            		groups.add(g);
            		g = new Groups();
            	}
            }
            
            return groups;
            
         } catch(IOException e) {
            e.printStackTrace();
            return null;
         }		
	}
}
