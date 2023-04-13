package io;

import java.util.ArrayList;
import java.util.List;

import game_components.*;
/**
 * 
 * @author joselynetran
 *
 */
public class GameComponentObserver implements GameSubject
{
  protected List<GameObserver> observers;
  
  /**
   * Constructor.
   */
  public GameComponentObserver()
  {
    observers = new ArrayList<>();
  }
  
  @Override
  public void addObserver(final GameObserver observer)
  {
    observers.add(observer);
    
  }

  @Override
  public void notifyObservers()
  {
    for (GameObserver wo : observers)
    {
      wo.handleHit();
    }
    
  }

  @Override
  public void removeObserver(final GameObserver observer)
  {
    observers.remove(observer);
  }

}
