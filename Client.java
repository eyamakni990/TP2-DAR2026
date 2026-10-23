package Clientpackage;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost", 1234);
             Scanner scanner = new Scanner(System.in);
             BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

            System.out.println("Client connecté.");

            System.out.print("Donner le premier opérande : ");
            double a = scanner.nextDouble();

            System.out.print("Donner l'opérateur (+, -, *, /) : ");
            String operateur = scanner.next();

            System.out.print("Donner le deuxième opérande : ");
            double b = scanner.nextDouble();

            // Envoi des trois éléments séparément
            pw.println(a);
            pw.println(operateur);
            pw.println(b);

            // Réception du résultat
            String resultat = br.readLine();

            System.out.println("Résultat reçu du serveur : " + resultat);


        System.out.println("Client arrêté.");
    }
}