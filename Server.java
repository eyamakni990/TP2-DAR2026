package Serverpackage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Serveur en attente d'un client...");

        Socket socket = serverSocket.accept();
        System.out.println("Un client est connecté.");

        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

        double a = Double.parseDouble(br.readLine());
        String operateur = br.readLine();
        double b = Double.parseDouble(br.readLine());

        double resultat;

        switch (operateur) {
            case "+":
                resultat = a + b;
                break;
            case "-":
                resultat = a - b;
                break;
            case "*":
                resultat = a * b;
                break;
            case "/":
                if (b == 0) {
                    pw.println("Erreur : division par zero.");
                    socket.close();
                    serverSocket.close();
                    return;
                }
                resultat = a / b;
                break;
            default:
                pw.println("Erreur : operateur invalide.");
                socket.close();
                serverSocket.close();
                return;
        }

        pw.println(resultat);
        System.out.println("Resultat envoyé : " + resultat);

        socket.close();
        serverSocket.close();

        System.out.println("Serveur arrêté.");
    }
}