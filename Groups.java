import java.util.ArrayList;

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
