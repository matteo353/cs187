package evaluator;

import parser.ArithParser;
import stack.LinkedStack;

public class InfixEvaluator extends Evaluator {

  private LinkedStack<String> operators = new LinkedStack<String>();
  private LinkedStack<Integer> operands  = new LinkedStack<Integer>();

  /** return stack object (for testing purpose). */
  public LinkedStack<String> getOperatorStack() { 
    return operators; 
  }
  
  public LinkedStack<Integer> getOperandStack() { 
    return operands;
  }
  
  public void process_operator() throws Exception {
    Integer A;
    Integer B;
    String Operator = operators.pop();
    
    if (!(Operator.equals("!")) && !(Operator.equals("*")) && !(Operator.equals("/")) && !(Operator.equals("+")) && !(Operator.equals("-"))) {
      throw new Exception ("invalid operator");
    }
    
    if (Operator.equals("!")) {
      A = operands.pop();
      if (A == null) {
       throw new Exception ("too few operands");
      }
      else {
        operands.push(A * -1);
      }
    }
  
      
    else {
      A = operands.pop();
      B = operands.pop();
      
      if (A == null || B == null) {
        throw new Exception ("too few operands");
      }
      
      else if (Operator.equals("+")) {
        operands.push(A + B);
      }
      
      else if (Operator.equals("-")) {
        operands.push(B - A);
      }
      
      else if (Operator.equals("*")) {
        operands.push(A * B);
      }

      else if (Operator.equals("/")) {
        if (A == 0) {
          throw new Exception ("division by zero");
        }
        else {
          operands.push(B / A);
        }
      }
      
      else {
        throw new Exception("invalid operator"); 
      }
    }
  }

  

  /** This method performs one step of evaluation of a infix expression.
   *  The input is a token. Follow the infix evaluation algorithm
   *  to implement this method. If the expression is invalid, throw an
   *  exception with the corresponding exception message.
   */
  public void evaluate_step(String token) throws Exception {
    if (isOperand(token)) {
      operands.push(Integer.parseInt(token));
      // TODO: What do we do if the token is an operand?
    } else {
      
      if (token.equals("(")) {
        operators.push(token);
      }
      
      else if ((operators.top() == null) || (precedence(token) > (precedence(operators.top())))) {
        operators.push(token);
      }
      
      else if (token.equals(")")) {  
        while((operators.size() != 0) && (!operators.top().equals("("))) {
          process_operator();
      }
       
        if ((operators.isEmpty()) || (!operators.top().equals("("))) {
         throw new Exception ("missing (");
       }
 
        operators.pop();
      }
        else {
          while ((operators.top() != null) && (precedence(token)) <= precedence(operators.top())) {
            process_operator();
          }
            operators.push(token);
          
        } 
      }
     
    }
  
  /** This method evaluates an infix expression (defined by expr)
   *  and returns the evaluation result. It throws an Exception object
   *  if the infix expression is invalid.
   */
  public Integer evaluate(String expr) throws Exception {

    for (String token : ArithParser.parse(expr)) {
      evaluate_step(token);
    }
    
    while(!operators.isEmpty()) {
      process_operator();       
    }
    /* TODO: what do we do after all tokens have been processed? */

    // The operand stack should have exactly one operand after the evaluation is done
    if (operands.size() > 1) {
      throw new Exception("too many operands");
    } else if (operands.size() < 1) {
      throw new Exception("too few operands");
    }

    return operands.pop();
  }

  public static void main(String[] args) throws Exception {
    System.out.println(new InfixEvaluator().evaluate("5+(5+2*(5+9))/!8"));
  }
}
