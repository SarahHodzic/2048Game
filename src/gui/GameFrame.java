package gui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
/**
 * Kreira JFrame prozor u koji je smjestena igra
 */
public class GameFrame extends JFrame{
	GamePanel panel;
	JMenuBar meni;
	JMenu cetiri;
	JMenu pet;
	JMenu uputstva;
	
	/**
	 * Kreira JFrame okvir, postavlja boju pozadine, dodaje meni i panel na kojem je postavljena igrica
	 * @param s	Title JFrame-a
	 */
	
	public GameFrame(String s) {
		super(s);
		meni = new JMenuBar();
		meni.setBackground(Color.lightGray);
		cetiri = new JMenu("4x4");
		pet = new JMenu("5x5");
		uputstva = new JMenu("Uputstva");
		meni.add(cetiri);
		meni.add(pet);
		meni.add(uputstva);
		this.setJMenuBar(meni);
		panel = new GamePanel();
		panel.pocniIgru();
		
		cetiri.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getContentPane().remove(panel);
				panel = new GamePanel(4);
				panel.pocniIgru();
				getContentPane().add(panel);
				addKeyListener(panel);
		        panel.requestFocusInWindow();
				validate();
				repaint();
			}
		});
		
		pet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getContentPane().remove(panel);
				panel = new GamePanel(5);
				panel.pocniIgru();
				getContentPane().add(panel);
				addKeyListener(panel);
		        panel.requestFocusInWindow();
				validate();
				repaint();
			}
		});
		
		uputstva.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Uputstva upute = new Uputstva();
				upute.setVisible(true);
			}
		});
		
		
		this.addKeyListener(panel);
		this.setSize(600,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.add(panel);
		this.setResizable(false);
	}
	
	/**
	 * Otvara prozor sa defaultnim nazivom igre "2048"
	 */
	public GameFrame() {
		this("2048");
	}
}
