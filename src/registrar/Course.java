package registrar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 */
public class Course {

    private Set<Student> studentEnrolled;
    private List<Student> waitlist;
    private String number;
    private String name;
    private int limit;

    public Course(){
        studentEnrolled = new HashSet<>();
        waitlist = new ArrayList<>();
        limit = 16;
    }

    public void setCatalogNumber(String number){
        this.number = number;
    }

    public void setTitle(String title){
        this.name = title;
    }

    public int getEnrollmentLimit(){
        return limit;
    }

    public boolean setEnrollmentLimit(int limit){
        //If students are enrolled you can't change the limit
        if (studentEnrolled.size() == 0){
            this.limit = limit;
            return true;
        }
        return false;
    }

    public Set<Student> getStudents(){
        return studentEnrolled;
    }

    public List<Student> getWaitList(){
        return waitlist;
    }

    public boolean enrollIn(Student s){
        if (studentEnrolled.contains(s)){
            return true;
        }
        if (studentEnrolled.size() >= limit){
            if (waitlist.contains(s)){
                return false;
            }
            waitlist.add(s);
            return false;
        }
        studentEnrolled.add(s);
        return true;
    }

    public void dropStudent(Student s){
        if (studentEnrolled.contains(s)) {
            studentEnrolled.remove(s);
            if (waitlist.size() > 0) {
                Student toEnroll = waitlist.remove(0);
                studentEnrolled.add(toEnroll);
                toEnroll.enrolledIn.add(this);
            }
        }
        else if (waitlist.contains(s)){
            waitlist.remove(s);
        }
    }

}
