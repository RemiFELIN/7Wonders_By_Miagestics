package lanceur;

import client.Client;
import client.strategie.*;
import moteur.WrapperJeu;

import java.net.BindException;

import static commun.ConsoleLogger.*;

/**
 * Le lanceur permettant de générer un résumé concis de 500 parties '7Wonders'
 * @author Rémi Felin
 */
public class Stat {

    private final static String adresse = "127.0.0.1";
    private final static int port = 10101;
    private final static int nombre_joueurs = 5;
    private final static int nombresDeParties = 500;

    static public void main(String[] args) {

        final Strategie[] mesStrat = new Strategie[] { new StratPointVictoire(false), new StratRandom(false), new StratRessources(false), new StratMilitaire(false), new StratScientifique(false) };
        String[] nomStratJoueur = new String[mesStrat.length];
        for (byte i = 0; i < mesStrat.length; i++)
            nomStratJoueur[i] = mesStrat[i].toString();

        try {
            new WrapperJeu(adresse, port, nombresDeParties, nomStratJoueur);
        } catch (BindException e) {
            error(RED_BOLD + "Adresse " + adresse + ":" + port + " déja utilisé, veuillez en utiliser un autre");
            return;
        }

        for (int i = 0; i < nombre_joueurs; i++) {
            final int id = i;
            new Thread(new Runnable() {
                @Override
                public final void run() {
                    (new Client(id, adresse, port, mesStrat[id])).démarrer();
                }
            }).start();
        }
    }
}
