# Compte rendu du TP N°2
## Activité 2-2 : Échange d’objets en Java à travers les sockets TCP

### 1. Objectif du TP

L’objectif de cette activité est de se familiariser avec les flux de traitement des objets en Java et de comprendre comment transmettre un objet d’une application cliente vers une application serveur à travers une connexion TCP.

L’application permet au client d’envoyer un objet contenant les informations nécessaires à une opération arithmétique. Le serveur reçoit cet objet, récupère ses données, effectue le calcul demandé et renvoie le résultat au client.


### 2. Implémentation de l’échange d’objets

#### 2.1. Création de la classe sérialisable

Une classe nommée `Operation` est créée pour représenter une opération arithmétique. Elle implémente l’interface `Serializable`, ce qui permet à ses objets d’être transmis à travers un flux d’objets.

Cette classe contient trois attributs :
- `operande1` : le premier nombre ;
- `operateur` : l’opérateur arithmétique ;
- `operande2` : le deuxième nombre.

Des méthodes d’accès permettent au serveur de récupérer les informations contenues dans l’objet reçu.

   <img width="676" height="773" alt="image" src="https://github.com/user-attachments/assets/b8903796-559d-48d0-96d3-d1ceca273475" />


#### 3.2. Application Client

Le client établit une connexion TCP avec le serveur à l’aide de la classe `Socket`.

   <img width="579" height="35" alt="image" src="https://github.com/user-attachments/assets/80bc8c56-baff-49ba-adc2-bb0719c82749" />


Il demande à l’utilisateur de saisir le premier opérande, l’opérateur et le deuxième opérande. Ensuite, il crée un objet de type `Operation` contenant ces informations.

   <img width="563" height="285" alt="image" src="https://github.com/user-attachments/assets/2a363ba6-82f5-4f93-835a-548f1012d000" />


L’objet est envoyé au serveur à l’aide de `ObjectOutputStream` et de la méthode `writeObject()`.

   <img width="755" height="30" alt="image" src="https://github.com/user-attachments/assets/9bf09a13-d701-428e-8aa4-00a3032aae65" />
   <img width="296" height="60" alt="image" src="https://github.com/user-attachments/assets/4e1efe31-bc33-497b-b7d2-5cd72397cb34" />



Après l’envoi, le client attend la réponse du serveur à travers `ObjectInputStream`, puis affiche le résultat reçu.

   <img width="722" height="30" alt="image" src="https://github.com/user-attachments/assets/dc81a256-7689-4345-883e-7bfc95910c6b" />


#### 2.3. Application Serveur

Le serveur crée un objet `ServerSocket` sur le port 1234 et attend la connexion d’un client.

   <img width="584" height="70" alt="image" src="https://github.com/user-attachments/assets/b17b5386-a361-4e39-9066-19b884c14c8f" />


Après l’établissement de la connexion, il utilise `ObjectInputStream` et la méthode `readObject()` pour recevoir l’objet envoyé par le client.

   <img width="722" height="61" alt="image" src="https://github.com/user-attachments/assets/6f40c1b1-d3cf-4dff-9be1-03ab90d5dc0f" />


Le serveur récupère ensuite le premier opérande, l’opérateur et le deuxième opérande à l’aide des méthodes d’accès de la classe `Operation`.

   <img width="478" height="96" alt="image" src="https://github.com/user-attachments/assets/8f6aa8f0-8989-4241-b348-0eee4cfafd32" />


Une structure `switch` permet de réaliser l’opération demandée : addition, soustraction, multiplication ou division. Le serveur vérifie également que le diviseur n’est pas nul.

   <img width="613" height="715" alt="image" src="https://github.com/user-attachments/assets/27ac2601-cfd6-4dbf-b681-13f109face70" />


Enfin, le résultat est transmis au client à l’aide de `ObjectOutputStream` et de la méthode `writeObject()`.

   <img width="741" height="33" alt="image" src="https://github.com/user-attachments/assets/094d25e3-20b6-4963-9a8c-a470ceeb935e" />
   <img width="430" height="55" alt="image" src="https://github.com/user-attachments/assets/c918d200-4563-4cb6-af6a-ff03bb496ff9" />



### 3. Exemple d’exécution

coté client :

   <img width="421" height="177" alt="image" src="https://github.com/user-attachments/assets/5fdc475c-066e-4626-a20a-35e8367b7b62" />

coté serveur :

   <img width="340" height="110" alt="image" src="https://github.com/user-attachments/assets/8df526ae-a859-482c-95f6-f79c6fd942ec" />


### 4. Rôle des flux d’objets

Les classes `ObjectOutputStream` et `ObjectInputStream` permettent respectivement d’envoyer et de recevoir des objets Java.

Contrairement aux flux de caractères utilisés dans l’activité précédente, ces flux permettent de transmettre directement un objet contenant plusieurs attributs.

L’interface `Serializable` est nécessaire pour autoriser la sérialisation de l’objet `Operation`.

### 5. Conclusion

Cette activité a permis de découvrir la sérialisation en Java et l’échange d’objets entre deux applications communiquant par sockets TCP.

Grâce à la classe `Operation`, le client peut regrouper les informations d’une opération arithmétique dans un seul objet. Le serveur reçoit cet objet, traite les données et renvoie le résultat au client.

Ce travail permet de mieux comprendre l’utilisation des flux d’objets dans les applications réparties et complète les notions abordées dans l’activité précédente.
