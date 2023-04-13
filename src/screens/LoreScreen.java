package screens;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import gui.ScaledImage;
import gui.TextGraphic;
import io.FileIntoText;
import io.ResourceFinder;
import visual.statik.sampled.BufferedImageOpFactory;
import visual.statik.sampled.Content;
import visual.statik.sampled.ContentFactory;
import visual.statik.sampled.ImageFactory;

/**
 * 
 * @author joselynetran
 *
 */
public class LoreScreen extends MainScreen
{

  private String loreText;
  private Font font;

  /**
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
   * 
   */
  private void dannyIcon()
  {

    Content dannyContent = ScaledImage.scaledImage("danny.png", this.rf, 0.43);
    dannyContent.setLocation(-45, this.view.getHeight() / 5);
    this.add(dannyContent);

  }

  /**
   * 
   */
  private void setuploreBox()
  {
    visual.statik.described.Content loreTextBox = new visual.statik.described.Content(
        new Rectangle(500, 350), new Color(67, 41, 18), new Color(218, 168, 114),
        new BasicStroke(10f));
    loreTextBox.setLocation(240, 60);
    this.add(loreTextBox);
  }

  private void readLoreText()
  {
    this.loreText = FileIntoText.fileIntoText("lore.txt", rf);
    TextGraphic tg = new TextGraphic(12, this.loreText, 860, 220, this.font);
    this.add(tg);
  }

}
