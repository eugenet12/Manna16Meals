import java.util.ArrayList;
import java.io.IOException;
import java.io.FileWriter;

public class Groups {
	private int numberOfPeople;
	private ArrayList<Student> group;
	
	public Groups() {
		numberOfPeople = 0;
		group = new ArrayList<Student>();
	}
	
	public void addStudent(Student s) {
		group.add(s);
		numberOfPeople++;
	}
	
	public void writeCSV(FileWriter writer) {
		try {
			for (Student s: group) {
				writer.append(s.getName());
				writer.append(',');
				writer.append(s.getEmail());
				writer.append('\n');
			}
			writer.append('\n');
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getNumberOfPeople() {
		return numberOfPeople;
	}

	@Override
	public String toString() {
		String people = "";
		for (Student s: group) {
			people += s.getName() + ": " + s.getEmail() + "\n";
		}
		return people;
	}	
}
