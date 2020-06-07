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
 * La classe <code>GSImport</code> est utilisÃ©e pour importer une grille.
 *
 * @version 1.1
 * @author William-Arno CLEMENT
 */

public class GSImport implements ActionListener {
  /**
   * La fenêtre initiale, cette variable de classe permet de revenir à la fenetre initiale apres le choix du fichier.
   */
  private JFrame ma_fenetre;

  /**
   * Grille à modifier
   */
  private GSGrid ma_Grille;
  /**
   * le fichier est-il accessible? true si oui, false si non.
   */
  private Boolean accessible;
  /**
   * le fichier à ouvrir est contenu dans cette variable.
   */
  private File mon_fichier;

  /**
   * mon_tableau permet de transformer une liste d'entier en grille.
   */
  private int[] mon_tableau = new int[9];

  /**
   * constructeur dédié à la création des composantes publiques.
   * @param frame la JFrame importée
   * @param Grid la Grille importée
   */

  public GSImport(JFrame frame, GSGrid Grid) {

    this.ma_fenetre = frame;
    this.ma_Grille = Grid;

  }
  /**
   * cette classe procède à l'importation de la grille apres verification de l'accessibilité au fichier.
   * @param e ActionEvent lié
   */
  public void actionPerformed(ActionEvent e) {


    importer();
    if(this.accessible == true){

      this.ma_Grille.Importic(this.mon_tableau);

    }

  }

  /**
   * Classe dédié à l'import des différentes valeurs contenues dans le fichier.
   */

  public void importer() {

    System.out.println("GSImport launched ! ");

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
   * Classe dédié à la mise en page des différentes valeurs contenues dans le fichier pour en faire une grille de sudoku.
   * @return TRUE si la lecture du fichier est un succès
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
