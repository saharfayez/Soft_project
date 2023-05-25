package soft_project;

public class Teacher {

    public String academicNumber;
    public String username;

    public Teacher(String academicNumber, String username) {
        this.academicNumber = academicNumber;
        this.username = username;
    }

    public String getAcademicNumber() {
        return academicNumber;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "Teacher{"
                + "academicNumber='" + academicNumber + '\''
                + ", username='" + username + '\''
                + '}';
    }
}
