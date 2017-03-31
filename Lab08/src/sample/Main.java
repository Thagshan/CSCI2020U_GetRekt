package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

import static java.lang.System.exit;

/**
 * Created by jesse on 26/01/17.
 */

public class Main extends Application {

    ObservableList<StudentRecord> students = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Lab 08");

        BorderPane layout = new BorderPane();

        //menu
        MenuBar menu = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem newItem = new MenuItem("New");
        newItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                newFile();
            }
        });
        MenuItem loadItem = new MenuItem("Load");
        loadItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                load();
            }
        });
        MenuItem saveItem = new MenuItem("Save");
        saveItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                save();
            }
        });
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                exit(0);
            }
        });

        menu.getMenus().addAll(fileMenu);
        fileMenu.getItems().addAll(newItem, loadItem, saveItem, exitItem);

        layout.setTop(menu);


        //Table
        TableView table = new TableView();
        layout.setCenter(table);
        table.setEditable(true);
        table.setItems(students);

        TableColumn<StudentRecord, String> sidColumn = new TableColumn("SID");
        TableColumn<StudentRecord, Float> assignmentColumn = new TableColumn("Assignments");
        TableColumn<StudentRecord, Float> midtermColumn = new TableColumn("Midterm");
        TableColumn<StudentRecord, Float> examColumn = new TableColumn("Exams");
        TableColumn<StudentRecord, Float> finalGradeColumn = new TableColumn("Final Grade");
        TableColumn<StudentRecord, Character> gradeColumn = new TableColumn("Letter Grade");

        sidColumn.setMinWidth(100);
        assignmentColumn.setMinWidth(150);
        midtermColumn.setMinWidth(100);
        examColumn.setMinWidth(100);
        finalGradeColumn.setMinWidth(100);
        gradeColumn.setMinWidth(150);

        sidColumn.setCellValueFactory(new PropertyValueFactory<StudentRecord, String>("ID"));
        assignmentColumn.setCellValueFactory(new PropertyValueFactory<StudentRecord, Float>("assignments"));
        midtermColumn.setCellValueFactory(new PropertyValueFactory<StudentRecord, Float>("midterm"));
        examColumn.setCellValueFactory(new PropertyValueFactory<StudentRecord, Float>("exam"));
        finalGradeColumn.setCellValueFactory(new PropertyValueFactory<StudentRecord, Float>("finalGrade"));
        gradeColumn.setCellValueFactory(new PropertyValueFactory<StudentRecord, Character>("grade"));

        sidColumn.setOnEditCommit((TableColumn.CellEditEvent<StudentRecord, String> event) -> {
            ((StudentRecord) event.getTableView().getItems().get(event.getTablePosition().getRow())).setID(event.getNewValue());
        });
        assignmentColumn.setOnEditCommit((TableColumn.CellEditEvent<StudentRecord, Float> event) -> {
            ((StudentRecord) event.getTableView().getItems().get(event.getTablePosition().getRow())).setAssignments(event.getNewValue());
        });
        midtermColumn.setOnEditCommit((TableColumn.CellEditEvent<StudentRecord, Float> event) -> {
            ((StudentRecord) event.getTableView().getItems().get(event.getTablePosition().getRow())).setMidterm(event.getNewValue());
        });
        examColumn.setOnEditCommit((TableColumn.CellEditEvent<StudentRecord, Float> event) -> {
            ((StudentRecord) event.getTableView().getItems().get(event.getTablePosition().getRow())).setExam(event.getNewValue());
        });
        finalGradeColumn.setOnEditCommit((TableColumn.CellEditEvent<StudentRecord, Float> event) -> {
            ((StudentRecord) event.getTableView().getItems().get(event.getTablePosition().getRow())).setExam(event.getNewValue());
        });
        gradeColumn.setOnEditCommit((TableColumn.CellEditEvent<StudentRecord, Character> event) -> {
            ((StudentRecord) event.getTableView().getItems().get(event.getTablePosition().getRow())).setGrade(event.getNewValue());
        });
        table.getColumns().addAll(sidColumn, assignmentColumn, midtermColumn, examColumn, finalGradeColumn, gradeColumn);

        /* create an edit form (for the bottom of the user interface) */
        GridPane editArea = new GridPane();
        layout.setBottom(editArea);
        editArea.setPadding(new Insets(10, 10, 10, 10));
        editArea.setVgap(10);
        editArea.setHgap(10);

        Label sidLabel = new Label("SID:");
        editArea.add(sidLabel, 0, 0);
        TextField sidField = new TextField();
        sidField.setPromptText("SID");
        editArea.add(sidField, 1, 0);

        Label assignmentsLabel = new Label("Assignments:");
        editArea.add(assignmentsLabel, 2, 0);
        TextField assignmentsField = new TextField();
        assignmentsField.setPromptText("Assignments");
        editArea.add(assignmentsField, 3, 0);

        Label midtermLabel = new Label("Midterm:");
        editArea.add(midtermLabel, 0, 2);
        TextField midtermField = new TextField();
        midtermField.setPromptText("Midterm");
        editArea.add(midtermField, 1, 2);

        Label examLabel = new Label("Final Exam:");
        editArea.add(examLabel, 2, 2);
        TextField examField = new TextField();
        examField.setPromptText("Exam");
        editArea.add(examField, 3, 2);

        Button addButton = new Button("Add");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String sid = sidField.getText();
                Float assignments = Float.parseFloat(assignmentsField.getText());
                Float midterm = Float.parseFloat(midtermField.getText());
                Float exam = Float.parseFloat(examField.getText());

                char letterGrade = 'F';
                float grade = (assignments * 0.2f) + (midterm * 0.3f) + (exam * 0.5f);
                if (grade < 50.0f) {
                    letterGrade = 'F';
                } else if (grade > 49.0f && grade < 60.0f) {
                    letterGrade = 'D';
                } else if (grade > 59.0f && grade < 70.0f) {
                    letterGrade = 'C';
                } else if (grade > 69.0f && grade < 80.0f) {
                    letterGrade = 'B';
                } else if (grade > 89.0f && grade < 100.0f) {
                    letterGrade = 'A';
                }

                table.getItems().add(new StudentRecord(sid, assignments, midterm, exam, grade, letterGrade));

                sidField.setText("");
                assignmentsField.setText("");
                midtermField.setText("");
                examField.setText("");
            }
        });
        editArea.add(addButton, 1, 4);

        Scene scene = new Scene(layout, 700, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void newFile() {
        for(int i=0; i<students.size(); i++) {
            students.remove(i);
        }
    }

    public void save() {
        try {
            FileWriter writer = new FileWriter(new File("students.csv"));
            String lineData = "";

            for (int i=0; i<students.size(); i++) {
                String sid = students.get(i).getID();
                String assignments = String.valueOf(students.get(i).getAssignments());
                String midterm = String.valueOf(students.get(i).getMidterm());
                String exam = String.valueOf(students.get(i).getExam());

                lineData += (sid + "," + assignments + "," + midterm + "," + exam + "\n");
                writer.write(lineData);
            }

            writer.flush();
            writer.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        File file = fileChooser.showOpenDialog(new Stage());

        String dataFile = file.getName();
        String line = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader(dataFile));
            while ((line = br.readLine()) != null) {
                String[] studentData = line.split(",");

                String sid = studentData[0];
                float midterm = Float.parseFloat(studentData[1]);
                float assignment = Float.parseFloat(studentData[2]);
                float exam = Float.parseFloat(studentData[3]);

                students.add(new StudentRecord(sid, midterm, assignment, exam));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ImageView imageFile(String path) {
        return new ImageView(new Image("file:" + path));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
