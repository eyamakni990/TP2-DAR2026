package Clientpackage;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import communPackage.Operation;

public class ClientObjet {

    public static void main(String[] args) {

        try (
                Socket socket = new Socket("localhost", 1234);
                Scanner scanner = new Scanner(System.in);
                ObjectOutputStream oos =
                        new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois =
                        new ObjectInputStream(socket.getInputStream())
        ) {

            System.out.println("Client connecté.");

            System.out.print("Donner le premier opérande : ");
            double a = scanner.nextDouble();

            System.out.print("Donner l'opérateur (+, -, *, /) : ");
            String operateur = scanner.next();

            System.out.print("Donner le deuxième opérande : ");
            double b = scanner.nextDouble();

            Operation operation = new Operation(a, operateur, b);

            // Envoi de l'objet au serveur
            oos.writeObject(operation);
            oos.flush();

            System.out.println("Objet envoyé au serveur.");

            // Réception du résultat
            Object reponse = ois.readObject();

            if (reponse instanceof Double) {
                System.out.println("Résultat reçu du serveur : " + reponse);
            } else {
                System.out.println(reponse);
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}