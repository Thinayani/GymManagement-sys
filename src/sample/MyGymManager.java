package sample;

import com.mongodb.*;
import javafx.application.Application;
import java.io.FileWriter;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MyGymManager implements GymManager {

    Scanner scan = new Scanner(System.in);
    DefaultMember defaultMember = new DefaultMember();
    StudentMember studentMember = new StudentMember();
    Over60Member adultMembers = new Over60Member();

    // defining the variables
    private String memberName;
    private String memberId;
    private int memberAge;
    private String memberSchool;
    private String memberType;
    private String gender;

    static MongoClient mongoClients;

    static {
        mongoClients = new MongoClient();
    }

    // defining the database and collection variables
    public static DB  mongoDatabase = mongoClients.getDB("GymManage");
    public static DBCollection mongoCollection = mongoDatabase.getCollection("GymMembers");
    public static BasicDBObjectBuilder memberDetails = BasicDBObjectBuilder.start();

    // Adding a new member
    @Override
    public void addMember(String categoryName) {

        // printing out the options
        System.out.println("\n--------------- Enter the category ---------------\nDefault Member   :  1\nStudent Member   :  2\nAdult Member     :  3");
        System.out.println("--------------------------------------------------------------------");
        System.out.print("\nEnter the option  : ");
        categoryName = scan.next().toLowerCase();

        // obtaining the full name
        System.out.print("\nEnter your full name  : ");
        scan.nextLine();
        memberName = scan.nextLine().toLowerCase();
        defaultMember.setMemberName(memberName);

        // obtaining the Id
        System.out.print("Enter your Id         : ");
        memberId = scan.nextLine();
        defaultMember.setMemberID(memberId);

        // obtaining the gender
        System.out.print("Enter your gender     : ");
        gender = scan.next().toLowerCase();
        defaultMember.setGender(gender);

        // getters
        new BasicDBObjectBuilder();
        memberDetails.append("_id",defaultMember.getMemberID());
        memberDetails.append("Name",defaultMember.getMemberName());
        memberDetails.append("Gender",defaultMember.getGender());

        // implementing a loop for the options
        if ("2".equals(categoryName)) {
            memberType = "Student Member";
            System.out.print("Enter your school     : ");
            scan.nextLine();
            memberSchool = scan.nextLine().toLowerCase();
            studentMember.setSchoolName(memberSchool);
            memberDetails.append("Member Type", memberType);
            memberDetails.append("School", studentMember.getSchoolName());
            memberDetails.append("Member Age", "-");
        } else if ("3".equals(categoryName)) {
            memberType = "Over 60 Member";
            while (true) {
                System.out.print("Enter your age        : ");
                memberAge = scan.nextInt();
                // implementing validations
                if (memberAge >= 60) {
                    adultMembers.setAge(memberAge);
                    memberDetails.append("Member Type", memberType);
                    memberDetails.append("Member Age", adultMembers.getAge());
                    memberDetails.append("School", "-");
                    break;
                } else {
                    System.out.println("\nYour age should be above 60 :( ");
                }
            }
        } else if ("1".equals(categoryName)) {
            memberType = "Default Member";
            memberDetails.append("Member Type", memberType);
            memberDetails.append("Member Age", "-");
            memberDetails.append("School", "-");
        } else {
            System.out.println("Invalid Input ");
        }

        WriteResult details = mongoCollection.insert(memberDetails.get());
    }

    //Deleting a member
    @Override
    public void deleteMember() {
        System.out.print("\nEnter the Id that you want to delete : ");
        int deleteMember = scan.nextInt();
        BasicDBObject querySelection = new BasicDBObject();
        querySelection.put("_id",String.valueOf(deleteMember));
        DBObject member = null;
        DBCursor cursor = mongoCollection.find(querySelection);
        member = cursor.next();
        System.out.println("\nMember Type: "+member.get("Member Type"));
        mongoCollection.remove(new BasicDBObject().append("_id", String.valueOf(deleteMember)));
        System.out.println("The member has been successfully removed.");

    }

    // viewing the list of members in the console
    @Override
    public void viewMember() {

        DBObject member = null;
        DBCursor cursor = mongoCollection.find();
        System.out.println("------------------------------------------------------");
        while (cursor.hasNext()){
            member = cursor.next();
            System.out.println("Membership Id: "+member.get("_id"));
            System.out.println("Member Name  : "+member.get("Name"));
            System.out.println("Member Type  : "+member.get("Member Type"));
            System.out.println("Member Age   : "+member.get("Member Age"));
            System.out.println("Gender       : "+member.get("Gender"));
            System.out.println("------------------------------------------------------");
        }
    }

    // saving and writing the list of members in a text file
    @Override
    public void saveFile() {
        FileWriter members = null;
        try {
            members = new FileWriter("Members.txt");
            DBObject memberList = null;
            DBCursor cursor = mongoCollection.find();
            members.write("------------------------------------------------------");
            while (cursor.hasNext()){
                memberList = cursor.next();
                members.write("\nMembership Id: "+memberList.get("_id").toString());
                members.write("\nMember Name  : "+memberList.get("Name").toString());
                members.write("\nMember Type  : "+memberList.get("Member Type").toString());
                try{
                    members.write("\nMember Age   : " + memberList.get("Member Age").toString());
                }catch(NullPointerException exception){
                    members.write("\n-");
                }
                members.write("\nGender       : "+memberList.get("Gender").toString());
                members.write("\n------------------------------------------------------");
            }
            members.close();
            System.out.println("File has been successfully saved :) ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // viewing the UI table
    @Override
    public void viewTable(){
        Application.launch(Main.class);
    }

}

