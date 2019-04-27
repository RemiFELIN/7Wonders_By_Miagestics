package lanceur;

import client.Client;
import moteur.WrapperJeu;

import static commun.ConsoleLogger.*;

import java.net.BindException;

/**
 * Permet de lancer une partie avec un nombre défini de joueurs
 * @author Pierre Saunders
 */
public class Partie {

    private final static String adresse = "127.0.0.1";
    private final static int port = 10101;
    private final static int nombre_joueurs = 4;

    public final static void main(String args[]) {
        log(YELLOW_BOLD_BRIGHT + "\n\n------------------");
        log(YELLOW_BOLD_BRIGHT + "Début programme !!");

        try {
            new WrapperJeu(adresse, port, nombre_joueurs, 7);
        } catch (BindException e) {
            log(RED_BOLD + "Adresse " + adresse + ":" + port + " déja utilisé, veuillez en utiliser un autre");
            return;
        }

        for (int i = 0; i < nombre_joueurs; i++) {
            final int id = i;
            new Thread(new Runnable() {
                @Override
                public final void run() {
                    (new Client(id, adresse, port)).démarrer();
                }
            }).start();
        }
    }
}