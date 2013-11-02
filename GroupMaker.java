import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.utils.ArrayList;
import java.utils.Collections;
import java.uit;

public class GroupMaker {

	ArrayList<Student> listOfStudents = new ArrayList<Student>();
	ArrayList<Groups> listOfGroups = new ArrayList<Groups>();
	String csvSplitBy = ",";

	public static void main(String[] args) {
		try {
			String fileName = 
			FileWriter writer = new FileWriter(sFileName);

			FileReader info = new FileReader(new File(args[0]));
			// FileReader set1 = new FileReader(new File(args[1]));
			// FileReader set2 = new FileReader(new File(args[2]));
			// FileReader set3 = new FileReader(new File(args[3]));

			readPeople(info);

			createGroups();
			// createGroups(writer, info, set1, set2, set3);
			
			for(Groups g: listOfGroups) {
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
		br = new BufferedReader(info);

		line = br.readLine(); // read header line

		while ((line = br.readLine()) != null) {

			String[] personInfo = line.split(csvSplitBy);

			Student s = new Student(personInfo[0], personInfo[1]);
			listOfStudents.add(s);
		}
	}

	private static void createGroups() {
		Collections.shuffle(listOfStudents);

		int n = listOfStudents.size();
		boolean remain1, remain2;

		if (n % 3 == 1) remain1 = true;
		if (n % 3 == 2) {
			reamin1 = true;
			remain2 = true;
		}

		for(int index = 0; index/3 < n; index += 3) {
			Groups groups = new Groups();
			groups.addStudent(listOfStudents.get(index));
			groups.addStudent(listOfStudents.get(index + 1));
			groups.addStudent(listOfStudents.get(index + 2));
			listOfGroups.add(groups));
		}
		if (remain2) listOfGroups.get(1).addStudent(listOfStudents.get(n - 2));
		if (remain1) listOfGroups.get(0).addStudent(listOfStudents.get(n - 1));
	}
}