import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import java.awt.Container;
import java.awt.BorderLayout;
import javax.swing.event.*;

/**
 * La classe <code>GMUI</code> est utilisée pour afficher l'interface Utilisateur (UI)
 * avec les options Nouvelle grille, importer  et Sauver.
 *
 * @version 1.1
 * @author William-Arno CLEMENT
 */

public class GMUI  {

  /**
   * La barre de menu principale
   */
  JMenuBar menuBar = new JMenuBar();
  /**
   * Le menu Fichier ou on peut creer une nouvelle grille, importer une grille ou sauver une grille.
   */
  JMenu menu_1 = new JMenu("Fichier");
  /**
   * Le menu Grille ou on peut verifier si la grille est cohérente.
   */
  JMenu menu_2 = new JMenu("Grille");
  /**
   * Le menu Aide ou on affiche des infos supplémentaires.
   */
  JMenu menu_3 = new JMenu("Aide");


  /**
   * Item permettant d'ouvrir un nouveau fichier vide.
   */
  JMenuItem Item_1_1 = new JMenuItem("Nouveau");
  /**
   * Item permettant d'ouvrir une grille pré-existante dans un fichier.
   */
  JMenuItem Item_1_2 = new JMenuItem("Importer");
  /**
   * Item permettant de sauvegarder un grille cohérente.
   */
  JMenuItem Item_1_3 = new JMenuItem("Sauver");



  /**
   * Item permettant de vérifier si une grille est cohérente.
   */
  JMenuItem Item_2_3 = new JMenuItem("Verifier");

  /**
   * Item permettant d'ouvrir la classe GMHelp.
   */

  JMenuItem Item_3_1 = new JMenuItem("A propos");

  /**
   * Groupe de boutons.
   */
  ButtonGroup couleurs = new ButtonGroup();

  /**
   * Container s'accordant à la JFrame
   */
  public Container content;

  /**
   * constructeur dédié à la création des composantes publiques ainsi qu'à la mise en place des différentes classes.
   */
  public GMUI() {

    // creation de la fenetre
    JFrame frame = new JFrame("Sudoku GridMaker");
    content = frame.getContentPane();

    // on insere le container "content" dans le jFrame
    content.setLayout(new BorderLayout());

    menu_1.add(Item_1_1);

    GMGrid Grid = new GMGrid();

    GMNew _new = new GMNew(Grid);
    menu_1.add(Item_1_1);
    Item_1_1.addActionListener(_new);

    GMImport _import = new GMImport(frame, Grid);
    menu_1.add(Item_1_2);
    Item_1_2.addActionListener(_import);

    GMSaver _export = new GMSaver(frame,Grid);
		menu_1.add(Item_1_3);
		Item_1_3.addActionListener(_export);



    GMTest _test = new GMTest(Grid);
    menu_2.add(Item_2_3);
    Item_2_3.addActionListener(_test);

    GMHelp _help = new GMHelp();
    menu_3.add(Item_3_1);
    Item_3_1.addActionListener(_help);



    menuBar.add(menu_1);
    menuBar.add(menu_2);
    menuBar.add(menu_3);



    frame.add(menuBar, BorderLayout.NORTH);


    content.add(Grid, BorderLayout.CENTER);


    frame.setSize(700, 730);

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    frame.setVisible(true);



  }

}
