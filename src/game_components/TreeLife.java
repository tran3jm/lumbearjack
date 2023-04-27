package game_components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

import io.ResourceFinder;
import utils.GameColorPallet;
import visual.statik.SimpleContent;
import visual.statik.sampled.ImageFactory;

/**
 * Treelife content that will display the tree health.
 * 
 * @author joselynetran
 *
 */
public class TreeLife implements SimpleContent, GameObserver
{
  private static final double TOTAL_TREE_HEALTH = 30.0;
  private boolean playerMissed;  
  private ResourceFinder rf;

  private RoundRectangle2D healthContainer;
  private Rectangle health;
  private double damage;
  private int recX;
  private int recY;
  
  private int minBound;
  private int maxBound;
  /**
   * Constructor for treelife.
   * 
   * @param rf
   *          for BufferedImages
   * @param minBound
   *		  minBound of cursor hit
   * @param maxBound
   *		  maxBound of cursor hit
   */
  public TreeLife(final ResourceFinder rf, final int minBound, final int maxBound)
  {

    // for reading in necessary bufferedimages
    this.rf = rf;

    // sets up x and y coordinates of elements
    this.recX = 125;
    this.recY = 23;
    
    // sets up min and max bound of cursor
    this.minBound = minBound;
    this.maxBound = maxBound;

    this.healthContainer = new RoundRectangle2D.Double(this.recX, this.recY, 200, 50, 15, 15);
    this.health = new Rectangle(this.recX, this.recY + 2, 195, 45);
    this.damage = 0;

    this.playerMissed = false;
  }

  @Override
  public void render(final Graphics arg0)
  {
    Graphics2D g;

    RoundRectangle2D backgroundBar = new RoundRectangle2D.Double(this.recX, this.recY, 200, 50, 15,
        15);

    // rectangle bar behind health
    g = (Graphics2D) arg0;
    g.setColor(GameColorPallet.BAR_FILLER);
    g.fill(backgroundBar);

    // actual tree health rectangle
    g.setColor(GameColorPallet.BRIGHT_GREEN);
    g.fill(this.health);

    // Border outside of health of tree
    g.setStroke(new BasicStroke(4));
    g.setColor(Color.black);
    g.draw(this.healthContainer);

    // heart for healthbar
    BufferedImage heart = new ImageFactory(rf).createBufferedImage("heart.png",
        BufferedImage.TYPE_INT_ARGB);
    g.drawImage(heart, null, this.recX - 50, this.recY - 20);

  }

  @Override
  public void reset()
  {
    // damage is back to 0 and health bar is back to full width
    this.damage = 0;
    this.health = new Rectangle(this.recX, this.recY + 2, 195, 45);
  }

  @Override
  public void handleHit(final double hit)
  {
    // will make this cleaner later
    if (this.minBound < hit && hit < this.maxBound)
    {
      this.damage += 5;
      this.playerMissed = false;
    }
    else
    {
      this.playerMissed = true;
    }

    // if tree is chopped, then set health rectangle to be invisible
    if (this.hasWon())
      this.health.width = 0;
    else
      this.health.width = (int) calculateDamageToWidth();

  }

  
  /**
   * Returns if tree has no more life. 
   * 
   * @return true or false if tree is cut
   */
  public boolean hasWon()
  {
    return this.damage == TOTAL_TREE_HEALTH;
  }

  /**
   * Returns if tree has half life.
   * 
   * @return true or false if tree is cut
   */
  public boolean halfDamage()
  {
    return this.damage >= TOTAL_TREE_HEALTH / 2;
  }
  
  /**
   * Setter for setting lowest bound of greenArea.
   * @param min
   */
  public void setNewMinBound(final int min)
  {
    this.minBound = min;
  }
  
  /**
   * Setter for setting maximum bound of greenArea.
   * @param max
   */
  public void setNewMaxBound(final int max)
  {
    this.maxBound = max;
  }

  /**
   * Did the player miss the mark?
   * @return true or false.
   */
  public boolean playerMisses()
  {
    return this.playerMissed;
  }
  /**
   * Helper function to set how much green the the health bar (the tree life) is visually shown.
   * 
   * @return the calculted width after taking damage into consideration
   */
  private double calculateDamageToWidth()
  {
    return this.healthContainer.getWidth()
        - (this.damage / TOTAL_TREE_HEALTH) * this.healthContainer.getWidth();
  }

}
