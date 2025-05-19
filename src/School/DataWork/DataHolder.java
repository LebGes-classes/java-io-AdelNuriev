package School.DataWork;

import School.Data.*;
import Users.Student.Student;
import Users.Teacher.Teacher;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.io.*;
import java.util.LinkedList;

public class DataHolder implements Serializable {
    @Serial
    private static final long serialVersionUID = 7235920555568433336L;
    private static final String filename = "C:\\Users\\User\\Desktop\\Notepad++\\Journal.xlsx";
    public static LinkedList<Score> getScores() {
        LinkedList<Score> scores = new LinkedList<>();
        FileInputStream file;
        try {
            file = new FileInputStream(filename);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        XSSFWorkbook workbook;
        try {
            workbook = new XSSFWorkbook(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int[] startPositions = {1, 27, 54, 81};
        int[] endPositions = {25, 52, 79, 104};
        LinkedList<Student> students = getStudents();
        LinkedList<Subject> subjects = getSubjects();

        for (int i = 3; i < 7; i++) {
            XSSFSheet sheet = workbook.getSheetAt(i);
            int groupName = 11400;
            Group group = new Group();
            for (int k = 0; k < endPositions.length; k++) {
                for (int j = startPositions[k]; j <= endPositions[k] && i <= sheet.getLastRowNum(); j++) {
                    Row row = sheet.getRow(j);
                    String name = "";
                    String lastname = "";
                    for (Cell cell : row) {
                        if (row.getCell(0).getCellType() == Cell.CELL_TYPE_NUMERIC) {
                            groupName = (int) row.getCell(0).getNumericCellValue();
                            group = new Group(groupName);
                        } else if (cell.getColumnIndex() == 0) {
                            lastname = cell.getStringCellValue().trim();
                        } else if (cell.getColumnIndex() == 1) {
                            name = cell.getStringCellValue().trim();
                        } else {
                            Student stud = new Student(name, lastname, group);
                            for (Student student : students) {
                                for (Subject subject : subjects) {
                                    if (student.equals(stud) & student.getGroup().getName() == groupName & subject.getName().equals(sheet.getSheetName())) {
                                        scores.add(new Score((int) cell.getNumericCellValue(), subject, student));
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        try {
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return scores;
    }

    public static LinkedList<Subject> getSubjects() {
        LinkedList<Subject> subjects = new LinkedList<>();
        FileInputStream file;
        try {
            file = new FileInputStream(filename);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        XSSFWorkbook workbook;
        try {
            workbook = new XSSFWorkbook(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        XSSFSheet sheet = workbook.getSheetAt(0);
        Row getRowWithSubjects = sheet.getRow(1);
        for (Cell cell : getRowWithSubjects) {
            if (cell.getColumnIndex() == 0 || cell.getColumnIndex() == 1) {
                continue;
            } else {
                subjects.add(new Subject(cell.getStringCellValue().trim()));
            }
        }

        try {
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return subjects;
    }

    public static LinkedList<Student> getStudents() {
        LinkedList<Student> students = new LinkedList<>();
        FileInputStream file;
        try {
            file = new FileInputStream(filename);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        XSSFWorkbook workbook;
        try {
            workbook = new XSSFWorkbook(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        XSSFSheet sheet = workbook.getSheetAt(0);
        int groupName = 0;
        Group group = new Group();
        for (Row row : sheet) {
            if (row.getRowNum() == 0 | row.getLastCellNum() == -1) {
                continue;
            } else if (row.getCell(0).getCellType() == Cell.CELL_TYPE_NUMERIC) {
                groupName = (int) row.getCell(0).getNumericCellValue();
                group = new Group(groupName);
            } else {
                String lastname = row.getCell(0).getStringCellValue().trim();
                String name = row.getCell(1).getStringCellValue().trim();
                group.setName(groupName);
                Student student = new Student(name, lastname, group);
                students.add(student);
                group.addMember(student);
            }
        }

        try {
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    public static LinkedList<Teacher> getTeachers() {
        LinkedList<Teacher> teachers = new LinkedList<>();
        FileInputStream file;
        try {
            file = new FileInputStream(filename);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        XSSFWorkbook workbook;
        try {
            workbook = new XSSFWorkbook(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        LinkedList<Subject> subjects = getSubjects();
        XSSFSheet sheet = workbook.getSheetAt(1);
        int start = 1;
        int end = 12;
        for (int i = start; i <= end; i++) {
            String name = "", lastname = "", subjectName = "";
            for (Cell cell : sheet.getRow(i)) {
                if (cell.getColumnIndex() == 0) {
                    lastname = cell.getStringCellValue().trim();
                } else if (cell.getColumnIndex() == 1) {
                    name = cell.getStringCellValue().trim();
                } else if (cell.getColumnIndex() == 3) {
                    subjectName = cell.getStringCellValue().trim();
                }
            }
            boolean isFound = false;
            for (Subject subject : subjects) {
                if (subject.getName().equals(subjectName)) {
                    Teacher teacher = new Teacher(name, lastname);
                    teacher.setSubject(subject);
                    teachers.add(teacher);
                    isFound = true;
                }
                if (isFound) break;
            }
        }

        LinkedList<Score> scores = getScores();
        for (Teacher teacher : teachers) {
            LinkedList<Score> scoresOfTeacher = new LinkedList<>();
            for (Score score : scores) {
                if (score.getSubject().getName().equals(teacher.getSubject().getName())) {
                    scoresOfTeacher.add(score);
                }
            }
            teacher.setScores(scoresOfTeacher);
        }

        try {
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return teachers;
    }
}
