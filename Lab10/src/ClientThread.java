import java.net.*;
import java.io.*;
import java.lang.Thread;
import javafx.scene.control.*;

public class ClientThread extends Thread {
    private Socket client;
    private DataInputStream in;
    private TextArea bulletin;

    public ClientThread(Socket client, TextArea bulletin) {
        this.client = client;
        this.bulletin = bulletin;
        try {
            in = new DataInputStream(this.client.getInputStream());
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while(true) {
            try {
                String[] clientData = in.readUTF().split(";;");
                String name = clientData[0];
                String msg = clientData[1];
                bulletin.appendText(name + ": " + msg + "\n\n");
            } catch(EOFException eof) {
                //client has disconnected
                try {
                    in.close();
                    client.close();
                } catch(IOException ex) {
                    ex.printStackTrace();
                }
                break;
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
}
