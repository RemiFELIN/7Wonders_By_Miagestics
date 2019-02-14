
# 7Wonders By Miagestics 
![7Wonders](https://image.noelshack.com/fichiers/2019/06/1/1549316565-7-wonders.jpg)

## Notre projet

Notre objectif est de réaliser une simulation complète d'une partie "7Wonders" jouée par 2 joueurs (version 1) et par 7 joueurs (version finale) répondant à un certains nombres de contraintes.

## Descriptions et règles du jeu

*Chacun des 2 à 7 joueurs prend la tête d’une ville légendaire (Babylone, Éphèse, Rhodes…), et va disposer de trois Âges pour la faire prospérer et même bâtir la légendaire Merveille du monde qui y est associée. Le but du jeu est d’embellir sa cité et de la rendre plus influente que celle de ses adversaires.* 

En d'autres termes, le but de ce jeu est d'accumuler le maximum de points durant les 3 âges du jeu (au cours desquels nous pouvons collecter un certain nombre de cartes), le gagnant est celui qui en accumulent le plus. Pour ce faire, le joueur a la possibilités d'adopter plusieurs stratégies ...

La documentation complète concernant les règles du jeu est disponibles [ici](http://www.7wonders.net/wp-content/uploads/2017/06/7WONDERS_RULES_FR.pdf)

## Usage

Ouvrir un terminal et executer:

1. `git clone https://github.com/RemiFELIN/7Wonders_By_Miagestics`
2. `cd 7Wonders_By_Miagestics`
3. `mvn clean install`
4. `cd lanceur`
5. `mvn exec:java`


## Technologies et méthodes déployées

> Méthode AGILE

> JAVA 

> Maven Project

## Spécification du projet

> représentation de jeu (représentation des merveilles, des bâtiments, de l'argent, de l'achat chez les voisins, etc.).

> moteur de jeu (mise en place, gestion de la distributions de cartes, passage des cartes, actions, détermination des points, de la fin de la partie...).

> robots de jeu, en partant d'une version très simple à des stratégies de jeu plus sophistiqués (chaque nouvel élément intégré dans votre moteur de jeu et dans la représentation doit être utilisée par un robot).

> simulation de parties entre 2 types de robots ou plus, comptage des points, des victoires et classement entre vos robots.

> visualisation de l'état du jeu (en fin de partie pour commencer, tout au long de la partie ensuite). Cette visualisation devra être textuelle. Une version graphique N'EST PAS demandée, car impossible dans le temps imparti.

> deux exécutions (c.f. cours maven pour configurer le pom) : 
> - [id dans le pom.xml : partie-serveur et partie-client] : une exécution  d'une partie, avec le déroulé visible et compréhensible
> - [id dans le pom.xml : stat-serveur et stat-client] : une exécution de 500 parties avec vos différents robots/IA, sans autres sorties textuelles que le résumé global : nombre de victoire, moyenne des points.
>   - cela suppose que votre serveur peut relancer une partie, et que vos clients peuvent se ré-initialiser

> Il ne s'agit pas de réaliser une version interactive de ce jeu (1 ou plusieurs joueurs humains).

> La priorité est donnée à une version à 2 joueurs robots intelligents + 1 "random", plutôt qu'à 3 ou 4 robots assez stupides.

## Notre équipe

Professeur encadrant : [M. Philippe RENEVIER](https://github.com/PhilippeRenevierGonin)

*"The Miagestics"*

- [Rémi FELIN](https://github.com/RemiFELIN)
- [Yannick CARDINI](https://github.com/YannickCardini)
- [Benoît MONTORSI](https://github.com/BenoitMtr)
- [Thomas GAUCI](https://github.com/ThomasGauci)
- [Pierre SAUNDERS](https://github.com/saundersp)

## License

[MIT](http://opensource.org/licenses/MIT)

Copyright (c) 2019-present, FELIN Rémi - MONTORSI Benoît - CARDINI Yannick - SAUNDERS Pierre - GAUCI Thomas

