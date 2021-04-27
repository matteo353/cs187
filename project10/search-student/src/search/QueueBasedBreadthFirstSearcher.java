package search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * An implementation of a Searcher that performs an iterative search,
 * storing the list of next states in a Queue. This results in a
 * breadth-first search.
 * 
 */
public class QueueBasedBreadthFirstSearcher<T> extends Searcher<T> {

  /**
   * QueueBasedBreadthFirstSearcher.
   * @param searchProblem : search problem
   */
  public QueueBasedBreadthFirstSearcher(SearchProblem<T> searchProblem) {
    super(searchProblem);
  }

  @Override
  public List<T> findSolution() {
    // TODO
    List<T> preds = new ArrayList<>();
    
    Queue<T> queue = new LinkedList<>();
    T start = searchProblem.getInitialState();
    
    preds.add(start);
    queue.add(start);
    
    List<T> solution = new ArrayList<>();
   
    
    while (!queue.isEmpty()) {
      T curr = queue.remove();
      for (T next : searchProblem.getSuccessors(curr)) {
        if (!preds.contains(next)) {
          queue.add(next);
          preds.add(next);
        }
      }
      
      
      if (searchProblem.isGoal(curr) == true) {
        
        T prev = preds.get(preds.indexOf(curr));
        solution.add(curr);
        
        while (prev != null) {
          solution.add(prev);
          prev = preds.get(preds.indexOf(prev));
          
        }
        break;
      }
      
 
    }
    return solution;
  }
}
