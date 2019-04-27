# 6eme livraison

![7Wonders](https://image.noelshack.com/fichiers/2019/06/1/1549316565-7-wonders.jpg)

## Détails de la livraison

> Nous avons réussi les points suivants (objectif atteint) :

* Toutes les mécaniques de jeu sont intégrées :
  * Les effets de merveilles
  * Le calcul de score de joueur prends en compte les effets spéciaux des cartes commerciaux et guildes
  * Intégration des cartes chaînées (batîment suivant)

* Intégration d'un second lanceur qui simule 500 parties et affiche les statistique de réussite de chaque stratégie

* Ajout de détails du déroulement de la partie (ex: contenu des ressources disponibles par joueurs)

> Cependant, nous avons rencontré des difficultés dans les domaines suivants :

* L'ajout des effet de cartes commerciaux et guildes est une mécanique trés lourde qui à de multiple impacts sur différentes parties du jeu, se qui rend l'intégration et les test unitaires complexes

* Les effets de merveilles sont intégrés aux étapes des merveilles, mais n'ont actuellement aucun effet réelle sur la partie

* Certaines mécaniques étaient mal intégrés selon les régles du jeu (ex: les jetons de défaite)