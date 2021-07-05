package sample;

public class DefaultMember {

    // defining the variables of default member
    private String memberID;
    private String memberName;
    private String gender;

    public DefaultMember(String memberID, String memberName, String gender) {
        super();
        this.memberID = memberID;
        this.memberName = memberName;
        this.gender = gender;
    }

    public DefaultMember() {
    }

    // Creating constructors

    // getting and setting memberID
    public String getMemberID() {
        return memberID;
    }
    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    // getting and setting memberName
    public String getMemberName() {
        return memberName;
    }
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    // getting and setting gender
    public String getGender(){
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

}
