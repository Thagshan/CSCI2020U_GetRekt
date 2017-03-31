import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.*;
import java.net.*;
import java.io.*;
import java.lang.Thread;
import javafx.concurrent.*;

public class Server extends Application {
    private ServerSocket server;
    private final TextArea bulletin = new TextArea();

    public void start(Stage primaryStage) {
        GridPane root = new GridPane();

        root.add(bulletin, 0, 0);
        Button exitButton = new Button("Exit");
        root.add(exitButton, 0, 1);
        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(1);
            }
        });
        primaryStage.setTitle("Server");
        primaryStage.setScene(new Scene(root, 300, 300));
        primaryStage.show();

        //start server
        try {
            server = new ServerSocket(4444);
        } catch(IOException e) {
            e.printStackTrace();
        }

        //Create task to handle client connections
        //concurrently with javafx
        Task task = new Task<Void>() {
            @Override public Void call() {
                while(true) {
                    try {
                        Socket client = server.accept();
                        new Thread(new ClientThread(client, bulletin)).start();
                    } catch(IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        new Thread(task).start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
