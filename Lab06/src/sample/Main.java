package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

public class Main extends Application {

    Canvas canvas;

    //Pie chart data
    private static String[] ageGroups = {
            "18-25","26-35","36-45","46-55","56-65","65+"
    };

    private static int[] purchasesByAgeGroup = {
            648,1021,2453,3173,1868,2247
    };

    private static Color[] pieColours = {
            Color.AQUA, Color.GOLD, Color.DARKORANGE,
            Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM
    };

    //Bar chart data
    private static double[] avgHousingPricesByYear = {
            247381.0,264171.4,287715.3,294736.1,
            308431.4,322635.9,340253.0,363153.7
    };

    private static double[] avgCommercialPricesByYear = {
            1121585.3,1219479.5,1246354.2, 1295364.8,
            1335932.6,1472362.0,1583521.9,1613246.3
    };

    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();

        canvas = new Canvas();
        canvas.setHeight(600);
        canvas.setWidth(800);
        root.getChildren().add(canvas);

        primaryStage.setTitle("Lab 06");
        primaryStage.setScene(new Scene(root, 650, 400));
        primaryStage.show();

        draw(root);
    }

    public void draw(Group root) {
        GraphicsContext gc = canvas.getGraphicsContext2D();


        //pie chart
        float runningAngle = 0;
        float currentAngle = 0;
        float runningTotal = 0;
        for(int i=0; i<purchasesByAgeGroup.length; i++) {
            runningTotal += purchasesByAgeGroup[i];
        }
        float total = runningTotal/360;

        for(int i=0; i<purchasesByAgeGroup.length; i++) {
            gc.setFill(pieColours[i]);
            currentAngle = (float)(purchasesByAgeGroup[i])/total;
            gc.fillArc(300,60,300,300,runningAngle,currentAngle,ArcType.ROUND);
            runningAngle += currentAngle;
        }


        //bar chart
        float maxHeight = 0;
        for(int i=0; i<avgCommercialPricesByYear.length; i++) {
            if((avgCommercialPricesByYear[i]/10000) > maxHeight) {
                maxHeight = ((float)avgCommercialPricesByYear[i]/10000);
            }
        }
        float runningX = 40;
        for(int i=0; i<avgHousingPricesByYear.length; i++) {
            gc.setFill(Color.RED);
            float redHeight = (float)avgHousingPricesByYear[i]/5000;
            gc.fillRect(runningX, 200+(maxHeight-redHeight), 10, redHeight);
            gc.setFill(Color.BLUE);
            float blueHeight = (float)avgCommercialPricesByYear[i]/5000;
            gc.fillRect(runningX+10, 200+(maxHeight-blueHeight), 10, blueHeight);
            runningX += 25;
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
