package sample;

public class Over60Member extends DefaultMember {
    private int age;

    public Over60Member() {
    }

    public Over60Member(String memberID, String memberName, String gender, int age) {
        super(memberID, memberName, gender);
        this.age = age;
    }

    // Creating constructors

    // getting and setting age
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

}