package logika;

import java.awt.Color;

/**
 * Klasa Polja
 */

public class Polje
{
    int vrijednost;
    Color bojaPolja;


    /**
     * Konstruktor kreira plocicu sa vrijednosti 0
     */
    public Polje()
    {
        vrijednost = 0;
    }


    /**
     * Konstruktor kreira plocicu sa zadatom vrijednosti
     * 
     * @param broj vrijednost plocice
     */
    public Polje( int broj )
    {
        vrijednost = broj;
    }


    /**
     * 
     * Vraca vrijednost plocice
     * 
     * @return vrijednost
     */
    public int getVrijednost()
    {
        return vrijednost;
    }


    /**
     * Postavlja vrijednost plocice.
     *  
     * @param vrijednost1 vrijednost na koju se postavlja plocica
     */
    public void setVrijednost( int vrijednost1 )
    {
        this.vrijednost = vrijednost1;
    }


    /**
     * Predstavlja plocicu kao string
     */
    public String toString()
    {
        return String.valueOf(vrijednost);
    }


    /**
     * 
     * Postavlja boju plocice na osnovu njene vrijednosti 
     */
    public void setBoja()
    {
        if ( this.getVrijednost() == 2 )
        {
        	bojaPolja = new Color( 238, 228, 218 );
        }
        else if ( this.getVrijednost() == 4 )
        {
        	bojaPolja = new Color( 237, 224, 200 );
        }
        else if ( this.getVrijednost() == 8 )
        {
        	bojaPolja = new Color( 242, 177, 121 );
        }
        else if ( this.getVrijednost() == 16 )
        {
        	bojaPolja = new Color( 245, 149, 99 );
        }
        else if ( this.getVrijednost() == 32 )
        {
        	bojaPolja = new Color( 246, 124, 95 );
        }
        else if ( this.getVrijednost() == 64 )
        {
        	bojaPolja = new Color( 246, 94, 59 );
        }
        else if ( this.getVrijednost() == 128 )
        {
        	bojaPolja = new Color( 237, 207, 114 );
        }
        else if ( this.getVrijednost() == 256 )
        {
        	bojaPolja = new Color( 237, 204, 97 );
        }
        else if ( this.getVrijednost() == 512 )
        {
        	bojaPolja = new Color( 237, 200, 80 );
        }
        else if ( this.getVrijednost() == 1024 )
        {
        	bojaPolja = new Color( 237, 197, 63 );
        }
        else if(this.getVrijednost() == 2048)
        {
        	bojaPolja = new Color(237, 194, 46);
        }
        else
        {
        	bojaPolja = new Color(204, 192, 179);
        }
    }


    /**
     * 
     * Vraca boju plocice
     * 
     * @return bojaPolja
     */
    public Color getBoja()
    {
        this.setBoja();
        return bojaPolja;
    }

}
