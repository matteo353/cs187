package stack;


/**
 * A {@link LinkedStack} is a generic stack that is implemented using
 * a Linked List structure to allow for unbounded size.
 */
public class LinkedStack<T> {
    private LLNode<T> head;
    private int size = 0;
  // TODO: define class variables here

  /**
   * Remove and return the top element on this stack.
   * If stack is empty, return null (instead of throw exception)
   */
  public T pop() {
    
    if (size == 0) {
      return null;
    }
    else { 
    size --;
    LLNode<T> temp = head; 
    head = head.link;
    return temp.info;
    }
  }

  /**
   * Return the top element of this stack (do not remove the top element).
   * If stack is empty, return null (instead of throw exception)
   */
  public T top() {
    // TODO
    if (size == 0) { 
      return null;
    }
    return head.info;
  }

  /**
   * Return true if the stack is empty and false otherwise.
   */
  public boolean isEmpty() {   
    if (size == 0) {
      return true;
    }
    return false;
  }

  /**
   * Return the number of elements in this stack.
   */
  public int size() {
    return size;
  }

  /**
   * Pushes a new element to the top of this stack.
   */
  public void push(T elem) { //head insertion
    LLNode <T> newNode = new LLNode<T>(elem);
    newNode.link = head;
    head = newNode;
    size ++;
  }

}
