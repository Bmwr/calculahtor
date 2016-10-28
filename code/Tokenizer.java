import java.io.File;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.io.FileNotFoundException;

public class Tokenizer
{

  public static LinkedList<String> token(String filename) throws FileNotFoundException
  {
    //   Determines whether or not a file exists.
    File file = new File(filename);
      if (!file.exists() || !file.canRead())
      {
        System.out.println("File not found. Program terminated.");
        System.exit(0);
      }

    // Reads the file in and prints it out as a String then places it into a list.
    String text             = new Scanner(file).useDelimiter("\\A").next();
    text = text.replaceAll("\n"," ; ").toLowerCase                      ();
    text = text.replaceAll("\\(", " ( "                                  );
    text = text.replaceAll("\\)", " ) "                                  );
    LinkedList<String> list = new LinkedList<String>                    ();
    StringTokenizer st      = new StringTokenizer(text                   );

    while (st.hasMoreTokens())
    {  list.add(st.nextToken());  }

    System.out.println("The List contains: " + list);
    return list;
  }
}
