package lab05;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainForm {
    @FXML
    public TableView<StudentRecord> tblData;

    @FXML
    public TableColumn<StudentRecord, String> tblColSid;
    @FXML
    public TableColumn<StudentRecord, Double> tblColAssignments;
    @FXML
    public TableColumn<StudentRecord, Double> tblColMidterm;
    @FXML
    public TableColumn<StudentRecord, Double> tblColFinalExam;
    @FXML
    public TableColumn<StudentRecord, Double> tblColFinalMark;
    @FXML
    public TableColumn<StudentRecord, String> tblColLetterGrade;

    @FXML
    public void initialize() {
        tblData.setItems(DataSource.getAllMarks());

        tblColSid.setCellValueFactory(new PropertyValueFactory<>("sid"));
        tblColAssignments.setCellValueFactory(new PropertyValueFactory<>("assignments"));
        tblColMidterm.setCellValueFactory(new PropertyValueFactory<>("midterm"));
        tblColFinalExam.setCellValueFactory(new PropertyValueFactory<>("finalExam"));
        tblColFinalMark.setCellValueFactory(new PropertyValueFactory<>("finalMark"));
        tblColLetterGrade.setCellValueFactory(new PropertyValueFactory<>("letterGrade"));
    }
}
