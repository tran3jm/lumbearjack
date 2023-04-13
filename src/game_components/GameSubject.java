
package game_components;

/**
 * WeatherSubject interface.
 * 
 * @author joselynetran
 * @version 01/31/2023
 */
public interface GameSubject
{
  /**
   * Adds observer.
   * 
   * @param observer
   *          Observer to add
   */
  public void addObserver(final GameObserver observer);

  /**
   * Notifies observe when datum is read.
   * 
   */
  public void notifyObservers();

  /**
   * Removes observer.
   * 
   * @param observer
   *          Observer to remove
   */
  public void removeObserver(final GameObserver observer);
}
