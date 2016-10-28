import java.io.FileNotFoundException;

public class MathInterpreter
{
  public static void main(String[] args) throws FileNotFoundException
  {
    Parser.parse(Tokenizer.token(args[0]));
  }
}
