package io;

import java.util.ArrayList;
import java.util.List;

import game_components.*;
/**
 * Class that holds all the game observers.
 *
 * @author joselynetran and panamuhamad
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
  public void notifyObservers(final double hit)
  {
    for (GameObserver wo : observers)
    {
      wo.handleHit(hit);
    }
    
  }

  @Override
  public void removeObserver(final GameObserver observer)
  {
    observers.remove(observer);
  }

}
