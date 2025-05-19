package School.DataWork;

import School.*;
import School.Data.Subject;
import School.Data.Score;
import Users.Student.Student;
import Users.Teacher.Teacher;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.io.*;

public class DataWriter implements Serializable {
    @Serial
    private static final long serialVersionUID = 7235920555568433336L;
    private final static String filename = "C:\\Users\\User\\Desktop\\Notepad++\\Journal.xlsx";

    public static void recordToFirstSheet(School school) {
        XSSFWorkbook workbook;
        try {
            workbook = new XSSFWorkbook(new FileInputStream(filename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        XSSFSheet sheet = workbook.getSheetAt(0);

        recordStudents(school, sheet, 1);

        Row row = sheet.getRow(1);
        int cellnum = 2;
        for (Subject subject : school.getSubjects()) {
            Cell cell = row.getCell(cellnum++);
            cell.setCellValue(subject.getName());
        }
        try {
            FileOutputStream out = new FileOutputStream(filename);
            workbook.write(out);
            out.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void recordToSubjectSheet(School school) {
        XSSFWorkbook workbook;
        try {
            workbook = new XSSFWorkbook(new FileInputStream(filename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (int i = 3; i < 8; i++) {
            XSSFSheet sheet = workbook.getSheetAt(i);
            recordStudents(school, sheet, 0);
            int rownum = 1;
            for (Student student : school.getStudents()) {
                int indexOfStudent = student.getGroup().getMembers().indexOf(student);
                Row rowForScore = sheet.getRow(rownum++);
                int cellnum = 2;
                for (Score score : school.getTeachers().get(i - 3).scoresOfSpecificStudent(student.getGroup(), indexOfStudent)) {
                    Cell cell = rowForScore.getCell(cellnum++, Row.CREATE_NULL_AS_BLANK);
                    cell.setCellValue(score.getValue());
                }
                if (indexOfStudent + 1 == student.getGroup().getMembers().size()) {
                    rownum += 2;
                }

            }
        }
        try {
            FileOutputStream out = new FileOutputStream(filename);
            workbook.write(out);
            out.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void recordToTeacherSheet(School school) {
        XSSFWorkbook workbook;
        try {
            workbook = new XSSFWorkbook(new FileInputStream(filename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        XSSFSheet sheet = workbook.getSheetAt(1);
        int rownum = 1;
        for (Teacher teacher : school.getTeachers()) {
            Row row = sheet.getRow(rownum++);
            Cell cellForTeacherName = row.getCell(1);
            Cell cellForTeacherLastname = row.getCell(0);
            Cell cellForTeacherSubject = row.getCell(3);

            cellForTeacherLastname.setCellValue(teacher.getLastname());
            cellForTeacherName.setCellValue(teacher.getName());
            cellForTeacherSubject.setCellValue(teacher.getSubject().getName());
        }
        try {
            FileOutputStream out = new FileOutputStream(filename);
            workbook.write(out);
            out.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void recordStudents(School school, XSSFSheet sheet, int startRow) {
        int rownum = startRow;
        for (int i = 0; i < school.getGroups().size(); i++) {
            Row rowForGroupName = sheet.getRow(rownum);
            Cell cellForGroupName = rowForGroupName.getCell(0);
            cellForGroupName.setCellValue(school.getGroups().get(i).getName());
            for (Student student : school.getGroups().get(i).getMembers()) {
                Row rowForStudent = sheet.getRow(++rownum);
                Cell cellForStudentName = rowForStudent.getCell(1);
                cellForStudentName.setCellValue(student.getName());
                Cell cellForStudentLastname = rowForStudent.getCell(0);
                cellForStudentLastname.setCellValue(student.getLastname());
            }
            rownum += 2;
        }
    }
}
