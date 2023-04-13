package screens;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.geom.RoundRectangle2D;

import gui.TextGraphic;
import io.FileIntoText;
import io.ResourceFinder;
import utils.ScaledImage;
import visual.statik.sampled.Content;

/**
 * Screen that contains the lore of Danny.
 * 
 * @author joselynetran
 *
 */
public class LoreScreen extends MainScreen
{

  private String loreText;
  private Font font;

  /**
   * Constructor of LoreScreen.
   * 
   * @param width
   * @param height
   * @param rf
   * @param font
   */
  public LoreScreen(final int width, final int height, final ResourceFinder rf, final Font font)
  {
    super(width, height, rf);
    this.font = font;

    // set up gui
    this.setuploreBox();
    this.dannyIcon();
    this.readLoreText();
  }

  /**
   * Setting up Danny Icon next to Lore box.
   */
  private void dannyIcon()
  {

    Content dannyContent = ScaledImage.scaledImage("danny.png", this.rf, 0.43);
    dannyContent.setLocation(-45, this.view.getHeight() / 5);
    this.add(dannyContent);

  }

  /**
   * Setting up lorebox that will have text contained in it.
   */
  private void setuploreBox()
  {
    visual.statik.described.Content loreTextBox = new visual.statik.described.Content(
        new RoundRectangle2D.Double(240, 60, 500, 350, 15, 15), new Color(67, 41, 18),
        new Color(218, 168, 114), new BasicStroke(10f));
    this.add(loreTextBox);
  }

  /**
   * Setting up lore text to put into box.
   */
  private void readLoreText()
  {
    this.loreText = FileIntoText.fileIntoText("lore.txt", rf);
    TextGraphic tg = new TextGraphic(12, this.loreText, 860, 220, this.font);
    this.add(tg);
  }

}
