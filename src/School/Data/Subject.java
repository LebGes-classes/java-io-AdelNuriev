package School.Data;

import Users.Teacher.Teacher;

import java.io.Serial;
import java.io.Serializable;

public class Subject implements Serializable {
    @Serial
    private static final long serialVersionUID = 7235920555568433336L;
    private String name;
    private Teacher teacher;

    public Subject(String name, Teacher teacher) {
        this.name = name;
        this.teacher = teacher;
    }

    public Subject(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Teacher getTeacher() { return teacher; }
    public void setTeacher(Teacher teacher) { this.teacher = teacher; }

    @Override
    public String toString() {
        return  "Предмет: " + name;

    }
}
