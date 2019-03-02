package lanceur;

//import java.io.PrintStream;
//import java.io.UnsupportedEncodingException;

import client.Client;
import serveur.Serveur;
import moteur.Jeu;

public class Lanceur {

    private final static String adresse = "127.0.0.1";
    private final static int port = 10101;
    private final static int nombre_joueurs = 3;

    public final static void main(String args[]) {

        Jeu.log("\n\n------------------");

        //Changement de l'encodage de la console (code venant du cours)
        //Ne catch pas mais n'affiche pas les charactères spéciaux correctement
        /*try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
            System.setErr(new PrintStream(System.err, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            Jeu.error("Impossible de régler la console en utf-8", e);
        }*/

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