import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.*;
import java.awt.event.*;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;

import java.lang.System;

/**
 * La classe <code>GSPlay</code> est utilisÃ©e afficher
 * l'interface de résolution manuelle et initier les algorithmes de vérification
 * automatique.
 *
 * @version 1.1
 * @author William-Arno CLEMENT
 */

public class GSPlay implements ActionListener, KeyListener  {

    /**
     * Container qui va s'accorder à la fenêtre via le getContentPane
     */
  private Container content;
  /**
   * ma_Grille est la grille importée depuis la classe GSMenu
   */
  private GSGrid ma_Grille;
  /**
   * ma_Frame est la fenêtre importée depuis la classe GSMenu
   */
  private JFrame ma_Frame;
  /**
   * Bouton Fini cliquable lorsque
   * la grille est correctement remplie.
   */
  private JButton Fini = new JButton("Fini !");
  /**
   * startTime correspond au temps de démarrage de GSSolver
   */
  private long startTime;
  /**
   * gameplay est la fenetre dans laquelle on va présenter le
   * sudoku et le temps nécéssaire à sa résolution.
   */
  JFrame gameplay = new JFrame("Sudoku GridSolver :: Play");
  /**
   * constructeur dédié à la création des composantes publiques.
   * @param grille la grille importée
   * @param frame la JFrame importée
   */
  public GSPlay(GSGrid grille, JFrame frame) {
    this.ma_Grille = grille;
    this.ma_Frame = frame;

  }

  /**
   * Fonction lancée lorsque le bouton Jouer de GSMenu
   * est cliqué, ce qui déclenche l'affichage de la fenetre
   * ainsi que l'affichage dédié à la résolution manuelle.
   * @param e ActionEvent lié à la classe
   */

  public void actionPerformed(ActionEvent e) {

    //System.out.println("hello !");
    GameplayWindow();
    this.ma_Grille.isPlaying(true);

  }

  /**
   * Classe dédié à l'affichage de la fenêtre, ainsi qu'à
   * sa mise en page.
   */

  public void GameplayWindow(){

    this.startTime = System.nanoTime();

    this.ma_Frame.setVisible(false);

    // creation de la fenetre

    content = this.gameplay.getContentPane();

    // on insere le container "content" dans le jFrame
    this.gameplay.setLayout(new BorderLayout());
    this.gameplay.setSize(650, 730);

    this.gameplay.setFocusable(true);
    this.gameplay.requestFocusInWindow();
    this.gameplay.addKeyListener(this);
    this.Fini.setEnabled(false);

    long my_time= System.nanoTime() - this.startTime;
    GSWin _win = new GSWin(this.gameplay, my_time);
    Fini.addActionListener(_win);
    this.gameplay.add(Fini, BorderLayout.SOUTH);

    content.add(this.ma_Grille, BorderLayout.CENTER);


    this.gameplay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.gameplay.setVisible(true);

  }


  /**
   * Fonction lancée lorsque le joueur clique sur une
   * touche du clavier. On vérifie quelle est la case active via la méthode
   * whoIsActive_X et whoIsActive_Y. Et on place la valeur touchée dans cette case.
   *
   * @param e KeyEvent lié à la classe
   */


  public void keyPressed(KeyEvent e){

    //System.out.println("Touche pressée : " + e.getKeyCode() + " (" + e.getKeyChar() + ")");


    if (e.getKeyCode() == 97 || e.getKeyCode() == 49) {//1
      this.ma_Grille.setValuetoCase(this.ma_Grille.whoIsActive_X(), this.ma_Grille.whoIsActive_Y(), 1);
    } else if (e.getKeyCode() == 98 || e.getKeyCode() == 50){//2
      this.ma_Grille.setValuetoCase(this.ma_Grille.whoIsActive_X(), this.ma_Grille.whoIsActive_Y(), 2);
    } else if (e.getKeyCode() == 99 || e.getKeyCode() == 51){//3
      this.ma_Grille.setValuetoCase(this.ma_Grille.whoIsActive_X(), this.ma_Grille.whoIsActive_Y(), 3);
    } else if (e.getKeyCode() == 100 || e.getKeyCode() == 52){//4
      this.ma_Grille.setValuetoCase(this.ma_Grille.whoIsActive_X(), this.ma_Grille.whoIsActive_Y(), 4);
    } else if (e.getKeyCode() == 101 || e.getKeyCode() == 53){//5
      this.ma_Grille.setValuetoCase(this.ma_Grille.whoIsActive_X(), this.ma_Grille.whoIsActive_Y(), 5);
    } else if (e.getKeyCode() == 102 || e.getKeyCode() == 54){//6
      this.ma_Grille.setValuetoCase(this.ma_Grille.whoIsActive_X(), this.ma_Grille.whoIsActive_Y(), 6);
    } else if (e.getKeyCode() == 103 || e.getKeyCode() == 55){//7
      this.ma_Grille.setValuetoCase(this.ma_Grille.whoIsActive_X(), this.ma_Grille.whoIsActive_Y(), 7);
    } else if (e.getKeyCode() == 104 || e.getKeyCode() == 56){//8
      this.ma_Grille.setValuetoCase(this.ma_Grille.whoIsActive_X(), this.ma_Grille.whoIsActive_Y(), 8);
    } else if (e.getKeyCode() == 105 || e.getKeyCode() == 57){//9
      this.ma_Grille.setValuetoCase(this.ma_Grille.whoIsActive_X(), this.ma_Grille.whoIsActive_Y(), 9);
    } else if (e.getKeyCode() == 8 || e.getKeyCode() == 27 || e.getKeyCode() == 96  || e.getKeyCode() == 48){
      this.ma_Grille.setValuetoCase(this.ma_Grille.whoIsActive_X(), this.ma_Grille.whoIsActive_Y(), 0);
    }

    if (isGameOver()) {
      this.Fini.setEnabled(true);
    }



  }

  /**
   * Fonction nécessaire au bon fonctionnement de KeyListener
   *
   *
   * @param e ActionEvent lié à la classe
   */


  public void keyReleased(KeyEvent e){
    //System.out.println("relaché");
  }

  /**
   * Fonction nécessaire au bon fonctionnement de KeyListener
   *
   *
   * @param e ActionEvent lié à la classe
   */

  public void keyTyped(KeyEvent e) {
    //System.out.println("clic");
  }
  /**
   * Renvoie VRAI si la grille est correctement remplie.
   *
   * @return si la grille est correctement remplie
   */
  public Boolean isGameOver(){

    if (!this.ma_Grille.isComplete()) {

      //System.out.println("*********************************************************************************************************************************************************************************");
      return true;

    }

    return false;

  }




}
