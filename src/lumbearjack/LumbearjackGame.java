package lumbearjack;


import javax.swing.JFrame;

import app.JApplication;
import screens.StartingScreen;

public class LumbearjackGame extends JApplication
{
  StartingScreen startScreen;
  private boolean starting;
  
  public LumbearjackGame(int width, int height)
  {
    super(width, height);
    this.starting = true;
    this.startScreen = new StartingScreen(width, height);
    // TODO Auto-generated constructor stub
  }

  @Override
  public void init()
  {
    // TODO Auto-generated method stub
    JFrame screens = new JFrame();
    if (this.starting)
    {
      screens.add(this.startScreen);
    }
  }
  
    /**
   * Construct and invoke  (in the event dispatch thread) 
   * an instance of this JApplication.
   * 
   * @param args The command line arguments
   */
  public static void main(final String[] args)
  {
    JApplication app = new LumbearjackGame(800, 600);
    invokeInEventDispatchThread(app);
  }
}
