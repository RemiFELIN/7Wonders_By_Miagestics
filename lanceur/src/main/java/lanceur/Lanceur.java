package lanceur;

import client.Client;
import serveur.Serveur;
import moteur.Jeu;

public class Lanceur {

    private final static String adresse = "127.0.0.1";
    private final static int port = 10101;
    private final static int nombre_joueurs = 3;


    public final static void main(String args[]) {
        Jeu.log("\n\n------------------");
        Jeu.log("Début programme !!");

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