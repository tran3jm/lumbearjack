package game_components;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import visual.dynamic.described.DescribedSprite;

/**
 * Class that adds animation to our cursor.
 * UNUSED, DOES NOT WORK AS INTENDED.
 */
public class CursorOnBar extends DescribedSprite
{
  
  /**
   * Constructor.
   */
  public CursorOnBar(final int x, final int y)
  {
    super();
    Cursor c = new Cursor();
    this.addKeyTime(0, new Point2D.Double(x, y), 0.0, 1.0, c);
    this.addKeyTime(1000, new Point2D.Double(x + 460, y), 0.0, 1.0, c);
    this.addKeyTime(2000, new Point2D.Double(x, y), 0.0, 1.0, c);
  }
  
  /**
   * Returns current place of cursor.
   */
  public Rectangle2D getCurrentLocation()
  {
    return this.getBounds2D();
  }
  

}
