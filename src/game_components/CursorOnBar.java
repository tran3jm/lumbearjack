package game_components;

import java.awt.geom.Point2D;

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
  public CursorOnBar()
  {
    super();
    Cursor c = new Cursor();
    this.addKeyTime(0, new Point2D.Double(0, 0), 0.0, 1.0, c);
    this.addKeyTime(500, new Point2D.Double(50, 0), 0.0, 1.0, c);
    this.addKeyTime(1500, new Point2D.Double(150, 0), 0.0, 1.0, c);
    this.setEndState(REMOVE);
  }
}
