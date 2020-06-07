  import javax.swing.JFrame;

  import java.awt.Container;
  import java.awt.BorderLayout;
  import javax.swing.event.*;

  import javax.swing.*;
  import java.awt.*;

  import javax.swing.border.Border;
  import javax.swing.border.LineBorder;

  /**
   * La classe <code>GMGrid</code> est utilisÃ©e pour représenter la grille.
   *
   * @version 1.1
   * @author William-Arno CLEMENT
   */

  public class GMGrid extends JLabel{

    /**
     * tableau de valeurs à deux dimensions contenant les valeurs de la grille.
     */
    private int[][] mon_tableau = new int[9][9];

    /**
     * tableau de cases à deux dimensions contenant toutes les informations nécéssaire au déroulement du sudoku.
     */
    private GMCase[][] Case = new GMCase[9][9];

    /**
     * La grille convertie en liste d'entiers.
     */
    private int[] a_renvoyer = new int[9];


    /**
     * constructeur dédié à la création des composantes publiques.
     */
    public GMGrid() {

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

          Case[i][j] = new GMCase();
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
      System.out.println("ligne--------------+");
      longueur = String.valueOf(go[i]).length();

      for ( j = 0; j < 9 - longueur; j++) {
        this.mon_tableau[i][j] = 0;
        this.Case[i][j].setValeur(this.mon_tableau[i][j]);
        this.Case[i][j].repaint();
        System.out.println("i= " + i +" j = " + j + " valeur = " + this.mon_tableau[i][j]);
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
        System.out.println("i= " + i +" j = " + j + " valeur = " + this.mon_tableau[i][j]);
      }

    }
    System.out.println("Importic réussi.");
    //this.importation = true;
    repaint();


  }

  /**
   * Classe dédiée à l'exportation de la grille.
   * @return  la grille convertie en liste d'entiers.
   */
  public int[] Exportic(){

    for (int i = 0; i < 9; i++) {
      String temp = "";

      for (int j= 0; j < 9 ; j++) {


        temp = temp + Integer.toString(this.Case[i][j].getValeur());

      }
      this.a_renvoyer[i] = Integer.parseInt(temp);
      //System.out.println(this.a_renvoyer[i]);

    }

    return this.a_renvoyer;
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

}
