package Serverpackage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import communPackage.Operation;

public class ServerObjet {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(1234);

        System.out.println("Serveur en attente d'un client...");

        while (true) {

            Socket socket = serverSocket.accept();

            System.out.println("Un client est connecté.");

            try {
                ObjectOutputStream oos =new ObjectOutputStream(socket.getOutputStream());

                ObjectInputStream ois =new ObjectInputStream(socket.getInputStream());

                Operation operation =(Operation) ois.readObject();

                double a = operation.getOperande1();
                String operateur = operation.getOperateur();
                double b = operation.getOperande2();

                System.out.println("Opération reçue : "+ a + " " + operateur + " " + b);

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
                            oos.writeObject("Erreur : division par zéro.");
                            oos.flush();
                            continue;
                        }
                        resultat = a / b;
                        break;

                    default:
                        oos.writeObject("Erreur : opérateur invalide.");
                        oos.flush();
                        continue;
                }

                oos.writeObject(Double.valueOf(resultat));
                oos.flush();

                System.out.println("Résultat envoyé : " + resultat);

                ois.close();
                oos.close();

            } catch (ClassNotFoundException e) {
                System.out.println("Classe de l'objet introuvable.");

            } finally {
                socket.close();
            }
        }
    }
}