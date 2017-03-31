import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.*;
import java.net.*;
import java.io.*;

public class Client extends Application {
    private Socket socket;
    private DataOutputStream out;

    public void start(Stage primaryStage) {
        GridPane root = new GridPane();

        Label nameLabel = new Label("Name ");
        root.add(nameLabel, 0, 0);
        TextField nameField = new TextField();
        root.add(nameField, 1, 0);

        Label msgLabel = new Label("Message ");
        root.add(msgLabel, 0, 1);
        TextField msgField = new TextField();
        root.add(msgField, 1, 1);

        Button sendButton = new Button("Send");
        root.add(sendButton, 0, 2);
        sendButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sendMessage(nameField.getText(), msgField.getText());
            }
        });

        Button exitButton = new Button("Exit");
        root.add(exitButton, 1, 2);
        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(socket != null && out != null) {
                    try {
                        out.close();
                        socket.close();
                    } catch(IOException e) {
                        e.printStackTrace();
                    }
                }
                System.exit(1);
            }
        });

        //try to connect to server
        try {
            socket = new Socket("localhost", 4444);
            //init output stream
            out = new DataOutputStream(socket.getOutputStream());
        } catch(IOException e) {
            e.printStackTrace();
        }

        primaryStage.setTitle("Client");
        primaryStage.setScene(new Scene(root, 300, 300));
        primaryStage.show();
    }

    public void sendMessage(String name, String message) {
        try {
            if(socket != null && out != null) {
                out.writeUTF(name + ";;" + message);
            } else {
                System.err.println("Cannot send message");
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
