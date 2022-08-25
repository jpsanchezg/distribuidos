import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.sound.sampled.SourceDataLine;

/**
 *
 * @author 5442
 */
public class Servidor {
    public static void main(String[] args) throws IOException {
        final int PORT = 4040;
        ServerSocket serverSocket = new ServerSocket(PORT);
        int contadorclientes = 0;
        
        System.out.println("Server started...");
        System.out.println("Wating for clients...");
        
        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println(clientSocket);
            Thread t = new Thread() {
                public void run() {
                    try (
                        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                        Scanner in = new Scanner(clientSocket.getInputStream());
                    ) { 
                        while (in.hasNextLine()) {
                            String input = in.nextLine();
                            if (input.equalsIgnoreCase("exit")) {
                                break;
                            }
                            System.out.println("Received radius from client: " + input);
                            
                            double radius = Double.valueOf(input);
                            double area = Math.PI* radius *radius ;
                            out.println(area);
                        }
                    } catch (IOException e) { }
                }
            };
            t.start();
            contadorclientes++;
        }
    }   
}