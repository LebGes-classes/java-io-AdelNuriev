package Users.Student;

import School.*;
import School.Data.*;
import Users.Teacher.Teacher;
import Users.Users;

import java.io.Serial;
import java.util.LinkedList;

public class Student extends Users {
    @Serial
    private static final long serialVersionUID = 7235920555568433336L;
    private Group group;

    public Student(String name, String lastname) {
        super(name, lastname);
    }

    public Student(String name, String lastname, Group group) {
        super(name, lastname);
        this.group = group;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getLastname() { return lastname; }
    public void setLastname(String lastname) { this.lastname = lastname; }
    public Group getGroup() { return group; }
    public void setGroup(Group group) { this.group = group; }

    @Override
    public String toString() {
        return name + " " + lastname + "\n";
    }

    public LinkedList<Teacher> getListOfTeacher(School school) {
        LinkedList<Teacher> teacherList = new LinkedList<>();
        for(int i = 0; i < school.getSubjects().size(); i++) {
            teacherList.add(school.getSubjects().get(i).getTeacher());
        }
        return teacherList;
    }

    public double getAverageScoreForTheSubject(Subject subject) {
        int sum = 0;
        int count = 0;
        for(int i = 0; i < subject.getTeacher().getScores().size(); i++) {
            sum += subject.getTeacher().getScores().get(i).getValue();
            count++;
        }
        return (double) sum/count;
    }

    public int getAverageScoresForTheSubject(Schedule schedule) {
        int sum = 0;
        int count = 0;
        for(int i = 0; i < schedule.getSubjects().size(); i++) {
            for(int j = 0; j < schedule.getSubjects().get(i).getTeacher().getScores().size(); j++) {
                sum += schedule.getSubjects().get(i).getTeacher().getScores().get(j).getValue();
                count++;
            }
        }
        return sum/count;
    }

    public LinkedList<Score> getScoresForTheSubject(Subject subject) {
        LinkedList<Score> scores = new LinkedList<>();
        for (Score score : subject.getTeacher().getScores()) {
            if (score.getStudent().equals(this)) scores.add(score);
        }
        return scores;
    }
}
