package guessme;

/**
 * A LinkedList-based implementation of the Guess-A-Number game.
 */
public class LinkedListGame {
private int guess;
private boolean gameOver;
private int numGuesses;
LLIntegerNode headCandidates;
LLIntegerNode headPriorGuesses;
LLIntegerNode tailPriorGuesses;

  // TODO: declare data members as necessary


   /********************************************************
   * NOTE: for this project you must use linked lists
   * implemented by yourself. You are NOT ALLOWED to use
   * Java arrays of any type, or any class in the java.util
   * package (such as ArrayList).
   *******************************************************/

  /********************************************************
   * NOTE: you are allowed to add new methods if necessary,
   * but DO NOT remove any provided method, and do NOT add
   * new files (as they will be ignored by the autograder).
   *******************************************************/

  // LinkedListGame constructor method
  public LinkedListGame() {   
    reset();
  }

  /** Resets data members and game state so we can play again.
   * 
   */
  public void reset() {
    this.gameOver = false;
    this.numGuesses = 0;
    this.guess = 1000;
    this.headPriorGuesses = null;
    this.tailPriorGuesses = null;
    for (int i = 9999; i > 999; i--) {
      LLIntegerNode temp = new LLIntegerNode(i, headCandidates);
      headCandidates = temp;
    }
    // TODO
  }

  /** Returns true if n is a prior guess; false otherwise.
   * 
   */
  public boolean isPriorGuess(int n) {
    LLIntegerNode temp = headPriorGuesses;
    while(temp != null) {
      if (temp.getInfo() == n) {
        return true;
      }
      temp = temp.getLink();
    }
    return false;
  }

  /** Returns the number of guesses so far.
   * 
   */
  public int numGuesses() {
    // TODO done
    return numGuesses;
  }

  /**
   * Returns the number of matches between integers a and b.
   * You can assume that both are 4-digits long (i.e. between 1000 and 9999).
   * The return value must be between 0 and 4.
   * 
   * <p>A match is the same digit at the same location. For example:
   *   1234 and 4321 have 0 match;
   *   1234 and 1114 have 2 matches (1 and 4);
   *   1000 and 9000 have 3 matches (three 0's).
   */
  public static int numMatches(int a, int b) {
    int matches = 0;
    while(a>0)
    {
      if(a%10==b%10)
        matches++;
      a=a/10;
      b=b/10;
    }
      return matches;
    }
  /**
   * Returns true if the game is over; false otherwise.
   * The game is over if the number has been correctly guessed
   * or if no candidate is left.
   */
  public boolean isOver() {
   return gameOver;
  }

  /**
   * Returns the guess number and adds it to the list of prior guesses.
   * The insertion should occur at the end of the prior guesses list,
   * so that the order of the nodes follow the order of prior guesses.
   */
  public int getGuess() {
    LLIntegerNode temp2 = new LLIntegerNode(guess, null);
    if (tailPriorGuesses == null) {
        
        headPriorGuesses = temp2;
        tailPriorGuesses = temp2;
        
    }
    
    else { 
     
      tailPriorGuesses.setLink(temp2);
      tailPriorGuesses = temp2;
      
    }
    
    numGuesses++;
    return guess;
  }

  /**
   * Updates guess based on the number of matches of the previous guess.
   * If nmatches is 4, the previous guess is correct and the game is over.
   * Check project description for implementation details.
   * 
   * <p>Returns true if the update has no error; false if no candidate 
   * is left (indicating a state of error);
   */
  public boolean updateGuess(int nmatches) {
    // TODO

    if (nmatches == 4) {
      gameOver = true;
      return true;
    }
    
    LLIntegerNode headList = null;
    LLIntegerNode tailList = null;
    LLIntegerNode tempList = headCandidates;
    

    while (tempList != null) {
       // AA is not being incremented
      if (nmatches == (numMatches(guess, tempList.getInfo()))) {
        
        LLIntegerNode AA = new LLIntegerNode(tempList.getInfo(), null);
        
        if (headList == null) {
          
          headList = AA;          
          tailList = AA;
          
         } 
       
        else {
          tailList.setLink(AA);
          tailList = AA;            
        }   
        
      }
      
      tempList = tempList.getLink();              
    }
    
    headCandidates = headList;
    
    if (headCandidates != null) {
      guess = headCandidates.getInfo();
    }
    
    else {
      gameOver = true;
      return false;
    }
    return true;

  }

  /**
   *  Returns the head of the prior guesses list.
   *  Returns null if there hasn't been any prior guess
   */
  public LLIntegerNode priorGuesses() {
    
    return headPriorGuesses;
  }

  /**
   * Returns the list of prior guesses as a String. For example,
   * if the prior guesses are 1000, 2111, 3222, in that order,
   * the returned string should be "1000, 2111, 3222", in the same order,
   * with every two numbers separated by a comma and space, except the
   * last number (which should not be followed by either comma or space).
   *
   * <p>Returns an empty string if here hasn't been any prior guess
   */
  public String priorGuessesString() {
    String str = "";
    LLIntegerNode temp = headPriorGuesses;
    if(headPriorGuesses == null) {
      return str;
    }
    while (temp.getLink() != null) {
      str = str + Integer.toString(temp.getInfo()) + ", ";
      temp = temp.getLink();
    }
      str = str + temp.getInfo();
      return str;
  }

}
