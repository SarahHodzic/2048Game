package logika;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Klasa za najveci postignuti rezultat do sada
 */

public class NajveciScore {
	
        private String putanja = "resources/highscore.txt";
        private int najboljiRezultat = 0;

        /**
         * Konstruktor koji instancira objekat {@code NajveciScore}.
         * Pri instanciranju, automatski ucitava najbolji rezultat iz datoteke
         * cija je putanja specificirana.
         */
        public NajveciScore() {
            ucitajNajboljiRezultat();
        }


        /**
         * Metoda za ucitavanje najboljeg rezultata iz datoteke.
         * Cita prvu liniju datoteke pretpostavljajuci da se u njoj nalazi
         * najveci rezultat kao string, koji zatim konvertuje u cijeli broj.
         * U slucaju greske, ispisuje poruku o greski.
         */
        private void ucitajNajboljiRezultat() {
            try (BufferedReader reader = new BufferedReader(new FileReader(putanja))) {
                String red = reader.readLine();
                if (red != null) {
                    najboljiRezultat = Integer.parseInt(red);
                }
            } catch (IOException | NumberFormatException e) {
                System.err.println("Greška pri učitavanju najboljeg rezultata: " + e.getMessage());
            }
        }

        /**
         * Metoda za spremanje novog najboljeg rezultata u datoteku.
         *
         * @param score Novi rezultat koji se potencijalno postavlja kao najbolji.
         */
        public void spasiNajboljiRezultat(int score) {
            if (score > najboljiRezultat) {
                najboljiRezultat = score;
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(putanja))) {
                    writer.write(String.valueOf(score));
                } catch (IOException e) {
                    System.err.println("Greška pri spremanju najboljeg rezultata: " + e.getMessage());
                }
            }
        }

        /**
         * Vraca trenutni najbolji rezultat.
         *
         * @return Najbolji rezultat kao cijeli broj.
         */
        public int getNajboljiRezultat() {
            return najboljiRezultat;
        }
    

}
