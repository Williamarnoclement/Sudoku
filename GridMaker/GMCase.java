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

/**
 * La classe <code>GMCase</code> est utilisÃ©e pour représenter
 * une case.
 *
 * @version 1.1
 * @author William-Arno CLEMENT
 */

public class GMCase extends JPanel implements ActionListener, ItemListener  {

  /**
   * La valeur actuellement rentré dans la case.
   */
  private int value = 0;

  /**
   *  texte inscrit sur la case.
   */
  private String text = "";

  /**
   *  Bouton cliquable modifiant l'état de la case.
   */
  private JButton button = new JButton(text);

  /**
   *  l'utilisateur entre t-il une valeur ?
   */
  private Boolean situation = false;

  /**
   *  JLabel permettant de selectionner la valeur de la case.
   */
  private JLabel choix_label;

  /**
   *  JComboBox permettant de selectionner la valeur de la case.
   */
  private JComboBox<String> choix_box;

  /**
   *  liste permettant de selectionner la valeur de la case de 0(vide) à 9.
   */
  private String[] choix_liste;

  /**
   * constructeur dédié à la création des composantes publiques ainsi qu'à la mise en place de la case.
   */
  public GMCase() {


    this.add(this.button);
    this.button.setOpaque(false);
    this.button.setContentAreaFilled(false);
    this.button.setBorderPainted(false);
    this.button.setPreferredSize(new Dimension(50, 50));
    this.button.addActionListener(this);


    choix_liste = new String[] {"vide", "1", "2", "3",
    "4", "5", "6", "7", "8", "9"};

    choix_label = new JLabel("!");

    choix_box = new JComboBox<>(choix_liste);
    this.add(this.choix_box);
    choix_box.addItemListener(this);
    this.choix_box.setVisible(false);

  }

  /**
   * au clic, change l'état de la situation et met en page la case.
   * @param e ActionEvent lié à la classe
   */
  public void actionPerformed(ActionEvent e) {

    this.situation = true;
    this.miseEnPage();

  }

  /**
   * permet à l'utilisateur de choisir une valeur pour la case au clic.
   * @param e ItemEvent lié à la classe
   */
  @Override
  public void itemStateChanged(ItemEvent e) {
    if (e.getStateChange() == ItemEvent.SELECTED) {
      if (e.getItem().toString() != "vide") {
        System.out.println(e.getItem().toString());
        this.text = e.getItem().toString();
        this.value = (int) Integer.valueOf(e.getItem().toString());
        this.situation = false;
        this.miseEnPage();
      } else if (e.getItem().toString() == "vide") {


        this.text = "";
        this.value = 0;
        this.situation = false;
        this.miseEnPage();

      }
    }
  }


  /**
   * Méthode de mise en page de la case.
   */
  public void miseEnPage(){

    //System.out.println("Clic !");

    if (situation == true) {
      this.button.setVisible(false);
      this.choix_box.setVisible(true);
    } else {
      this.button.setText(this.text);
      this.button.setVisible(true);
      this.choix_box.setVisible(false);
    }


    //this.button.setText(this.text);
    this.repaint();


  }

  /**
   * Pemet de modifier la valeur de la case.
   * @param ma_valeur valeur à mettre dans la case.
   */
  public void setValeur(int ma_valeur){

    if (ma_valeur == 0) {
      this.text = "";
    } else {
      this.text = String.valueOf(ma_valeur);
    }
    this.value = (int) ma_valeur;
    this.situation = false;
    this.miseEnPage();

  }

  /**
   * Renvoie la valeur de la case.
   * @return renvoie la valeur de la case.
   */
  public int getValeur(){

    return this.value;

  }



}
