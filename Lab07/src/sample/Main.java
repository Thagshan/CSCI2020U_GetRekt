package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main extends Application {

    Canvas canvas;
    Map<String, Integer> weatherMap = new HashMap<>();
    ArrayList<String> weatherMapKeys = new ArrayList<>();

    private static Color[] pieColours = {
            Color.AQUA, Color.GOLD, Color.DARKORANGE,
            Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM
    };

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        primaryStage.setTitle("Lab 07");
        primaryStage.setScene(new Scene(root, 700, 400));

        canvas = new Canvas();
        canvas.setHeight(400);
        canvas.setWidth(700);
        root.getChildren().add(canvas);

        String line = "";
        File file = new File("./weatherwarnings-2015.csv");

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                String[] weatherData = line.split(",");
                if (weatherMap.containsKey(weatherData[5])) {
                    int num = weatherMap.get(weatherData[5]);
                    weatherMap.replace(weatherData[5], num + 1);
                } else {
                    weatherMap.put(weatherData[5], 1);
                    weatherMapKeys.add(weatherData[5]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        draw(root);
        primaryStage.show();
    }

    public void draw(Group root) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        //pie chart
        float runningAngle = 0;
        float currentAngle = 0;
        float runningTotal = 0;
        for(int i=0; i<weatherMap.size(); i++) {
            runningTotal += weatherMap.get(weatherMapKeys.get(i));
        }
        float total = runningTotal/360;

        for(int i=0; i<weatherMap.size(); i++) {
            gc.setFill(pieColours[i]);
            currentAngle = (float)(weatherMap.get(weatherMapKeys.get(i)))/total;
            gc.fillArc(350,30,300,300,runningAngle,currentAngle,ArcType.ROUND);
            gc.strokeArc(350,30,300,300,runningAngle,currentAngle,ArcType.ROUND);
            runningAngle += currentAngle;
        }

        //legend
        for(int i=0; i<weatherMap.size(); i++) {
            gc.setFill(pieColours[i]);
            gc.setStroke(Color.BLACK);
            gc.fillRect(50,70+((i+1)*35),50,25);
            gc.strokeRect(50,70+((i+1)*35),50,25);
            Label label = new Label(weatherMapKeys.get(i));
            root.getChildren().add(label);
            label.setTranslateX(110);
            label.setTranslateY(75+((i+1)*35));
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
