package game_components;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Path2D;

import visual.statik.described.AggregateContent;
import visual.statik.described.Content;

/**
 * Cursor for our gamebar.
 * UNUSED DOES NOT WORK AS INTENDED
 */
public class Cursor extends AggregateContent
{
  
  private Content c;
  /**
   * Creates our Cursor.
   */
  public Cursor()
  {
    this.c = new Content(triangle(), Color.WHITE, Color.WHITE, new BasicStroke());
    add(c);
  }
  
  
  private Shape triangle()
  {
    Path2D triangle = new Path2D.Double();
    triangle.moveTo(50,  0);
    triangle.lineTo(100, 100);
    triangle.lineTo(0, 100);
    return triangle;
  }
}
