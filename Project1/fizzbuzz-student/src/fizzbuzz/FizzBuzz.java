package fizzbuzz;

public class FizzBuzz {
  private final int fizzNumber;
  private final int buzzNumber;
  /**
   * Construct an object that can compute fizzbuzz values for a game of 
   * Fizz and Buzz.
   * 
   * @param fizzNumber an integer between 1 and 9
   * @param buzzNumber an integer between 1 and 9
   */
  public FizzBuzz(int fizzNumber, int buzzNumber) {
    this.fizzNumber = fizzNumber;
    this.buzzNumber = buzzNumber;
  }

  /**
   * Returns the fizzbuzz value for n. The rules are:
   * - if n is divisible by fizzNumber, or contains the digit fizzNumber, return "fizz" 
   * - if n is divisible by buzzNumber, or contains the digit buzzNumber, return "buzz"
   * - however, if both the above conditions are true, you must return "fizzbuzz"
   * - if none of the above conditions is true, return the number itself.
   *
   * <p>For example, getValue(1) returns "1".
   * 
   * @param n a positive integer
   * @return the fizzbuzz value, as a String
   */
  public String getValue(int n) {
    String str = "";
	if ((n == fizzNumber) || (Integer.toString(n).contains(Integer.toString(fizzNumber))) || (n % fizzNumber == 0)){
      str += "fizz";
    }
	if ((n == buzzNumber) || (Integer.toString(n).contains(Integer.toString(buzzNumber))) || (n % buzzNumber == 0)){
      str+= "buzz";
    }
	if (str.contains("fizz") || (str.contains("buzz"))) {
	  return str;
	}
    return Integer.toString(n); // return the number itself as a String
  }

  /**
   * Returns an array of the fizzbuzz values from start to end, inclusive.
   * 
   * <p>For example, if the fizz number is 3 and buzz number is 4,
   * getValues(2,6) should return an array of Strings:
   * 
   * <p>{"2", "fizz", "buzz", "5", "fizz"}
   * 
   * @param start
   *            the number to start from; start > 0
   * @param end
   *            the number to end at; end >= start
   * @return the array of fizzbuzz values
   */
  public String[] getValues(int start, int end) {

    String[] str = new String[(end-start)+1];
    int cnt = 0;
	  for (int i = start; i < end + 1; i++) {
		  str[cnt] = getValue(i);
		  cnt += 1;
	  }
	  return str;
  }
}
