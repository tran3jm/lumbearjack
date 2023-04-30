package screens;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

import bears.DannyBear;
import bears.PaperHatBear;
import bears.PirateBear;
import bears.StrawHatBear;
import bears.TopHatBear;

import io.ResourceFinder;
import utils.ScaledImage;
import visual.statik.sampled.Content;

/**
 * Screen that contains the lore of Danny.
 * 
 * @author joselynetran
 *
 */
public class CustomScreen extends MainScreen
{

  private Content currentBear;
  private int index = 0;
  private ArrayList<String> bearImgFilenames;
  /**
   * Constructor of LoreScreen.
   * 
   * @param width
   * @param height
   * @param rf
   */
  public CustomScreen(final int width, final int height, final ResourceFinder rf)
  {
    super(width, height, rf);
    this.currentBear = null;
    setUpImages();
    // set up gui
    this.setuploreBox();
    this.dannyIcon();
  }

  /**
   * Setting up Danny Icon next to Lore box.
   */
  public void dannyIcon()
  {
    if (this.index == this.bearImgFilenames.size())
    {
      this.index = 0;
    }
    if (currentBear != null)
    {
      this.remove(currentBear);
    }
    Content dannyContent = ScaledImage.scaledImage(this.bearImgFilenames.get(this.index),
                                                   this.rf, 0.28);
    this.index += 1;
    dannyContent.setLocation(325, this.view.getHeight() / 14);
    this.currentBear = dannyContent;
    this.add(dannyContent);

  }
  /**
   * Returns the bear to use for our GameScreen.
   * @return game.
   */
  public DannyBear getBear()
  {
    DannyBear bear;
    switch (this.index-1)
    {
      case 1:
        bear = new PirateBear(this.rf, 600);
        break;
      case 2:
        bear = new PaperHatBear(this.rf, 600);
        break;
      case 3:
        bear = new StrawHatBear(this.rf, 600);
        break;
      case 4:
        bear = new TopHatBear(this.rf, 600);
        break;
      default:
        bear = new DannyBear(this.rf, 600);
    }
    return bear;
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
  private void setUpImages()
  {
    this.bearImgFilenames = new ArrayList<>();
    this.bearImgFilenames.add("danny_chop.png");
    this.bearImgFilenames.add("pirate_chop.png");
    this.bearImgFilenames.add("paperhat_chop.png");
    this.bearImgFilenames.add("straw_chop.png");
    this.bearImgFilenames.add("tophat_chop.png");

  }

}
