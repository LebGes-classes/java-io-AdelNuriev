package Users.Teacher;

import School.Data.*;
import Users.Users;
import Users.Student.Student;
import School.School;

import java.io.Serial;
import java.util.LinkedList;

public class Teacher extends Users {
    @Serial
    private static final long serialVersionUID = 7235920555568433336L;
    private LinkedList<Score> scores;
    private Subject subject;

    public Teacher(String name, String lastname) {
        super(name, lastname);
        scores = new LinkedList<>();
    }

    public Teacher(String name, String lastname, Subject subject) {
        super(name, lastname);
        this.subject = subject;
        scores = new LinkedList<>();
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getLastname() { return lastname; }
    public void setLastname(String lastname) { this.lastname = lastname; }
    public LinkedList<Score> getScores() { return scores; }
    public void setScores(LinkedList<Score> scores) { this.scores = scores; }
    public Subject getSubject() { return subject; }
    public void setSubject(Subject subject) { this.subject = subject; }

    @Override
    public String toString() {
        return name + " " + lastname + ", " + subject + "\n";
    }

    public void putScore(int value, Student student, School school) {
        Score score = new Score(value, subject, student);
        scores.add(score);
        for(Teacher teacher : school.getTeachers()){
            if (this.equals(teacher)) {
                teacher.setScores(scores);
            }
        }
        school.setScores(score);
    }

    public LinkedList<Score> scoresOfSpecificStudent(Group studentGroup, int indexOfStudent) {
        LinkedList<Score> scoresOfSpecificStudent = new LinkedList<>();
        for (Score score : scores) {
            if (score.getStudent().equals(studentGroup.getMembers().get(indexOfStudent))) {
                scoresOfSpecificStudent.add(score);
            }
        }
        return scoresOfSpecificStudent;
    }
}
