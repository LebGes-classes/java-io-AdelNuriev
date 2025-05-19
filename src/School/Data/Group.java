package School.Data;

import Users.Student.Student;

import java.io.Serializable;
import java.util.LinkedList;

public class Group implements Serializable {
    private static final long serialVersionUID = 7235920555568433336l;
    private int name;
    private LinkedList<Student> members;

    public Group() {
        members = new LinkedList<>();
    }

    public Group(int name) {
        this.name = name;
        members = new LinkedList<>();
    }

    public Group(int name, LinkedList<Student> members) {
        this.name = name;
        this.members = members;
    }

    public void setName(int name) { this.name = name; }
    public int getName() { return name; }
    public void setMembers(LinkedList<Student> members) { this.members = members; }
    public LinkedList<Student> getMembers() { return members; }

    public String toString() {
        String str = name + "\n";
        for (int i = 0; i < members.size(); i++) {
            str += (i + 1) + ") "  + members.get(i).getName() + " " + members.get(i).getLastname() + "\n";
        }
        return str;
    }

    public void addMember(Student student) {
        members.add(student);
    }

}
