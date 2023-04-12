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

import gui.TextGraphic;
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

    // initializing factories
    ImageFactory imageFactory = new ImageFactory(this.rf);
    ContentFactory contentFactory = new ContentFactory(this.rf);
    BufferedImageOpFactory bfFactory = BufferedImageOpFactory.createFactory();
    BufferedImageOp scaleOp = bfFactory.createScaleOp(0.43, 0.43);

    // initializes images
    BufferedImage danny = imageFactory.createBufferedImage("danny.png",
        BufferedImage.TYPE_INT_ARGB);
    BufferedImage after = new BufferedImage(danny.getWidth(), danny.getHeight(),
        BufferedImage.TYPE_INT_ARGB);

    // creating danny the bear as content
    scaleOp.filter(danny, after);
    Content dannyContent = contentFactory.createContent(after);
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
    InputStream is = this.rf.findInputStream("lore.txt");
    BufferedReader in = new BufferedReader(new InputStreamReader(is));

    String line;
    this.loreText = "";
    try
    {
      while ((line = in.readLine()) != null)
      {
        this.loreText += line + "\n";
      }
    }
    catch (IOException ioe)
    {
      this.loreText = "Danny lore";
    }

    TextGraphic tg = new TextGraphic(12, this.loreText, 860, 220, this.font);
    this.add(tg);
  }

}
