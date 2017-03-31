import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by jesse on 07/03/17.
 */
public class Main extends Application {

    int width = 600, height = 500;
    Canvas canvas;

    public void start(Stage primaryStage) {
        StackPane layout = new StackPane();

        canvas = new Canvas();
        canvas.setWidth(width);
        canvas.setHeight(height);

        downloadStockPrices();

        layout.getChildren().add(canvas);

        primaryStage.setScene(new Scene(layout, width, height));
        primaryStage.setTitle("Lab 9");
        primaryStage.show();
    }

    public void downloadStockPrices() {
        List<Float> stockData1 = new ArrayList<>();
        List<Float> stockData2 = new ArrayList<>();

        try {
            InputStream in = new URL("http://ichart.finance.yahoo.com/table.csv?s=GOOG&a=1&b=01&c=2" +
                    "010&d=11&e=31&f=2015&g=m").openStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line = null;

            while((line = br.readLine()) != null) {
                String[] lineData = line.split(",");

                if(!line.contains("Close")) {
                    stockData1.add(Float.parseFloat(lineData[4]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            InputStream in = new URL("http://ichart.finance.yahoo.com/table.csv?s=AAPL&a=1&b=01&c=2" +
                    "010&d=11&e=31&f=2015&g=m").openStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line = null;

            while((line = br.readLine()) != null) {
                String[] lineData = line.split(",");

                if(!line.contains("Close")) {
                    stockData2.add(Float.parseFloat(lineData[4]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        drawPlot(stockData1,stockData2);
    }

    public void drawPlot(List<Float> stock1, List<Float> stock2) {
        List<Integer> stockPointsX = new ArrayList<>();
        List<Integer> stockPointsY = new ArrayList<>();

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);

        gc.strokeLine(50, height-50, 50, 50);
        gc.strokeLine(50,height-50, width-50,height-50);

        //get max
        float maxVal = 0;
        for(int i=0; i<stock1.size(); i++) {
            if(stock1.get(i) > maxVal) {
                maxVal = stock1.get(i);
            }
        }

        for(int i=0; i<stock1.size(); i++) {
            int xPos = (i*((width-100)/stock1.size()))+50;
            int yPos = height-Math.round(stock1.get(i))/(Math.round(maxVal)/(height-100));
            stockPointsX.add(xPos);
            stockPointsY.add(yPos);
        }

        gc.setStroke(Color.RED);
        for(int i=1; i<stockPointsX.size(); i++) {
            gc.strokeLine(stockPointsX.get(i-1),stockPointsY.get(i-1),stockPointsX.get(i),stockPointsY.get(i));
        }

        //get max
        maxVal = 0;
        for(int i=0; i<stock2.size(); i++) {
            if(stock2.get(i) > maxVal) {
                maxVal = stock2.get(i);
            }
        }

        stockPointsX.clear();
        stockPointsY.clear();

        for(int i=0; i<stock2.size(); i++) {
            int xPos = (i*((width-100)/stock2.size()))+50;
            int stockRange = Math.round(maxVal);
            int graphRange = height-100;
            int yPos = height-Math.round(((stock2.get(i)*graphRange)/stockRange));
            stockPointsX.add(xPos);
            stockPointsY.add(yPos);
        }

        gc.setStroke(Color.BLUE);
        for(int i=1; i<stockPointsX.size(); i++) {
            gc.strokeLine(stockPointsX.get(i-1),stockPointsY.get(i-1),stockPointsX.get(i),stockPointsY.get(i));
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
