package screens;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import game_components.*;
import io.GameComponentObserver;
import io.ResourceFinder;
import visual.statik.sampled.Content;

/**
 * GameScreen.
 * 
 * @author joselynetran
 *
 */
public class GameScreen extends MainScreen implements KeyListener
{

  private static final int TOTAL_TREE_HEALTH = 30;

  private GameComponentObserver gco;

  private HashMap<String, Content> bearFrames;
  private HashMap<String, Content> treeFrames;

  private int treeHealth;

  /**
   * 
   * @param width
   * @param height
   * @param rf
   */
  public GameScreen(final int width, final int height, final ResourceFinder rf)
  {
    super(width, height, rf);

    this.treeHealth = 30;
    bearFrames = new BearFrames(this.rf, height).getFrames();
    treeFrames = new TreeFrames(this.rf, height).getFrames();

    gco = new GameComponentObserver();

    this.addKeyListener(this);
    this.addGameComponents();
  }

  @Override
  public void keyTyped(final KeyEvent e)
  {
    // TODO Auto-generated method stub

  }

  @Override
  public void keyPressed(final KeyEvent e)
  {
    int key = e.getKeyCode();

    if (key == 32 && this.treeHealth > 0)
    {
      gco.notifyObservers();
      this.remove(bearFrames.get(BearFrames.SWING));
      this.add(bearFrames.get(BearFrames.CHOP));
      this.treeHealth -= 1;

      if (this.treeHealth == TOTAL_TREE_HEALTH / 2)
      {
        this.remove(treeFrames.get(TreeFrames.FH_TREE));
        this.add(bearFrames.get(BearFrames.CHOP));
        this.add(treeFrames.get(TreeFrames.PH_TREE));
      }

      if (this.treeHealth == 0)
      {
        this.remove(treeFrames.get(TreeFrames.PH_TREE));
        this.add(bearFrames.get(BearFrames.CHOP));
        this.add(treeFrames.get(TreeFrames.CUT_TREE));
      }
    }
  }

  @Override
  public void keyReleased(final KeyEvent e)
  {
    int key = e.getKeyCode();
    if (key == 32)
    {
      this.remove(bearFrames.get(BearFrames.CHOP));
      this.add(bearFrames.get(BearFrames.SWING));
    }
  }

  /**
   * 
   */
  private void addGameComponents()
  {
    this.add(treeFrames.get(TreeFrames.FH_TREE));
    this.add(bearFrames.get(BearFrames.SWING));
  }

  /**
   * For testing key presses.
   * @param e
   * @param keyStatus
   */
  private void printKeyPressed(final KeyEvent e, final String keyStatus)
  {

    // You should only rely on the key char if the event
    // is a key typed event.
    int key = e.getKeyCode();
    System.out.println("number = " + key + " (" + KeyEvent.getKeyText(key) + ")");

  }

}
