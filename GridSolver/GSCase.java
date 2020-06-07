import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JButton;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

/**
 * La classe <code>GSCase</code> est utilisée pour représenter
 * une case.
 *
 * @version 1.1
 * @author William-Arno CLEMENT
 */

public class GSCase extends JPanel implements MouseListener{

  /**
   * La valeur actuellement rentré dans la case.
   */
  private int value;
  /**
   *  seconde valeur si il y en a une
   */
  private int secondvalue;
  /**
   *  troisieme valeur si il y en a une
   */
  private int thirdvalue;
  /**
   *  quatrieme valeur si il y en a une
   */
  private int fourthvalue;

  /**
   *  texte inscrit sur la case
   */
  private String text = "";

  /**
   *  Cette case à t-elle été initialisée à l'import ?
   */
  private Boolean isInitial = false;
  /**
   *  Cette case est-elle active ?
   */
  private Boolean isActive;


  /**
   *  JLabel utilisé pour représenter visuellement la case.
   */
  JLabel label = new JLabel();



  /**
   *  nombre de chiffres entrés dans la case.
   */
  private byte nombreChiffres = 0;

  /**
   *  abscisses de la case
   */

  private int the_X;

  /**
   *  ordonnées de la case
   */
  private int the_Y;


  /**
   *  La grille à laquelle appartient cette case
   */
  private GSGrid ma_Grille;

  /**
   * constructeur dédié à la création des composantes publiques.
   * @param grille la grille importée
   * @param i x de la case dans la Grille
   * @param j y de la case dans la Grille
   */
  public GSCase(GSGrid grille,int i, int j) {

    this.the_X = i;
    this.the_Y = j;
    this.setBackground(Color.white);

    addMouseListener(this);

    this.ma_Grille = grille;

    this.add(label);

    this.value= 0;
    this.secondvalue = 0;
    this.thirdvalue = 0;
    this.fourthvalue = 0;

    desactive();
    miseEnPage();

  }


  /**
   * Initie la mise en page lorque l'utilisateur clique sur la case
   * @param e ActionEvent lié à la classe
   */
  public void actionPerformed(ActionEvent e) {

    this.miseEnPage();

  }


  /**
   * Permet la mise en page de la case : coloriage de background, inscription
   * de la valeur ou indication des differentes valeurs.
   */
  public void miseEnPage(){

    if (this.value != 0) {

      this.label.setText(this.text);
      this.label.setVisible(true);
      this.repaint();

    } else {
      this.label.setText("");
      this.label.setVisible(true);
      this.repaint();
    }


  }

  /**
   * Initialise la grille avant résolution.
   */

  public void Initialisation(){

    //System.out.println("Clic !");

    if (!this.isInitial) {


      label.setText(this.text);


    } else {
      label.setText(this.text);
      this.setBackground(Color.lightGray);
    }

    miseEnPage();


  }

  /**
   * insert valeur après vérification
   * @param ma_valeur la valeur à insérer
   */

  public void setValeur(int ma_valeur){

    if (ma_valeur == 0) {
      this.isInitial = false;
      this.text = "";
    } else {
      this.text = String.valueOf(ma_valeur);
      this.isInitial = true;
    }
    this.value = (int) ma_valeur;
    this.Initialisation();
    //System.out.println("setValeur réussi.");

  }

  /**
   * Renvoie la première valeur de la case
   * @return la valeur principale de la case
   */

  public int getValeur(){

    return this.value;

  }


  /**
   * repeint la case si cette dernière est non initiale.
   * @param e MouseEvent
   */

  public void mouseClicked(MouseEvent e) {

    if (!this.isInitial) {

      this.isActive = true;
      this.setBackground(Color.GREEN);
      this.ma_Grille.testActivity(this.the_X, this.the_Y);

    }
  }

  /**
   * nécessaire au bon fonctionnement de MouseListener
   */
  public void mousePressed(MouseEvent e) {}

    /**
     * nécessaire au bon fonctionnement de MouseListener
     */
  public void mouseReleased(MouseEvent e) {}

    /**
     * la case devient jaune au passage de la souris.
     */
  public void mouseEntered(MouseEvent e) {

    if (!isInitial) {
      if (!isActive) {
        this.setBackground(Color.yellow);
      }
    }
  }

  /**
   * la case devient blanche après le passage de la souris.
   */

  public void mouseExited(MouseEvent e) {
    if (!isInitial) {
      if (!isActive) {
        this.setBackground(Color.white);
      }
    }
  }

  /**
   * désactive la case . Elle n'est plus le focus de l'utilisateur.
   */
  public void desactive(){

    this.isActive = false;
    this.setBackground(Color.white);

  }

  /**
   * Renvoie si la case est active, si elle a le focus de l'utilisateur.
   * @return dit si la case est active
   */
  public Boolean getActivity(){
    return this.isActive;
  }


   /**
    * met à jour valeur après vérification de cohérence dans la grille.
    * @param ma_valeur la valeur à insérer
    */

  public void updateValeur(int ma_valeur){

    if (ma_valeur == 0) {

      this.value = 0;
      this.nombreChiffres = 0;
      miseEnPage();

    } else {
      if (!verifInputVal(ma_valeur)) {
        System.out.println("verif ok.");


        if (this.value != ma_valeur ) {
            if (this.nombreChiffres == 0) {
              this.nombreChiffres++;
              this.value = ma_valeur;
              this.text = String.valueOf(this.value);
            } else if (this.nombreChiffres == 1 && ma_valeur != this.value) {
              this.secondvalue = ma_valeur;
              this.nombreChiffres++;
              this.text = String.valueOf(this.value + ", " + this.secondvalue);
            } else if (this.nombreChiffres == 2 && ma_valeur != this.value && ma_valeur != this.secondvalue) {
              this.thirdvalue = ma_valeur;
              this.nombreChiffres++;
              this.text = String.valueOf(this.value + ", " + this.secondvalue + ", " + this.thirdvalue);
            } else if (this.nombreChiffres == 3 && ma_valeur != this.value && ma_valeur != this.secondvalue && ma_valeur != this.thirdvalue) {
              this.fourthvalue = ma_valeur;
              this.nombreChiffres++;
              this.text = String.valueOf(this.value + ", " + this.secondvalue + ", " + this.thirdvalue + ", " + this.fourthvalue);
            }
        }
        this.setBackground(Color.PINK);
        System.out.println("-------------------------------+");
        System.out.println("----------------------+" + this.value);
        System.out.println("----------------------+" + this.secondvalue);
        System.out.println("----------------------+" + this.thirdvalue);
        System.out.println("----------------------+" + this.fourthvalue);
        miseEnPage();

      } else if (verifInputVal(ma_valeur)){
        System.out.println("verif nope.");
        this.setBackground(Color.RED);
      }
    }


  }
  /**
   * verifie une valeur entrée
   * @param val la valeur à vérifier
   * @return si la valeur est redondante.
   */

  public Boolean verifInputVal(int val){

    int temp = this.value;

    this.value = val;

    GSTest _test = new GSTest(this.ma_Grille);
    if ( _test.verifRegion(this.the_X, this.the_Y) ) {
      System.out.println("---------------------+region");
      this.value = temp;
      return true;
    }
    if ( _test.verifLigne(this.the_X)) {
      System.out.println("---------------------+ligne");
      this.value = temp;
      return true;
    }
    if ( _test.verifColonne(this.the_Y)) {
      System.out.println("---------------------+colonne");
      this.value = temp;
      return true;
    }
    this.value = temp;
    return false;

  }
  /**
   * Renvoie le nombre de chiffres dans la case.
   * @return nombre de chiffres dans la case.
   */

  public int getNB(){
    return this.nombreChiffres;
  }
  /**
   * insert valeur sans vérification
   * @param ma_valeur la valeur à insérer
   */

  public void insertValeur(int ma_valeur){
    this.value = ma_valeur;
    this.text = String.valueOf(this.value);
    System.out.println("valeur insérée ! " + this.value);

    miseEnPage();
  }





}
