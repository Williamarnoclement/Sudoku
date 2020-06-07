import javax.swing.JFrame;

import java.awt.Container;
import java.awt.BorderLayout;
import javax.swing.event.*;

import javax.swing.*;
import java.awt.*;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 * La classe <code>GSGrid</code> est utilisÃ©e pour représenter la grille.
 *
 * @version 1.1
 * @author William-Arno CLEMENT
 */

public class GSGrid extends JLabel {

  /**
   * tableau de valeurs à deux dimensions contenant les valeurs de la grille.
   */
  private int[][] mon_tableau = new int[9][9];

  /**
   * tableau de cases à deux dimensions contenant toutes les informations nécéssaire au déroulement du sudoku.
   */
  private GSCase[][] Case = new GSCase[9][9];

  /**
   * le menu est utilisé dans cette classe pour vérifier si la grille est jouable suite à l'importation.
   */
  GSMenu my_menu;

  /**
   * abscisses de la case actuellement active
   */
  private int Active_X = 0;
  /**
   * ordonnées de la case actuellement active
   */
  private int Active_Y = 0;

  /**
   * Le joueur est-il en train de jouer ?
   */

  private Boolean isPlaying;


  /**
   * constructeur dédié à la création des composantes publiques.
   * @param menu le menu importée
   */
  public GSGrid(GSMenu menu) {

    this.my_menu = menu;

    int i, j ;
    int increment = 1;
    int consti = 9;
    int constj = 9;
    this.setLayout(new FlowLayout());

    JLabel Conteneur_Grille = new JLabel();

    Conteneur_Grille.setMinimumSize(new Dimension(540, 540));
    Conteneur_Grille.setPreferredSize(new Dimension(620, 620));
    Conteneur_Grille.setMaximumSize(new Dimension(940, 940));

    // Premier panneau
    GridLayout ma_Grille_pagination = new GridLayout(consti,constj);

    Conteneur_Grille.setLayout(ma_Grille_pagination);

    //Boucle pour i = 0 a 5²
    for (i = 0; i < consti; i++) {

      for (j= 0; j < constj ; j++) {

        Case[i][j] = new GSCase(this,i,j);
        //Border thinBorder = LineBorder.createBlackLineBorder();
        //this.Case[i][j].setBorder(thinBorder);

        if (i==2 || i==5 || i==8) {
          if (j == 0) {
            this.Case[i][j].setBorder(BorderFactory.createMatteBorder(1, 5, 5, 1, Color.BLACK));
          } else if(j==2 || j==5 || j==8) {
            this.Case[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 5, 5, Color.BLACK));
          } else {
            this.Case[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 5, 1, Color.BLACK));
          }
        }
        if (i==0) {
          if (j == 0) {
            this.Case[i][j].setBorder(BorderFactory.createMatteBorder(5, 5, 1, 1, Color.BLACK));
          } else if(j==2 || j==5 || j==8) {
            this.Case[i][j].setBorder(BorderFactory.createMatteBorder(5, 1, 1, 5, Color.BLACK));
          } else {
            this.Case[i][j].setBorder(BorderFactory.createMatteBorder(5, 1, 1, 1, Color.BLACK));
          }
        }

        if (i==1 || i==3 || i==4 || i==6 || i==7) {
          if (j == 0) {
            this.Case[i][j].setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.BLACK));
          }
          else if (j==2 || j==5 || j==8) {
            this.Case[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 5, Color.BLACK));
          }
          else{
            this.Case[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
          }

        }


        Conteneur_Grille.add(this.Case[i][j]);

      }
    }

    this.add(Conteneur_Grille);

    //this.importation = false;


  }

  /**
   * Classe dédiée à l'importation de la grille. Elle transforme une liste d'entier en valeurs de cases de la grille.
   * @param go la liste d'entier du fichier
   */

  public void Importic(int[] go){
    int i,j;
    int longueur;
    System.out.println("Into Importic");
    for (i = 0; i < 9 ;  i++) {
      //System.out.println("ligne--------------+");
      longueur = String.valueOf(go[i]).length();

      for ( j = 0; j < 9 - longueur; j++) {
        this.mon_tableau[i][j] = 0;
        this.Case[i][j].setValeur(this.mon_tableau[i][j]);
        this.Case[i][j].repaint();
        //System.out.println("i= " + i +" j = " + j + " valeur = " + this.mon_tableau[i][j]);
      }
      int[] transfert = new int[longueur];
      String str = Integer.toString(go[i]);
      for ( j = 0; j < longueur; j++) {
        transfert[j] = (int) Character.getNumericValue(str.charAt(j));
      }
      int k = 0;
      for ( j = 9 - longueur; j < 9; j++) {
        this.mon_tableau[i][j] = transfert[k];
        k++;
        this.Case[i][j].setValeur(this.mon_tableau[i][j]);
        this.Case[i][j].repaint();
        //System.out.println("i= " + i +" j = " + j + " valeur = " + this.mon_tableau[i][j]);
      }

    }
    System.out.println("Importic réussi.");
    this.my_menu.isPlayable();



  }

  /**
   * Permet de regarder la valeur se trouvant dans une case.
   * @param i abscisses de la case à tester
   * @param j ordonnées de la case à tester
   * @return la valeur principale de la case.
   */
  public int testic(int i, int j){

    return this.Case[i][j].getValeur();

  }


  /**
   * Permet de voir si la case suivante est active ou non.
   * @param x abscisses de la case à tester
   * @param y ordonnées de la case à tester
   */
   public void testActivity(int x, int y){


     for (int i = 0 ; i < 9 ; i++ ) {
       for (int j = 0 ; j < 9 ; j++ ) {
         if (this.Case[i][j].getActivity() == true && (i != x || j != y)) {
           this.Active_X = x;
           this.Active_Y = y;
           this.Case[i][j].desactive();
         }
       }
     }
   }

   /**
    * @return l'abscisse de la case active.
    */
   public int whoIsActive_X(){
     return this.Active_X;
   }
   /**
    * @return l'ordonnée de la case active.
    */
   public int whoIsActive_Y(){
     return this.Active_Y;
   }

   /**
    * permet d'inserer une valeur dans une case.
    * @param x abscisse de la case à modifier
    * @param y ordonnée de la case à modifier
    * @param val valeur à inserer
    */
   public void setValuetoCase(int x, int y, int val){
     if (this.Case[x][y].getActivity() && getMode()) {
       this.Case[x][y].updateValeur(val);
       //System.out.println("setValuetoCase réussi.");
     }
   }

   /**
    * @return TRUE si la grille est completement remplie.
    */
   public Boolean isComplete(){

     for (int i = 0 ; i < 9 ; i++ ) {
       for (int j = 0 ; j < 9 ; j++ ) {
         if (this.Case[i][j].getNB() != 1 && this.Case[i][j].getValeur() == 0) {
           return true;
         }
       }
     }
     return false;
   }

   /**
    * on insère TRUE si le joueur utilise cette grille pour la résoudre manuellement
    * @param _bool le joueur est-il en train de jouer ?
    */
   public void isPlaying(Boolean _bool){
     this.isPlaying = _bool;

   }

   /**
    * @return TRUE si le joueur utilise cette grille pour la résoudre manuellement, false sinon.
    */
   public Boolean getMode(){
     return this.isPlaying;

   }

   /**
    * algorithme de backtracking, permet la résolution de la grille automatiquement.
    * @return TRUE si la résolution est terminée.
    */
   public boolean solve() {
    System.out.println("solving..");
    for (int row = 0; row < 9; row++) {
        for (int column = 0; column < 9; column++) {
            if (this.mon_tableau[row][column] == 0) {
                for (int k = 1; k <= 9; k++) {
                    this.mon_tableau[row][column] = k;
                    this.Case[row][column].insertValeur(k);
                    GSTest _test = new GSTest(this);
                    if (_test.isValid(row, column) && solve()) {
                        System.out.println("k= " + k);
                        return true;
                    }
                    this.mon_tableau[row][column] = 0;
                    this.Case[row][column].insertValeur(0);
                }
                return false;
            }
        }
    }
    return true;
}

}
