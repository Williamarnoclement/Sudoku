import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 * La classe <code>GMTest</code> est utilisée pour tester la cohérence
 * de la grille, au clic de l'item Verifier de la classe GMUI ou au lancement de la classe GMSaver.
 *
 * @version 1.1
 * @author William-Arno CLEMENT
 */

public class GMTest implements ActionListener {

  /**
   * La grille importée.
   */
  private GMGrid ma_Grille;

  /**
   * constructeur dédié à la création des composantes publiques.
   * @param Grid la grille importée.
   */
  public GMTest(GMGrid Grid) {

    this.ma_Grille = Grid;

  }

  /**
   * Lance la méthode isCorrect au clic de l'item Verifier de la classe GMUI.
   */
  public void actionPerformed(ActionEvent e) {
    isCorrect();
  }

  /**
   * La méthode test va effectuer une verification ligne/colonne/region globale.
   * @return VRAI si il existe une redondance et si la grille est incohérente.
   */
  public Boolean test() {

    //System.out.println("Test Grille ! ");

    /* Verification colonne */

    for ( int i = 0; i < 9 ; i++ ) {

      for ( int j = 0; j < 9 ; j++ ) {

        for (int i_prime = i + 1; i_prime < 9 ; i_prime++ ) {

          if (this.ma_Grille.testic(i, j ) == this.ma_Grille.testic(i_prime, j ) && this.ma_Grille.testic(i, j ) != 0) {
            System.out.println("dedondance colonne : ligne " + i + " colonne : " + j + " avec ligne " + i_prime + " et  colonne : " + j );
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
            System.out.println("dedondance ligne :  ligne " + i + " colonne : " + j + " avec ligne " + i + " et  colonne : " + j_prime );
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
   * Permet d'afficher un message en cas de redondance et de non cohérence de la grille.
   */
  public void Message(){
    JFrame f = new JFrame("Redondance");
    JDialog d = new JDialog(f, "Redondance");
    JLabel l = new JLabel("<html>Deux meme chiffres presents dans la meme ligne/colonne/region.<br> Veuillez modifier votre grille !</html>");
    d.add(l);
    d.setSize(300, 200);
    d.setVisible(true);
  }

  /**
   * Permet d'afficher un message en cas de cohérence de la grille.
   */
  public void Coherent(){
    JFrame f = new JFrame("Grille coherente");
    JDialog d = new JDialog(f, "Grille coherente");
    JLabel l = new JLabel("<html>Votre Grille est coherente. Aucune redondance.<br> Vous pouvez desormais <b>sauver votre grille</b> !</html>");
    d.add(l);
    d.setSize(300, 200);
    d.setVisible(true);
  }


  /**
   * Permet d'afficher un message en cas d'incohérence de la grille.
   */
  public void isReady() {
    if (test()) Message();
  }

  /**
   * Renvoie si la grille est correcte.
   * @return TRUE si la grille est cohérente
   */
  public Boolean isCorrect(){
    if (test()) {
      Message();
      return false;
    }
    if (!test()) {
      Coherent();
      return true;
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

}
