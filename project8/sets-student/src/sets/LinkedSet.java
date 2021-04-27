package sets;

import java.util.Iterator;

/**
 * A LinkedList-based implementation of Set
 */

/********************************************************
 * NOTE: Before you start, check the Set interface in
 * Set.java for detailed description of each method.
 *******************************************************/

/********************************************************
 * NOTE: for this project you must use linked lists
 * implemented by yourself. You are NOT ALLOWED to use
 * Java arrays of any type, or any Collection-based class 
 * such as ArrayList, Vector etc. You will receive a 0
 * if you use any of them.
 *******************************************************/ 

/********************************************************
 * NOTE: you are allowed to add new methods if necessary,
 * but do NOT add new files (as they will be ignored).
 *******************************************************/

public class LinkedSet<E> implements Set<E> {
  private LinkedNode<E> head = null;
  private int size = 0;

  // Constructors
  public LinkedSet() {
  }

  public LinkedSet(E e) {
    this.head = new LinkedNode<E>(e, null);
  }

  private LinkedSet(LinkedNode<E> head) {
    this.head = head;
  }

  @Override
  public int size() {
    // TODO (1)
    size = 0;
    LinkedNode<E> runner = head;
    while (runner != null) {
        size++;
        runner = runner.getNext();
    }
    return size;
  }

  @Override
  public boolean isEmpty() {
    // TODO (2)
    if (size() == 0) {
      return true;
    }
    return false;
  }

  @Override
  public Iterator<E> iterator() {
    return new LinkedNodeIterator<E>(this.head);
  }

  @Override
  public boolean contains(Object o) {
    // TODO (3)
    if (head == null) {
      return false;
    }
    LinkedNodeIterator<E> runner = (LinkedNodeIterator<E>) iterator();
    while (runner.hasNext() != false) {
      if (runner.next().equals(o)) {
         return true;
      }
    }
    return false;
  }

  @Override
  public boolean isSubset(Set<E> that) {
    // TODO (4)
    LinkedNodeIterator<E> runner = (LinkedNodeIterator<E>) iterator();
    while(runner.hasNext() == true) {
      if (!that.contains(head.getData())) {
        return false;
      }
      runner.next();
    }
    return true;
  }

  @Override
  public boolean isSuperset(Set<E> that) {
    // TODO (5)
    if (that.isSubset(this) == true) {
      return true;
    }
    return false;
  }

  @Override
  public Set<E> adjoin(E e) {
    // TODO (6)
    if (this.contains(e)) {
      return this;
    }
    else {
      LinkedNode<E> newNode = new LinkedNode<E>(e, head);
      LinkedSet<E> newSet = new LinkedSet<E>(newNode);
      return newSet;
    }
  }

  @Override
  public Set<E> union(Set<E> that) {
    // TODO (7)
    LinkedNode<E> ptr = head;
    for (E point : that) {
      if (this.contains(point) == false) {
        LinkedNode<E> tempNode = new LinkedNode<E>(point, ptr);
        ptr = tempNode;
      }
    }
   LinkedSet<E> newSet = new LinkedSet<E>(ptr);
   return newSet; 
  }
  
  
  
  
  @Override
  public Set<E> intersect(Set<E> that) {
    // TODO (8)
    
    LinkedNode<E> ptr = head;
    for (E point : this) {
      if (that.contains(point) == true) {
        LinkedNode<E> tempNode = new LinkedNode<E>(point, ptr);
        ptr = tempNode;
      }
    }
   LinkedSet<E> newSet = new LinkedSet<E>(ptr);
   return newSet; 
  }

  @Override
  public Set<E> subtract(Set<E> that) {
    // TODO (9)
    LinkedNode<E> ptr = null;
    LinkedNodeIterator<E> runner = (LinkedNodeIterator<E>) iterator();
    while(runner.hasNext() == true) {
      if (!that.contains(head.getData())) {
        LinkedNode<E> temp = new LinkedNode<E>(head.getData(), ptr);
        ptr = temp;
      }
      head = head.getNext();
      runner.next();
    }
    LinkedSet<E> newSet = new LinkedSet<E>(ptr);
    return newSet;
  }

  @Override
  public Set<E> remove(E e) {
    // TODO (10)
    LinkedNode<E> ptr = null;
    for (E point : this) {
      if (point != e) {
        LinkedNode<E> temp = new LinkedNode<E>(point, ptr);
        ptr = temp;
      }
    }
    LinkedSet<E> newSet = new LinkedSet<E>(ptr);
    return newSet;
  }

  @Override
  @SuppressWarnings("unchecked")
  public boolean equals(Object o) {
    if (! (o instanceof Set)) {
      return false;
    }
    Set<E> that = (Set<E>)o;
    return this.isSubset(that) && that.isSubset(this);
  }

  @Override
  public int hashCode() {
    int result = 0;
    for (E e : this) {
      result += e.hashCode();
    }
    return result;
  }
}
