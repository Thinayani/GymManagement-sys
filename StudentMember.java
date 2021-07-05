package sample;

public class StudentMember extends DefaultMember{
    private String schoolName;

    public StudentMember(){
    }

    public StudentMember(String memberID, String memberName, String gender, String schoolName) {
        super(memberID, memberName, gender);
        this.schoolName = schoolName;
    }

    // Creating constructors

    // getting and setting schoolName
    public String getSchoolName() {
        return schoolName;
    }
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

}
