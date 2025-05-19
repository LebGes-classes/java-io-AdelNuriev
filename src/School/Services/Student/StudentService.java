package School.Services.Student;

import School.DataWork.DataWriter;
import School.Services.Service;
import Users.Student.Student;
import School.School;
import School.Data.*;
import Menu.Menu;
import java.util.Scanner;

public class StudentService extends Service {
    private Student stud;

    public StudentService(){
        super();
    }

    public StudentService(Student stud){
        super();
        this.stud = stud;
        startService();
    }

    @Override
    public void showActions() {
        System.out.println("1. Получить список учителей");
        System.out.println("2. Получить среднее по предмету");
        System.out.println("3. Узнать оценки за предмет");
        System.out.println("4. Сохранить данные");
        System.out.println("0. Выйти из личного кабинета");
    }

    @Override
    public void actions() {
        user = new Scanner(System.in);
        String choice = user.nextLine().trim();
        School school = School.load("C:\\Users\\User\\IdeaProjects\\Streams\\Journal.json");

        String chooseSubject;
        boolean isFound = false;

        switch (choice) {
            case "1":
                System.out.println(stud.getListOfTeacher(school)
                        .toString()
                        .replace("[", "")
                        .replace("]", "")
                        .replace(",", "")
                );
                school.save("C:\\Users\\User\\IdeaProjects\\Streams\\Journal.json");
                break;
            case "2":
                System.out.println("Введите предмет");
                chooseSubject = user.nextLine().trim();
                for (Subject subject : school.getSubjects()) {
                    if (subject.getName().equalsIgnoreCase(chooseSubject)) {
                        System.out.format("%.2f", stud.getAverageScoreForTheSubject(subject));
                        System.out.println();
                        isFound = true;
                    }
                    if (isFound) break;
                }
                school.save("C:\\Users\\User\\IdeaProjects\\Streams\\Journal.json");
                break;
            case "3":
                System.out.println("Введите предмет");
                chooseSubject = user.nextLine().trim();
                Menu.clearConsole();
                for (Subject subject : school.getSubjects()) {
                    if (subject.getName().equalsIgnoreCase(chooseSubject)) {
                        System.out.println(stud.getScoresForTheSubject(subject)
                                .toString()
                                .replace("[", "")
                                .replace("]", "")
                                .replace(",", "")
                        );
                        isFound = true;
                    }
                    if (isFound) break;
                }
                school.save("C:\\Users\\User\\IdeaProjects\\Streams\\Journal.json");
                break;
            case "4":
                school.save("C:\\Users\\User\\IdeaProjects\\Streams\\Journal.json");
                DataWriter.recordToFirstSheet(school);
                DataWriter.recordToSubjectSheet(school);
                DataWriter.recordToTeacherSheet(school);
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
        while (!finishService) {
            Menu.clearConsole();
            showActions();
            actions();
        }
        Menu.clearConsole();
        Menu menu = new Menu();
        menu.chooseMenu();
    }
}
