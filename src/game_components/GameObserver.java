package game_components;

/**
 * WeatherObserver interface.
 * 
 * @author joselynetran
 * @version 01/31/2023
 */
public interface GameObserver
{
  /**
   * Resets list.
   */
  public void reset();

  /**
   * Adds datum into list.
   * @param hit the damage
   */
  public void handleHit(final double hit);
}
