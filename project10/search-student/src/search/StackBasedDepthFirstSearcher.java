package search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * An implementation of a Searcher that performs an iterative search,
 * storing the list of next states in a Stack. This results in a
 * depth-first search.
 * 
 */
public class StackBasedDepthFirstSearcher<T> extends Searcher<T> {

  /**
   * StackBasedDepthFirstSearcher.
   * @param searchProblem : search problem
   */
  public StackBasedDepthFirstSearcher(SearchProblem<T> searchProblem) {
    super(searchProblem);
  }

  @Override
  public List<T> findSolution() {
    // TODO
    List<T> preds = new ArrayList<>();
    
    Stack<T> stack = new Stack<T>();
    T start = searchProblem.getInitialState();
    
    
    stack.push(start);
    preds.add(start);
    
    List<T> path = new ArrayList<>();
    
    while(!stack.isEmpty()) {
      T curr = stack.pop();
      
      for (T next : searchProblem.getSuccessors(curr)) {
        
        if (!preds.contains(next)) {
          preds.add(next);
          stack.push(next);
          
        }
      
    }
      if (searchProblem.isGoal(curr) == true) {
       
        T prev = preds.get(preds.indexOf(curr));
        path.add(curr);
        
        while (prev != null) {
          path.add(prev);
          prev = preds.get(preds.indexOf(prev));
        }
        break;
      }
       
    }
    return path;
  }
}
