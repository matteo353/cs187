package structures;

import java.util.NoSuchElementException;

/**************************************************************************************
 * NOTE: before starting to code, check support/structures/UnboundedQueueInterface.java
 * for detailed explanation of each interface method, including the parameters, return
 * values, assumptions, and requirements
 ***************************************************************************************/
public class Queue<T> implements UnboundedQueueInterface<T> {
  private int size;
  private Node<T> head;
  private Node<T> tail;
  
  
  
  class Node<T> {
    public T data;
    public Node<T> next;
    
    public Node(T data) { 
      this.data = data;
    }
    
    public Node(T data, Node<T> next) {
      this.data = data; 
      this.next = next;
    }
  }

  public Queue() {
    // TODO 1
    size = 0;
    tail = null;
    head = null;
  }

  public Queue(Queue<T> other) {
    // TODO 2
    Queue<T> AA = new Queue<T>();
    while (other.size() > 0) {
      enqueue(other.peek());
      AA.enqueue(other.dequeue());
      
    }
    while(AA.size() > 0) {
      other.enqueue(AA.dequeue());
    }
  }

  @Override
  public boolean isEmpty() {
    // TODO 3
    if (size == 0) {
      return true;
    }
    return false;
  }

  @Override
  public int size() {
    // TODO 4
    return size;
  }

  @Override
  public void enqueue(T element) {
    // TODO 5

    Node<T> newNode = new Node<T>(element, null);
    if (head != null) {
      head.next = newNode;
      head = newNode;
    }
    if (head == null) {
      head = newNode;
    }
    if (tail == null) {
      tail = newNode;
    }
    size++;
  }

  @Override
  public T dequeue() throws NoSuchElementException {
    // TODO 6
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
     T tailElement = tail.data;
     tail = tail.next;
     size--;
     return tailElement;
    }

  @Override
  public T peek() throws NoSuchElementException {
    // TODO 7
    
    if (isEmpty() == true) {
      throw new NoSuchElementException();
    }
    else {
      return tail.data;
    }
  }


  @Override
  public UnboundedQueueInterface<T> reversed() {
    // TODO 8
   Queue<T> newQueue = new Queue<T>();
   reverse(newQueue, head);
   return newQueue;
  }
  
  
  public void reverse(Queue<T> newQ, Node<T> head){
    if (head == null) {
      return;
    }
    reverse(newQ, head.next);
    newQ.enqueue(head.data);  
  }
  
}


