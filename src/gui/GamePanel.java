package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.JComponent;

import javax.swing.JPanel;
import javax.swing.KeyStroke;

import logika.Logika2048;
import logika.Polje;

/**
 * Panel u koji se iscrtava igrica i na kojem se ispisuju podaci za trenutni score, najveci rezultat i najveci broj plocice
 */

public class GamePanel extends JPanel implements KeyListener
{
    Logika2048 igra = new Logika2048();

    private int sirinaPanela = 600;
    private int visinaPanela = 600;
    
    
    /**
     * Kreira novi {@code GamePanel} postavljajuci boju pozadine i omogucava
	 * restartovanje igre pritiskom na taster ENTER. Ako je igra u stanju "game over",
	 * pritiskom na ENTER ce se igra restartovati i ispisati poruka u konzolu.
	 * U suprotnom, ako igra nije u "game over" stanju, ispisuje se poruka
	 * da igra nije u "game over" stanju.
	 * 
	 * Postavlja se slusalac dogadjaja za pritisak tastera ENTER, koji provjerava
	 * da li je trenutni status igre "game over". Ako jeste, igra se restartuje
	 * pozivom metode {@code pocniIgru}.
     */
    public GamePanel() {
    	this.setBackground(new Color(204, 192, 179));
    	
    	this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ENTER"), "restartGame");
    	this.getActionMap().put("restartGame", new AbstractAction() {
    	    @Override
    	    public void actionPerformed(ActionEvent e) {
    	    	if (igra.gameOver()) {
    	            System.out.println("Restartovanje igre...");
    	            pocniIgru(); 
    	        } 
    	    	else {
    	            System.out.println("Igra nije u 'game over' stanju.");
    	        }
    	    }
    	});

    }
    
    /**
     * Kreira novi panel ali sa zadatim velicinama, broja kolona i redova ploce
     * @param velicina - dimenzije ploce
     */
    
    public GamePanel(int velicina) {
    	this();
    	igra = new Logika2048(velicina);
    }


    /**
     * Provjerava da li su pritisnute strelice i izvrsava odgovarajuce akcije i dodaje novo random polje na praznom mjestu 
     * - azurira JFrame sa svakim potezom.
     * 
     * @param e
     *            KeyEvent koji se provjerava
     */
    @Override
    public void keyPressed( KeyEvent e )
    {
        if (e.getKeyCode() == KeyEvent.VK_UP )
        {
        	//System.out.println("GORE");
            igra.up();
            igra.spawn();
            repaint();
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN )
        {
        	//System.out.println("DOLE");
        	igra.down();
        	igra.spawn();
            repaint();
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT )
        {
        	//System.out.println("LIJEVO");
        	igra.left();
        	igra.spawn();
            repaint();
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT )
        {
        	//System.out.println("DESNO");
        	igra.right();
        	igra.spawn();
            repaint();
        }
    }


    @Override
    public void keyReleased( KeyEvent e )
    {
     

    }


    @Override
    public void keyTyped( KeyEvent e )
    {
       

    }


    /**
     * Crtanje GUI-ja pomocu niza stringova, ploce, plocica.
     * Ako je igra zavrsena igra.gameOver() vraca true, preko cijelog prozora se iscrtava
	 * poluprozirni sloj kako bi se naglasio kraj igre, te se prikazuje poruka "KRAJ IGRE" i trenutni bodovi,
	 * kao i instrukcija za pocetak nove igre pritiskom na taster Enter.
     * 
     * @param g Graphics parametar
     */
    public void paint( Graphics g )
    {
        super.paint( g );
        Graphics2D g2 = (Graphics2D)g;
        g2.drawString( "2048", 250, 20 );
        g2.drawString( "Bodovi: " + igra.getScore(),
            200 - 4 * String.valueOf( igra.getScore() ).length(),
            40 );
        g2.drawString( "Najveće polje: " + igra.getNajvecePolje(),
            280 - 4 * String.valueOf( igra.getNajvecePolje() ).length(),
            40 );
        g2.drawString( "Najveći rezultat: " + igra.getNajboljiRezultat(),
                400 - 4 * String.valueOf( igra.getNajboljiRezultat() ).length(),
                40 );
        g2.setColor( Color.white );
        
        g2.fillRoundRect(100, 50, igra.dimenzija * 80 + 30, igra.dimenzija * 80 + 20, 10, 10);
        for ( int i = 0; i < igra.dimenzija; i++ )
        {
            for ( int j = 0; j < igra.dimenzija; j++ )
            {
            	crtajPolja( g, igra.ploca[i][j], j * 80 + 120, i * 80 + 60 );
            }
        }
        if (igra.gameOver())
        {
        	System.out.println("gameover");

            g2.setColor(new Color(15, 15, 15, 127));
            g2.fillRect(0, 0, sirinaPanela, visinaPanela); 
         
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Arial", Font.BOLD, 34));
            FontMetrics fm = g2.getFontMetrics();
            String poruka = "KRAJ IGRE";
            g2.drawString(poruka, (sirinaPanela - fm.stringWidth(poruka)) / 2, visinaPanela / 2 - 70);           
            
            fm = g2.getFontMetrics();
            String bodovi = "Score " + igra.score;
            g2.drawString(bodovi, (sirinaPanela - fm.stringWidth(bodovi)) / 2, visinaPanela / 2);

            g2.setFont(new Font("Arial", Font.PLAIN, 24));
            fm = g2.getFontMetrics();
            String instrukcija = "Za početak nove igre pritisnite Enter";
            g2.drawString(instrukcija, (sirinaPanela - fm.stringWidth(instrukcija)) / 2, visinaPanela / 2 + 35);
            this.setFocusable(true);
        }
    }


    /**
     * Crtanje pojedinacnog polja - poziva se iz metode paint.
     * 
     * @param g Graficki parametar
     * @param polje Plocica koja se crta
     * @param x X koordinata na kojoj se crta
     * @param y Y koordinata na kojoj se crta
     */

    public void crtajPolja(Graphics g, Polje polje, int x, int y)
    {
        int vrijednostPolja = polje.getVrijednost();

        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.lightGray);
        g2.fillRoundRect(x, y, 70, 70, 5, 5);
        g2.setColor(Color.black);
        if (vrijednostPolja > 0)
        {
            g2.setColor(polje.getBoja());
            g2.fillRoundRect(x, y, 70, 70, 5, 5);
            g2.setColor(Color.black);
            
            Font font = new Font("Arial", Font.PLAIN, 24);
            g2.setFont(font);
            FontMetrics fm = g2.getFontMetrics();

            String text = String.valueOf(vrijednostPolja);
            int textWidth = fm.stringWidth(text);
            int textHeight = fm.getAscent(); 
            
            int textX = x + (70 - textWidth) / 2;
            int textY = y + ((70 - textHeight) / 2) + fm.getAscent();

            g2.drawString(text, textX, textY);
        }
    }
    
    
    /**
     * Pokrece novu igru inizijalizacijom logike igre i postavljanjem dvije nove random plocice sa brojevima 2 ili 4 na nekom od praznih mjesta
     */
    public void pocniIgru() {
    	igra = new Logika2048(igra.dimenzija);
        igra.spawn();
        igra.spawn();
        this.setFocusable(false);
        repaint();
    }


}
