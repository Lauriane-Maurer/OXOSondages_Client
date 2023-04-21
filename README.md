# Application de publication de sondages pour la compagnie d'assurance OXO

## 1 - Description du projet

Le client souhaite la création d'une nouvelle plateforme à disposition des salariés, pour leur permettre de voter sur des sujets liés à la vie de l'entreprise.
Le projet Client crée l'interface client pour l'utilisation de l'application.

## 2 - Pages du site :

Pages du site :

- index.html : page d'accueil qui contient la liste des sondages créés.
  Pour chaque sondage:
      - Un bouton "Modifier" permet d'accéder à la page "form" pour modifier les données du sondage 
      - Un bouton "Supprimer" permet de supprimer le sondage.
  En bas de la liste, un bouton "Ajouter un sondage" permet de créer un nouveau sondage.

- form.html : permet l'ajout ou la modification d'un sondage dans la liste de la page index, celui-ci est enregistré dans la base
  de donnée du site.



## 4 - Outils de réalisation :

Code réalisé avec : Intellij
Projet réalisé en Java, HTML, CSS et Javascript  
Framework utilisé : Spring Boot

### Bibliothèques utilisées :

Coté service:
- Spring Boot DevTools
- Spring Web
- Mysql driver
- Spring Date JPA

Côté client:
- Spring Boot DevTools
- Spring Web
- Thymeleaf

## 5 - Installation du projet :

- Logiciel requis : Intellij
- Une base de donnée avec une table "sondages"
- Le port du serveur est 8089. Si le projet ne s'affiche pas du fait d'un port de serveur non disponible,  le numéro du serveur peut être modifié dans la classe "Application.properties".
  


### Merci d'avoir pris le temp de lire le ReadMe