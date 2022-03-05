# Inversion of Control & Injection of Dependencies
### Introduction

Dans ce chapitre on a pu traiter les différentes manières d’injection de contrôle et injection de dépendances, ainsi que les types de couplages qui existent.
On essayera à travers ce TP d'illustrer et mettre en pratique les notions étudiées et discutées dans le chapitre. 

-  Couplages :  
    * Couplage Fort :  C’est quand une classe est liée à une classe directement sans usage d’interface.
      Cette méthode présente des limitations et complications lors de l'extension de l’application. Il faudra donc modifier le code source.
      On ne respecte plus le concept d’application fermée à la modification et ouverte à l’extension.
    * Couplage Faible : Nos implémentations d’interfaces ne dépendent plus sur une classe mais sur l’interface de la classe que l’on veut en dépendre. Je m’explique;
      Dans le cas précédent on a : 
      ![Couplage Fort](https://user-images.githubusercontent.com/61352259/156883271-edc69e21-9bfa-474c-b511-b42db19554cf.png)
      
      Cette méthode de couplage nous ne permet pas de changer ou dépendre automatiquement sur la nouvelle classe B2 si et seulement si on change le code source de la classe A.
      ![Couplage Faible](https://user-images.githubusercontent.com/61352259/156883350-1ae4f758-688b-4d32-ad33-99f8fc78c993.png)
      
      Comme cela on ne se soucie plus de la classe à implémenter, l’implémentation est dynamique.
      
- Injection de dépendances : 
  * Injection statique 
  * Injection dynamique 
  * Injection via Framework Spring : 
    * Injection via XML
    * Injection via Annotation

### Réalisation
1. Interface DAO :

![Interface DAO](https://user-images.githubusercontent.com/61352259/156883542-e99c4794-cd6b-45d3-a8cb-ad885823f37c.png)

2. Implémentation de l’interface DAO : 

![Screenshot 2022-03-05 at 13 46 34](https://user-images.githubusercontent.com/61352259/156883674-cb9b66ea-a405-4eca-bf18-79089e127a2d.png)

3. Interface métier : 

![Screenshot 2022-03-05 at 13 46 56](https://user-images.githubusercontent.com/61352259/156883677-d879bdec-d1d2-4071-92e4-635ec93ecbf9.png)

4. Implémentation  de l’interface métier : 


*Plus en détails : 

![Screenshot 2022-03-05 at 13 47 36](https://user-images.githubusercontent.com/61352259/156883707-7251a825-a532-4204-99a4-584d63943293.png)

![Screenshot 2022-03-05 at 13 47 51](https://user-images.githubusercontent.com/61352259/156883715-ac811fde-6c50-49ea-8c43-eb7f14207a54.png)

On fait l’injection de l’interface non pas de la classe *DaoImp.

5. Faire l'injection des dépendances :
  * Par instanciation statique :

![Screenshot 2022-03-05 at 13 48 07](https://user-images.githubusercontent.com/61352259/156883726-3145caff-b9fa-466b-aeb9-95a4ee180640.png)

On instancie manuellement l’injection de l’implémentation de l’interface dans l’objet *metier. Le résultat de cette injection statique est que l’on peut plus facilement et pratiquement porter des modifications à notre application sans avoir accès au code source. Cela peut parfois mal tourner si on fait des fausses manipulations lors de la manipulation du code source.

  * Par instanciation dynamique : 

![Screenshot 2022-03-05 at 13 51 15](https://user-images.githubusercontent.com/61352259/156883851-62968e8c-d227-4796-b517-d59a8ccb20f4.png)

Cette instanciation dynamique repose sur le fait de faire appel à un fichier externe contenant le nom des classes à employer. Cette méthode est certe pas assez sécurisée mais reste meilleur en terme de concept de fermeture à la modification et ouverture à l’extension, le développeur ne se cassera plus la tête à lire le code mais simplement modifier le nom de la classe à utiliser.

![Screenshot 2022-03-05 at 13 51 46](https://user-images.githubusercontent.com/61352259/156883875-6070b110-c2b1-4ebf-bcc2-16ecf9835cdb.png) *(config.txt)

  * En utilisant le Framework Spring : 

Avant toute chose il faut vérifier les plugins de l’IDE et importer les dépendances nécessaires:

![Screenshot 2022-03-05 at 13 52 48](https://user-images.githubusercontent.com/61352259/156883915-25c92fba-e4e1-4b5e-a7e6-b034a8fbff48.png)

![Screenshot 2022-03-05 at 13 53 05](https://user-images.githubusercontent.com/61352259/156883924-0134c338-6aea-4bfa-85d1-2acbd8b92f81.png)

***JUINIT*** est une bibliothèque de test unitaire 
On crée en deuxième lieu un fichier config Spring pour y mettre la configuration nécessaire : 
![Screenshot 2022-03-05 at 13 54 01](https://user-images.githubusercontent.com/61352259/156883957-6f7c2a75-7a56-4ac1-8ab6-73e46d7fcfff.png)

![Screenshot 2022-03-05 at 13 54 15](https://user-images.githubusercontent.com/61352259/156883961-df1a7f18-ad77-4305-b144-642f9713210f.png)

Ces beans définissent les classes à injecter et les dépendances; bean **metier** dépend du bean à l’id *dao.

    * Version Annotation : 

![Screenshot 2022-03-05 at 13 55 11](https://user-images.githubusercontent.com/61352259/156883987-373b9a2d-8cd8-4d18-8231-4bd65052a532.png)

Pour utiliser cette méthode il est nécessaire d’ajouter des annotations @Component à chaque classe influente : 
**DAOIMP :**

![Screenshot 2022-03-05 at 13 56 00](https://user-images.githubusercontent.com/61352259/156884010-f6c2849f-a99a-4692-aba7-1a0c3b5e0b69.png)

**METIERIMPL :**

![Screenshot 2022-03-05 at 13 56 28](https://user-images.githubusercontent.com/61352259/156884026-e9c103b3-20eb-418a-b31c-68e2a6815fa5.png)

**@Autowired** nous permet d’injecter un objet dynamiquement.
autowired opère sur l’objet *IDao dao*

![Screenshot 2022-03-05 at 13 57 11](https://user-images.githubusercontent.com/61352259/156884054-d65c75d4-229a-434c-8695-ab5f838cac28.png)

On fait appel à l’objet context et lui instancie les packages dao et metier.
puis pour l’objet métier on affecte le bean de type IMetier.

    * Version XML :

![Screenshot 2022-03-05 at 13 58 07](https://user-images.githubusercontent.com/61352259/156884084-1ccb4d23-bd1b-406d-8556-b6cbe8241dc5.png)

pour ce cas on n’a pas besoin des annotations mais plutôt d’importer et définir la configuration Spring dans le fichier XML (beans).





