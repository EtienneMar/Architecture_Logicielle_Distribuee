NOTA BENE : les metadatas (fichier générée par eclipse)
des projets on était supprimé pour un 
soucis de poids du fichier

Attention ma solution a été réalisé à l'aide de SPRING DATA JPA, 
n'utilisant pas hibernate il faut vérifier que vous avez à votre disposition


- Un système de serveur local pour héberger la SGBD exemple MAMP ou PHPMYADMIN

- Dans votre système de base de donnée créer une base de donnée hotel_la_verrerie
et une autre base de donnée hotel_progress


- Le Framework SPRING 

1- Lancer votre IDE dans Projet C:\Users\33683\Desktop\Projet Rest\Question1A2
2- Importer les projets MAVEN grâce à import projects sur Eclipse
3- Selectionner tout les fichiers XML
4- Une fois les projets charger pour les deux projets hotel aller 
dans leurs repertoires :
src/main/ressources 
	- application properties 

Celui-ci contient les différentes propriété JPA modifiée les lignes suivantes :

spring.datasource.url=jdbc:mysql://localhost:3307/hotel_la_verrerie?serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=root
Créer une base de donnée en fonction du mot entre le / et le ?: 
hotel_la_verrerie, agence_selectour ect...

En fonction de l'adresse de votre base de donnée modifiée le port du localhost
Par défaut il est égal à 3307
Modifiée aussi le mot de passe et le nom d'utilisateur pour vous connecter à vos 
base de donnée. 

Une fois ceci fait, vérifier que le compileur et la JRE soit 
dans la version JAVA-SE 11 (java 11) pour vérifier ces caractéristiques de l'IDE
aller dans le build path du projet 

Faire cette vérification pour chacun des projets. 

Dernièrement dans les propriétés application.properties du projet client*
vérifier le que le port server ne soit pas occupée par défaut il est sur 
le port : 8084

Idem pour le projet hotel le port par défaut est 8080 
et pour le projet hotel2 le port par défaut est 5555.

Enfin lancer impérativement les deux projets serveur en exécutant le fichier class application java
Puis lancer le projet client en exécutant le fichier class application.java


La réponse à la première question de la CLI (URL VALIDE) est : 
http://localhost:

NOTA BENE pour la question	3 REFAIRE EXACTEMENT les mêmes test Mais à la place remplacer par le dossier
question 3. 
