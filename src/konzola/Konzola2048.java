package konzola;

import java.util.Scanner;

import logika.Logika2048;


/**
 * Klasa za implementaciju igre u konzoli
 */

public class Konzola2048 {
	
	static Scanner sc = new Scanner(System.in);

	
	/**
     * Glavna metoda za pokretanje igre 2048.
     * Inicijalizuje igru, omogucava korisniku da unosi komande za pomjeranje plocica
     * i prikazuje stanje igre nakon svakog poteza. Igra se nastavlja dok korisnik
     * ne izgubi, tj. dok ima mogucih poteza.
     *
     * @param args Argumenti komandne linije.
     */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Logika2048 igra = izvrsiInicijalizaciju();
		
		do {
			igra.spawn();
			prikaziStanjeIgre(igra);
			
			System.out.print("Unesite smjer u kojem se zelite pomjeriti, koristite wasd komande: ");
			String akcija = sc.next();
			if (akcija.equals("w")) {
			    igra.up();
			} else if (akcija.equals("a")) {
			    igra.left();
			} else if (akcija.equals("s")) {
			    igra.down();
			} else if (akcija.equals("d")) {
			    igra.right();
			}
		} while (!igra.gameOver());
		System.out.println("Kraj igre!!!");
	}
	
	/**
     * Prikazuje trenutno stanje igre u konzoli, ukljucujuci raspored plocica na ploci,
     * trenutne bodove, najvece polje i najveci rezultat do sada.
     *
     * @param igra Instanca igre cije se stanje prikazuje.
     */
	private static void prikaziStanjeIgre(Logika2048 igra) {
		
		if(igra.ploca.length == 4)
			System.out.println(" +-------+-------+-------+-------+");
		else System.out.println(" +-------+-------+-------+-------+-------+");
	    
	    for (int i = 0; i < igra.ploca.length; i++) {
	        for (int j = 0; j < igra.ploca[i].length; j++) {
	            System.out.format(" | %-4s ", igra.ploca[i][j].toString());
	        }
	        
	        System.out.println(" |");
	       
	        if(igra.ploca.length == 4)
				System.out.println(" +-------+-------+-------+-------+");
			else System.out.println(" +-------+-------+-------+-------+-------+");
	    }
	    
	    System.out.println("Bodovi: " + igra.score + "   Najveće polje: " + igra.getNajvecePolje());
	    System.out.println("Najveci bodovi do sada: " + igra.getNajboljiRezultat());
	}
	
	/**
     * Izvrsava inicijalizaciju igre, omogucavajuci korisniku da odabere dimenzije
     * ploce. Kreira i vraca instancu igre na osnovu korisnikovog izbora.
     *
     * @return Instanca {@code Logika2048} spremna za igru.
     */
	private static Logika2048 izvrsiInicijalizaciju() {
		System.out.println("Ovo je igrica 2048.");
		ispisiUputstva();
		System.out.println();
		System.out.println("Odaberite dimenzije ploče: ");
		System.out.println("\t1. 4x4");
		System.out.println("\t2. 5x5");
		
		
		int izbor = sc.nextInt();
		
		switch (izbor) {
		case 1:
			return new Logika2048(4);
		case 2:
			return new Logika2048(5);
		default:
			return new Logika2048();
		}
		
	}

	 /**
     * Ispisuje uputstva za igru u konzoli.
     */
	private static void ispisiUputstva() {
		System.out.println("Igra 2048 je logička slagalica koja se igra na kvadratnoj ploči veličine 4x4 ili 5x5. ");
		System.out.println("Cilj igre je kombinovati pločice sa istim brojevima kako bi se stvorila pločica sa brojem 2048. ");
		System.out.println("Igrač koristi tipke sa strelicama kako bi pomicao pločice po praznim poljima ploče, a nakon svakog poteza ");
		System.out.println("se pojavljuje nova pločica s brojem 2 ili 4 na praznom polju. Igra se nastavlja sve dok ne postignete ");
		System.out.println("pločicu sa brojem 2048 ili dok ne budete više u mogućnosti napraviti poteze.");
	}

}
