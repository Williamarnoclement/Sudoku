import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 * La classe <code>GSTest</code> est utilisÃ©e pour la vérification
 * des differentes valeurs sur la grille.
 * @version 1.1
 * @author William-Arno CLEMENT
 */


public class GSTest implements ActionListener {

  /**
   * ma_Grille est la grille importée depuis la classe parente.
   */
  private GSGrid ma_Grille;

  /**
   * constructeur dédié à la création des composantes publiques.
   * @param Grid la grille importée
   */

  public GSTest(GSGrid Grid) {

    this.ma_Grille = Grid;

  }
  /**
   * Fonction lancée lorsque le bouton Tester est cliqué
   * @param e ActionEvent lié à la classe
   */
  public void actionPerformed(ActionEvent e) {

  }

  /**
   * La méthode test va effectuer une verification ligne/colonne/region globale
   * @return VRAI si la verification est un succès
   */
  public Boolean test() {

    //System.out.println("Test Grille ! ");

    /* Verification colonne */

    for ( int i = 0; i < 9 ; i++ ) {

      for ( int j = 0; j < 9 ; j++ ) {

        for (int i_prime = i + 1; i_prime < 9 ; i_prime++ ) {

          if (this.ma_Grille.testic(i, j ) == this.ma_Grille.testic(i_prime, j ) && this.ma_Grille.testic(i, j ) != 0) {
            //System.out.println("dedondance colonne : ligne " + i + " colonne : " + j + " avec ligne " + i_prime + " et  colonne : " + j );
            return true;
          }


        }

      }

    }

    /* Verification ligne */

    for ( int i = 0; i < 9 ; i++ ) {

      for ( int j = 0; j < 9 ; j++ ) {

        for (int j_prime = j + 1; j_prime < 9 ; j_prime++ ) {

          if (this.ma_Grille.testic(i, j ) == this.ma_Grille.testic(i, j_prime ) && this.ma_Grille.testic(i, j ) != 0) {
            //System.out.println("dedondance ligne :  ligne " + i + " colonne : " + j + " avec ligne " + i + " et  colonne : " + j_prime );
            return true;
          }

        }

      }

    }

    /* Verification région */

    for (int i = 0; i < 9; i = i+3 ) {
      for (int j = 0; j < 9; j = j+3 ) {
        if (verifRegion(i, j)) {
          //System.out.println("dedondance region :  ligne " + i + " colonne : " + j );
          return true;
        }
      }
    }

    return false;

  }


  /**
   * La méthode test va effectuer une verification pour un region donnée
   * @param x coordonnées x de la région à vérifier
   * @param y colonne y de la région à vérifier
   * @return la verification
   */

  public Boolean verifRegion(int x, int y){

    int Major_X = 1;
    int Major_Y = 1;


    if (0 <= x &&  x <= 2)
    {
      Major_X = 1;

    } else if (3 <= x &&  x  <= 5){

      Major_X = 2;

    } else if (6 <= x  &&  x <= 8){

      Major_X = 3;

    }

    if (0 <= y  &&  y  <= 2)
    {
      Major_Y = 1;

    } else if (3 <= y  &&  y <= 5){

      Major_Y = 2;

    } else if (6 <= y &&  y <= 8){

      Major_Y = 3;

    }

    int Init_X = (3 * Major_X) - 1;
    int Init_Y = (3 * Major_Y) - 1;
    int incrementable = 0;
    int[] tableau_test = new int[9];

    /* On importe les valeurs de la région */
    //System.out.println("-----------+");
    for (int i = 0; i < 3 ; i++ ) {
      for (int j = 0; j < 3 ; j++ ) {
        //System.out.println(this.ma_Grille.testic(Init_X - i,Init_Y - j));
        tableau_test[incrementable] = this.ma_Grille.testic(Init_X - i,Init_Y - j);
        incrementable++;
      }
    }

    /*On verifie l'unicité des valeurs du tableau */


    for (int i = 0; i < 9 ; i++ ) {
      for (int j =  i + 1 ; j < 9 ; j++ ) {

        if (tableau_test[i] == tableau_test[j] && tableau_test[i] != 0) {
          return true;

        }

      }
    }


    return false;

  }

  /**
   * La méthode test va effectuer une verification pour une ligne donnée
   * @param x ligne de vérification
   * @return la verification
   */

  public Boolean verifLigne(int x){

    int i = x;

    for ( int j = 0; j < 9 ; j++ ) {

      for (int j_prime = j + 1; j_prime < 9 ; j_prime++ ) {

        if (this.ma_Grille.testic(i, j ) == this.ma_Grille.testic(i, j_prime ) && this.ma_Grille.testic(i, j ) != 0) {
          //System.out.println("dedondance ligne :  ligne " + i + " colonne : " + j + " avec ligne " + i + " et  colonne : " + j_prime );
          return true;
        }
      }
    }
    return false;
  }


  /**
   * La méthode test va effectuer une verification pour une colonne donnée
   * @param y colonne de vérification
   * @return la verification
   */
  public Boolean verifColonne(int y){

    int j = y;

    for ( int i = 0; i < 9 ; i++ ) {


      for (int i_prime = i + 1; i_prime < 9 ; i_prime++ ) {

        if (this.ma_Grille.testic(i, j ) == this.ma_Grille.testic(i_prime, j ) && this.ma_Grille.testic(i, j ) != 0) {
          //System.out.println("dedondance colonne : ligne " + i + " colonne : " + j + " avec ligne " + i_prime + " et  colonne : " + j );
          return true;
        }
      }
    }
    return false;
  }


  /**
   * La méthode isValid renvoie TRUE si la vérification est valide
   * @param row coordonnées x de la case à vérifier
   * @param column coordonnées y de la case à vérifier
   * @return TRUE si la vérification est valide
   */

  public Boolean isValid(int row, int column){
    System.out.println("IsVALID");
    return (!verifLigne(row) && !verifColonne(column) && !verifRegion(row, column));
  }


}
