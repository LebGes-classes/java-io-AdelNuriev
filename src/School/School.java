package School;

import School.Data.*;
import School.DataWork.DataHolder;
import Users.Student.Student;
import Users.Teacher.Teacher;

import java.io.*;
import java.util.LinkedList;

public class School implements Serializable {
    @Serial
    private static final long serialVersionUID = 7235920555568433336L;
    private LinkedList<Student> students;
    private LinkedList<Teacher> teachers;
    private LinkedList<Score> scores;
    private LinkedList<Subject> subjects;
    private LinkedList<Group> groups;

    public School(LinkedList<Student> students, LinkedList<Teacher> teachers, LinkedList<Score> scores, LinkedList<Subject> subjects, LinkedList<Group> groups) {
        this.students = students;
        this.teachers = teachers;
        this.scores = scores;
        this.subjects = subjects;
        this.groups = groups;
    }

    public School() {
        students = DataHolder.getStudents();
        teachers = DataHolder.getTeachers();
        scores = DataHolder.getScores();
        subjects = setUpSubjects();
        groups = setUpGroups();

    }

    public LinkedList<Student> getStudents() { return students; }
    public void setStudents(LinkedList<Student> students) { this.students = students; }
    public LinkedList<Teacher> getTeachers() { return teachers; }
    public void setTeachers(LinkedList<Teacher> teachers) { this.teachers = teachers; }
    public LinkedList<Score> getScores() { return scores; }
    public void setScores(Score score) { scores.add(score); }
    public LinkedList<Subject> getSubjects() { return subjects; }
    public void setSubjects(LinkedList<Subject> subjects) { this.subjects = subjects; }
    public LinkedList<Group> getGroups() { return groups; }
    public void setGroups(LinkedList<Group> groups) { this.groups = groups; }

    public LinkedList<Subject> setUpSubjects() {
        LinkedList<Teacher> teachers1 = DataHolder.getTeachers();
        LinkedList<Subject> subjects = DataHolder.getSubjects();
        for (Teacher teacher : teachers1) {
            for (Subject subject : subjects) {
                if (subject.getName().equals(teacher.getSubject().getName())) {
                    subject.setTeacher(teacher);
                }
            }
        }
        return subjects;
    }

    public LinkedList<Group> setUpGroups() {
        LinkedList<Student> students1 = DataHolder.getStudents();
        LinkedList<Group> groups1 = new LinkedList<>();
        for (Student student : students1) {
            if (!groups1.contains(student.getGroup())) {
                groups1.add(student.getGroup());
            }
        }
        return groups1;
    }

    public void save(String filename) {
        FileOutputStream fileOutput;
        try {
            fileOutput = new FileOutputStream(filename);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ObjectOutputStream objectOutput;
        try {
            objectOutput = new ObjectOutputStream(fileOutput);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            objectOutput.writeObject(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            fileOutput.flush();
            objectOutput.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static School load(String filename) {
        FileInputStream fileInput;
        try {
            fileInput = new FileInputStream(filename);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ObjectInputStream objectInput;
        try {
            objectInput = new ObjectInputStream(fileInput);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        School school;
        try {
            school = (School) objectInput.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            fileInput.close();
            objectInput.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return school;
    }
}
