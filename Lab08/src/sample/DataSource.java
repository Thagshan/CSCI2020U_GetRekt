package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 * Created by jesse on 06/02/17.
 */
public class DataSource {

    public ObservableList<StudentRecord> students = FXCollections.observableArrayList();

    public ObservableList<StudentRecord> getStudents() {
        return students;
    }

    public void addStudent(String sid, float assignments, float midterm, float exam) {
        students.add(new StudentRecord(sid, assignments, midterm, exam));
    }

}
