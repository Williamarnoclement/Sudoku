import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import javax.swing.JFrame;

import java.nio.file.Files;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.DataInputStream;

import java.lang.NumberFormatException;

/**
 * La classe <code>GMImport</code> est utilisée pour importer une grille
 * dans GridMaker.
 *
 * @version 1.1
 * @author William-Arno CLEMENT
 */

public class GMImport implements ActionListener {

  /**
   * La fenetre précédente.
   */
  private JFrame ma_fenetre;

  /**
   * La grille importée.
   */
  private GMGrid ma_Grille;

  /**
   * le fichier est-il accessible? true si oui, false si non.
   */
  private Boolean accessible;

  /**
   * le fichier à importer.
   */
  private File mon_fichier;

  /**
   * Liste des 9 entier présents dans le fichier importé.
   */
  private int[] mon_tableau = new int[9];

  /**
   * constructeur dédié à la création des composantes publiques.
   * @param frame la fenetre précédente.
   * @param Grid la grille dans laquel on va mettre la grille.
   */
  public GMImport(JFrame frame, GMGrid Grid) {

    this.ma_fenetre = frame;
    this.ma_Grille = Grid;

  }

  /**
   * Au clic du bouton Importer de la classe GMUI, on lance la procédure d'importation
   *
   * @param e ActionEvent lié à la classe
   */
  public void actionPerformed(ActionEvent e) {


    importer();
    if(this.accessible == true){
      /**
      for (int i = 0; i < 9 ; i++ ) {
        System.out.println(mon_tableau[i]);
      }
      **/
      this.ma_Grille.Importic(this.mon_tableau);

    }

  }

  /**
   * Détermine si le fichier est accessible.
   *
   */
  public void importer() {

    System.out.println("GMImport launched ! ");

    JFileChooser chooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
    "Gri Files", "gri");
    chooser.setFileFilter(filter);
    int returnVal = chooser.showOpenDialog(this.ma_fenetre);
    if(returnVal == JFileChooser.APPROVE_OPTION) {
      System.out.println("Fichier ouvert : " + chooser.getSelectedFile().getName());

      this.mon_fichier = chooser.getSelectedFile();
      if (lire_fichier() == true) {
        System.out.println("Fichier lu et en mémoire.");
        this.accessible = true;
      } else {
        System.out.println("Impossible d'ouvrir le fichier.");
        this.accessible = false;
      }
    }

  }

  /**
   * Lit le fichier et extrait les neuf entiers présents dans celui-ci.
   * @return Renvoie TRUE si la lecture est un succès.
   */
  public Boolean lire_fichier(){

    try {
      /* Ouverture du fichie sur l'entrée standard */
      FileInputStream fichier = new FileInputStream(this.mon_fichier);
      /* Lecture du fichier dans le flux de données */
      DataInputStream flux = new DataInputStream(fichier);
      int incrementable = 0;
      String contenu = "";
      while(0 < flux.available()){
        //contenu = contenu + flux.readChar();
        //System.out.println(flux.readInt());
        this.mon_tableau[incrementable] = flux.readInt();
        incrementable++;
      }
      /* Fermeture du flux du fichier */
      flux.close();
      System.out.println("----------------------+");


      System.out.println("fin programme !");
      return true;

      /* Gestion des erreurs */
    } catch (FileNotFoundException e) {
      System.err.println("Le fichier n'est pas disponible..");
      return false;
    } catch (IOException e) {
      System.err.println("IOException..");
      return false;
    } catch (NumberFormatException e){
      System.err.println("NumberFormatException..");
      return false;
    }
  }
}
