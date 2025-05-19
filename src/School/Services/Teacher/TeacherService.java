package School.Services.Teacher;

import School.DataWork.DataWriter;
import School.Services.Service;
import Users.Student.Student;
import Users.Teacher.Teacher;
import School.Data.*;
import School.School;
import Menu.Menu;

import java.util.Scanner;

public class TeacherService extends Service {
    private Teacher teacher;
    private boolean finishService;

    public TeacherService() {
        super();
    }

    public TeacherService(Teacher teacher) {
        super();
        this.teacher = teacher;
        startService();
    }

    @Override
    public void showActions() {
        System.out.println("1. Поставить оценку");
        System.out.println("2. Увидеть оценки студента");
        System.out.println("3. Получить журнал группы");
        System.out.println("4. Сохранить данные");
        System.out.println("0. Выйти из личного кабинета");
    }

    @Override
    public void actions() {
        user = new Scanner(System.in);
        String choice = user.nextLine().trim();
        School school = School.load("C:\\Users\\User\\IdeaProjects\\Streams\\Journal.json");

        int groupName;
        Group studentGroup = new Group();
        boolean isFound = false;
        int indexOfStudent;

        switch(choice) {
            case "1":
                System.out.println("Введите оценку");
                int chooseValue = user.nextInt();
                Menu.clearConsole();
                System.out.println("Введите группу");
                groupName = user.nextInt();
                Menu.clearConsole();
                for (Group group : school.getGroups()) {
                    if (group.getName() == groupName) {
                        System.out.println(group);
                        System.out.println("Выберете ученика");
                        studentGroup = group;
                        isFound = true;
                    }
                    if (isFound) break;
                }
                Menu.clearConsole();
                indexOfStudent = user.nextInt() - 1;
                teacher.putScore(chooseValue, school.getGroups().get(school.getGroups().indexOf(studentGroup)).getMembers().get(indexOfStudent), school);
                school.save("C:\\Users\\User\\IdeaProjects\\Streams\\Journal.json");
                break;
            case "2":
                System.out.println("Введите группу");
                groupName = user.nextInt();
                Menu.clearConsole();
                for (Group group : school.getGroups()) {
                    if (group.getName() == groupName) {
                        System.out.println(group);
                        System.out.println("Выберете ученика");
                        studentGroup = group;
                        isFound = true;
                    }
                    if (isFound) break;
                }

                indexOfStudent = user.nextInt() - 1;
                System.out.println(teacher.scoresOfSpecificStudent(studentGroup, indexOfStudent)
                        .toString()
                        .replace("[", "")
                        .replace("]", "")
                        .replace(",", "")
                );
                school.save("C:\\Users\\User\\IdeaProjects\\Streams\\Journal.json");
                break;
            case "3":
                System.out.println("Введите группу");
                groupName = user.nextInt();
                Menu.clearConsole();
                for (Group group : school.getGroups()) {
                    if (group.getName() == groupName) {
                        System.out.println(group);
                        System.out.println("Выберете ученика");
                        studentGroup = group;
                        isFound = true;
                    }
                    if (isFound) break;
                }
                Menu.clearConsole();
                for (Student student : studentGroup.getMembers()) {
                    System.out.print(student + " ");
                    System.out.println(teacher.scoresOfSpecificStudent(studentGroup, studentGroup.getMembers().indexOf(student))
                            .toString()
                            .replace("[", "")
                            .replace("]", "")
                            .replace(",", "")
                    );
                }
                school.save("C:\\Users\\User\\IdeaProjects\\Streams\\Journal.json");
                break;
            case "4":
                school.save("C:\\Users\\User\\IdeaProjects\\Streams\\Journal.json");
                DataWriter.recordToFirstSheet(school);
                DataWriter.recordToTeacherSheet(school);
                DataWriter.recordToSubjectSheet(school);
                break;
            case "0":
                finishService = true;
                break;
            default:
                System.out.println("Такой функции не существует");
        }
    }

    @Override
    public void startService() {
        while(!finishService) {
            Menu.clearConsole();
            showActions();
            actions();
        }
        Menu.clearConsole();
        Menu menu = new Menu();
        menu.chooseMenu();
    }
}
