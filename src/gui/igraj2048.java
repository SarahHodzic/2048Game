package gui;

/**
 * Glavna klasa za pokretanje igre 2048.
 */

public class igraj2048 {
	/**
	 * Kreira instancu GameFrame, koja predstavlja glavni prozor igre,
	 * i postavlja se da bude vidljiva korisniku. Ovim se inicijalizira GUI
	 * i omogucava korisniku da komunicira sa igrom 2048.
	 * @param args argumenti komandne linije
	 */
	public static void main(String[] args) {
		GameFrame igra = new GameFrame();
		igra.setVisible(true);
	}
}
