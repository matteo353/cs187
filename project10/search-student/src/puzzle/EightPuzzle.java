package puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import search.SearchProblem;
import search.Solver;

/**
 * A class to represent an instance of the eight-puzzle.
 * The spaces in an 8-puzzle are indexed as follows:
 * 0 | 1 | 2
 * --+---+---
 * 3 | 4 | 5
 * --+---+---
 * 6 | 7 | 8
 * The puzzle contains the eight numbers 1-8, and an empty space.
 * If we represent the empty space as 0, then the puzzle is solved
 * when the values in the puzzle are as follows:
 * 1 | 2 | 3
 * --+---+---
 * 4 | 5 | 6
 * --+---+---
 * 7 | 8 | 0
 * That is, when the space at index 0 contains value 1, the space 
 * at index 1 contains value 2, and so on.
 * From any given state, you can swap the empty space with a space 
 * adjacent to it (that is, above, below, left, or right of it,
 * without wrapping around).
 * For example, if the empty space is at index 2, you may swap
 * it with the value at index 1 or 5, but not any other index.
 * Only half of all possible puzzle states are solvable! See:
 * https://en.wikipedia.org/wiki/15_puzzle
 * for details.
 * 

 * @author liberato
 *
 */
public class EightPuzzle implements SearchProblem<List<Integer>> {
      List<Integer> Values;
  /**
   * Creates a new instance of the 8 puzzle with the given starting values.
   * The values are indexed as described above, and should contain exactly the
   * nine integers from 0 to 8.
   * 
   * @param startingValues
   *            the starting values, 0 -- 8
   * @throws IllegalArgumentException
   *             if startingValues is invalid
   */
  public EightPuzzle(List<Integer> startingValues) {
      if (startingValues.size() != 9) { //has to start w 9 values
        throw new IllegalArgumentException("Needs 9 values");
      }
      for (int i = 0; i < startingValues.size(); i++) {
        if (startingValues.get(i) < 0 || startingValues.get(i) > 8) {
          throw new IllegalArgumentException("Wrong values");
        }
      }
      Values = startingValues;
  }

  @Override
  public List<Integer> getInitialState() {
    // TODO
    return Values;
  }

  @Override
  public List<List<Integer>> getSuccessors(List<Integer> currentState) {
    // TODO
    List<List<Integer>> successors = new ArrayList<List<Integer>>();
    List<Integer> cur = currentState;
    
    int blank = cur.indexOf(0);
    
    if (blank > 2) {
      successors.add(swap(cur, blank, blank - 3));
    }
    
    if (blank < 6) {
      successors.add(swap(cur, blank, blank + 3));
    }
    
    if (blank % 3 != 2) {
      successors.add(swap(cur, blank, blank + 1));
    }
    
    if (blank % 3 != 0) {
      successors.add(swap(cur, blank, blank - 1));
    }
    
    return successors;
  }

  private List<Integer> swap (List<Integer> curr, int index, int index2){
    List<Integer> temp = new ArrayList<Integer>();
    
    for (int i = 0; i < curr.size() ; i ++) {
      temp.add(i, curr.get(i));
    }
    
    Integer temporary = temp.get(index);
    temp.set(index, temp.get(index2));
    temp.set(index2, temporary);
    
    return temp;
    
  }


  @Override
  public boolean isGoal(List<Integer> state) {
    // TODO
    Integer[] goal = {1, 2, 3, 4, 5, 6, 7, 8, 0};
    if (state.equals(Arrays.asList(goal))) {
      return true;
    }
    return false;
  }

  /**
   * supporting man method.
   */
  public static void main(String[] args) {
    EightPuzzle e = new EightPuzzle(Arrays.asList(new Integer[] { 1, 2, 3,
        4, 0, 6, 7, 5, 8 }));

    List<List<Integer>> r = new Solver<List<Integer>>(e).solveWithBFS();
    for (List<Integer> l : r) {
      System.out.println(l);
    }
  }
}
