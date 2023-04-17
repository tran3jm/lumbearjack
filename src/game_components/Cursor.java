package game_components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Path2D;

import visual.statik.described.AggregateContent;
import visual.statik.described.Content;

/**
 * Cursor for our gamebar.
 */
public class Cursor extends AggregateContent
{

  private Content c;

  /**
   * Creates our Cursor.
   */
  public Cursor()
  {
    this.c = new Content(triangle(), Color.black, new Color(252, 183, 20),
        new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
    add(c);
  }

  /**
   * Creates a triangle shape for our cursor.
   * @return the cursor.
   */
  private Shape triangle()
  {
    Path2D triangle = new Path2D.Double();
    triangle.moveTo(20, 0);
    triangle.lineTo(40, 40);
    triangle.lineTo(0, 40);
    triangle.lineTo(20, 0);
    return triangle;
  }
}
