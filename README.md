# IN205_Projet1

Commande pour compiler : mvn clean install
Commande pour exécuter : mvn exec:java

Exercice 1 :

La classe Board est publique. On met pour le moment les attributs de la classe en privé et les méthodes en public.

Exercice 2 :

L'orientation des navires peut être représentée par un enum Orientation dont les quatres valeurs peuvent être NORTH, SOUTH, EAST, et WEST.
On peut commencer à organiser les fichiers sources par dossier cohérent. On crée ainsi un dossier pour les fichiers source en lien avec le plateau de jeu (dossier "Board"), un dossier pour les différentes classes de navires (dossier "Ship"), un dossier pour le joueur (dossier "Player"), ...

Exercice 3 :

Les coordonées des navires x et y commencent à 0 et terminent à (taille - 1). Les coordonnées en entrées commencent elles à A pour les colones et 1 pour les lignes, il faut donc faire attention au décalage d'indice.
De plus lors de l'utilisation de scanner en entrée, la coordonnée x correspond aux colonnes et y aux lignes.

Lorsqu'un navire dépasse du plateau de jeu, ou qu'on veut placer un nouveau navire qui chevaucherait un navire déjà placé, il faut lever une exception. Pour cela on utilise les blocs try{...} catch(){...} dans la méthode putShip(). Ils permettent de tester si une exception est levée, la lancer depuis le bloc "try" et l'attraper dans le bloc "catch".

Exercice 4 :

On utilise de nouveau les exceptions et blocs try{...} catch(){...} afin de vérifier que les coordonnées rentrées par l'utilisateur sont bien cohérentes, i.e. comprises entre 0 et (taille - 1).

Exercice 5 :

Dans cet exercie on procède au "refactoring" du code.
On change les attributs du Board et notamment :
-le tableau navires devient un tableau de ShipState qui permet de mémoriser l'état (touché ou pas) en chacun de ses points de chaque navire placé. Cela permet notamment de determiner quand un navire est coulé.
-le tableau frappes devient un tableau de Boolean, valant null s'il n'y a pas de frappe aux coordonnées correspondantes, false si la frappe est manquée, et true si elle touche.

Il faut donc adapter toutes les méthodes de Board pour qu'elles prennent en compte cette modification d'attribut.

Exercice 6 :

Il faut maintenant être capable d'envoyer des frappes du plateau du joueur au plateau adverse et inversement. Pour cela on crée la méthode sendHit(). On utlise notamment l'enum Hit pour mémoriser l'état de la frapper afin de savoir si elle a été réussie ou non. On utilisera les méthode du enum, afin de récuperer le type de navire dans le cas où celui-ci serait coulé par la frappe.

Exercice 7 :

Maintenant qu'on a tous les éléments pour réaliser le jeu, on décide de faire jouer une IA contre elle même. Elle envoie initialement des frappes aléatoirement (mais prend en compte la position de la frappe précédente si celle-ci est réussie). Il faut donc faire appelle aux méthodes de Random.

Afin de pouvoir suivre le déroulé de la partie, on utilise la méthode Thread.sleep() qui doit être mise dans un bloc try{...} car elle peut jeter une exception.

Etant donné que la méthode putShips() de BattleShipsAI prend en argument un tableau de navire, on utilise pour ce test un tableau.

Exercice 8 :

On veut maintenant faire jouer un utilisateur contre l'IA. On a déjà la classe Player. On fait hériter une classe AIPlayer qui descend de Player et redefinit les classes putShips() et sendHit() en utilisant les méthodes putShips() et sendHit() de BattleShipsAI qui définissent la stratégie de l'IA.

J'ai remarqué que dans la boucle principale l'IA avait la possibilité de continuer à jouer tant que sa frapper était réussie. Afin de rétablir l'équilibre entre l'utilisateur et l'IA, j'ai donné cette posiblilité de continuer à jouer à l'utiliateur s'il réussi une frappe.