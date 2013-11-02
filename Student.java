public class Student {
	
	private String name;
	private String email;
	
	// constructor
	public Student(String name, String email) {
		this.name = name;
		this.email = email;
	}
	
	// return 0 if equal 1 otherwise
	public boolean areEqual(Student s) {
		return name.equals(s.name) && email.equals(s.email);
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", email=" + email + "]";
	}
	
	// getter and setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
