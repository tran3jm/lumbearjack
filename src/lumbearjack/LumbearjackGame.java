package lumbearjack;

import java.io.IOException;

import app.JApplication;
import screens.StartingScreen;

public class LumbearjackGame extends JApplication
{
  StartingScreen startScreen;
  
  
  public LumbearjackGame(int width, int height)
  {
    super(width, height);
    this.startScreen = new StartingScreen(width, height);
    // TODO Auto-generated constructor stub
  }

  @Override
  public void init()
  {
    // TODO Auto-generated method stub
    (JPanel)this.getContentPane().add(this.startScreen);
  }
  
    /**
   * Construct and invoke  (in the event dispatch thread) 
   * an instance of this JApplication.
   * 
   * @param args The command line arguments
   */
  public static void main(final String[] args)
  {
    JApplication app = new LumbearJackGame(600, 800);
    invokeInEventDispatchThread(app);
  }
}
