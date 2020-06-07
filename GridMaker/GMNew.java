import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.io.File;
import java.io.IOException;

/**
 * La classe <code>GMNew</code> est utilisée pour reinitialiser la grille
 * en initialisant toutes les valeurs à zero.
 *
 * @version 1.1
 * @author William-Arno CLEMENT
 */

public class GMNew implements ActionListener {

  /**
   * La grille à reinitialiser.
   */
  private GMGrid ma_Grille;

  /**
   * constructeur dédié à la création des composantes publiques.
   * @param Grid grille à reinitialiser
   */
	public GMNew(GMGrid Grid) {

    this.ma_Grille = Grid;

	}

  /**
   * Lance la reinitialisation de la grille au clic du bouton Nouveau de la classe GMUI.
   * @param e ActionEvent lié à la classe
   */
	public void actionPerformed(ActionEvent e) {


        	Nouveau();

    }

  /**
   * Cette méthode créé une grille vide.
   */
  public void Nouveau() {

    System.out.println("Nouvelle Grille ! ");

    int[] mon_tableau = new int[9];

    for (int i = 0; i < 9 ; i++ ) {
      mon_tableau[i] = 0;
    }

    this.ma_Grille.Importic(mon_tableau);

  }
}
