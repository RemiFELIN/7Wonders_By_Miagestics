# 4eme livraison

![7Wonders](https://image.noelshack.com/fichiers/2019/06/1/1549316565-7-wonders.jpg)

## Détails de la livraison

Nous avons réussi les points suivants (objectif atteint) :

* Implémentation des Plateaux/Merveilles

* Implémentation des Symboles Scientifiques

* implémentation de la stratégie militaire (le fait de ne prendre que des cartes donnant des bonus militaire)

* Intégration partielle des guildes, les cartes sont bien dans le jeu à parti de l'âge 3 mais n'ont pour l'instant aucun effets

Cependant, nous avons rencontré des difficultés dans les domaines suivants :

* L'implémentation de la vision de jeu devait, à la base, être plus optimisé structurellement, mais la sérialization JSON (le fait de passer les données sous socket.io) à finalement rendu la chose moins optimisé.
