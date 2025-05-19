package Menu;

import School.School;
import School.Services.Student.StudentService;
import School.Services.Teacher.TeacherService;
import Users.Student.Student;
import Users.Teacher.Teacher;

import java.util.Scanner;

public class Menu {
    private final Scanner scanner;
    private School school = new School();

    public Menu() {
        scanner = new Scanner(System.in);
    }

    public void chooseMenu() {
        System.out.println("====== Дневник Студента ======");
        System.out.println("Для выхода нажмите X");
        System.out.println("Вход");

        System.out.println("Введите имя:");
        String name = scanner.nextLine().trim();

        if (name.equals("X")) System.exit(0);

        System.out.println("Введите фамилию:");
        String lastname = scanner.nextLine().trim();

        if (lastname.equals("X")) System.exit(0);

        school.save("C:\\Users\\User\\IdeaProjects\\Streams\\Journal.json");

        Student stud = new Student(name, lastname);
        for (Student student : school.getStudents()) {
            if (student.equals(stud)) {
                StudentService studentService = new StudentService(student);
                studentService.startService();
                school.save("C:\\Users\\User\\IdeaProjects\\Streams\\Journal.json");
            }
        }
        Teacher teach = new Teacher(name, lastname);
        for (Teacher teacher : school.getTeachers()) {
            if (teacher.equals(teach)) {
                TeacherService teacherService = new TeacherService(teacher);
                teacherService.startService();
                school.save("C:\\Users\\User\\IdeaProjects\\Streams\\Journal.json");
            }
        }
    }

    public static void clearConsole(){
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception E) {
            System.out.println(E);
        }
    }
}
