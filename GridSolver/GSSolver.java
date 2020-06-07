import javax.swing.JFrame;
import javax.swing.JLabel;
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
 * La classe <code>GSSolver</code> est utilisÃ©e pour afficher
 * l'interface de résolution automatique et initier l'algorithme de résolution
 * automatique.
 *
 * @version 1.1
 * @author William-Arno CLEMENT
 */

public class GSSolver implements ActionListener  {

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
   * label est le JLabel dans lequel va être inscrit le temps
   * nécéssaire à la résolution automatique du Sudoku.
   */
  private JLabel label = new JLabel("Resolution en cours");
  /**
   * startTime correspond au temps de démarrage de GSSolver
   */
  private long startTime;
  /**
   * gameplay est la fenetre dans laquelle on va présenter le
   * sudoku et le temps nécéssaire à sa résolution.
   */
  JFrame gameplay = new JFrame("Sudoku GridSolver :: AutoSolve");

  /**
   * constructeur dédié à la création des composantes publiques.
   * @param grille la grille importée
   * @param frame la JFrame importée
   */

  public GSSolver(GSGrid grille, JFrame frame) {
    this.ma_Grille = grille;
    this.ma_Frame = frame;

  }

  /**
   * Fonction lancée lorsque le bouton AutoSolve de GSMenu
   * est cliqué, ce qui déclenche l'affichage de la fenetre
   * ainsi que l'affichage de la grille et son temps d'execution.
   * @param e ActionEvent lié à la classe
   */

  public void actionPerformed(ActionEvent e) {

    //System.out.println("hello !");
    ASWindow();
    this.ma_Grille.isPlaying(false);
    this.ma_Grille.solve();
    if (isGameOver() == true) {
      double time =(double) (System.nanoTime() - this.startTime)/ 1_000_000_000;
      this.label.setText("Resolu en " +  time + " secondes.");
      System.out.println("Resolu en " +  time + " secondes.");
    }

  }

  /**
   * Classe dédié à l'affichage de la fenêtre, ainsi qu'à
   * sa mise en page.
   */
  public void ASWindow(){

    this.startTime = System.nanoTime();

    this.ma_Frame.setVisible(false);

    // creation de la fenetre

    content = this.gameplay.getContentPane();

    // on insere le container "content" dans le jFrame
    this.gameplay.setLayout(new BorderLayout());
    this.gameplay.setSize(650, 730);

    this.gameplay.setFocusable(true);
    this.gameplay.requestFocusInWindow();


    content.add(this.label, BorderLayout.SOUTH);
    content.add(this.ma_Grille, BorderLayout.CENTER);




    this.gameplay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.gameplay.setVisible(true);

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
