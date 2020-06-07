import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.io.File;
import java.io.IOException;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.FileNotFoundException;

import javax.swing.JFrame;


/**
 * La classe <code>GMSaver</code> est utilisée pour sauvegarder une grille
 * en exportant la grille sous forme de liste d'entier dans un fichier.
 *
 * @version 1.1
 * @author William-Arno CLEMENT
 */


public class GMSaver implements ActionListener {

  /**
   * Le fichier dans lequel on va sauvegarder la grille
   */
  private File mon_fichier;

  /**
   * La fenêtre principale
   */
	private JFrame ma_fenetre;

  /**
   * La grille à sauvegarder/exporter
   */
	private GMGrid ma_Grille;

  /**
   * la liste d'entier à imprimer dans le fichier.
   */
  private int[] a_imprimer = new int[9];

  /**
   * constructeur dédié à la création des composantes publiques..
   * @param frame la fenetre importée
   * @param Grid la grille importée
   */
	public GMSaver(JFrame frame, GMGrid Grid) {

		this.ma_fenetre = frame;
    this.ma_Grille = Grid;

	}

  /**
   * Au cluc du bouton Sauver de la classe GMUI, on lance la procédure de verification
   * puis la methode saver qui va lancer la sauvegarde.
   * @param e ActionEvent lié à la classe
   */
	public void actionPerformed(ActionEvent e) {

    GMTest _test = new GMTest(this.ma_Grille);
    if (_test.isCorrect()) {
      saver();
    } else {
      System.out.println("Format grille non correct.");
    }


	}

  /**
   * Classe permettant la sauvegarde de la classe sous forme de liste d'entier dans un fichier défini.
   */
	public void saver() {

		System.out.println("GMSaver launched ! ");

    FileOutputStream fos = null;
    DataOutputStream dos = null;


		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
		"Gri Files", "gri");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(this.ma_fenetre);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("Fichier choisi : " + chooser.getSelectedFile().getName());

			this.mon_fichier = chooser.getSelectedFile();
		}


		try {

			if (this.mon_fichier.createNewFile()) {
        System.out.println("File created: " + this.mon_fichier.getName());
      } else {
        System.out.println("File already exists.");
      }

      fos = new FileOutputStream(this.mon_fichier);

      dos = new DataOutputStream(fos);

      this.a_imprimer = this.ma_Grille.Exportic();
      //String temp = "";

      for ( int i = 0; i < 9 ; i++ ) {
        dos.writeInt(this.a_imprimer[i]);
      }



		}
		catch(IOException e) {
			System.out.println("nope");
		}
		catch(NullPointerException  npe){
			System.out.println("npe");
		}

	}

}
