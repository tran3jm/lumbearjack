package screens;

import bears.DannyBear;
import bears.SelectedBear;

import io.ResourceFinder;
import visual.statik.sampled.Content;

/**
 * Screen that contains the lore of Danny.
 * 
 * @author joselynetran
 *
 */
public class CustomScreen extends MainScreen
{
  private SelectedBear selectBear;
  private DannyBear currentBear;
  private Content currentBearContent;

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
    this.selectBear = new SelectedBear(this.rf, height);
    this.currentBear = null;

    // set up gui
    this.nextDannyIcon();
  }

  /**
   * Setting up Danny Icon next to Lore box.
   */
  public void nextDannyIcon()
  {
    this.remove(this.currentBearContent);
    this.currentBear = this.selectBear.nextBear();
    this.setCurrentBearOnScreen();

  }

  /**
   * Setting up Danny Icon next to Lore box.
   */
  public void prevDannyIcon()
  {
    this.remove(this.currentBearContent);
    this.currentBear = this.selectBear.prevBear();
    this.setCurrentBearOnScreen();

  }

  /**
   * Helper method to reduce redundant code of setting Danny Icons.
   */
  private void setCurrentBearOnScreen()
  {
    this.currentBearContent = this.currentBear.getBearContent(DannyBear.SWING);
    this.currentBearContent.setLocation(100, 0);
    this.add(this.currentBearContent);
  }

  /**
   * Returns the bear to use for our GameScreen.
   * 
   * @return game.
   */
  public DannyBear getBear()
  {
    return this.currentBear;
  }


}
