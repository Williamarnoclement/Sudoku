import javax.swing.JComponent;
import java.awt.*;

import javax.swing.JFrame;

/**
 * La classe <code>GSBackGround</code> est utilisÃ©e pour afficher
 * une image de fond dans le menu. Rien de plus.
 *
 * @version 1.1
 * @author William-Arno CLEMENT
 */


public class GSBackGround extends JComponent {

  /**
   * image de fond
   */
  private Image img;

  /**
   * frame du menu
   */
  private JFrame my_frame;


  /**
   * constructeur de classe !
   * @param frame frame du menu
   */
  public GSBackGround(JFrame frame) {
    super();
    img = Toolkit.getDefaultToolkit().getImage("./images/sudoku.png");
    this.my_frame = frame;
  }


  /**
   * on repaint !
   * @param pinceau pinceau
   */
  @Override
  public void paintComponent(Graphics pinceau) {
    Graphics secondPinceau = pinceau.create();
    if (this.isOpaque()) {
      secondPinceau.setColor(this.getBackground());
      secondPinceau.fillRect(0, 0, this.getWidth(), this.getHeight());
    }


    secondPinceau.drawImage(img ,0, 30, this.my_frame.getWidth(), this.my_frame.getWidth() * 1, this);

  }

}
