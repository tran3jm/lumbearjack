package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Helper util method to read text files into strings
 * to reduce redundancy.
 * 
 * @author joselynetran
 *
 */
public class FileIntoText
{
  /**
   * 
   * @param filename
   * @param rf
   * @return text from file
   */
  public static String fileIntoText(final String filename, final ResourceFinder rf)
  {
    InputStream is = rf.findInputStream(filename);
    BufferedReader in = new BufferedReader(new InputStreamReader(is));

    String line;
    String text = "";
    try
    {
      while ((line = in.readLine()) != null)
      {
        text += line + "\n";
      }
    }
    catch (IOException ioe)
    {
      text = "text";
    }
    return text;
  }
}
