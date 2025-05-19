package School.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedList;

public class Schedule implements Serializable {
    @Serial
    private static final long serialVersionUID = 7235920555568433336L;
    private LinkedList<Subject> subjects;

    public Schedule(LinkedList<Subject> subjects) {
        this.subjects = subjects;
    }

    public void setSubjects(LinkedList<Subject> subjects) { this.subjects = subjects; }
    public LinkedList<Subject> getSubjects() { return subjects; }
}
