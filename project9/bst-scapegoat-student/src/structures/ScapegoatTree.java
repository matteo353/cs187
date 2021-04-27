package structures;

import java.util.Iterator;

public class ScapegoatTree<T extends Comparable<T>> extends BinarySearchTree<T> {
  private int upperBound;


  @Override
  public void add(T t) {
    // TODO
    BSTNode<T> node = new BSTNode<T>(t, null, null);
    root = addToSubtree(root, node);
    upperBound ++;
    if (height() > log32(upperBound)) {
        node = node.getParent();
    
      while (3 * subtreeSize(node) <= 2 * subtreeSize(node.getParent())) {
        node = node.getParent();
      }
      node = node.getParent();
      BSTNode<T> parent = node.getParent();
      BinarySearchTree<T> tempTree = new BinarySearchTree<T>();
      Iterator<T> iterate = inorderIterator(node);
      while (iterate.hasNext()) {
        tempTree.add(iterate.next());
      }
      tempTree.balance();
      BSTNode<T> temproot = tempTree.getRoot();
      if(parent.getRight() == node) {
        parent.setRight(temproot);
      }
      else {
        parent.setLeft(temproot);
      }
    }
  }

  @Override
  public boolean remove(T element) {
    // TODO
    if (super.remove(element)) {
      if (2 * size() < upperBound) {
        balance();
        upperBound = size();
      }
      return true;
    }
    return false;
  }
  
  private double log32(int i) {
    return (Math.log(i) / Math.log(3.0 / 2.0));
  }
  
}
