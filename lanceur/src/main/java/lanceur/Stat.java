package lanceur;

import client.Client;
import client.strategie.*;
import moteur.wrapperJeu;

import java.net.BindException;

import static commun.ConsoleLogger.*;

/**
 * Le lanceur permettant de générer un résumé concis de 500 parties '7Wonders'
 * @author Rémi Felin
 */
public class Stat {

    private static wrapperJeu partie;
    private final static String adresse = "127.0.0.1";
    private final static int port = 10101;
    private final static int nombre_joueurs = 5;
    private final static int nombresDeParties = 500;
    int mean;

    static public void main(String[] args) {

        try {
            desactiverLog();
            partie = new wrapperJeu(adresse, port, nombresDeParties);
        } catch (BindException e) {
            log(RED_BOLD + "Adresse " + adresse + ":" + port + " déja utilisé, veuillez en utiliser un autre");
            return;
        }

        Strategie[] mesStrat = new Strategie[] { new StratPointVictoire(false), new StratRandom(false), new StratRessources(false), new StratMilitaire(false), new StratScientifique(false) };

        for (int i = 0; i < nombre_joueurs; i++) {
            int id = i;
            new Thread(new Runnable() {
                @Override
                public final void run() {
                    (new Client(id, adresse, port, mesStrat[id])).démarrer();
                }
            }).start();
        }



    }
}
