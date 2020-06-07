import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
/**
 * La classe <code>GSWin</code> est utilisÃ©e pour lancer l'affichage des
 * félicitations au joueur pour avoir résolu la grille.
 *
 * @version 1.1
 * @author William-Arno CLEMENT
 */

public class GSWin implements ActionListener {

  /**
   * the durée exprime le temps nécessaire à la résolution du Sudoku en nanosecondes
   */
  private long the_duree;

  /**
   * precedente est la JFrame GSPlay que ce programme va fermer.
   */
  private JFrame precedente;

  /**
   * constructeur dédié à la création des composantes publiques.
   * @param p la frame précedente
   * @param duree la durée nécéssaire à la résolution du sudoku par le joueur
   */

	public GSWin(JFrame p,long duree) {
    this.the_duree = duree;
    this.precedente = p;
	}

  /**
   * Fonction lancée lorsque le bouton Fini de GSPlay
   * est cliqué, ce qui déclenche l'affichage de la fenetre
   * ainsi la fermeture de la fenetre précedente.
   * @param e ActionEvent lié à la classe
   */

	public void actionPerformed(ActionEvent e) {
    this.precedente.setVisible(true);
    fenetre_win();
	}

  /**
   * Affichage de la fenetre de Félicitations au joueur. On inscrit le temps nécessaire à la résolution du sudoku.
   *
   */

	public void fenetre_win(){

		JFrame fenetre = new JFrame();
		fenetre.setSize(300, 250);
		fenetre.setLocation(100, 100);

		JLabel etiquette = new JLabel("<html><body><br> <h1>Gagne !!</h1><br> Vous avez reussi le sudoku ! Bravo !<br>Vous avez resolu la Grille en "+ Double.toString((double)this.the_duree  / 600000) +" secondes !</body></html>");

    fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		etiquette.setHorizontalAlignment(JLabel.CENTER);

		fenetre.add(etiquette, BorderLayout.CENTER);

		fenetre.setVisible(true);
	}
}
