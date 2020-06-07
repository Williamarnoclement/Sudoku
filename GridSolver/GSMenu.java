import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.Container;
import java.awt.BorderLayout;
import javax.swing.event.*;

import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * La classe <code>GSMenu</code> est utilisÃ©e pour lancer l'interface
 * d'importation et ainsi permettre la résolution manuelle ou automatique.
 *
 * @version 1.1
 * @author William-Arno CLEMENT
 */

public class GSMenu  {

  /**
   * Container qui va s'accorder à la fenêtre via le getContentPane
   */
  public Container content;

  /**
   * Le Bouton Importer permet de lancer la classe d'importation de grille GSImport.
   */
  JButton Importer = new JButton("Importer Grille");

  /**
   * Le Bouton Importer permet de lancer la classe GSPlay: le sudoku en résolution manuelle.
   */
  JButton Jouer = new JButton("Jouer");

  /**
   * Le Bouton Importer permet de lancer la classe GSSolver: le sudoku en résolution automatique.
   */
  JButton autoSolve = new JButton("Resolution automatique");

  /**
   * La classe GSMenu créé une interface pour le joueur souhaitant jouer à un grille ou la résoudre automatiquement.
   */
  public GSMenu() {

    // creation de la fenetre
    JFrame frame = new JFrame("Sudoku GridSolver");
    content = frame.getContentPane();

    // on insere le container "content" dans le jFrame
    this.content.setLayout(new BorderLayout());

    JPanel panel = new JPanel();

    JLabel etiquette = new JLabel("<html><body><br><h1>Sudoku GridSolver</h1>Bienvenue dans Sudoku GridSolver! Pour commencer, importez une grille, puis cliquez sur JOUER. </body></html>");
    etiquette.setHorizontalAlignment(JLabel.CENTER);
    frame.add(etiquette, BorderLayout.NORTH);


    GSGrid _grille = new GSGrid(this);

    GSImport _import = new GSImport(frame,_grille);
    Importer.addActionListener(_import);

    GSPlay _play = new GSPlay(_grille, frame);
    Jouer.addActionListener(_play);

    GSSolver _autosolver = new GSSolver(_grille, frame);
    autoSolve.addActionListener(_autosolver);

    Jouer.setEnabled(false);
    autoSolve.setEnabled(false);

    panel.add(Importer);
    panel.add(Jouer);
    panel.add(autoSolve);

    content.add(panel, BorderLayout.SOUTH);

    content.add(new GSBackGround(frame) , BorderLayout.CENTER);



    frame.setSize(600, 730);

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    frame.setVisible(true);



  }

  /**
   * Les boutons jouer et autoSolve sont initialement non-interactifs.
   */

  public void isPlayable(){

    this.Jouer.setEnabled(true);
    this.autoSolve.setEnabled(true);

  }

}
