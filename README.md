# Compte rendu du TP N°2
## Activité 1 : Échange d’une opération complète entre un Client et un Serveur TCP

### 1. Objectif du TP

L’objectif de cette activité est d’étendre les applications Client et Serveur développées dans le TP N°1 activité 2 afin de permettre l’échange d’informations plus complexes à travers une connexion TCP.

Le client doit envoyer les éléments d’une opération arithmétique au serveur. Le serveur effectue le calcul demandé, puis renvoie le résultat au client, qui l’affiche à l’écran.

### 2. Réalisation de l’application

#### 2.1. Application Client

Le client établit une connexion TCP avec le serveur à l’aide de la classe `Socket`.

   <img width="581" height="41" alt="image" src="https://github.com/user-attachments/assets/af2af003-596b-4a1c-b686-9503c472d51b" />


Il demande ensuite à l’utilisateur de saisir :
- Le premier opérande.
- L’opérateur arithmétique (`+`, `-`, `*` ou `/`).
- Le deuxième opérande.

    <img width="565" height="277" alt="image" src="https://github.com/user-attachments/assets/4915c3bb-32a0-49d0-9952-73c09e833316" />

Les trois éléments sont envoyés séparément au serveur à l’aide de `PrintWriter` et de la méthode `println()`.
    <img width="278" height="88" alt="image" src="https://github.com/user-attachments/assets/9f13c377-f444-4043-ab48-f44a42aa1745" />

Après l’envoi, le client attend la réponse du serveur avec `BufferedReader` et affiche le résultat reçu.

   <img width="887" height="63" alt="image" src="https://github.com/user-attachments/assets/0edf36cd-51f8-4ad8-b98f-c6fc2c47ea9c" />


#### 2.2. Application Serveur

Le serveur crée un objet `ServerSocket` sur le port 1234 et attend la connexion d’un client avec la méthode `accept()`.

  <img width="592" height="146" alt="image" src="https://github.com/user-attachments/assets/baadab37-b646-47c9-abc8-7a731fe471a1" />

Après l’établissement de la connexion, le serveur reçoit les trois éléments envoyés par le client :
- Le premier opérande.
- L’opérateur.
- Le deuxième opérande.

  <img width="489" height="92" alt="image" src="https://github.com/user-attachments/assets/c0bf88af-59cb-4aae-a9e4-270b1f3286ad" />


Il utilise ensuite une structure `switch` pour identifier l’opération demandée et calculer le résultat correspondant.

Les quatre opérations arithmétiques sont prises en charge : addition, soustraction, multiplication et division. Le serveur vérifie également que le diviseur n’est pas nul avant d’effectuer une division.

  <img width="561" height="689" alt="image" src="https://github.com/user-attachments/assets/d88bb2d4-3871-4e6f-9519-09b67062fe0a" />


Enfin, le résultat est envoyé au client à l’aide de `PrintWriter`.

  <img width="763" height="57" alt="image" src="https://github.com/user-attachments/assets/7d85c07a-0b46-477f-8da2-2c10b2e542e4" />


### 4. Flux de communication

Le fonctionnement de l’application suit les étapes suivantes :

1. Le serveur démarre et attend une connexion.
2. Le client se connecte au serveur.
3. Le client saisit les deux opérandes et l’opérateur.
4. Le client envoie les trois éléments séparément.
5. Le serveur reçoit les données et effectue le calcul.

### 5. Exemple d’exécution

Côté Client :

<img width="380" height="170" alt="image" src="https://github.com/user-attachments/assets/3c085f65-6adb-4c72-9b73-e6d146296e8e" />


Côté Serveur :

<img width="372" height="122" alt="image" src="https://github.com/user-attachments/assets/beafe435-164c-4cfb-add6-5870094d6076" />

Cet exemple montre que le client envoie les valeurs 55 et 30 avec l’opérateur de multiplication. Le serveur calcule ensuite 55 × 30 et renvoie le résultat 1650 au client.

### 6. Difficultés et solutions

La principale difficulté consiste à assurer la transmission correcte des données entre le client et le serveur.

Pour résoudre ce problème, les trois éléments de l’opération sont envoyés dans un ordre précis : premier opérande, opérateur, puis deuxième opérande. Le serveur lit les données dans le même ordre.

L’utilisation de BufferedReader et de PrintWriter facilite la transmission des données sous forme de texte. La structure switch permet ensuite de sélectionner le calcul approprié.

### 7. Conclusion

Ce TP a permis de mettre en pratique la communication entre un client et un serveur à l’aide des sockets TCP en Java.

L’application réalisée permet au client d’envoyer une opération arithmétique complète sous forme de trois éléments distincts. Le serveur traite la demande, calcule le résultat et le renvoie au client.

Cette activité permet ainsi de mieux comprendre le fonctionnement des sockets, les flux d’entrée-sortie et l’échange de données entre deux applications réparties.
