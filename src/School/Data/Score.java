package School.Data;

import Users.Student.Student;

import java.io.Serial;
import java.io.Serializable;

public class Score implements Serializable {
    @Serial
    private static final long serialVersionUID = 7235920555568433336L;
    private int value;
    private Subject subject;
    private Student student;

    public Score(int value, Subject subject, Student student) {
        this.value = value;
        this.subject = subject;
        this.student = student;
    }

    public int getValue() { return value; }
    public void setValue(int value) { this.value = value; }
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
    public Subject getSubject() { return subject; }
    public void setSubject(Subject subject) { this.subject = subject; }

    public String toString() {
        return value + " ";
    }

}
