package gui;


import javax.swing.JFrame;


import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Klasa Uputstva nasleđuje JFrame i služi za prikazivanje uputstava za igru 2048.
 */

public class Uputstva extends JFrame{
	
	/**
	 * Konstruktor klase kreira prozor sa naslovom "Uputstva" i postavlja njegovu velicinu na 300x200 piksela.
	 * Prozor se pozicionira na sredinu ekrana. U okviru prozora, kreirana je JTextArea komponenta
	 * za prikaz teksta uputstva igre. Tekstualna komponenta je postavljena da automatski prelomi rijeci,
	 * da sadrzaj bude neizmjenjiv i da se ne prikazuju scroll bar-ovi osim kada je to potrebno.
	 * 
	 * JTextArea je dodana u JScrollPane kako bi korisnik mogao lahko pregledati
	 * cijelo uputstvo ukoliko tekst premasuje vidljivi dio prozora.
	 */
	public Uputstva() {
		this.setTitle("Uputstva");
		this.setSize(300,200);
		this.setLocationRelativeTo(null);
		
		JTextArea tekstUputstva = new JTextArea();
	    tekstUputstva.setText("Igra 2048 je logička slagalica koja se igra na kvadratnoj ploči veličine 4x4 ili 5x5. "
	        + "Cilj igre je kombinovati pločice sa istim brojevima kako bi se stvorila pločica sa brojem 2048. "
	        + "Igrač koristi tipke sa strelicama kako bi pomicao pločice po praznim poljima ploče, a nakon svakog poteza "
	        + "se pojavljuje nova pločica s brojem 2 ili 4 na praznom polju. Igra se nastavlja sve dok ne postignete "
	        + "pločicu sa brojem 2048 ili dok ne budete više u mogućnosti napraviti poteze.");
	    tekstUputstva.setWrapStyleWord(true);
	    tekstUputstva.setLineWrap(true);
	    tekstUputstva.setEditable(false);
	    tekstUputstva.setOpaque(false);
	    
	    JScrollPane scrollPane = new JScrollPane(tekstUputstva);
        this.add(scrollPane);
	}
	
	

}
