import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
/**
 * La classe <code>GMHelp</code> est utilisée pour afficher la section à propos de GridMaker.
 *
 *
 * @version 1.1
 * @author William-Arno CLEMENT
 */

public class GMHelp implements ActionListener {

	public GMHelp() {

	}

	/**
   * Au clic de l'item A propos de la classe GMUI, on lance cette classe
   *
   * @param e ActionEvent lié à la classe
   */
	public void actionPerformed(ActionEvent e) {
		System.out.println("A propos");
		fenetre_about();
	}


	/**
   * Fenetre a propos.
   *
   */
	public void fenetre_about(){

		JFrame fenetre = new JFrame();
		fenetre.setSize(300, 250);
		fenetre.setLocation(100, 100);

		JLabel etiquette = new JLabel("<html><body>A propos de<br> <h1>GridMaker</h1><br> Cree par<br>Youcef Annab<br>William-Arno Clement<br>Au sein de l'IUT Informatique de Fontainebleau</body></html>");


		etiquette.setHorizontalAlignment(JLabel.CENTER);

		fenetre.add(etiquette, BorderLayout.CENTER);

		fenetre.setVisible(true);
	}
}
