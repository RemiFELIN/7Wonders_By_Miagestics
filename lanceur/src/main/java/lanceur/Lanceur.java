package lanceur;

import client.Client;
import serveur.Serveur;
import static moteur.ConsoleLogger.*;

public class Lanceur {

    private final static String adresse = "127.0.0.1";
    private final static int port = 10101;
    private final static int nombre_joueurs = 4;

    public final static void main(String args[]) {
        log(YELLOW_BOLD_BRIGHT + "\n\n------------------");
        log(YELLOW_BOLD_BRIGHT + "Début programme !!");

        final String info[] = new String[] { adresse, Integer.toString(port) };
        Thread serveur = new Thread(new Runnable() {
            @Override
            public void run() {
                Serveur.main(info);
            }
        });
        serveur.start();

        Thread tabJoueurs[] = new Thread[nombre_joueurs];
        for (int i = 0; i < nombre_joueurs; i++) {
            final String infoJoueur[] = new String[] { adresse, Integer.toString(port), Integer.toString(i) };
            tabJoueurs[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    Client.main(infoJoueur);
                }
            });
            tabJoueurs[i].start();
        }
    }
}