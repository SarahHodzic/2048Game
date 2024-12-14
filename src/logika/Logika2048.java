package logika;

/**
 * Klasa Logika2048 implementira osnovnu logiku igre 2048
 */

public class Logika2048
{
    public Polje[][] ploca;
    
    NajveciScore najboljiRez = new NajveciScore();

    public int dimenzija = 4;

    int border = 0;

    public int score = 0;


    /**
     * Defaultni konstruktor za plocu - postavlja matricu 4x4
     */
    public Logika2048()
    {
        ploca = new Polje[4][4];
        for ( int i = 0; i < ploca.length; i++ )
        {
            for ( int j = 0; j < ploca[i].length; j++ )
            {
                ploca[i][j] = new Polje();
            }
        }
    }


    /**
     * Konstruktor za plocu - postavlja matricu sa zadanim dimenzijama
     * 
     * @param dimenzija1 Dimenzije matrice ploce
     */
    public Logika2048(int dimenzija1)
    {
        this.dimenzija = dimenzija1;
        ploca = new Polje[dimenzija][dimenzija];
        for ( int i = 0; i < ploca.length; i++ )
        {
            for ( int j = 0; j < ploca[i].length; j++ )
            {
                ploca[i][j] = new Polje();
            }
        }
    }

    /**
     * 
     * Getter metoda koja vraca plocu
     * 
     * @return ploca
     */
    public Polje[][] getPloca()
    {
        return ploca;
    }


    /**
     * 
     * Getter metoda koja vraca bodove
     * 
     * @return score
     */
    public int getScore()
    {
        return score;
    }


    /**
     * 
     * Nalazi plocicu sa najvecom vrijednosti i vraca je
     * 
     * @return najveca Vraca najvecu vrijednost
     */
    public int getNajvecePolje()
    {
        int najveca = ploca[0][0].getVrijednost();
        for ( int i = 0; i < ploca.length; i++ )
        {
            for ( int j = 0; j < ploca[i].length; j++ )
            {
                if ( ploca[i][j].getVrijednost() > najveca )
                {
                    najveca = ploca[i][j].getVrijednost();
                }
            }
        }
        return najveca;
    }


   


    /**
     * Vraca plocu kao string
     */
    public String toString()
    {
        String s = "";
        for ( int i = 0; i < ploca.length; i++ )
        {
            for ( int j = 0; j < ploca[i].length; j++ )
            {
                s += ploca[i][j].toString() + " ";
            }
            s += "\n";
        }
        return s;
    }

    /**
     * Generise novu plocicu na slucajnoj praznoj poziciji nakon svakog poteza.
     */
    public void spawn()
    {
    	 boolean imaPraznih = false;
    	    for (int r = 0; r < dimenzija; r++) {
    	        for (int c = 0; c < dimenzija; c++) {
    	            if (ploca[r][c].getVrijednost() == 0) {
    	                imaPraznih = true;
    	                break;
    	            }
    	        }
    	        if (imaPraznih) break;
    	    }

    	    if (!imaPraznih) {
    	        System.out.println("Nema dostupnih mjesta za novu pločicu.");
    	        return; 
    	    }
      
    	
        boolean empty = true;
        while ( empty )
        {          
            int red = (int)( Math.random() * dimenzija );
            int kolona = (int)( Math.random() * dimenzija );
            double x = Math.random();
            if ( ploca[red][kolona].getVrijednost() == 0 )
            {
                if ( x < 0.2 )
                {
                	ploca[red][kolona] = new Polje(4);
                    empty = false;
                }
                else
                {
                	ploca[red][kolona] = new Polje(2);
                    empty = false;
                }
            }

        }

    }


    /**
     * Provjerava da li je igra zavrsena (da li ima mogucih poteza ili da li je dostignuta plocica 2048).
     * 
     * @return {@code true} ako je igra zavrsena, inace {@code false}.
     */      
    public boolean gameOver() {
        int brojac = 0;
        boolean ima2048 = false;
        int plocaDimenzija = ploca.length; 

        for (int i = 0; i < plocaDimenzija; i++) {
            for (int j = 0; j < plocaDimenzija; j++) {
                if (ploca[i][j].getVrijednost() == 2048) {
                    ima2048 = true;
                }
      
                boolean mozeGore = i > 0 && ploca[i][j].getVrijednost() == ploca[i - 1][j].getVrijednost();
                boolean mozeDole = i < plocaDimenzija - 1 && ploca[i][j].getVrijednost() == ploca[i + 1][j].getVrijednost();
                boolean mozeLijevo = j > 0 && ploca[i][j].getVrijednost() == ploca[i][j - 1].getVrijednost();
                boolean mozeDesno = j < plocaDimenzija - 1 && ploca[i][j].getVrijednost() == ploca[i][j + 1].getVrijednost();

                if (!mozeGore && !mozeDole && !mozeLijevo && !mozeDesno && ploca[i][j].getVrijednost() > 0) {
                    brojac++;
                }
            }
        }

        if (ima2048) {
        	najboljiRez.spasiNajboljiRezultat(score);
            return true;
        }

        if (brojac == plocaDimenzija * plocaDimenzija) {
        	najboljiRez.spasiNajboljiRezultat(score);
            return true;
        }

        return false;
    }



    /**
     * 
     * Ova metoda se poziva kada se pritisne strelica gore - prolazi kroz cijelu plocu i poziva verticalMove
     *  sa parametrom "up" za svaku plocicu.
     */
    public void up()
    {
        for ( int i = 0; i < dimenzija; i++ )
        {
            border = 0;
            for ( int j = 0; j < dimenzija; j++ )
            {
                if ( ploca[j][i].getVrijednost() != 0 )
                {
                    if ( border <= j )
                    {
                        verticalMove( j, i, "up" );
                    }
                }
            }
        }
    }


    /**
     * 
     * Ova metoda se poziva kada se pritisne strelica dole - prolazi kroz cijelu plocu i poziva verticalMove
     * sa parametrom "down" za svaku plocicu.
     */
    public void down()
    {
        for ( int i = 0; i < dimenzija; i++ )
        {
            border = ( dimenzija - 1 );
            for ( int j = dimenzija - 1; j >= 0; j-- )
            {
                if ( ploca[j][i].getVrijednost() != 0 )
                {
                    if ( border >= j )
                    {
                        verticalMove( j, i, "down" );
                    }
                }
            }
        }
    }


    /**
     * Poredi vrijednosti dvije plocice i ako su iste ili ako je jedna jednaka 0 (prazna plocica) 
     * - njihove vrijednosti se dodaju (pod uslovom da su plocice koje poredimo dvije razlicite plocice 
     * i da se krecu u odgovarajucem smjeru) - Koristi rekurziju za prolazak kroz cijelu kolonu.
     * 
     * @param red Red u kojem se trenutno nalazi plocica koja se poredi
     *  
     * @param kolona Kolona u kojoj se trenutno nalazi plocica koja se poredi
     * 
     * @param smjer smjer (gore ili dole) u kojem se plocica krece
     */
    private void verticalMove( int red, int kolona, String smjer )
    {
        Polje polje1 = ploca[border][kolona];
        Polje polje2 = ploca[red][kolona];
        if ( polje1.getVrijednost() == 0 || polje1.getVrijednost() == polje2.getVrijednost() )
        {
            if ( red > border || ( smjer.equals( "down" ) && ( red < border ) ) )
            {
                int addScore = polje1.getVrijednost() + polje2.getVrijednost();
                if ( polje1.getVrijednost() != 0 )
                {
                    score += addScore;
                }
                polje1.setVrijednost( addScore );
                polje2.setVrijednost( 0 );
            }
        }
        else
        {
            if ( smjer.equals( "down" ) )
            {
                border--;
            }
            else
            {
                border++;
            }
            verticalMove( red, kolona, smjer );
        }
    }


    /**
     * Ova metoda se poziva kada se pritisne strelica lijevo - prolazi kroz cijelu plocu i poziva horizontalMove
     * sa parametrom "left" za svaku plocicu.
     */
    public void left()
    {
        for ( int i = 0; i < dimenzija; i++ )
        {
            border = 0;
            for ( int j = 0; j < dimenzija; j++ )
            {
                if ( ploca[i][j].getVrijednost() != 0 )
                {
                    if ( border <= j )
                    {
                        horizontalMove( i, j, "left" );
                    }
                }
            }
        }
    }


    /**
     * Ova metoda se poziva kada se pritisne strelica desno - prolazi kroz cijelu plocu i poziva horizontalMove
     * sa parametrom "right" za svaku plocicu.
     */
    public void right()
    {
        for ( int i = 0; i < dimenzija; i++ )
        {
            border = ( dimenzija - 1 );
            for ( int j = ( dimenzija - 1 ); j >= 0; j-- )
            {
                if ( ploca[i][j].getVrijednost() != 0 )
                {
                    if ( border >= j )
                    {
                        horizontalMove( i, j, "right" );
                    }
                }
            }
        }
    }


    /**
     * 
     * Poredi vrijednosti dvije plocice i ako su iste ili ako je jedna jednaka 0 (prazna plocica) 
     * - njihove vrijednosti se dodaju (pod uslovom da su plocice koje poredimo dvije razlicite plocice 
     * i da se krecu u odgovarajucem smjeru) - Koristi rekurziju za prolazak kroz cijeli red.
     * 
     * @param red Red u kojem se trenutno nalazi plocica koja se poredi
     *  
     * @param kolona Kolona u kojoj se trenutno nalazi plocica koja se poredi
     * 
     * @param smjer smjer (lijevo ili desno) u kojem se plocica krece
     */
    private void horizontalMove(int red, int kolona, String smjer)
    {
        Polje polje1 = ploca[red][border];
        Polje polje2 = ploca[red][kolona];
        if (polje1.getVrijednost() == 0 || polje1.getVrijednost() == polje2.getVrijednost())
        {
            if ( kolona > border || (smjer.equals( "right" ) && ( kolona < border )))
            {
                int addScore = polje1.getVrijednost() + polje2.getVrijednost();
                if (polje1.getVrijednost() != 0)
                {
                    score += addScore;
                }
                polje1.setVrijednost(addScore);
                polje2.setVrijednost(0);
            }
        }
        else
        {
            if (smjer.equals("right"))
            {
                border--;
            }
            else
            {
                border++;
            }
            horizontalMove(red, kolona, smjer);
        }
    }
    /**
     * Vraca vrijednost najboljeg rezultata koji je igrac postigao.
     * 
     * @return Najbolji rezultat igraca
     */
    
    public int getNajboljiRezultat() {
    	return najboljiRez.getNajboljiRezultat();
    }
    
}
