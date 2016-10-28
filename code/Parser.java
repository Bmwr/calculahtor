import java.util.List;
import java.lang.Math;
import java.util.Stack;
import java.util.HashMap;
import java.util.LinkedList;

public class Parser
{
  private static HashMap<String, Double> hash = new HashMap<String, Double>();

  public static List<String> nextStatement(List<String> token)
  {
    List<String> alpha = new LinkedList<String>(token);
    alpha = token.subList(0, token.indexOf(";"));
    System.out.println(alpha);
    return alpha;
  }

  public static void parse(List<String> token)
  {
    while(!token.isEmpty())
    {
      assign(nextStatement(token));
      token = token.subList(token.indexOf(";") + 1, token.size());
      System.out.println(token);
    }
  }

  public static Double evaluate(List<String> alpha)
  {
    Stack<String> operators = new Stack<String>();
    Stack<Double> values    = new Stack<Double>();

    for(String a : alpha)
    {
      if      (a.equals("(") || a.equals(")"))  ;
      else if (a.equals("+"))  operators.push(a);
      else if (a.equals("-"))  operators.push(a);
      else if (a.equals("*"))  operators.push(a);
      else if (a.equals("/"))  operators.push(a);
      else if (a.equals("^"))  operators.push(a);
      else if (values.size() != 0)
      {
        values.push(getNumber(a));
        String ops  = operators.pop();
        double vals = values.pop();
        if      (ops.equals("+"))   vals = values.pop() +           vals;
        else if (ops.equals("-"))   vals = values.pop() -           vals;
        else if (ops.equals("*"))   vals = values.pop() *           vals;
        else if (ops.equals("/"))   vals = values.pop() /           vals;
        else if (ops.equals("^"))   vals = values.pop() * Math.exp(vals);
        values.push(vals);
      }
      else values.push(getNumber(a));
    }
    return values.pop();
  }

  public static Double getNumber(String a)
  {
   if (hash.containsKey(a))
    return hash.get(a);
   return Double.parseDouble(a);
  }

  public static List<String> assign(List<String> alpha)
  {
    if(alpha.get(1).equals("="))
    {  hash.put(alpha.get(0), evaluate(alpha.subList(2, alpha.size()))); }

    if (alpha.get(0).equals("print"))
    { System.out.print("The answer is: " + evaluate(alpha.subList(1, alpha.size()))); }
    return null;
  }
}
