package sets;

import java.util.Iterator;
import java.util.NoSuchElementException;

class LinkedNodeIterator<E> implements Iterator<E> {
  // TODO (1) define data variables
  LinkedNode<E> current;
  // Constructors
  public LinkedNodeIterator(LinkedNode<E> head) {
    this.current = head;
  }

  @Override
  public boolean hasNext() {
   return (current != null);
  }

  @Override
  public E next(){
    if (hasNext() == false) {
    throw new NoSuchElementException();
    }
    else {
      E holder = current.getData();
      current = current.getNext();
      return holder; 
    }
  }

  @Override
  public void remove() {
    // Nothing to change for this method
    throw new UnsupportedOperationException();
  }
}
