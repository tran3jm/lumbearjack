package game_components;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import visual.dynamic.described.DescribedSprite;

/**
 * Class that adds animation to our cursor.
 */
public class CursorOnBar extends DescribedSprite
{
  
  /**
   * Constructor.
   * @param x
   * @param y
   * @param keyTime1
   * @param keyTime2
   * @param keyTime3
   */
  public CursorOnBar(final int x, final int y, 
      final int keyTime1, final int keyTime2, final int keyTime3)
  {
    super();
    Cursor c = new Cursor();
    this.addKeyTime(keyTime1, new Point2D.Double(x, y), 0.0, 1.0, c);
    this.addKeyTime(keyTime2, new Point2D.Double(x + 460, y), 0.0, 1.0, c);
    this.addKeyTime(keyTime3, new Point2D.Double(x, y), 0.0, 1.0, c);
  }
  
/**
 * Returns our current location on the bar.
 * @return current location.
 */
  public Rectangle2D getCurrentLocation()
  {
    return this.getBounds2D();
  }
  

}
